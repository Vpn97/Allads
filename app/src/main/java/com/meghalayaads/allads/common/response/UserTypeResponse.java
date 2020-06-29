package com.meghalayaads.allads.common.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.meghalayaads.allads.common.model.UserType;
import com.meghalayaads.allads.common.util.Error;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 27-06-2020.
 */
public class UserTypeResponse implements Parcelable {

    @SerializedName("status")
    private boolean status;

    @SerializedName("user_types")
    private ArrayList<UserType> userTypes;

    @SerializedName("errors")
    private ArrayList<Error> errors;


    public UserTypeResponse(boolean status, ArrayList<UserType> userTypes, ArrayList<Error> errors) {
        this.status = status;
        this.userTypes = userTypes;
        this.errors = errors;
    }

    public UserTypeResponse() {
    }

    protected UserTypeResponse(Parcel in) {
        status = in.readByte() != 0;
        userTypes = in.createTypedArrayList(UserType.CREATOR);
        errors = in.createTypedArrayList(Error.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeTypedList(userTypes);
        dest.writeTypedList(errors);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserTypeResponse> CREATOR = new Creator<UserTypeResponse>() {
        @Override
        public UserTypeResponse createFromParcel(Parcel in) {
            return new UserTypeResponse(in);
        }

        @Override
        public UserTypeResponse[] newArray(int size) {
            return new UserTypeResponse[size];
        }
    };


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<UserType> getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(ArrayList<UserType> userTypes) {
        this.userTypes = userTypes;
    }

    public ArrayList<Error> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<Error> errors) {
        this.errors = errors;
    }

    public static Creator<UserTypeResponse> getCREATOR() {
        return CREATOR;
    }
}
