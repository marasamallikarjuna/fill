package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegisterRequest {

    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("password")
    @Expose
    private String password;

    public RegisterRequest(String contactNumber, String emailId, String password) {
        this.contactNumber = contactNumber;
        this.emailId = emailId;
        this.password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}