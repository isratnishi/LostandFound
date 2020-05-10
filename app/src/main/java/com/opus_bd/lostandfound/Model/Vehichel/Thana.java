
package com.opus_bd.lostandfound.Model.Vehichel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Thana {

    @SerializedName("thanaCode")
    @Expose
    private Object thanaCode;
    @SerializedName("thanaName")
    @Expose
    private String thanaName;
    @SerializedName("shortName")
    @Expose
    private Object shortName;
    @SerializedName("thanaNameBn")
    @Expose
    private Object thanaNameBn;
    @SerializedName("districtId")
    @Expose
    private Integer districtId;
    @SerializedName("district")
    @Expose
    private District_ district;
    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("isDelete")
    @Expose
    private Object isDelete;
    @SerializedName("createdAt")
    @Expose
    private Object createdAt;
    @SerializedName("updatedAt")
    @Expose
    private Object updatedAt;
    @SerializedName("createdBy")
    @Expose
    private Object createdBy;
    @SerializedName("updatedBy")
    @Expose
    private Object updatedBy;

    public Object getThanaCode() {
        return thanaCode;
    }

    public void setThanaCode(Object thanaCode) {
        this.thanaCode = thanaCode;
    }

    public String getThanaName() {
        return thanaName;
    }

    public void setThanaName(String thanaName) {
        this.thanaName = thanaName;
    }

    public Object getShortName() {
        return shortName;
    }

    public void setShortName(Object shortName) {
        this.shortName = shortName;
    }

    public Object getThanaNameBn() {
        return thanaNameBn;
    }

    public void setThanaNameBn(Object thanaNameBn) {
        this.thanaNameBn = thanaNameBn;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public District_ getDistrict() {
        return district;
    }

    public void setDistrict(District_ district) {
        this.district = district;
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

    public Object getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Object createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Object createdBy) {
        this.createdBy = createdBy;
    }

    public Object getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Object updatedBy) {
        this.updatedBy = updatedBy;
    }

}
