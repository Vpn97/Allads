package com.meghalayaads.allads.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Allads
 * Created by Vishal Nagvadiya on 05-07-2020.
 */
public class Category implements Parcelable {

    @SerializedName("cat_id")
    private Integer catId;

    @SerializedName("cat_name")
    private String categoryName;

    @SerializedName("user_type_id")
    private Integer userTypeId;

    @SerializedName("description")
    private String description;


    public Category(Integer catId, String categoryName, Integer userTypeId, String description) {
        this.catId = catId;
        this.categoryName = categoryName;
        this.userTypeId = userTypeId;
        this.description = description;
    }

    public Category() {
    }

    protected Category(Parcel in) {
        if (in.readByte() == 0) {
            catId = null;
        } else {
            catId = in.readInt();
        }
        categoryName = in.readString();
        if (in.readByte() == 0) {
            userTypeId = null;
        } else {
            userTypeId = in.readInt();
        }
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (catId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(catId);
        }
        dest.writeString(categoryName);
        if (userTypeId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userTypeId);
        }
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
