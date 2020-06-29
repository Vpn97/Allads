package com.meghalayaads.allads.user.activity.registration;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.util.Error;
import com.meghalayaads.allads.databinding.ActivitySignUpBinding;
import com.meghalayaads.allads.user.events.OnSignUpEvent;
import com.meghalayaads.allads.user.viewmodel.registation.ActivitySignUpViewModel;

import java.util.ArrayList;

public class ActivitySignUp extends AppCompatActivity implements OnSignUpEvent {


    private ActivitySignUpBinding mBinding;
    private ActivitySignUpViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        allocation();
        setEvent();
    }

    private void allocation() {
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);
        mBinding.setLifecycleOwner(this);
        model = ViewModelProviders.of(this).get(ActivitySignUpViewModel.class);
        model.setEvent(this);
        mBinding.setModel(model);

    }

    private void setEvent() {
        //get user type
        // model.getUserTypes();

        //set User Types
       /* model.getUserTypesLiveData().observe(this, userTypes -> {
            if(null!=userTypes) {
                LinkedHashSet<UserType> linkedHashSet=new LinkedHashSet<>(userTypes);
                ArrayList<UserType> userTypesList=new ArrayList<>(linkedHashSet);
                mBinding.chipsUserType.removeAllViews();
                for (UserType userType : userTypesList) {
                    final Chip mChip = (Chip) this.getLayoutInflater().inflate(R.layout.item_chip_user_type, null, false);
                    mChip.setText(userType.getTypeName());
                    mBinding.chipsUserType.addView(mChip);
                }

                if(userTypes.size()>0){
                    mBinding.progressBar.setVisibility(View.GONE);
                }

            }
        });*/


        mBinding.btnSignUp.setOnClickListener(view -> {
            if(model.validateUserData()){
            }
        });


    }

    @Override
    public void setErrorUI(ArrayList<Error> errors) {
        if (null != errors && errors.size()>0) {
            mBinding.txtUserId.setErrorEnabled(false);
            mBinding.txtUserName.setErrorEnabled(false);
            mBinding.txtPassword.setErrorEnabled(false);
            mBinding.txtConfPassword.setErrorEnabled(false);
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
            mBinding.txtUserId.setErrorEnabled(false);
            mBinding.txtUserName.setErrorEnabled(false);
            mBinding.txtPassword.setErrorEnabled(false);
            mBinding.txtConfPassword.setErrorEnabled(false);
        }
    }


    public enum SING_UP_ERROR_CODE {
        ERR001, //mobile number validation;
        ERR002,//user name validate
        ERR003,// password length
        ERR004,// password dose'not match
    }
}