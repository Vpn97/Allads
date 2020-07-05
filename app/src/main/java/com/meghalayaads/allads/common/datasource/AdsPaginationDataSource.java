package com.meghalayaads.allads.common.datasource;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.google.gson.Gson;
import com.meghalayaads.allads.common.model.AdMst;
import com.meghalayaads.allads.common.model.PaginationData;
import com.meghalayaads.allads.common.response.AdsPaginationResponse;
import com.meghalayaads.allads.common.services.CommonServices;
import com.meghalayaads.allads.common.util.AdsConstant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Allads
 * Created by Vishal Nagvadiya on 05-07-2020.
 */
public class AdsPaginationDataSource extends PageKeyedDataSource<Integer, AdMst> {

    private CommonServices services;
    private Application application;

    public AdsPaginationDataSource(CommonServices services, Application application) {
        this.services = services;
        this.application = application;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, AdMst> callback) {

        PaginationData paginationData=new PaginationData();
        paginationData.setPage(1);
        paginationData.setAdsPerPage(AdsConstant.PER_PAGE_ADS);
        services.adsPaginationReq(paginationData).enqueue(new Callback<AdsPaginationResponse>() {
            @Override
            public void onResponse(Call<AdsPaginationResponse> call, Response<AdsPaginationResponse> response) {
               // Log.d(AdsConstant.TAG, "onResponse: " + new Gson().toJson(response.body()));
                if(response.body()!=null && response.body().getAds()!=null && !response.body().getAds().isEmpty()){
                    callback.onResult(response.body().getAds(),null,2);
                }

            }

            @Override
            public void onFailure(Call<AdsPaginationResponse> call, Throwable t) {
                Log.d(AdsConstant.TAG, "onFailure: " + new Gson().toJson(t));
            }
        });


    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, AdMst> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, AdMst> callback) {

        PaginationData paginationData=new PaginationData();
        paginationData.setPage(params.key);
        paginationData.setAdsPerPage(AdsConstant.PER_PAGE_ADS);
        services.adsPaginationReq(paginationData).enqueue(new Callback<AdsPaginationResponse>() {
            @Override
            public void onResponse(Call<AdsPaginationResponse> call, Response<AdsPaginationResponse> response) {
                Log.d(AdsConstant.TAG, "onResponse: " + new Gson().toJson(response.body()));
                if(response.body()!=null && response.body().getAds()!=null && !response.body().getAds().isEmpty()){
                    callback.onResult(response.body().getAds(),params.key+1);
                }

            }

            @Override
            public void onFailure(Call<AdsPaginationResponse> call, Throwable t) {
                Log.d(AdsConstant.TAG, "onFailure: " + new Gson().toJson(t));
            }
        });
    }
}
