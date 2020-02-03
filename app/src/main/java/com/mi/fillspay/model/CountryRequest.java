package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryRequest {

    public CountryRequest(String locationID, String pointOfSaleID, String utilityName) {
        this.locationID = locationID;
        this.pointOfSaleID = pointOfSaleID;
        this.utilityName = utilityName;
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

    public String getUtilityName() {
        return utilityName;
    }

    public void setUtilityName(String utilityName) {
        this.utilityName = utilityName;
    }

    @SerializedName("locationID")
    @Expose
    private String locationID;

    @SerializedName("pointOfSaleID")
    @Expose
    private String pointOfSaleID;

    @SerializedName("utility")
    @Expose
    private String utilityName;

}
