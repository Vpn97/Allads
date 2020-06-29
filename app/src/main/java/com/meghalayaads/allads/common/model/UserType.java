package com.meghalayaads.allads.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

/**
 * Allads
 * Created by Vishal Nagvadiya on 27-06-2020.
 */
public class UserType implements Parcelable {

    @SerializedName("user_type_id")
    private int userTypeId;

    @SerializedName("type_name")
    private String typeName;

    @SerializedName("description")
    private String description;

    @SerializedName("type_code")
    private String typeCode;

    @SerializedName("created_date")
    private Date createdDate;


    public UserType(int userTypeId, String typeName, String description, String typeCode, Date createdDate) {
        this.userTypeId = userTypeId;
        this.typeName = typeName;
        this.description = description;
        this.typeCode = typeCode;
        this.createdDate = createdDate;
    }


    public UserType() {
    }

    protected UserType(Parcel in) {
        userTypeId = in.readInt();
        typeName = in.readString();
        description = in.readString();
        typeCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userTypeId);
        dest.writeString(typeName);
        dest.writeString(description);
        dest.writeString(typeCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<UserType> CREATOR = new Creator<UserType>() {
        @Override
        public UserType createFromParcel(Parcel in) {
            return new UserType(in);
        }

        @Override
        public UserType[] newArray(int size) {
            return new UserType[size];
        }
    };

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserType)) return false;
        UserType userType = (UserType) o;
        return userTypeId == userType.userTypeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userTypeId, typeName, description, typeCode, createdDate);
    }
}
