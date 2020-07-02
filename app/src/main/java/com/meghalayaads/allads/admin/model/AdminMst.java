package com.meghalayaads.allads.admin.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Allads
 * Created by Vishal Nagvadiya on 02-07-2020.
 */
public class AdminMst implements Parcelable {

    @SerializedName("admin_id")
    private int adminId;

    @SerializedName("mob_no")
    private String mobNo;

    @SerializedName("uid")
    private int uid;

    @SerializedName("can_edit_prices")
    private boolean canEditPrices;

    @SerializedName("created_date")
    private Date createdDate;

    @SerializedName("can_post_ads")
    private boolean canPostAds;


    public AdminMst(int adminId, String mobNo, int uid, boolean canEditPrices, Date createdDate, boolean canPostAds) {
        this.adminId = adminId;
        this.mobNo = mobNo;
        this.uid = uid;
        this.canEditPrices = canEditPrices;
        this.createdDate = createdDate;
        this.canPostAds = canPostAds;
    }

    public AdminMst() {
    }

    protected AdminMst(Parcel in) {
        adminId = in.readInt();
        mobNo = in.readString();
        uid = in.readInt();
        canEditPrices = in.readByte() != 0;
        canPostAds = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(adminId);
        dest.writeString(mobNo);
        dest.writeInt(uid);
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

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
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
