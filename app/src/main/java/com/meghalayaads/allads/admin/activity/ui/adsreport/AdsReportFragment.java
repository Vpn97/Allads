package com.meghalayaads.allads.admin.activity.ui.adsreport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.meghalayaads.allads.admin.activity.adapter.ReportItemAdapter;
import com.meghalayaads.allads.databinding.FragmentAdsReportBinding;

import java.util.ArrayList;


public class AdsReportFragment extends Fragment {

    private AdsReportFragmentViewModel adsReportFragmentViewModel;
    private FragmentAdsReportBinding mBinding;
    private ReportItemAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mBinding= FragmentAdsReportBinding.inflate(inflater,container,false);
        adsReportFragmentViewModel =new ViewModelProvider(this).get(AdsReportFragmentViewModel.class);
        mBinding.setLifecycleOwner(this);
        allocation();
        setEvent();


        return mBinding.getRoot();
    }

    private void allocation() {

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2,
                LinearLayoutManager.VERTICAL);
            mBinding.rvReportItem.setLayoutManager(manager);


    }

    private void setEvent() {
        adsReportFragmentViewModel.getAdsReports().observe(getViewLifecycleOwner(), new Observer<ArrayList<String[]>>() {
            @Override
            public void onChanged(ArrayList<String[]> strings) {
                adapter=new ReportItemAdapter(getContext(),strings);
                mBinding.rvReportItem.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }
}