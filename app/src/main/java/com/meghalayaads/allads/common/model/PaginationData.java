package com.meghalayaads.allads.common.model;

import com.google.gson.annotations.SerializedName;

/**
 * Allads
 * Created by Vishal Nagvadiya on 05-07-2020.
 */
public class PaginationData {

    @SerializedName("page")
    private Integer page;

    @SerializedName("next_page")
    private Integer nextPage;

    @SerializedName("ads_per_page")
    private Integer adsPerPage;

    @SerializedName("uid")
    private Integer uid;

    @SerializedName("user_type_id")
    private Integer userTypeId;

    @SerializedName("mob_no")
    private Integer mobNo;

    @SerializedName("cat_id")
    private Integer catId;

    @SerializedName("sub_cat_id")
    private  Integer subCatId;

    @SerializedName("search_code")
    private String searchCode;

    @SerializedName("user_name")
    private String userName;

    @SerializedName("start_ad_id")
    private Integer startAdId;

    @SerializedName("end_ad_id")
    private  Integer endAdId;

    public PaginationData() {
    }


    public PaginationData(Integer page, Integer nextPage, Integer adsPerPage, Integer uid,
                          Integer userTypeId, Integer mobNo, Integer catId, Integer subCatId,
                          String searchCode, String userName, Integer startAdId, Integer endAdId) {
        this.page = page;
        this.nextPage = nextPage;
        this.adsPerPage = adsPerPage;
        this.uid = uid;
        this.userTypeId = userTypeId;
        this.mobNo = mobNo;
        this.catId = catId;
        this.subCatId = subCatId;
        this.searchCode = searchCode;
        this.userName = userName;
        this.startAdId = startAdId;
        this.endAdId = endAdId;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getAdsPerPage() {
        return adsPerPage;
    }

    public void setAdsPerPage(Integer adsPerPage) {
        this.adsPerPage = adsPerPage;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Integer getMobNo() {
        return mobNo;
    }

    public void setMobNo(Integer mobNo) {
        this.mobNo = mobNo;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public Integer getSubCatId() {
        return subCatId;
    }

    public void setSubCatId(Integer subCatId) {
        this.subCatId = subCatId;
    }

    public String getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStartAdId() {
        return startAdId;
    }

    public void setStartAdId(Integer startAdId) {
        this.startAdId = startAdId;
    }

    public Integer getEndAdId() {
        return endAdId;
    }

    public void setEndAdId(Integer endAdId) {
        this.endAdId = endAdId;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }
}
