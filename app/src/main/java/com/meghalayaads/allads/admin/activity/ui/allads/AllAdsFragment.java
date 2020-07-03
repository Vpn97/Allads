package com.meghalayaads.allads.admin.activity.ui.allads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.meghalayaads.allads.databinding.FragmentAllAdsBinding;

public class AllAdsFragment extends Fragment {

    private AllAdsFragmentViewModel model;
    private FragmentAllAdsBinding mBinding;

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        mBinding= FragmentAllAdsBinding.inflate(inflater,container,false);
        model = ViewModelProviders.of(this).get(AllAdsFragmentViewModel.class);

        allocation();
        setEvent();

        return mBinding.getRoot();
    }

    private void allocation() {

    }

    private void setEvent() {
    }
}