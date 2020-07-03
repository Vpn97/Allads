package com.meghalayaads.allads.admin.service;

import com.meghalayaads.allads.admin.response.AdsPriceMastResponse;
import com.meghalayaads.allads.common.util.CommonRestURL;

import java.util.HashMap;
import java.util.LinkedHashMap;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Allads
 * Created by Vishal Nagvadiya on 02-07-2020.
 */
public interface AdminService {

    @POST(CommonRestURL.GET_ADMIN_REPORT)
    Call<LinkedHashMap<String,Double>> getAdsReports();


    @POST(CommonRestURL.GET_ADS_PRICE_MST)
    Call<AdsPriceMastResponse> getAdsPriceMstDtl(@QueryMap HashMap<String,String> mQueryMap);
}
