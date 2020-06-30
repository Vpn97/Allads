package com.meghalayaads.allads.user.response.registration;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Allads
 * Created by Vishal Nagvadiya on 30-06-2020.
 */
public class StatusResponse  implements Parcelable {

    @SerializedName("status")
    private boolean status;

    public StatusResponse(boolean status) {
        this.status = status;
    }

    public StatusResponse() {
    }

    protected StatusResponse(Parcel in) {
        status = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (status ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<StatusResponse> CREATOR = new Creator<StatusResponse>() {
        @Override
        public StatusResponse createFromParcel(Parcel in) {
            return new StatusResponse(in);
        }

        @Override
        public StatusResponse[] newArray(int size) {
            return new StatusResponse[size];
        }
    };

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
