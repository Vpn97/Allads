package com.meghalayaads.allads.admin.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.meghalayaads.allads.admin.model.AdsPriceMst;
import com.meghalayaads.allads.common.util.Error;

import java.util.ArrayList;

/**
 * Allads
 * Created by Vishal Nagvadiya on 04-07-2020.
 */
public class AdsPriceUpdateResponse implements Parcelable {


    @SerializedName("status")
    private boolean status;

    @SerializedName("errors")
    private ArrayList<Error> errors;

    @SerializedName("ads_price_mst")
    private AdsPriceMst adsPriceMst;


    public AdsPriceUpdateResponse(boolean status, ArrayList<Error> errors, AdsPriceMst adsPriceMst) {
        this.status = status;
        this.errors = errors;
        this.adsPriceMst = adsPriceMst;
    }

    public AdsPriceUpdateResponse() {
    }

    protected AdsPriceUpdateResponse(Parcel in) {
        status = in.readByte() != 0;
        errors = in.createTypedArrayList(Error.CREATOR);
        adsPriceMst = in.readParcelable(AdsPriceMst.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeTypedList(errors);
        dest.writeParcelable(adsPriceMst, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AdsPriceUpdateResponse> CREATOR = new Creator<AdsPriceUpdateResponse>() {
        @Override
        public AdsPriceUpdateResponse createFromParcel(Parcel in) {
            return new AdsPriceUpdateResponse(in);
        }

        @Override
        public AdsPriceUpdateResponse[] newArray(int size) {
            return new AdsPriceUpdateResponse[size];
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

    public AdsPriceMst getAdsPriceMst() {
        return adsPriceMst;
    }

    public void setAdsPriceMst(AdsPriceMst adsPriceMst) {
        this.adsPriceMst = adsPriceMst;
    }
}
