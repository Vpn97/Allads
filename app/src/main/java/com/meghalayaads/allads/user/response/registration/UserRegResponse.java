package com.meghalayaads.allads.user.response.registration;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.meghalayaads.allads.common.model.UserMst;
import com.meghalayaads.allads.common.util.Error;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 29-06-2020.
 */
public class UserRegResponse implements Parcelable {

    @SerializedName("status")
    private boolean status;

    @SerializedName("user")
    private UserMst user;

    @SerializedName("errors")
    private ArrayList<Error> errors;


    public UserRegResponse(boolean status, UserMst user, ArrayList<Error> errors) {
        this.status = status;
        this.user = user;
        this.errors = errors;
    }

    public UserRegResponse() {
    }

    protected UserRegResponse(Parcel in) {
        status = in.readByte() != 0;
        user = in.readParcelable(UserMst.class.getClassLoader());
        errors = in.createTypedArrayList(Error.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeParcelable(user, flags);
        dest.writeTypedList(errors);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserRegResponse> CREATOR = new Creator<UserRegResponse>() {
        @Override
        public UserRegResponse createFromParcel(Parcel in) {
            return new UserRegResponse(in);
        }

        @Override
        public UserRegResponse[] newArray(int size) {
            return new UserRegResponse[size];
        }
    };

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public UserMst getUser() {
        return user;
    }

    public void setUser(UserMst user) {
        this.user = user;
    }

    public ArrayList<Error> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<Error> errors) {
        this.errors = errors;
    }
}
