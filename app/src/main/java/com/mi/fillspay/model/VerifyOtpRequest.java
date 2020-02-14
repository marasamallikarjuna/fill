package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyOtpRequest {

    @SerializedName("mobileNumber")
    @Expose
    private String mobileNumber;

    @SerializedName("requestId")
    @Expose
    private String requestId;

    @SerializedName("code")
    @Expose
    private String code;

    public VerifyOtpRequest(String mobileNumber, String requestId, String code) {
        this.mobileNumber = mobileNumber;
        this.requestId = requestId;
        this.code = code;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}