package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProcessPaymentRequest {

    @SerializedName("entityTransactionId")
    @Expose
    private String entityTransactionId;
    @SerializedName("amount")
    @Expose
    private String amount;

    public String getEntityTransactionId() {
        return entityTransactionId;
    }

    public void setEntityTransactionId(String entityTransactionId) {
        this.entityTransactionId = entityTransactionId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

}