package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryPojo {

    @SerializedName("country")
    @Expose
    private String country;

    @SerializedName("countryCode")
    @Expose
    private String countryCode;

    @SerializedName("logo")
    @Expose
    private String logo;

    @SerializedName("isdCode")
    @Expose
    private String isd_code;

    public String getIsd_code() { return isd_code; }

    public void setIsd_code(String isd_code) { this.isd_code = isd_code; }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryCode() { return countryCode; }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}