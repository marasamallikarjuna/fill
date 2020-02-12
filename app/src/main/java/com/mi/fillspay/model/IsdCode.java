package com.mi.fillspay.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IsdCode {

    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("isdCode")
    @Expose
    private String isdCode;
    @SerializedName("flag")
    @Expose
    private String flag;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIsdCode() {
        return isdCode;
    }

    public void setIsdCode(String isdCode) {
        this.isdCode = isdCode;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

}