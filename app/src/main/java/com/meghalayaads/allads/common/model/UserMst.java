package com.meghalayaads.allads.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Allads
 * Created by Vishal Nagvadiya on 29-06-2020.
 */
public class UserMst implements Parcelable {

    @SerializedName("uid")
    private int uid;

    @SerializedName("mob_id")
    private String mobileNum;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("password_hash")
    private String passwordHash;

    @SerializedName("address_id")
    private int addressId;

    @SerializedName("user_type_id")
    private int userTypeId;

    @SerializedName("email")
    private String email;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("created_date")
    private Date createdDate;

    @SerializedName("update_date")
    private String updateDate;


    public UserMst(int uid, String mobileNum, String userName, String passwordHash, int addressId, int userTypeId, String email, String deviceId, Date createdDate, String updateDate) {
        this.uid = uid;
        this.mobileNum = mobileNum;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.addressId = addressId;
        this.userTypeId = userTypeId;
        this.email = email;
        this.deviceId = deviceId;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }

    public UserMst() {
    }

    protected UserMst(Parcel in) {
        uid = in.readInt();
        mobileNum = in.readString();
        userName = in.readString();
        passwordHash = in.readString();
        addressId = in.readInt();
        userTypeId = in.readInt();
        email = in.readString();
        deviceId = in.readString();
        updateDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(uid);
        dest.writeString(mobileNum);
        dest.writeString(userName);
        dest.writeString(passwordHash);
        dest.writeInt(addressId);
        dest.writeInt(userTypeId);
        dest.writeString(email);
        dest.writeString(deviceId);
        dest.writeString(updateDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserMst> CREATOR = new Creator<UserMst>() {
        @Override
        public UserMst createFromParcel(Parcel in) {
            return new UserMst(in);
        }

        @Override
        public UserMst[] newArray(int size) {
            return new UserMst[size];
        }
    };

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
