package com.meghalayaads.allads.admin.service;

import com.meghalayaads.allads.admin.response.AdsPriceMstResponse;
import com.meghalayaads.allads.admin.response.AdsPriceUpdateResponse;
import com.meghalayaads.allads.common.util.CommonRestURL;

import java.util.HashMap;
import java.util.LinkedHashMap;

import retrofit2.Call;
import retrofit2.http.Body;
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
    Call<AdsPriceMstResponse> getAdsPriceMstDtl(@QueryMap HashMap<String,String> mQueryMap);

    @POST(CommonRestURL.UPDATE_ADS_PRICE)
    Call<AdsPriceUpdateResponse> updateAdsPrices(@QueryMap HashMap<String,String> mQueryMap, @Body com.meghalayaads.allads.admin.model.AdsPriceMst priceMst);
}
