package com.meghalayaads.allads.admin.activity.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SettingFragmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SettingFragmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}