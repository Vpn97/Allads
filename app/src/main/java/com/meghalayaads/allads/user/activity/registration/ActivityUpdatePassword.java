package com.meghalayaads.allads.user.activity.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.databinding.ActivityUpdatePasswordBinding;
import com.meghalayaads.allads.user.events.OnUpdatePasswordEvent;
import com.meghalayaads.allads.user.model.SignUpModel;
import com.meghalayaads.allads.user.response.registration.StatusResponse;
import com.meghalayaads.allads.user.viewmodel.registation.ActivityUpdatePasswordViewModel;

import java.util.ArrayList;

public class ActivityUpdatePassword extends AppCompatActivity implements OnUpdatePasswordEvent {

    private ActivityUpdatePasswordBinding mBinding;
    private ActivityUpdatePasswordViewModel model;
    private boolean isFromLogin;
    private SignUpModel signUpModel;
    private ArrayList<Error> errors=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        allocation();
        setEvent();

    }

    private void allocation() {

        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_update_password);
        mBinding.setLifecycleOwner(this);
        model= ViewModelProviders.of(this).get(ActivityUpdatePasswordViewModel.class);
        mBinding.setModel(model);
        model.setEvent(this);

        Intent intent=getIntent();

        if(null!=intent){
            isFromLogin=intent.getBooleanExtra(getString(R.string.key_is_from_login),false);
            signUpModel=intent.getParcelableExtra(getString(R.string.key_sign_up_model));

            if(null!=signUpModel && null!=signUpModel.getMobileNumber()){
                 model.getLiveData().getValue().setMobileNumber(signUpModel.getMobileNumber());
                 model.getLiveData().getValue().setDeviceId(signUpModel.getDeviceId());
                 model.getLiveData().getValue().setUserName(signUpModel.getUserName());
                 model.getLiveData().getValue().setUserType(signUpModel.getUserType());
            }else{
                errors.add(new Error(UPDATE_PASS_ERROR_CODE.PASS005.toString(),getString(R.string.enter_mobile_number),"REG"));
                backToReq(errors);
            }
        }

    }



    private void setEvent() {
        mBinding.btnUpdate.setOnClickListener(v->{
            model.updatePassword();
        });

        mBinding.btnCancel.setOnClickListener(v->{
            cancelReq();
        });
    }


    private void backToReq(ArrayList<Error> errors) {
        Intent intent=new Intent();
        setResult(RESULT_OK,intent);
        if(errors==null || errors.isEmpty()){
            intent.putExtra(getString(R.string.key_sign_up_model),signUpModel);
        }else{
            intent.putExtra(getString(R.string.key_errors),errors);
        }
        finish();
    }

    private void cancelReq() {
        setResult(RESULT_CANCELED);
        finish();
    }



    @Override
    public void onStartUpdatePassword() {
        startProgressbar();
    }

    @Override
    public void onPasswordUpdateSuccessful(StatusResponse response) {
        stopProgressbar();
        backToReq(null);
    }

    @Override
    public void onPasswordUpdateFail(StatusResponse response) {
        stopProgressbar();
        removeErrorUI();
        if(null!=response)
            setErrorUI(response.getErrors());
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
                        mBinding.txtPassword.setError(error.getMessage());
                        break;

                    case 2:
                        mBinding.txtConfPassword.setError(error.getMessage());
                        break;

                    case 3:

                        break;

                    case 4:

                        break;

                    case 5:
                        errors.add(new Error(UPDATE_PASS_ERROR_CODE.PASS005.toString(),getString(R.string.enter_mobile_number),"REG"));
                        backToReq(errors);
                        break;

                    default:
                        mBinding.txtConfPassword.setError(error.getMessage());
                }

            }
        } else {
            removeErrorUI();
        }
    }


    public enum UPDATE_PASS_ERROR_CODE {
        PASS001,// password length
        PASS002,// password dose'not match
        PASS003,//
        PASS004, // response fail

        PASS005, //mobile num
        PASS006, // invalid req
        PASS007 //server error
    }



    public void removeErrorUI() {
        mBinding.txtPassword.setError(null);
        mBinding.txtConfPassword.setError(null);
        mBinding.txtPassword.setErrorEnabled(false);
        mBinding.txtConfPassword.setErrorEnabled(false);
    }

    public void startProgressbar() {
        mBinding.rootView.setVisibility(View.INVISIBLE);
        mBinding.progressBar.setVisibility(View.VISIBLE);
    }

    public void stopProgressbar() {
        mBinding.rootView.setVisibility(View.VISIBLE);;
        mBinding.progressBar.setVisibility(View.GONE);
    }

}