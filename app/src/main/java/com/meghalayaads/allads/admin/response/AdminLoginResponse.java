package com.meghalayaads.allads.admin.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.meghalayaads.allads.admin.model.AdminMst;
import com.meghalayaads.allads.common.util.Error;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 02-07-2020.
 */
public class AdminLoginResponse implements Parcelable {

    @SerializedName("status")
    private boolean status;

    @SerializedName("errors")
    private ArrayList<Error> errors;

    @SerializedName("admin")
    private AdminMst adminMst;

    public AdminLoginResponse(boolean status, ArrayList<Error> errors, AdminMst adminMst) {
        this.status = status;
        this.errors = errors;
        this.adminMst = adminMst;
    }

    public AdminLoginResponse() {
    }


    protected AdminLoginResponse(Parcel in) {
        status = in.readByte() != 0;
        errors = in.createTypedArrayList(Error.CREATOR);
        adminMst = in.readParcelable(AdminMst.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeTypedList(errors);
        dest.writeParcelable(adminMst, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AdminLoginResponse> CREATOR = new Creator<AdminLoginResponse>() {
        @Override
        public AdminLoginResponse createFromParcel(Parcel in) {
            return new AdminLoginResponse(in);
        }

        @Override
        public AdminLoginResponse[] newArray(int size) {
            return new AdminLoginResponse[size];
        }
    };

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ArrayList<Error> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<Error> errors) {
        this.errors = errors;
    }

    public AdminMst getAdminMst() {
        return adminMst;
    }

    public void setAdminMst(AdminMst adminMst) {
        this.adminMst = adminMst;
    }
}
