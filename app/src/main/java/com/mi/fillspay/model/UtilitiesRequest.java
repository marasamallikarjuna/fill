package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UtilitiesRequest {
    @SerializedName("locationID")
    @Expose
    private String locationID;

    @SerializedName("pointOfSaleID")
    @Expose
    private String pointOfSaleID;

    @SerializedName("countryCode")
    @Expose
    private String countryCode;

    public UtilitiesRequest(String locationID, String pointOfSaleID, String countryCode) {
        this.locationID = locationID;
        this.pointOfSaleID = pointOfSaleID;
        this.countryCode = countryCode;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
