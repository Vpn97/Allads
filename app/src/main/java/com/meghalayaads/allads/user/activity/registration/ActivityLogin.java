package com.meghalayaads.allads.user.activity.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.common.model.UserMst;
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
        }

    }
}