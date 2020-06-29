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
    private String userId;

    @SerializedName("password")
    private String password;

    public LoginModel(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }


    public LoginModel() {
    }

    protected LoginModel(Parcel in) {
        userId = in.readString();
        password = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userId);
        dest.writeString(password);
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
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
        notifyPropertyChanged(BR.userId);

    }

    @Bindable
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        notifyPropertyChanged(BR.password);
    }
}
