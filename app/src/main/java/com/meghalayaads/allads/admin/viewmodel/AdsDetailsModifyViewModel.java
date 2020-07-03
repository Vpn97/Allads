package com.meghalayaads.allads.admin.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

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


    public AdsDetailsModifyViewModel(@NonNull Application application) {
        super(application);
        this.application=application;
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
}
