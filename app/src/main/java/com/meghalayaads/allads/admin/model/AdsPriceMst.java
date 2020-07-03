package com.meghalayaads.allads.admin.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.meghalayaads.allads.common.model.UserType;

import java.util.Date;

/**
 * Allads
 * Created by Vishal Nagvadiya on 02-07-2020.
 */
public class AdsPriceMst implements Parcelable {

    @SerializedName("ads_price_mst_id")
    private String adsPriceMstId;

    @SerializedName("user_type_id ")
    private String userTypeId;

    @SerializedName("user_type")
    private UserType userType;

    @SerializedName("amount_per_word")
    private String amountPerWord;

    @SerializedName("amount_per_img")
    private String amountPerImg;

    @SerializedName("word_limit")
    private String wordLimit;

    @SerializedName("lumpsum_amount")
    private String lumpSumAmount;

    @SerializedName("lumpsum_word_limit")
    private String lumpSumWordLimit;

    @SerializedName("admin_id")
    private String adminId;

    @SerializedName("is_active")
    private boolean isActive;

    @SerializedName("created_date")
    private Date createdDate;

    @SerializedName("updated_date")
    private Date updatedDate;

    @SerializedName("ads_time_limit_days")
    private String adsTimeLimitDays;


    public AdsPriceMst() {
    }

    public AdsPriceMst(String adsPriceMstId, String userTypeId, UserType userType, String amountPerWord,
                       String amountPerImg, String wordLimit, String lumpSumAmount, String lumpSumWordLimit,
                       String adminId, boolean isActive, Date createdDate, Date updatedDate, String adsTimeLimitDays) {
        this.adsPriceMstId = adsPriceMstId;
        this.userTypeId = userTypeId;
        this.userType = userType;
        this.amountPerWord = amountPerWord;
        this.amountPerImg = amountPerImg;
        this.wordLimit = wordLimit;
        this.lumpSumAmount = lumpSumAmount;
        this.lumpSumWordLimit = lumpSumWordLimit;
        this.adminId = adminId;
        this.isActive = isActive;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.adsTimeLimitDays = adsTimeLimitDays;
    }


    protected AdsPriceMst(Parcel in) {
        adsPriceMstId = in.readString();
        userTypeId = in.readString();
        userType = in.readParcelable(UserType.class.getClassLoader());
        amountPerWord = in.readString();
        amountPerImg = in.readString();
        wordLimit = in.readString();
        lumpSumAmount = in.readString();
        lumpSumWordLimit = in.readString();
        adminId = in.readString();
        isActive = in.readByte() != 0;
        adsTimeLimitDays = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(adsPriceMstId);
        dest.writeString(userTypeId);
        dest.writeParcelable(userType, flags);
        dest.writeString(amountPerWord);
        dest.writeString(amountPerImg);
        dest.writeString(wordLimit);
        dest.writeString(lumpSumAmount);
        dest.writeString(lumpSumWordLimit);
        dest.writeString(adminId);
        dest.writeByte((byte) (isActive ? 1 : 0));
        dest.writeString(adsTimeLimitDays);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AdsPriceMst> CREATOR = new Creator<AdsPriceMst>() {
        @Override
        public AdsPriceMst createFromParcel(Parcel in) {
            return new AdsPriceMst(in);
        }

        @Override
        public AdsPriceMst[] newArray(int size) {
            return new AdsPriceMst[size];
        }
    };

    public String getAdsPriceMstId() {
        return adsPriceMstId;
    }

    public void setAdsPriceMstId(String adsPriceMstId) {
        this.adsPriceMstId = adsPriceMstId;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getAmountPerWord() {
        return amountPerWord;
    }

    public void setAmountPerWord(String amountPerWord) {
        this.amountPerWord = amountPerWord;
    }

    public String getAmountPerImg() {
        return amountPerImg;
    }

    public void setAmountPerImg(String amountPerImg) {
        this.amountPerImg = amountPerImg;
    }

    public String getWordLimit() {
        return wordLimit;
    }

    public void setWordLimit(String wordLimit) {
        this.wordLimit = wordLimit;
    }

    public String getLumpSumAmount() {
        return lumpSumAmount;
    }

    public void setLumpSumAmount(String lumpSumAmount) {
        this.lumpSumAmount = lumpSumAmount;
    }

    public String getLumpSumWordLimit() {
        return lumpSumWordLimit;
    }

    public void setLumpSumWordLimit(String lumpSumWordLimit) {
        this.lumpSumWordLimit = lumpSumWordLimit;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getAdsTimeLimitDays() {
        return adsTimeLimitDays;
    }

    public void setAdsTimeLimitDays(String adsTimeLimitDays) {
        this.adsTimeLimitDays = adsTimeLimitDays;
    }
}
