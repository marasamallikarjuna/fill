package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UtilityResponse {

    @SerializedName("utilityname")
    @Expose
    private String utilityname;
    @SerializedName("image")
    @Expose
    private String image;

    public String getUtilityname() {
        return utilityname;
    }

    public void setUtilityname(String utilityname) {
        this.utilityname = utilityname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}