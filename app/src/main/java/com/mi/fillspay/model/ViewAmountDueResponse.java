package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ViewAmountDueResponse {

    @SerializedName("responseDateTime")
    @Expose
    private String responseDateTime;
    @SerializedName("payKiiTransactionID")
    @Expose
    private String payKiiTransactionID;
    @SerializedName("entityTransactionID")
    @Expose
    private String entityTransactionID;
    @SerializedName("billAmountDue")
    @Expose
    private Float billAmountDue;
    @SerializedName("billDueDate")
    @Expose
    private String billDueDate;
    @SerializedName("billsDue")
    @Expose
    private Integer billsDue;
    @SerializedName("totalAmountDue")
    @Expose
    private Float totalAmountDue;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("output1")
    @Expose
    private String output1;
    @SerializedName("output2")
    @Expose
    private String output2;
    @SerializedName("settlementCurrency")
    @Expose
    private String settlementCurrency;
    @SerializedName("baseCurrency")
    @Expose
    private String baseCurrency;
    @SerializedName("indicativeFXRate")
    @Expose
    private Float indicativeFXRate;
    @SerializedName("fillPayCharge")
    @Expose
    private Float fillPayCharge;

    public String getResponseDateTime() {
        return responseDateTime;
    }

    public void setResponseDateTime(String responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    public String getPayKiiTransactionID() {
        return payKiiTransactionID;
    }

    public void setPayKiiTransactionID(String payKiiTransactionID) {
        this.payKiiTransactionID = payKiiTransactionID;
    }

    public String getEntityTransactionID() {
        return entityTransactionID;
    }

    public void setEntityTransactionID(String entityTransactionID) {
        this.entityTransactionID = entityTransactionID;
    }

    public Float getBillAmountDue() {
        return billAmountDue;
    }

    public void setBillAmountDue(Float billAmountDue) {
        this.billAmountDue = billAmountDue;
    }

    public String getBillDueDate() {
        return billDueDate;
    }

    public void setBillDueDate(String billDueDate) {
        this.billDueDate = billDueDate;
    }

    public Integer getBillsDue() {
        return billsDue;
    }

    public void setBillsDue(Integer billsDue) {
        this.billsDue = billsDue;
    }

    public Float getTotalAmountDue() {
        return totalAmountDue;
    }

    public void setTotalAmountDue(Float totalAmountDue) {
        this.totalAmountDue = totalAmountDue;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getOutput1() {
        return output1;
    }

    public void setOutput1(String output1) {
        this.output1 = output1;
    }

    public String getOutput2() {
        return output2;
    }

    public void setOutput2(String output2) {
        this.output2 = output2;
    }

    public String getSettlementCurrency() {
        return settlementCurrency;
    }

    public void setSettlementCurrency(String settlementCurrency) {
        this.settlementCurrency = settlementCurrency;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency;
    }

    public Float getIndicativeFXRate() {
        return indicativeFXRate;
    }

    public void setIndicativeFXRate(Float indicativeFXRate) {
        this.indicativeFXRate = indicativeFXRate;
    }

    public Float getFillPayCharge() {
        return fillPayCharge;
    }

    public void setFillPayCharge(Float fillPayCharge) {
        this.fillPayCharge = fillPayCharge;
    }

}