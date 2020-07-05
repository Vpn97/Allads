package com.meghalayaads.allads.common.services;

import com.meghalayaads.allads.common.model.PaginationData;
import com.meghalayaads.allads.common.response.AdsPaginationResponse;
import com.meghalayaads.allads.common.response.UserTypeResponse;
import com.meghalayaads.allads.common.util.CommonRestURL;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Allads
 * Created by Vishal Nagvadiya on 27-06-2020.
 */
public interface CommonServices {

    @POST(CommonRestURL.GET_USER_TYPES)
    Call<UserTypeResponse> getUserTypes();

    @POST(CommonRestURL.GET_ADS_PAGINATION)
    Call<AdsPaginationResponse> adsPaginationReq(@Body PaginationData paginationData);

}
