package com.meghalayaads.allads.user.activity.registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.model.UserMst;
import com.meghalayaads.allads.common.util.AdsConstant;
import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.databinding.ActivitySignUpBinding;
import com.meghalayaads.allads.user.events.OnSignUpEvent;
import com.meghalayaads.allads.user.response.registration.UserRegResponse;
import com.meghalayaads.allads.user.viewmodel.registation.ActivitySignUpViewModel;

import java.util.ArrayList;

public class ActivitySignUp extends AppCompatActivity implements OnSignUpEvent {


    private static final int REQ_VERIFY_OTP = 12;
    private ActivitySignUpBinding mBinding;
    private ActivitySignUpViewModel model;
    private FirebaseAuth mAuth;


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

        mBinding.txtAlreadyAccount.setOnClickListener(v->{backToLogin(null);});

        mBinding.btnSignUp.setOnClickListener(view -> {
            if (model.validateUserData()) {
                startProgressbar();
                model.registerUser();
            }
        });

    }

    private void backToLogin(UserMst userMst) {
        if(userMst!=null) {
            Intent data = new Intent();
            data.putExtra(getString(R.string.key_user_login_data), userMst);
            setResult(RESULT_OK, data);
        }
        finish();
    }

    @Override
    public void setErrorUI(ArrayList<Error> errors) {
        stopProgressbar();
        if (null != errors && errors.size() > 0) {
            String msg = "";
           removeErrorUI();
            for (Error error : errors) {
                switch (error.toIntValue()) {
                    case 1:
                        mBinding.txtUserId.setError(error.getMessage());
                        break;

                    case 2:
                        mBinding.txtUserName.setError(error.getMessage());
                        break;

                    case 3:
                        mBinding.txtPassword.setError(error.getMessage());
                        break;

                    case 4:
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
        //startProgressbar();
        stopProgressbar();
        if (response.isStatus() && response.getUser() != null) {
           // backToLogin(response.getUser());
            Intent intent=new Intent(this,ActivityVerifyOTP.class);
            intent.putExtra(getString(R.string.key_moible_no),response.getUser().getMobileNum());
            intent.putExtra(getString(R.string.is_from_sign_up),true);
            startActivityForResult(intent,REQ_VERIFY_OTP);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        stopProgressbar();
        removeErrorUI();
        if(requestCode==REQ_VERIFY_OTP && resultCode==RESULT_OK){
            if(null!=data &&
            data.getStringExtra(getString(R.string.key_moible_no))!=null){
                UserMst mst=new UserMst();
                mst.setMobileNum(data.getStringExtra(getString(R.string.key_moible_no)));
                backToLogin(mst);
            }else if(null!=data &&
                    data.getParcelableArrayListExtra(getString(R.string.key_errors))!=null &&
                    !data.getParcelableArrayListExtra(getString(R.string.key_errors)).isEmpty()){
                setOTPErrorUI(data.getParcelableArrayListExtra(getString(R.string.key_errors)));
                mBinding.txtUserId.requestFocus();
            }

        }
    }

    private void setOTPErrorUI(ArrayList<Error> errors) {

        stopProgressbar();
        if(!errors.isEmpty()){
            removeErrorUI();
            StringBuffer buffer=new StringBuffer();
            for (Error error:errors){
                switch (error.toIntValue()){
                    case 1:
                        mBinding.txtUserId.setError(error.getMessage());
                        break;

                    case 2:
                    default:
                        buffer.append(error.getMessage()).append("\n");
                        break;
                }

            }
            if (!TextUtils.isEmpty(buffer)) {
                mBinding.txtError.setText(buffer);
                mBinding.txtError.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onRegistrationFail(UserRegResponse response) {

        stopProgressbar();
        removeErrorUI();
        if (response.getErrors() != null || !response.getErrors().isEmpty()) {
            StringBuffer buffer = new StringBuffer();
            for (Error e : response.getErrors()) {
                switch (e.toIntValue()) {
                    case 1:
                        //REG001, mobile already exist
                        mBinding.txtUserId.setError(getString(R.string.mobie_no_already_reg));
                        break;
                    case 2:
                        mBinding.txtUserId.setError(getString(R.string.enter_mobile_number));
                        break;
                    case 3:
                        mBinding.txtUserName.setError(getString(R.string.enter_user_name));
                        break;
                    case 4:
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


        mBinding.txtUserId.setError(null);
        mBinding.txtUserName.setError(null);
        mBinding.txtPassword.setError(null);
        mBinding.txtConfPassword.setError(null);

        mBinding.txtUserId.setErrorEnabled(false);
        mBinding.txtUserName.setErrorEnabled(false);
        mBinding.txtPassword.setErrorEnabled(false);
        mBinding.txtConfPassword.setErrorEnabled(false);

        mBinding.txtError.setText("");
        mBinding.txtError.setVisibility(View.GONE);
    }

    public void startProgressbar() {
        mBinding.rootCons.setVisibility(View.INVISIBLE);
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }

    public void stopProgressbar() {
        mBinding.rootCons.setVisibility(View.VISIBLE);;
        mBinding.progressBar.setVisibility(View.GONE);
    }

}