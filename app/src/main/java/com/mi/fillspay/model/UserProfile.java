package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfile {

    @SerializedName("fillsPayUserId")
    @Expose
    private Integer fillsPayUserId;
    @SerializedName("fillsPayCustomerId")
    @Expose
    private String fillsPayCustomerId;
    @SerializedName("firstName")
    @Expose
    private Object firstName;
    @SerializedName("lastName")
    @Expose
    private Object lastName;
    @SerializedName("contactNumber")
    @Expose
    private String contactNumber;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("password")
    @Expose
    private Object password;
    @SerializedName("emailNotifications")
    @Expose
    private Boolean emailNotifications;
    @SerializedName("smsNotifications")
    @Expose
    private Boolean smsNotifications;
    @SerializedName("appNotifications")
    @Expose
    private Boolean appNotifications;
    @SerializedName("role")
    @Expose
    private Object role;
    @SerializedName("accountStatus")
    @Expose
    private Integer accountStatus;
    @SerializedName("profilePic")
    @Expose
    private String profilePic;

    public Integer getFillsPayUserId() {
        return fillsPayUserId;
    }

    public void setFillsPayUserId(Integer fillsPayUserId) {
        this.fillsPayUserId = fillsPayUserId;
    }

    public String getFillsPayCustomerId() {
        return fillsPayCustomerId;
    }

    public void setFillsPayCustomerId(String fillsPayCustomerId) {
        this.fillsPayCustomerId = fillsPayCustomerId;
    }

    public Object getFirstName() {
        return firstName;
    }

    public void setFirstName(Object firstName) {
        this.firstName = firstName;
    }

    public Object getLastName() {
        return lastName;
    }

    public void setLastName(Object lastName) {
        this.lastName = lastName;
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

    public Object getPassword() {
        return password;
    }

    public void setPassword(Object password) {
        this.password = password;
    }

    public Boolean getEmailNotifications() {
        return emailNotifications;
    }

    public void setEmailNotifications(Boolean emailNotifications) {
        this.emailNotifications = emailNotifications;
    }

    public Boolean getSmsNotifications() {
        return smsNotifications;
    }

    public void setSmsNotifications(Boolean smsNotifications) {
        this.smsNotifications = smsNotifications;
    }

    public Boolean getAppNotifications() {
        return appNotifications;
    }

    public void setAppNotifications(Boolean appNotifications) {
        this.appNotifications = appNotifications;
    }

    public Object getRole() {
        return role;
    }

    public void setRole(Object role) {
        this.role = role;
    }

    public Integer getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}