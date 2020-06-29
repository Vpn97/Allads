package com.meghalayaads.allads.user.activity.registration;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.databinding.ActivityLoginBinding;
import com.meghalayaads.allads.user.viewmodel.registation.ActivityLoginViewModel;

public class ActivityLogin extends AppCompatActivity  {


    private static final int REQ_SIGN_UP = 11;

    private ActivityLoginBinding mBinding;
    private ActivityLoginViewModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        allocation();
        setEvent();
    }

    private void allocation() {
        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        model= ViewModelProviders.of(this).get(ActivityLoginViewModel.class);
        mBinding.setModel(model);
    }

    private void setEvent() {
        mBinding.btnSignUp.setOnClickListener(v->{
            startActivityForResult(new Intent(this, ActivitySignUp.class),REQ_SIGN_UP);
        });
    }


}