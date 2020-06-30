package com.meghalayaads.allads.user.service.registration;

import com.meghalayaads.allads.common.util.CommonRestURL;
import com.meghalayaads.allads.common.util.GsonUtil;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Allads
 * Created by Vishal Nagvadiya on 29-06-2020.
 */
public class RegistrationServiceImpl {

    private static Retrofit retrofit = null;

    public static RegistrationService getService(){

        if(retrofit==null){

            retrofit=new Retrofit
                    .Builder()
                    .baseUrl(CommonRestURL.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(GsonUtil.getGson()))
                    .client(GsonUtil.getOkHttpClientBuilder().build())
                    .build();
        }

        return retrofit.create(RegistrationService.class);

    }
}
