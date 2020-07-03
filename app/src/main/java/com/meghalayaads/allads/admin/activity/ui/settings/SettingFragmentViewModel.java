package com.meghalayaads.allads.admin.activity.ui.settings;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.meghalayaads.allads.admin.model.AdsPriceMst;
import com.meghalayaads.allads.admin.response.AdsPriceMastResponse;
import com.meghalayaads.allads.admin.service.AdminServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingFragmentViewModel extends AndroidViewModel {

    private Application application;
    private MutableLiveData<ArrayList<AdsPriceMst>> liveData=new MutableLiveData<>();
    public SettingFragmentViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
    }


    public MutableLiveData<ArrayList<AdsPriceMst>> getAdsPriceMstDtls(int adminId, String adminMobNo){


        HashMap<String, String> map = new HashMap<>();
        map.put("admin_id", String.valueOf(adminId));
        map.put("admin_mob", adminMobNo);
        AdminServiceImpl.getService().getAdsPriceMstDtl(map).enqueue(new Callback<AdsPriceMastResponse>() {
            @Override
            public void onResponse(Call<AdsPriceMastResponse> call, Response<AdsPriceMastResponse> response) {
                if(response.body().isStatus()){
                if(response.body().getAdsPriceMsts()!=null)
                    liveData.setValue(response.body().getAdsPriceMsts());
                }
            }

            @Override
            public void onFailure(Call<AdsPriceMastResponse> call, Throwable t) {
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        return liveData;
    }


}