package com.meghalayaads.allads.admin.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.meghalayaads.allads.admin.model.AdsPriceMst;
import com.meghalayaads.allads.common.util.Error;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 03-07-2020.
 */
public class AdsPriceMstResponse implements Parcelable {

    @SerializedName("status")
    private boolean status;

    @SerializedName("errors")
    private ArrayList<Error> errors;

    @SerializedName("ads_price_dtls")
    private ArrayList<AdsPriceMst> adsPriceMsts;


    public AdsPriceMstResponse(boolean status, ArrayList<Error> errors, ArrayList<AdsPriceMst> adsPriceMsts) {
        this.status = status;
        this.errors = errors;
        this.adsPriceMsts = adsPriceMsts;
    }


    public AdsPriceMstResponse() {
    }

    protected AdsPriceMstResponse(Parcel in) {
        status = in.readByte() != 0;
        errors = in.createTypedArrayList(Error.CREATOR);
        adsPriceMsts = in.createTypedArrayList(AdsPriceMst.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeTypedList(errors);
        dest.writeTypedList(adsPriceMsts);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AdsPriceMstResponse> CREATOR = new Creator<AdsPriceMstResponse>() {
        @Override
        public AdsPriceMstResponse createFromParcel(Parcel in) {
            return new AdsPriceMstResponse(in);
        }

        @Override
        public AdsPriceMstResponse[] newArray(int size) {
            return new AdsPriceMstResponse[size];
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

    public ArrayList<AdsPriceMst> getAdsPriceMsts() {
        return adsPriceMsts;
    }

    public void setAdsPriceMsts(ArrayList<com.meghalayaads.allads.admin.model.AdsPriceMst> adsPriceMsts) {
        this.adsPriceMsts = adsPriceMsts;
    }
}
