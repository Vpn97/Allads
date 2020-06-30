package com.meghalayaads.allads.user.activity.registration;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.model.UserMst;
import com.meghalayaads.allads.common.util.AdsConstant;
import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.common.util.ViewUtil;
import com.meghalayaads.allads.databinding.ActivitySignUpBinding;
import com.meghalayaads.allads.user.events.OnSignUpEvent;
import com.meghalayaads.allads.user.response.registration.UserRegResponse;
import com.meghalayaads.allads.user.viewmodel.registation.ActivitySignUpViewModel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ActivitySignUp extends AppCompatActivity implements OnSignUpEvent {


    private ActivitySignUpBinding mBinding;
    private ActivitySignUpViewModel model;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private CountDownTimer countDownTimer;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private BottomSheetBehavior sheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        allocation();
        setEvent();
    }

    private void allocation() {
        mAuth = FirebaseAuth.getInstance();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mBinding.setLifecycleOwner(this);
        model = ViewModelProviders.of(this).get(ActivitySignUpViewModel.class);
        model.setEvent(this);
        mBinding.setModel(model);

        sheetBehavior = BottomSheetBehavior.from(mBinding.bottomSheetOTP.bottomSheetRoot);
        sheetBehavior.setPeekHeight(0);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.e(AdsConstant.TAG, "getInstanceId failed", task.getException());
                        return;
                    }
                    // Get new Instance ID token
                    model.getSignUpLiveData().getValue().setDeviceId(task.getResult().getToken());
                });

    }

    private void setEvent() {

        mBinding.btnSignUp.setOnClickListener(view -> {
            if (model.validateUserData()) {
                removeErrorUI();
                ViewUtil.setVisibility(mBinding.rootCons, View.INVISIBLE);
                mBinding.progressBar.setVisibility(View.VISIBLE);
                mBinding.bottomSheetOTP.txtResend.setEnabled(false);
                PhoneAuthProvider.getInstance()
                        .verifyPhoneNumber("+91" + model.getSignUpLiveData().getValue().getMobileNumber().trim().replace(" ", ""),
                                60,
                                TimeUnit.SECONDS,
                                this,
                                mCallbacks);
            }
        });


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                ViewUtil.setVisibility(mBinding.rootCons, View.VISIBLE);
                mBinding.progressBar.setVisibility(View.GONE);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                   /*mBinding.bottomSheetOTP.txtOtp.setErrorEnabled(true);
                    mBinding.bottomSheetOTP.txtOtp.setError(getString(R.string.not_valid_moible_number));*/
                    ArrayList<Error> errors = new ArrayList<>();

                    errors.add(new Error(ActivitySignUp.SING_UP_ERROR_CODE.ERR001.toString(),
                            getString(R.string.not_valid_moible_number), "REG"));
                    setErrorUI(errors);
                    mBinding.bottomSheetOTP.txtResendCountDown.setText("");
                    countDownTimer.onFinish();


                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    mBinding.txtError.setError(getString(R.string.error_quota_exceeded));
                    mBinding.txtError.setVisibility(View.VISIBLE);
                } else {
                    mBinding.txtError.setError(e.getMessage());
                }
            }


            @Override
            public void onCodeSent(@NonNull String id, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(id, forceResendingToken);
                removeErrorUI();
                countDownTimer.start();
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                mBinding.bottomSheetOTP.txtResendCountDown.setText("");

                ViewUtil.setVisibility(mBinding.rootCons, View.VISIBLE);
                mBinding.progressBar.setVisibility(View.GONE);
                mBinding.bottomSheetOTP.txtResend.setEnabled(false);
                mVerificationId = id;
                mResendToken = forceResendingToken;
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);

                //model.getCount().setValue("");
                mBinding.bottomSheetOTP.txtResend.setEnabled(true);

                mBinding.bottomSheetOTP.txtOtp.setError("");
                mBinding.bottomSheetOTP.txtOtp.setErrorEnabled(false);
            }

        };

        mBinding.bottomSheetOTP.txtResend.setEnabled(false);
        countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                //model.getCount().setValue(String.valueOf();
                mBinding.bottomSheetOTP.txtResendCountDown.setText(String.valueOf(millisUntilFinished / 1000) + ":00");
                //here you can have your logic to set text to edit text
            }

            public void onFinish() {
                mBinding.bottomSheetOTP.txtResendCountDown.setText("");
                //model.getCount().setValue("");
                mBinding.bottomSheetOTP.txtResend.setEnabled(true);
            }

        };

        mBinding.bottomSheetOTP.btnConfirm.setOnClickListener(v -> {
                    mBinding.bottomSheetOTP.txtOtp.setError("");
                    mBinding.bottomSheetOTP.txtOtp.setErrorEnabled(false);
                    if(mBinding.bottomSheetOTP.txtOtp.getEditText().getText() != null &&
                            !TextUtils.isEmpty(mBinding.bottomSheetOTP.txtOtp.getEditText().getText())
                            && TextUtils.isDigitsOnly(mBinding.bottomSheetOTP.txtOtp.getEditText().getText())) {

                        signInWithPhoneAuthCredential(PhoneAuthProvider.getCredential(
                                mVerificationId,
                                mBinding.bottomSheetOTP.txtOtp.toString().trim()));
                    } else {
                        mBinding.bottomSheetOTP.txtOtp.setErrorEnabled(true);
                        mBinding.bottomSheetOTP.txtOtp.setError(getString(R.string.opt_wrong));
                    }
                }


        );


        mBinding.bottomSheetOTP.txtResend.setOnClickListener(view -> {
            mBinding.bottomSheetOTP.txtOtp.setError("");
            mBinding.bottomSheetOTP.txtOtp.setErrorEnabled(false);

            PhoneAuthProvider.getInstance()
                    .verifyPhoneNumber("+91" + model.getSignUpLiveData().getValue().getMobileNumber().trim().replace(" ", ""),
                            60,
                            TimeUnit.SECONDS,
                            this,
                            mCallbacks,
                            mResendToken);
        });


        mBinding.bottomSheetOTP.txt.setOnClickListener(v -> {
            if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED ||
                    sheetBehavior.getState() == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
            mBinding.txtUserId.setFocusable(true);
            mBinding.txtUserId.requestFocus();

        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        if (sheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED ||
                                sheetBehavior.getState() == BottomSheetBehavior.STATE_HALF_EXPANDED) {
                            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                        ViewUtil.setVisibility(mBinding.rootCons, View.GONE);
                        mBinding.progressBar.setVisibility(View.VISIBLE);
                        model.registerUser();
                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            mBinding.bottomSheetOTP.txtOtp.setErrorEnabled(true);
                            mBinding.bottomSheetOTP.txtOtp.setError(getString(R.string.opt_wrong));
                            ViewUtil.setVisibility(mBinding.rootCons, View.VISIBLE);
                            mBinding.progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

    private void backToLogin(UserMst userMst) {
        Intent data = new Intent();
        data.putExtra(getString(R.string.key_user_login_data), userMst);
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public void setErrorUI(ArrayList<Error> errors) {
        if (null != errors && errors.size() > 0) {
            removeErrorUI();
            String msg = "";
            for (Error error : errors) {
                switch (error.toIntValue()) {
                    case 1:
                        mBinding.txtUserId.setErrorEnabled(true);
                        mBinding.txtUserId.setError(error.getMessage());
                        break;

                    case 2:
                        mBinding.txtUserName.setErrorEnabled(true);
                        mBinding.txtUserName.setError(error.getMessage());
                        break;

                    case 3:
                        mBinding.txtPassword.setErrorEnabled(true);
                        mBinding.txtPassword.setError(error.getMessage());
                        break;

                    case 4:
                        mBinding.txtConfPassword.setErrorEnabled(true);
                        mBinding.txtConfPassword.setError(error.getMessage());
                        break;

                    default:


                }

            }
        } else {
            removeErrorUI();
        }
    }

    @Override
    public void onRegistrationSuccess(UserRegResponse response) {
        removeErrorUI();
        if (response.isStatus() && response.getUser() != null) {
            backToLogin(response.getUser());
        }
    }

    @Override
    public void onRegistrationFail(UserRegResponse response) {
        if (response.getErrors() != null || !response.getErrors().isEmpty()) {
            removeErrorUI();
            StringBuffer buffer = new StringBuffer();
            for (Error e : response.getErrors()) {

                switch (e.toIntValue()) {
                    case 1:
                        //REG001, mobile already exist
                        mBinding.txtUserId.setErrorEnabled(true);
                        mBinding.txtUserId.setError(getString(R.string.mobie_no_already_reg));
                        break;
                    case 2:
                        mBinding.txtUserId.setErrorEnabled(true);
                        mBinding.txtUserId.setError(getString(R.string.enter_mobile_number));
                        break;
                    case 3:
                        mBinding.txtUserName.setErrorEnabled(true);
                        mBinding.txtUserName.setError(getString(R.string.enter_user_name));
                        break;
                    case 4:
                        mBinding.txtPassword.setErrorEnabled(true);
                        mBinding.txtPassword.setError(getString(R.string.password_can_be));
                        break;
                    case 5:// response fail
                    default:
                        buffer.append(e.getMessage()).append("\n");
                        break;
                }
            }

            if (!TextUtils.isEmpty(buffer)) {
                mBinding.txtError.setText(buffer);
                mBinding.txtError.setVisibility(View.VISIBLE);
            }

        }
    }

    public enum SING_UP_ERROR_CODE {
        ERR001, //mobile number validation;
        ERR002,//user name validate
        ERR003,// password length
        ERR004,// password dose'not match

        // registration error
        REG001, //mobile already exist
        REG002, //mobile no cannot be empty
        REG003, //user name cannot be empty
        REG004,//password cannot be empty
        REG005 // response fail
    }

    public void removeErrorUI() {
        mBinding.txtUserId.setErrorEnabled(false);
        mBinding.txtUserName.setErrorEnabled(false);
        mBinding.txtPassword.setErrorEnabled(false);
        mBinding.txtConfPassword.setErrorEnabled(false);

        mBinding.txtUserId.setError("");
        mBinding.txtUserName.setError("");
        mBinding.txtPassword.setError("");
        mBinding.txtConfPassword.setError("");

        mBinding.txtError.setText("");
        mBinding.txtError.setVisibility(View.GONE);
    }

}