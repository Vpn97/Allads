package com.meghalayaads.allads.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.meghalayaads.allads.model.LoginModel;

/**
 * Allads
 * Created by Vishal Nagvadiya on 24-06-2020.
 */
public class ActivityLoginViewMode extends AndroidViewModel {
    private Application application;
    private MutableLiveData<LoginModel> loginModelData=new MutableLiveData<>();

    public ActivityLoginViewMode(@NonNull Application application) {
        super(application);
        this.application=application;
    }


    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public MutableLiveData<LoginModel> getLoginModelData() {
        return loginModelData;
    }

    public void setLoginModelData(MutableLiveData<LoginModel> loginModelData) {
        this.loginModelData = loginModelData;
    }
}
