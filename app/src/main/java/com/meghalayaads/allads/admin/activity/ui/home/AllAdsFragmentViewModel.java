package com.meghalayaads.allads.admin.activity.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AllAdsFragmentViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AllAdsFragmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}