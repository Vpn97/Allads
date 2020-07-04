package com.meghalayaads.allads.admin.activity.ui.settings;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.meghalayaads.allads.admin.response.AdsPriceMstResponse;
import com.meghalayaads.allads.admin.service.AdminServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingFragmentViewModel extends AndroidViewModel {

    private Application application;
    private MutableLiveData<ArrayList<com.meghalayaads.allads.admin.model.AdsPriceMst>> liveData=new MutableLiveData<>();
    public SettingFragmentViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }


    public MutableLiveData<ArrayList<com.meghalayaads.allads.admin.model.AdsPriceMst>> getAdsPriceMstDtls(int adminId, String adminMobNo){


        HashMap<String, String> map = new HashMap<>();
        map.put("admin_id", String.valueOf(adminId));
        map.put("admin_mob", adminMobNo);
        AdminServiceImpl.getService().getAdsPriceMstDtl(map).enqueue(new Callback<AdsPriceMstResponse>() {
            @Override
            public void onResponse(Call<AdsPriceMstResponse> call, Response<AdsPriceMstResponse> response) {
                if(response!=null && response.body()!=null && response.body().isStatus()){
                if(response.body().getAdsPriceMsts()!=null)
                    liveData.setValue(response.body().getAdsPriceMsts());
                }
            }

            @Override
            public void onFailure(Call<AdsPriceMstResponse> call, Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return liveData;
    }


}