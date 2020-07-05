package com.meghalayaads.allads.admin.activity.ui.allads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.meghalayaads.allads.admin.activity.adapter.AdsPagedAdapter;
import com.meghalayaads.allads.common.model.AdMst;
import com.meghalayaads.allads.databinding.FragmentAllAdsBinding;

public class AllAdsFragment extends Fragment {

    private AllAdsFragmentViewModel model;
    private FragmentAllAdsBinding mBinding;

    private AdsPagedAdapter adapter;
    private PagedList<AdMst> AdsPagedList;


    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        mBinding= FragmentAllAdsBinding.inflate(inflater,container,false);
        model = ViewModelProviders.of(this).get(AllAdsFragmentViewModel.class);

        allocation();
        setEvent();

        return mBinding.getRoot();
    }

    private void allocation() {


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.recyclerAds.setLayoutManager(layoutManager);

        adapter=new AdsPagedAdapter(mBinding.getRoot().getContext());
        mBinding.recyclerAds.setAdapter(adapter);
    }

    private void setEvent() {

        model.getAdMstPagedList().observe(getViewLifecycleOwner(), adMstsLiveData -> {
                AdsPagedList=adMstsLiveData;
                adapter.submitList(AdsPagedList);
        });
    }
}