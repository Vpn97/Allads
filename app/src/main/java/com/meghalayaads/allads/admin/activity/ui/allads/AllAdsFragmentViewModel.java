package com.meghalayaads.allads.admin.activity.ui.allads;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class AllAdsFragmentViewModel extends AndroidViewModel {

    private Application application;

    public AllAdsFragmentViewModel(@NonNull Application application) {
        super(application);
        this.application=application;


    }
}