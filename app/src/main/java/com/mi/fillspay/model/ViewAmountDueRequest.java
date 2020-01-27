package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewAmountDueRequest {

    @SerializedName("locationId")
    @Expose
    private String locationId;
    @SerializedName("pointOfSaleId")
    @Expose
    private String pointOfSaleId;
    @SerializedName("billerId")
    @Expose
    private String billerId;
    @SerializedName("input")
    @Expose
    private String input;
    @SerializedName("sku")
    @Expose
    private String sku;
    @SerializedName("entityTransactionID")
    @Expose
    private String entityTransactionID;

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getPointOfSaleId() {
        return pointOfSaleId;
    }

    public void setPointOfSaleId(String pointOfSaleId) {
        this.pointOfSaleId = pointOfSaleId;
    }

    public String getBillerId() {
        return billerId;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getEntityTransactionID() {
        return entityTransactionID;
    }

    public void setEntityTransactionID(String entityTransactionID) {
        this.entityTransactionID = entityTransactionID;
    }

}