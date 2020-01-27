package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubutilityRequest {

    @SerializedName("locationID")
    @Expose
    private String locationID;
    @SerializedName("pointOfSaleID")
    @Expose
    private String pointOfSaleID;
    @SerializedName("utility")
    @Expose
    private String utility;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;

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

    public String getUtility() {
        return utility;
    }

    public void setUtility(String utility) {
        this.utility = utility;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
