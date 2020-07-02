package com.meghalayaads.allads.user.activity.registration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.model.UserMst;
import com.meghalayaads.allads.common.util.AdsConstant;
import com.meghalayaads.allads.common.util.CommonUtil;
import com.meghalayaads.allads.common.util.DataStorage;
import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.common.util.ViewUtil;
import com.meghalayaads.allads.databinding.ActivityLoginBinding;
import com.meghalayaads.allads.databinding.DialoadForgetPasswordBinding;
import com.meghalayaads.allads.user.activity.Dashboard;
import com.meghalayaads.allads.user.events.OnLoginEvent;
import com.meghalayaads.allads.user.model.SignUpModel;
import com.meghalayaads.allads.user.response.registration.UserRegResponse;
import com.meghalayaads.allads.user.viewmodel.registation.ActivityLoginViewModel;

import java.util.ArrayList;

public class ActivityLogin extends AppCompatActivity  implements OnLoginEvent {


    private static final int REQ_SIGN_UP = 11;
    private static final int REQ_VERIFY_OTP = 12;
    private static final int REQ_VERIFY_OTP_FORGET_PASSWORD = 15;
    private static final int REQ_UPDATE_PASSWORD = 16;

    private ActivityLoginBinding mBinding;
    private ActivityLoginViewModel model;
    private UserMst userMst;
    private DataStorage storage;
    private DialoadForgetPasswordBinding forgetPasswordBinding;
    private BottomSheetDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        storage=new DataStorage(this,getString(R.string.key_user_data));
        allocation();
        setEvent();
    }

    private void allocation() {
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        model= ViewModelProviders.of(this).get(ActivityLoginViewModel.class);
        mBinding.setModel(model);
        model.setEvent(this);

        //get login
        userMst=new Gson().fromJson((String.valueOf(storage.read(getString(R.string.key_user_mst),DataStorage.STRING))),UserMst.class);
        loginNow(userMst);

        //get device id
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.e(AdsConstant.TAG, "getInstanceId failed", task.getException());
                        return;
                    }
                    // Get new Instance ID token
                    model.getLoginModelData().getValue().setDeviceId(task.getResult().getToken());
                });
    }

    private void setEvent() {
        mBinding.btnSignUp.setOnClickListener(v->{
            startActivityForResult(new Intent(this, ActivitySignUp.class),REQ_SIGN_UP);
        });

        mBinding.btnSignIn.setOnClickListener(v -> {
            model.loginRequest();
        });

        mBinding.txtForgetPasword.setOnClickListener(v->{
            showForgotPasswordWindow();
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        stopProgressbar();
        if(resultCode==RESULT_OK){

            if(requestCode==REQ_SIGN_UP && null!=data){
                UserMst user=data.getParcelableExtra(getString(R.string.key_user_login_data));
                if(null!=user){
                    Toast.makeText(this, user.getMobileNum() + " : "+user.getUserName(), Toast.LENGTH_SHORT).show();
                    mBinding.txtUserId.getEditText().setText(user.getMobileNum());
                    mBinding.txtPassword.setFocusable(true);
                    mBinding.txtPassword.requestFocus(View.FOCUS_UP);
                }
            }

            if(requestCode==REQ_VERIFY_OTP){
                if(null!=data &&
                        data.getStringExtra(getString(R.string.key_mobile_no))!=null){

                    userMst.setMobVerify(true);
                    //userMst.setMobileNum(data.getStringExtra(getString(R.string.key_moible_no)));
                    loginNow(userMst);
                }else if(null!=data &&
                        data.getParcelableArrayListExtra(getString(R.string.key_errors))!=null &&
                        !data.getParcelableArrayListExtra(getString(R.string.key_errors)).isEmpty()){
                    setErrorUI(data.getParcelableArrayListExtra(getString(R.string.key_errors)));
                    mBinding.txtUserId.requestFocus();
                }
            }

            if(requestCode==REQ_VERIFY_OTP_FORGET_PASSWORD){
                if(null!=data &&
                        data.getStringExtra(getString(R.string.key_mobile_no))!=null){
                        SignUpModel signUpModel=new SignUpModel();
                        signUpModel.setMobileNumber(data.getStringExtra(getString(R.string.key_mobile_no)));
                        //reset password
                        Intent intent=new Intent(this,ActivityUpdatePassword.class);
                        intent.putExtra(getString(R.string.key_sign_up_model),signUpModel);
                        startActivityForResult(intent,REQ_UPDATE_PASSWORD);
                }else if(null!=data &&
                        data.getParcelableArrayListExtra(getString(R.string.key_errors))!=null &&
                        !data.getParcelableArrayListExtra(getString(R.string.key_errors)).isEmpty()){
                   // setErrorUI(data.getParcelableArrayListExtra(getString(R.string.key_errors)));
                   // showForgotPasswordWindow();
                    ArrayList<Error> error =data.getParcelableArrayListExtra(getString(R.string.key_errors));
                    forgetPasswordBinding.txtMobileNo.requestFocus();
                    forgetPasswordBinding.txtMobileNo.setError(error.get(0).getMessage());
                }
            }

            if(requestCode==REQ_UPDATE_PASSWORD){
                if(null!=data &&
                        data.getParcelableArrayListExtra(getString(R.string.key_errors))!=null &&
                        !data.getParcelableArrayListExtra(getString(R.string.key_errors)).isEmpty()){
                    setForgetPasswordUIError(data.getParcelableArrayListExtra(getString(R.string.key_errors)),null);
                }else{
                    SignUpModel signUpModel=data.getParcelableExtra(getString(R.string.key_sign_up_model));
                    if(signUpModel!=null) {
                        mBinding.txtUserId.getEditText().setText(signUpModel.getMobileNumber());
                        mBinding.txtPassword.setFocusable(true);
                        mBinding.txtPassword.requestFocus(View.FOCUS_UP);
                        Toast.makeText(this, getString(R.string.password_updated), Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                }
            }

        }

    }

    private void loginNow(UserMst mst) {
        if(mst!=null){
            Toast.makeText(this, mst.getMobileNum()+" : login Successfully", Toast.LENGTH_SHORT).show();

            storage.write(getString(R.string.key_user_mst),new Gson().toJson(mst));
            Intent intent=new Intent(this, Dashboard.class);
            intent.putExtra(getString(R.string.key_user_mst),mst);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onLoginStart() {
        startProgressbar();
    }

    @Override
    public void onLoginSuccess(UserRegResponse response) {
        removeErrorUI();
        stopProgressbar();
        if (response!=null && response.getUser()!=null){
            userMst=response.getUser();
            if(userMst.isMobVerify()){
                loginNow(userMst);
            }else{
                //verify OPT activity
                Intent intent=new Intent(this,ActivityVerifyOTP.class);
                intent.putExtra(getString(R.string.key_mobile_no),userMst.getMobileNum());
                intent.putExtra(getString(R.string.is_from_login),true);
                startActivityForResult(intent,REQ_VERIFY_OTP);
            }
        }
    }

    @Override
    public void onLoginFail(UserRegResponse response) {
        removeErrorUI();
        stopProgressbar();
        if(response!=null && response.getErrors()!=null && !response.getErrors().isEmpty()){
            setErrorUI(response.getErrors());
        }
    }

    @Override
    public void setErrorUI(ArrayList<Error> errors) {
        stopProgressbar();
        removeErrorUI();
        if(null!=errors && !errors.isEmpty()){
            for(Error error:errors){
                switch (error.toIntValue()){
                    case 1:
                    mBinding.txtUserId.setErrorEnabled(true);
                    mBinding.txtUserId.setError(getString(R.string.validate_mobile_number));
                    break;

                    case 2:

                    case 4:
                        mBinding.txtPassword.setErrorEnabled(true);
                        mBinding.txtPassword.setError(getString(R.string.invalid_password));
                        break;

                    case 3:
                        mBinding.txtUserId.setErrorEnabled(true);
                        mBinding.txtUserId.setError(getString(R.string.mobile_not_reg_sign_up_now));
                        break;

                    case 5:
                        mBinding.txtPassword.setErrorEnabled(true);
                        mBinding.txtPassword.setError(getString(R.string.invalid_req));
                        break;

                    case 6:
                    default:
                        mBinding.txtPassword.setErrorEnabled(true);
                        mBinding.txtPassword.setError(error.getMessage());
                        break;
                }
            }

        }
    }

    @Override
    public void setForgetPasswordUIError(ArrayList<Error> errors,String mobNo) {

        stopProgressbarDialog();
        if(null!=errors && !errors.isEmpty()){
            Error error=errors.get(0);
            forgetPasswordBinding.txtMobileNo.requestFocus();
            forgetPasswordBinding.txtMobileNo.setError(error.getMessage());
        }else{
            //verify OPT activity
            if(null!=mobNo) {
                Intent intent = new Intent(this, ActivityVerifyOTP.class);
                intent.putExtra(getString(R.string.key_mobile_no), mobNo.trim());
                intent.putExtra(getString(R.string.is_from_forget_password), true);
                startActivityForResult(intent, REQ_VERIFY_OTP_FORGET_PASSWORD);
            }
        }
    }


    public void showForgotPasswordWindow(){
        LayoutInflater inflater = getLayoutInflater();
        View popupView = inflater.inflate(R.layout.diaload_forget_password, null);
        forgetPasswordBinding = DialoadForgetPasswordBinding.inflate(inflater, (ViewGroup) popupView,false);
        dialog=new BottomSheetDialog(this,R.style.CustomBottomSheet);
        dialog.setContentView(forgetPasswordBinding.getRoot());
        dialog.show();

        forgetPasswordBinding.btnConfirm.setOnClickListener(v -> {
            String mobNo=forgetPasswordBinding.txtMobileNo.getEditText().getText().toString();
            if(CommonUtil.isValidMobNo(mobNo)){
                startProgressbarDialog();
                model.isMobileNumberExist(mobNo.trim());
            }else{
                forgetPasswordBinding.txtMobileNo.setErrorEnabled(true);
                forgetPasswordBinding.txtMobileNo.setError(getString(R.string.validate_mobile_number));
            }

        });

        forgetPasswordBinding.btnCancel.setOnClickListener(view -> dialog.dismiss());

    }



    public enum LOGIN_ERROR_CODE {
        LOGIN001, //valid mob no
        LOGIN002, //valid paassword

        //from server
        LOGIN003, // mobile not register with allads
        LOGIN004, // invalid user id and password
        LOGIN005,//invalid req
        LOGIN006 //not able to connect server req

    }

    public void startProgressbar() {
        ViewUtil.enableDisableView(mBinding.constraintLayout,false);
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }

    public void stopProgressbar() {
        ViewUtil.enableDisableView(mBinding.constraintLayout,true);
        mBinding.progressBar.setVisibility(View.GONE);
    }


    public void startProgressbarDialog() {
        ViewUtil.enableDisableView(forgetPasswordBinding.rootView,false);
        forgetPasswordBinding.progressBar.setVisibility(View.VISIBLE);
    }

    public void stopProgressbarDialog() {
        ViewUtil.enableDisableView(forgetPasswordBinding.rootView,true);
        forgetPasswordBinding.progressBar.setVisibility(View.GONE);
    }

    public  void removeErrorUI(){
        mBinding.txtUserId.setError(null);
        mBinding.txtPassword.setError(null);

        mBinding.txtUserId.setErrorEnabled(false);
        mBinding.txtPassword.setErrorEnabled(false);
    }

}