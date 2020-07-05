package com.meghalayaads.allads.common.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Allads
 * Created by Vishal Nagvadiya on 05-07-2020.
 */
public class SubCategory implements Parcelable {

    @SerializedName("sub_cat_id")
    private Integer subCatId;

    @SerializedName("sub_cate_name")
    private String subCategoryName;

    @SerializedName("description")
    private String description;

    public SubCategory(Integer subCatId, String subCategoryName, String description) {
        this.subCatId = subCatId;
        this.subCategoryName = subCategoryName;
        this.description = description;
    }


    public SubCategory() {
    }

    protected SubCategory(Parcel in) {
        if (in.readByte() == 0) {
            subCatId = null;
        } else {
            subCatId = in.readInt();
        }
        subCategoryName = in.readString();
        description = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (subCatId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(subCatId);
        }
        dest.writeString(subCategoryName);
        dest.writeString(description);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SubCategory> CREATOR = new Creator<SubCategory>() {
        @Override
        public SubCategory createFromParcel(Parcel in) {
            return new SubCategory(in);
        }

        @Override
        public SubCategory[] newArray(int size) {
            return new SubCategory[size];
        }
    };

    public Integer getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(Integer subCatId) {
        this.subCatId = subCatId;
    }

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
