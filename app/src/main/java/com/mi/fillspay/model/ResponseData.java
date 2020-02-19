package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("timeStamp")
    @Expose
    private String timeStamp;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("path")
    @Expose
    private String path;

    @SerializedName("details")
    @Expose
    private String[] details;

    @SerializedName("error")
    @Expose
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }



    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String[] getDetails() {
        return details;
    }

    public void setDetails(String[] details) {
        this.details = details;
    }

}