package com.mi.fillspay.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BillerDescResponse implements Parcelable {

    @SerializedName("billerType")
    @Expose
    private String billerType;

    @SerializedName("countryName")
    @Expose
    private String countryName;

    @SerializedName("catalogVersion")
    @Expose
    private String catalogVersion;

    @SerializedName("countryCode")
    @Expose
    private String countryCode;

    @SerializedName("billerDescription")
    @Expose
    private String billerDescription;

    @SerializedName("billerName")
    @Expose
    private String billerName;

    @SerializedName("billerID")
    @Expose
    private String billerID;

    protected BillerDescResponse(Parcel in) {
        billerType = in.readString();
        countryName = in.readString();
        catalogVersion = in.readString();
        countryCode = in.readString();
        billerDescription = in.readString();
        billerName = in.readString();
        billerID = in.readString();
    }

    public static final Creator<BillerDescResponse> CREATOR = new Creator<BillerDescResponse>() {
        @Override
        public BillerDescResponse createFromParcel(Parcel in) {
            return new BillerDescResponse(in);
        }

        @Override
        public BillerDescResponse[] newArray(int size) {
            return new BillerDescResponse[size];
        }
    };

    public String getBillerType() {
        return billerType;
    }

    public void setBillerType(String billerType) {
        this.billerType = billerType;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCatalogVersion() {
        return catalogVersion;
    }

    public void setCatalogVersion(String catalogVersion) {
        this.catalogVersion = catalogVersion;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getBillerDescription() {
        return billerDescription;
    }

    public void setBillerDescription(String billerDescription) {
        this.billerDescription = billerDescription;
    }

    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getBillerID() {
        return billerID;
    }

    public void setBillerID(String billerID) {
        this.billerID = billerID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(billerType);
        dest.writeString(countryName);
        dest.writeString(catalogVersion);
        dest.writeString(countryCode);
        dest.writeString(billerDescription);
        dest.writeString(billerName);
        dest.writeString(billerID);
    }
}