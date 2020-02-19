package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UtilityResponse {

    @SerializedName("billerDescription")
    @Expose
    private String utilityname;
    @SerializedName("activeLogo")
    @Expose
    private String activeLogo;
    @SerializedName("inActiveLogo")
    @Expose
    private String inActiveLogo;

    public String getUtilityname() {
        return utilityname;
    }

    public void setUtilityname(String utilityname) {
        this.utilityname = utilityname;
    }

    public String getActiveLogo() {
        return activeLogo;
    }

    public void setActiveLogo(String activeLogo) {
        this.activeLogo = activeLogo;
    }

    public String getInActiveLogo() {
        return inActiveLogo;
    }

    public void setInActiveLogo(String inActiveLogo) {
        this.inActiveLogo = inActiveLogo;
    }

}