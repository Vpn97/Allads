package com.meghalayaads.allads.admin.activity.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.meghalayaads.allads.databinding.FragmentSettingsBinding;

public class SettingFragment extends Fragment {

    private SettingFragmentViewModel model;
    private FragmentSettingsBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding=FragmentSettingsBinding.inflate(inflater,container,false);
        model = ViewModelProviders.of(this).get(SettingFragmentViewModel.class);

        allocation();
        setEvent();


        return mBinding.getRoot();
    }

    private void allocation() {

    }

    private void setEvent() {

    }
}