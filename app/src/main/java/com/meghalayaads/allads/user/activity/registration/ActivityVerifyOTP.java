package com.meghalayaads.allads.user.activity.registration;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.databinding.ActivityVerifyOtpBinding;
import com.meghalayaads.allads.user.events.OnVerifyOTPEvent;
import com.meghalayaads.allads.user.viewmodel.registation.ActivityVerifyOTPViewModel;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ActivityVerifyOTP extends AppCompatActivity implements OnVerifyOTPEvent {


    private boolean isFromSignUp;
    private String mobileNumber;
    private CountDownTimer countDownTimer;
    private FirebaseAuth mAuth;
    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private ActivityVerifyOtpBinding mBinding;
    private ActivityVerifyOTPViewModel model;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private ArrayList<Error> errors = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        allocation();
        setEvent();

    }

    private void allocation() {
        Intent intent = getIntent();
        if (null != intent) {
            isFromSignUp = intent.getBooleanExtra(getString(R.string.is_from_sign_up), false);
            mobileNumber = intent.getStringExtra(getString(R.string.key_moible_no));
        }

        if (mobileNumber == null) {
            errors.add(new Error(VERIFY_OTP_ERROR_CODE.OTP001.toString(),
                    getString(R.string.validate_mobile_number), "REG"));
            backToReq(errors);
        }

        mAuth = FirebaseAuth.getInstance();
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_verify_otp);
        model = ViewModelProviders.of(this).get(ActivityVerifyOTPViewModel.class);
        mBinding.setModel(model);
        mBinding.setMobileNumber(mobileNumber);
        model.setOtpEvent(this);

    }


    private void setEvent() {

        startProgressbar();
        countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {
                //model.getCount().setValue(String.valueOf();
                mBinding.txtResendCountDown.setText(String.valueOf(millisUntilFinished / 1000) + ":00");
                //here you can have your logic to set text to edit text
            }

            public void onFinish() {
                mBinding.txtResendCountDown.setText("");
                //model.getCount().setValue("");
                mBinding.txtResend.setEnabled(true);
            }

        };


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                stopProgressbar();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                   /*mBinding.bottomSheetOTP.txtOtp.setErrorEnabled(true);
                    mBinding.bottomSheetOTP.txtOtp.setError(getString(R.string.not_valid_moible_number));*/
                    countDownTimer.onFinish();
                    errors.add(new Error(VERIFY_OTP_ERROR_CODE.OTP001.toString(),
                            getString(R.string.validate_mobile_number), "REG"));
                    backToReq(errors);

                } else //if (e instanceof FirebaseTooManyRequestsException)
                {
                    // The SMS quota for the project has been exceeded
                    // mBinding.txtError.setText(getString(R.string.error_quota_exceeded));
                    mBinding.txtOtp.setErrorEnabled(true);
                    mBinding.txtOtp.setError(e.getMessage());


                    errors.add(new Error(VERIFY_OTP_ERROR_CODE.OTP002.toString(),
                            e.getMessage(), "REG"));
                    backToReq(errors);
                }
            }

            @Override
            public void onCodeSent(@NonNull String id, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(id, forceResendingToken);
                countDownTimer.start();
                mVerificationId = id;
                mResendToken = forceResendingToken;
                stopProgressbar();
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                countDownTimer.onFinish();
                mBinding.txtResend.setEnabled(true);
            }
        };


        mBinding.txtResend.setEnabled(false);
        PhoneAuthProvider.getInstance()
                .verifyPhoneNumber("+91" + mobileNumber.trim().replace(" ", ""),
                        60,
                        TimeUnit.SECONDS,
                        this,
                        mCallbacks);


        //resend OTP
        mBinding.txtResend.setOnClickListener(view -> {
            mBinding.txtResend.setEnabled(false);
            startProgressbar();
            PhoneAuthProvider.getInstance()
                    .verifyPhoneNumber("+91" + mobileNumber.trim().replace(" ", ""),
                            60,
                            TimeUnit.SECONDS,
                            this,
                            mCallbacks,
                            mResendToken);
        });

        mBinding.btnConfirm.setOnClickListener(v -> {
            String otp = mBinding.txtOtp.getEditText().getText().toString().trim();

            if (otp != null &&
                    !TextUtils.isEmpty(otp)
                    && TextUtils.isDigitsOnly(otp)) {
                startProgressbar();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, otp);
                signInWithPhoneAuthCredential(credential);
            } else {
                mBinding.txtOtp.setErrorEnabled(true);
                mBinding.txtOtp.setError(getString(R.string.opt_wrong));
            }
        });
    }


    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {

                    stopProgressbar();

                    if (task.isSuccessful()) {
                        //backToReq(null, false);
                        model.setIsMobileVerify(mobileNumber);
                    } else {
                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                            mBinding.txtOtp.setErrorEnabled(true);
                            mBinding.txtOtp.setError(getString(R.string.opt_wrong));
                            //mBinding.txtOtp.setError(task.getException().getMessage());
                        }
                    }
                });

    }


    private void backToReq(ArrayList<Error> errors) {
        Intent intent = new Intent();

        if (null != errors && !errors.isEmpty()) {
            //fail to verify otp
            intent.putExtra(getString(R.string.key_errors), errors);
        } else {
            //otp verify successfully
            intent.putExtra(getString(R.string.key_moible_no), mobileNumber);
        }
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onMobileVerifySuccessDB() {
        backToReq(null);
    }

    @Override
    public void onMobileVerifyFailDB() {
        backToReq(null);
    }


    public enum VERIFY_OTP_ERROR_CODE {
        OTP001, //mobile number not validation;
        OTP002   //server error
    }

    public void startProgressbar() {
        mBinding.otpRoot.setVisibility(View.INVISIBLE);
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }

    public void stopProgressbar() {
        mBinding.otpRoot.setVisibility(View.VISIBLE);
        mBinding.progressBar.setVisibility(View.GONE);

    }

}