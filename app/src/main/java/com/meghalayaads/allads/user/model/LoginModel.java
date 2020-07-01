package com.meghalayaads.allads.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.SerializedName;
import com.meghalayaads.allads.BR;

/**
 * Allads
 * Created by Vishal Nagvadiya on 24-06-2020.
 */
public class LoginModel  extends BaseObservable implements Parcelable {

    @SerializedName("user_id")
    private String mobNo;

    @SerializedName("password")
    private String password;

    @SerializedName("device_id")
    private String deviceId;


    public LoginModel(String mobNo, String password, String deviceId) {
        this.mobNo = mobNo;
        this.password = password;
        this.deviceId = deviceId;
    }


    public LoginModel() {
    }

    protected LoginModel(Parcel in) {
        mobNo = in.readString();
        password = in.readString();
        deviceId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mobNo);
        dest.writeString(password);
        dest.writeString(deviceId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoginModel> CREATOR = new Creator<LoginModel>() {
        @Override
        public LoginModel createFromParcel(Parcel in) {
            return new LoginModel(in);
        }

        @Override
        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
        }
    };

    @Bindable
    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
        notifyPropertyChanged(BR.mobNo);

    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
        notifyPropertyChanged(BR.deviceId);
    }
}
