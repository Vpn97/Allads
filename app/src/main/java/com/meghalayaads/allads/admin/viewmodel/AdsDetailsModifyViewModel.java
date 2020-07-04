package com.meghalayaads.allads.admin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.meghalayaads.allads.R;
import com.meghalayaads.allads.admin.event.OnModifyAdsPriceEvent;
import com.meghalayaads.allads.admin.model.AdsPriceMst;
import com.meghalayaads.allads.admin.response.AdsPriceUpdateResponse;
import com.meghalayaads.allads.admin.service.AdminService;
import com.meghalayaads.allads.admin.service.AdminServiceImpl;
import com.meghalayaads.allads.common.util.Error;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Allads
 * Created by Vishal Nagvadiya on 03-07-2020.
 */
public class AdsDetailsModifyViewModel extends AndroidViewModel {
    private Application application;
    private MutableLiveData<String> pricePerWord=new MutableLiveData<>();
    private MutableLiveData<String> wordLimit=new MutableLiveData<>();
    private MutableLiveData<String> lumpSumAmount=new MutableLiveData<>();
    private MutableLiveData<String> lumpSumWordLimit =new MutableLiveData<>();
    private MutableLiveData<String> pricePerImg=new MutableLiveData<>();
    private MutableLiveData<String> adsTimeLimitDays=new MutableLiveData<>();
    private OnModifyAdsPriceEvent event;

    private MutableLiveData<AdsPriceMst> adsPriceMstLiveData;


    public AdsDetailsModifyViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
       adsPriceMstLiveData=new MutableLiveData<>();
       adsPriceMstLiveData.setValue(new AdsPriceMst());
    }



    public void updateAdsPrice(String adminMobNo){

        event.onStartUpdate();
        HashMap<String, String> map = new HashMap<>();
        map.put("admin_id", String.valueOf(adsPriceMstLiveData.getValue().getAdminId()));
        map.put("admin_mob", adminMobNo);
        AdminService adminService= AdminServiceImpl.getService();

        adminService.updateAdsPrices(map,adsPriceMstLiveData.getValue()).enqueue(new Callback<AdsPriceUpdateResponse>() {
            @Override
            public void onResponse(Call<AdsPriceUpdateResponse> call, Response<AdsPriceUpdateResponse> response) {
                    if(response.body()!=null && response.body().isStatus() && response.body().getAdsPriceMst()!=null){
                        event.onSuccess(response.body());
                    }else{
                        if(response.body().getErrors()!=null && !response.body().getErrors().isEmpty()){
                            event.onFail(response.body().getErrors());
                        }else{
                            ArrayList<Error> errors=new ArrayList<>();
                            errors.add(new Error("UPDATE001",application.getString(R.string.server_error),"ADMIN"));
                            event.onFail(errors);
                        }
                    }
            }

            @Override
            public void onFailure(Call<AdsPriceUpdateResponse> call, Throwable t) {
                ArrayList<Error> errors=new ArrayList<>();
                errors.add(new Error("UPDATE002",t.getMessage(),"ADMIN"));
                event.onFail(errors);
            }
        });
    }



    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public MutableLiveData<String> getPricePerWord() {
        return pricePerWord;
    }

    public void setPricePerWord(MutableLiveData<String> pricePerWord) {
        this.pricePerWord = pricePerWord;
    }

    public MutableLiveData<String> getWordLimit() {
        return wordLimit;
    }

    public void setWordLimit(MutableLiveData<String> wordLimit) {
        this.wordLimit = wordLimit;
    }

    public MutableLiveData<String> getLumpSumAmount() {
        return lumpSumAmount;
    }

    public void setLumpSumAmount(MutableLiveData<String> lumpSumAmount) {
        this.lumpSumAmount = lumpSumAmount;
    }

    public MutableLiveData<String> getLumpSumWordLimit() {
        return lumpSumWordLimit;
    }

    public void setLumpSumWordLimit(MutableLiveData<String> lumpSumWordLimit) {
        this.lumpSumWordLimit = lumpSumWordLimit;
    }

    public MutableLiveData<String> getPricePerImg() {
        return pricePerImg;
    }

    public void setPricePerImg(MutableLiveData<String> pricePerImg) {
        this.pricePerImg = pricePerImg;
    }

    public MutableLiveData<String> getAdsTimeLimitDays() {
        return adsTimeLimitDays;
    }

    public void setAdsTimeLimitDays(MutableLiveData<String> adsTimeLimitDays) {
        this.adsTimeLimitDays = adsTimeLimitDays;
    }

    public MutableLiveData<com.meghalayaads.allads.admin.model.AdsPriceMst> getAdsPriceMstLiveData() {
        return adsPriceMstLiveData;
    }

    public void setAdsPriceMstLiveData(MutableLiveData<com.meghalayaads.allads.admin.model.AdsPriceMst> adsPriceMstLiveData) {
        this.adsPriceMstLiveData = adsPriceMstLiveData;
    }

    public OnModifyAdsPriceEvent getEvent() {
        return event;
    }

    public void setEvent(OnModifyAdsPriceEvent event) {
        this.event = event;
    }
}
