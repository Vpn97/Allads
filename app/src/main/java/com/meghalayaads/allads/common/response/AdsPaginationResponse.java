package com.meghalayaads.allads.common.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.meghalayaads.allads.common.model.AdMst;
import com.meghalayaads.allads.common.model.PaginationData;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 05-07-2020.
 */
public class AdsPaginationResponse implements Parcelable {

    @SerializedName("ads")
    private ArrayList<AdMst> ads;

    @SerializedName("pagination_data")
    private PaginationData paginationData;

    @SerializedName("total_active_ads")
    private Integer totalActiveAds;

    @SerializedName("total_ad_in_page")
    private Integer totalAdInPage;



    public AdsPaginationResponse() {
    }

    public AdsPaginationResponse(ArrayList<AdMst> ads, PaginationData paginationData,
                                 Integer totalActiveAds, Integer totalAdInPage) {
        this.ads = ads;
        this.paginationData = paginationData;
        this.totalActiveAds = totalActiveAds;
        this.totalAdInPage = totalAdInPage;
    }

    protected AdsPaginationResponse(Parcel in) {
        ads = in.createTypedArrayList(AdMst.CREATOR);
        if (in.readByte() == 0) {
            totalActiveAds = null;
        } else {
            totalActiveAds = in.readInt();
        }
        if (in.readByte() == 0) {
            totalAdInPage = null;
        } else {
            totalAdInPage = in.readInt();
        }
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(ads);
        if (totalActiveAds == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalActiveAds);
        }
        if (totalAdInPage == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(totalAdInPage);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AdsPaginationResponse> CREATOR = new Creator<AdsPaginationResponse>() {
        @Override
        public AdsPaginationResponse createFromParcel(Parcel in) {
            return new AdsPaginationResponse(in);
        }

        @Override
        public AdsPaginationResponse[] newArray(int size) {
            return new AdsPaginationResponse[size];
        }
    };

    public ArrayList<AdMst> getAds() {
        return ads;
    }

    public void setAds(ArrayList<AdMst> ads) {
        this.ads = ads;
    }

    public PaginationData getPaginationData() {
        return paginationData;
    }

    public void setPaginationData(PaginationData paginationData) {
        this.paginationData = paginationData;
    }

    public Integer getTotalActiveAds() {
        return totalActiveAds;
    }

    public void setTotalActiveAds(Integer totalActiveAds) {
        this.totalActiveAds = totalActiveAds;
    }

    public Integer getTotalAdInPage() {
        return totalAdInPage;
    }

    public void setTotalAdInPage(Integer totalAdInPage) {
        this.totalAdInPage = totalAdInPage;
    }
}
