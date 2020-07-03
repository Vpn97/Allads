package com.meghalayaads.allads.admin.activity.ui.adsreport;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.meghalayaads.allads.admin.service.AdminService;
import com.meghalayaads.allads.admin.service.AdminServiceImpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdsReportFragmentViewModel extends ViewModel {

    private MutableLiveData<ArrayList<String[]>> liveData;

    public AdsReportFragmentViewModel() {
        liveData = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<String[]>> getAdsReports() {

        AdminService adminService = AdminServiceImpl.getService();
        adminService.getAdsReports().enqueue(new Callback<LinkedHashMap<String, Double>>() {
            @Override
            public void onResponse(Call<LinkedHashMap<String, Double>> call, Response<LinkedHashMap<String, Double>> response) {
                if (null != response.body()) {
                    ArrayList<String[]> strings = new ArrayList<>();
                    for (Map.Entry<String, Double> entry : response.body().entrySet()) {
                        String[] str = new String[2];
                        str[0] = entry.getKey();
                        str[1] = String.valueOf(entry.getValue());
                        strings.add(str);
                    }
                    liveData.setValue(strings);
                }
            }

            @Override
            public void onFailure(Call<LinkedHashMap<String, Double>> call, Throwable t) {

            }
        });

        return liveData;

    }
}