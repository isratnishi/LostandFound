package com.opus_bd.lostandfound.Model.Documentaion;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MetroAreaModel {

    @SerializedName("areaName")
    @Expose
    private String areaName;
    @SerializedName("areaNameBn")
    @Expose
    private Object areaNameBn;
    @SerializedName("districtId")
    @Expose
    private Object districtId;
    @SerializedName("shortOrder")
    @Expose
    private Object shortOrder;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("isDelete")
    @Expose
    private Object isDelete;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private Object updatedAt;
    @SerializedName("createdBy")
    @Expose
    private String createdBy;
    @SerializedName("updatedBy")
    @Expose
    private Object updatedBy;

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Object getAreaNameBn() {
        return areaNameBn;
    }

    public void setAreaNameBn(Object areaNameBn) {
        this.areaNameBn = areaNameBn;
    }

    public Object getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Object districtId) {
        this.districtId = districtId;
    }

    public Object getShortOrder() {
        return shortOrder;
    }

    public void setShortOrder(Object shortOrder) {
        this.shortOrder = shortOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Object getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Object isDelete) {
        this.isDelete = isDelete;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }}