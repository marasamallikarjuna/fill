package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProcessPaymentResponse {

    @SerializedName("responseDateTime")
    @Expose
    private String responseDateTime;
    @SerializedName("confirmationNumber")
    @Expose
    private String confirmationNumber;
    @SerializedName("ticketCaption")
    @Expose
    private String ticketCaption;
    @SerializedName("payKiiTransactionID")
    @Expose
    private String payKiiTransactionID;
    @SerializedName("entityTransactionID")
    @Expose
    private String entityTransactionID;
    @SerializedName("settlementCurrency")
    @Expose
    private String settlementCurrency;
    @SerializedName("baseCurrency")
    @Expose
    private String baseCurrency;
    @SerializedName("fXRate")
    @Expose
    private String fXRate;
    @SerializedName("transactionStatus")
    @Expose
    private Integer transactionStatus;

    public String getResponseDateTime() {
        return responseDateTime;
    }

    public void setResponseDateTime(String responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    public String getConfirmationNumber() {
        return confirmationNumber;
    }

    public void setConfirmationNumber(String confirmationNumber) {
        this.confirmationNumber = confirmationNumber;
    }

    public String getTicketCaption() {
        return ticketCaption;
    }

    public void setTicketCaption(String ticketCaption) {
        this.ticketCaption = ticketCaption;
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

    public String getFXRate() {
        return fXRate;
    }

    public void setFXRate(String fXRate) {
        this.fXRate = fXRate;
    }

    public Integer getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(Integer transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

}