package com.meghalayaads.allads.user.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.meghalayaads.allads.BR;

/**
 * Allads
 * Created by Vishal Nagvadiya on 27-06-2020.
 */
public class SignUpModel extends BaseObservable implements Parcelable {

    private String mobileNumber;
    private String userName;
    private String Password;
    private String confirmPassword;
    private String userType;
    private String deviceId;

    public SignUpModel() {
    }



    public SignUpModel(String mobileNumber, String userName, String password, String confirmPassword, String userType, String deviceId) {
        this.mobileNumber = mobileNumber;
        this.userName = userName;
        Password = password;
        this.confirmPassword = confirmPassword;
        this.userType = userType;
        this.deviceId = deviceId;
    }

    protected SignUpModel(Parcel in) {
        mobileNumber = in.readString();
        userName = in.readString();
        Password = in.readString();
        confirmPassword = in.readString();
        userType = in.readString();
        deviceId = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mobileNumber);
        dest.writeString(userName);
        dest.writeString(Password);
        dest.writeString(confirmPassword);
        dest.writeString(userType);
        dest.writeString(deviceId);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SignUpModel> CREATOR = new Creator<SignUpModel>() {
        @Override
        public SignUpModel createFromParcel(Parcel in) {
            return new SignUpModel(in);
        }

        @Override
        public SignUpModel[] newArray(int size) {
            return new SignUpModel[size];
        }
    };

    @Bindable
    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
        notifyPropertyChanged(BR.mobileNumber);
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        notifyPropertyChanged(BR.userName);
    }

    @Bindable
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
        notifyPropertyChanged(BR.password);
    }

    @Bindable
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        notifyPropertyChanged(BR.confirmPassword);
    }

    @Bindable
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
        notifyPropertyChanged(BR.userType);
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
