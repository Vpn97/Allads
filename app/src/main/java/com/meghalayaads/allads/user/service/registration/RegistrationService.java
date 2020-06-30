package com.meghalayaads.allads.user.service.registration;

import com.meghalayaads.allads.common.util.CommonRestURL;
import com.meghalayaads.allads.user.response.registration.UserRegResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Allads
 * Created by Vishal Nagvadiya on 29-06-2020.
 */
public interface RegistrationService {

    @POST(CommonRestURL.REG_USER_MST)
    Call<UserRegResponse> registerUserMaster(@QueryMap HashMap<String,String> mQueryMap);


}
