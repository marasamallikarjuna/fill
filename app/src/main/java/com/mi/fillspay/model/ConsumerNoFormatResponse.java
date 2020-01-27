package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConsumerNoFormatResponse {

    @SerializedName("inquiry")
    @Expose
    private Integer inquiry;
    @SerializedName("pastDue")
    @Expose
    private Integer pastDue;
    @SerializedName("excess")
    @Expose
    private Integer excess;
    @SerializedName("partial")
    @Expose
    private Integer partial;
    @SerializedName("listOfIOCatalog")
    @Expose
    private List<ListOfIOCatalog> listOfIOCatalog = null;

    public Integer getInquiry() {
        return inquiry;
    }

    public void setInquiry(Integer inquiry) {
        this.inquiry = inquiry;
    }

    public Integer getPastDue() {
        return pastDue;
    }

    public void setPastDue(Integer pastDue) {
        this.pastDue = pastDue;
    }

    public Integer getExcess() {
        return excess;
    }

    public void setExcess(Integer excess) {
        this.excess = excess;
    }

    public Integer getPartial() {
        return partial;
    }

    public void setPartial(Integer partial) {
        this.partial = partial;
    }

    public List<ListOfIOCatalog> getListOfIOCatalog() {
        return listOfIOCatalog;
    }

    public void setListOfIOCatalog(List<ListOfIOCatalog> listOfIOCatalog) {
        this.listOfIOCatalog = listOfIOCatalog;
    }
}
