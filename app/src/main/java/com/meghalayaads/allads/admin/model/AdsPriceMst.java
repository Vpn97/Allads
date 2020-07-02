package com.meghalayaads.allads.admin.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Allads
 * Created by Vishal Nagvadiya on 02-07-2020.
 */
public class AdsPriceMst implements Parcelable {

    @SerializedName("ads_price_mst_id")
    private int adsPriceMstId;

    @SerializedName("user_type_id ")
    private int userTypeId;

    @SerializedName("amount_per_word")
    private Double amountPerWord;

    @SerializedName("amount_per_img")
    private Double amountPerImg;

    @SerializedName("word_limit")
    private int wordLimit;

    @SerializedName("lumpsum_amount")
    private Double lumpSumAmount;

    @SerializedName("lumpsum_word_limit")
    private int lumpSumWordLimit;

    @SerializedName("admin_id")
    private int adminId;

    @SerializedName("created_date")
    private Date createdDate;

    @SerializedName("updated_date")
    private Date updatedDate;

    @SerializedName("ads_time_limit_days")
    private int adsTimeLimitDays;


    public AdsPriceMst(int adsPriceMstId, int userTypeId, Double amountPerWord, Double amountPerImg, int wordLimit, Double lumpSumAmount, int lumpSumWordLimit, int adminId, Date createdDate, Date updatedDate, int adsTimeLimitDays) {
        this.adsPriceMstId = adsPriceMstId;
        this.userTypeId = userTypeId;
        this.amountPerWord = amountPerWord;
        this.amountPerImg = amountPerImg;
        this.wordLimit = wordLimit;
        this.lumpSumAmount = lumpSumAmount;
        this.lumpSumWordLimit = lumpSumWordLimit;
        this.adminId = adminId;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.adsTimeLimitDays = adsTimeLimitDays;
    }

    public AdsPriceMst() {
    }

    protected AdsPriceMst(Parcel in) {
        adsPriceMstId = in.readInt();
        userTypeId = in.readInt();
        if (in.readByte() == 0) {
            amountPerWord = null;
        } else {
            amountPerWord = in.readDouble();
        }
        if (in.readByte() == 0) {
            amountPerImg = null;
        } else {
            amountPerImg = in.readDouble();
        }
        wordLimit = in.readInt();
        if (in.readByte() == 0) {
            lumpSumAmount = null;
        } else {
            lumpSumAmount = in.readDouble();
        }
        lumpSumWordLimit = in.readInt();
        adminId = in.readInt();
        adsTimeLimitDays = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(adsPriceMstId);
        dest.writeInt(userTypeId);
        if (amountPerWord == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(amountPerWord);
        }
        if (amountPerImg == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(amountPerImg);
        }
        dest.writeInt(wordLimit);
        if (lumpSumAmount == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(lumpSumAmount);
        }
        dest.writeInt(lumpSumWordLimit);
        dest.writeInt(adminId);
        dest.writeInt(adsTimeLimitDays);
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

    public int getAdsPriceMstId() {
        return adsPriceMstId;
    }

    public void setAdsPriceMstId(int adsPriceMstId) {
        this.adsPriceMstId = adsPriceMstId;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Double getAmountPerWord() {
        return amountPerWord;
    }

    public void setAmountPerWord(Double amountPerWord) {
        this.amountPerWord = amountPerWord;
    }

    public Double getAmountPerImg() {
        return amountPerImg;
    }

    public void setAmountPerImg(Double amountPerImg) {
        this.amountPerImg = amountPerImg;
    }

    public int getWordLimit() {
        return wordLimit;
    }

    public void setWordLimit(int wordLimit) {
        this.wordLimit = wordLimit;
    }

    public Double getLumpSumAmount() {
        return lumpSumAmount;
    }

    public void setLumpSumAmount(Double lumpSumAmount) {
        this.lumpSumAmount = lumpSumAmount;
    }

    public int getLumpSumWordLimit() {
        return lumpSumWordLimit;
    }

    public void setLumpSumWordLimit(int lumpSumWordLimit) {
        this.lumpSumWordLimit = lumpSumWordLimit;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
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

    public int getAdsTimeLimitDays() {
        return adsTimeLimitDays;
    }

    public void setAdsTimeLimitDays(int adsTimeLimitDays) {
        this.adsTimeLimitDays = adsTimeLimitDays;
    }
}
