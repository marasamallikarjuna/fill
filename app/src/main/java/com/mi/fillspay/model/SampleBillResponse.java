package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SampleBillResponse {

    public Object getFilename() {
        return filename;
    }

    public void setFilename(Object filename) {
        this.filename = filename;
    }

    public String getPicBytes() {
        return picBytes;
    }

    public void setPicBytes(String picBytes) {
        this.picBytes = picBytes;
    }

    @SerializedName("fileName")
    @Expose
    Object filename;

    @SerializedName("picBytes")
    @Expose
    String picBytes;
}
