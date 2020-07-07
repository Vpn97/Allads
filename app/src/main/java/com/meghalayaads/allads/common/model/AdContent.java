package com.meghalayaads.allads.common.model;

/**
 * Allads
 * Created by Vishal Nagvadiya on 06-07-2020.
 */
public class AdContent {


    private Integer adId;
    private Integer uid;

    private String adText;

    private boolean  isImage;

    private AdImageDtl imageDtl;

    public AdContent(String adText, boolean isImage, AdImageDtl imageDtl) {
        this.adText = adText;
        this.isImage = isImage;
        this.imageDtl = imageDtl;
    }

    public AdContent(Integer adId, Integer uid, String adText, boolean isImage, AdImageDtl imageDtl) {
        this.adId = adId;
        this.uid = uid;
        this.adText = adText;
        this.isImage = isImage;
        this.imageDtl = imageDtl;
    }

    public AdContent() {
    }

    public String getAdText() {
        return adText;
    }

    public void setAdText(String adText) {
        this.adText = adText;
    }

    public boolean isImage() {
        return isImage;
    }

    public void setImage(boolean image) {
        isImage = image;
    }

    public AdImageDtl getImageDtl() {
        return imageDtl;
    }

    public void setImageDtl(AdImageDtl imageDtl) {
        this.imageDtl = imageDtl;
    }

    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
