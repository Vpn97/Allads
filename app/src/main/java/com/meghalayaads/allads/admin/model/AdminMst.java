package com.meghalayaads.allads.admin.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Allads
 * Created by Vishal Nagvadiya on 02-07-2020.
 */
public class AdminMst implements Parcelable {

    private String adminId;
    private String uid;
    private boolean canEditPrices;
    private Date createdDate;
    private boolean canPostAds;

    public AdminMst(String adminId, String uid, boolean canEditPrices, Date createdDate, boolean canPostAds) {
        this.adminId = adminId;
        this.uid = uid;
        this.canEditPrices = canEditPrices;
        this.createdDate = createdDate;
        this.canPostAds = canPostAds;
    }


    public AdminMst() {
    }

    protected AdminMst(Parcel in) {
        adminId = in.readString();
        uid = in.readString();
        canEditPrices = in.readByte() != 0;
        canPostAds = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(adminId);
        dest.writeString(uid);
        dest.writeByte((byte) (canEditPrices ? 1 : 0));
        dest.writeByte((byte) (canPostAds ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AdminMst> CREATOR = new Creator<AdminMst>() {
        @Override
        public AdminMst createFromParcel(Parcel in) {
            return new AdminMst(in);
        }

        @Override
        public AdminMst[] newArray(int size) {
            return new AdminMst[size];
        }
    };

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public boolean isCanEditPrices() {
        return canEditPrices;
    }

    public void setCanEditPrices(boolean canEditPrices) {
        this.canEditPrices = canEditPrices;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isCanPostAds() {
        return canPostAds;
    }

    public void setCanPostAds(boolean canPostAds) {
        this.canPostAds = canPostAds;
    }
}
