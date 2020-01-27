package com.mi.fillspay.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListOfIOCatalog {

    @SerializedName("billerID")
    @Expose
    private String billerID;
    @SerializedName("s_k_u")
    @Expose
    private String sKU;
    @SerializedName("i_o_i_d")
    @Expose
    private Integer iOID;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("operation")
    @Expose
    private Integer operation;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("datatype")
    @Expose
    private String datatype;
    @SerializedName("minLength")
    @Expose
    private Integer minLength;
    @SerializedName("maxLength")
    @Expose
    private Integer maxLength;
    @SerializedName("validLengths")
    @Expose
    private String validLengths;
    @SerializedName("catalogVersion")
    @Expose
    private String catalogVersion;

    public String getBillerID() {
        return billerID;
    }

    public void setBillerID(String billerID) {
        this.billerID = billerID;
    }

    public String getSKU() {
        return sKU;
    }

    public void setSKU(String sKU) {
        this.sKU = sKU;
    }

    public Integer getIOID() {
        return iOID;
    }

    public void setIOID(Integer iOID) {
        this.iOID = iOID;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public Integer getMinLength() {
        return minLength;
    }

    public void setMinLength(Integer minLength) {
        this.minLength = minLength;
    }

    public Integer getMaxLength() {
        return maxLength;
    }

    public void setMaxLength(Integer maxLength) {
        this.maxLength = maxLength;
    }

    public String getValidLengths() {
        return validLengths;
    }

    public void setValidLengths(String validLengths) {
        this.validLengths = validLengths;
    }

    public String getCatalogVersion() {
        return catalogVersion;
    }

    public void setCatalogVersion(String catalogVersion) {
        this.catalogVersion = catalogVersion;
    }
}
