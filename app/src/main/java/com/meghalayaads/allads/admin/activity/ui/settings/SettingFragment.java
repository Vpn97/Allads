package com.meghalayaads.allads.admin.activity.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.meghalayaads.allads.R;
import com.meghalayaads.allads.admin.activity.adapter.AdsTypesListAdapter;
import com.meghalayaads.allads.admin.model.AdminMst;
import com.meghalayaads.allads.common.util.DataStorage;
import com.meghalayaads.allads.databinding.FragmentSettingsBinding;

public class SettingFragment extends Fragment {

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
        model.getAdsPriceMstDtls(adminMst.getAdminId(),adminMst.getMobNo()).
                observe(getViewLifecycleOwner(),adsPriceMsts -> {
                    if(adsPriceMsts!=null && !adsPriceMsts.isEmpty()) {
                        adapter = new AdsTypesListAdapter(adsPriceMsts, getContext());
                        mBinding.rvAdsTypes.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
        });
    }
}