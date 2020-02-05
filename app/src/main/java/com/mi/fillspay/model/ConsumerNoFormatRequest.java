package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumerNoFormatRequest {

    @SerializedName("locationID")
    @Expose
    private String locationID;
    @SerializedName("pointOfSaleID")
    @Expose
    private String pointOfSaleID;
    @SerializedName("billerID")
    @Expose
    private String billerID;

    public ConsumerNoFormatRequest(String locationID, String pointOfSaleID, String billerID) {
        this.locationID = locationID;
        this.pointOfSaleID = pointOfSaleID;
        this.billerID = billerID;
    }

    public String getLocationID() {
        return locationID;
    }

    public void setLocationID(String locationID) {
        this.locationID = locationID;
    }

    public String getPointOfSaleID() {
        return pointOfSaleID;
    }

    public void setPointOfSaleID(String pointOfSaleID) {
        this.pointOfSaleID = pointOfSaleID;
    }

    public String getBillerID() {
        return billerID;
    }

    public void setBillerID(String billerID) {
        this.billerID = billerID;
    }
}
