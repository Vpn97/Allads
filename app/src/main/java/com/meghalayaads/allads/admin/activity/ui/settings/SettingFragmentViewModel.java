package com.meghalayaads.allads.admin.activity.ui.settings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class SettingFragmentViewModel extends AndroidViewModel {

    private Application application;
    public SettingFragmentViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }


}