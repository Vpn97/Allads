package com.meghalayaads.allads.admin.activity.ui.settings;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.meghalayaads.allads.R;
import com.meghalayaads.allads.admin.activity.ActivityAdsDetailsModify;
import com.meghalayaads.allads.admin.activity.adapter.AdsTypesListAdapter;
import com.meghalayaads.allads.admin.event.SettingAdapterEvent;
import com.meghalayaads.allads.admin.model.AdminMst;
import com.meghalayaads.allads.admin.model.AdsPriceMst;
import com.meghalayaads.allads.common.util.DataStorage;
import com.meghalayaads.allads.databinding.FragmentSettingsBinding;

public class SettingFragment extends Fragment implements SettingAdapterEvent {


public static final int REQ_EDIT_PRICE=12;

    private SettingFragmentViewModel model;
    private FragmentSettingsBinding mBinding;
    private AdminMst adminMst;
    private DataStorage storage;
    private AdsTypesListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mBinding=FragmentSettingsBinding.inflate(inflater,container,false);
        model = ViewModelProviders.of(this).get(SettingFragmentViewModel.class);

        allocation();
        setEvent();


        return mBinding.getRoot();
    }

    private void allocation() {
        storage=new DataStorage(getActivity(),getString(R.string.key_user_data));
        adminMst =new Gson().fromJson(String.valueOf(storage.read(getString(R.string.key_admin_mst), DataStorage.STRING)),AdminMst.class);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mBinding.rvAdsTypes.setLayoutManager(layoutManager);
       /* mBinding.rvAdsTypes.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvAdsTypes.setLayoutManager(layoutManager);*/

        /*LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getApplicationContext(), R.anim.layout_animation_fall_down);
        mBinding.rvAdsTypes.setLayoutAnimation(controller);*/
    }

    private void setEvent() {

        startProgressbar();

        model.getAdsPriceMstDtls(adminMst.getAdminId(),adminMst.getMobNo()).
                observe(getViewLifecycleOwner(),adsPriceMsts -> {
                    if(adsPriceMsts!=null && !adsPriceMsts.isEmpty()) {

                        stopProgressbar();

                        adapter = new AdsTypesListAdapter(adsPriceMsts, getActivity(),this);
                        mBinding.rvAdsTypes.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            if(requestCode==REQ_EDIT_PRICE){
                Toast.makeText(getContext(), "Ads price updated", Toast.LENGTH_SHORT).show();
               //TODO update apdater
                if(null!=data && null!=data.getParcelableExtra(getString(R.string.key_ads_price_mst))){

                }else{

                }

                model.getAdsPriceMstDtls(adminMst.getAdminId(),adminMst.getMobNo());
                startProgressbar();
            }
        }

    }


    public void startProgressbar(){
        mBinding.rvAdsTypes.setVisibility(View.GONE);
        mBinding.progressBar.setVisibility(View.VISIBLE);


    }

    public void stopProgressbar(){
        mBinding.rvAdsTypes.setVisibility(View.VISIBLE);
        mBinding.progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setActivityForResult(AdsPriceMst priceMst) {
        Intent intent=new Intent(getContext(), ActivityAdsDetailsModify.class);
        intent.putExtra(getString(R.string.key_ads_price_mst),priceMst);
        startActivityForResult(intent, SettingFragment.REQ_EDIT_PRICE);
    }
}