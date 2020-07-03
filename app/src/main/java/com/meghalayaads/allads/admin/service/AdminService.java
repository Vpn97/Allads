package com.meghalayaads.allads.admin.service;

import com.meghalayaads.allads.common.util.CommonRestURL;

import java.util.LinkedHashMap;

import retrofit2.Call;
import retrofit2.http.POST;

/**
 * Allads
 * Created by Vishal Nagvadiya on 02-07-2020.
 */
public interface AdminService {

    @POST(CommonRestURL.GET_ADMIN_REPORT)
    Call<LinkedHashMap<String,Double>> getAdsReports();
}
