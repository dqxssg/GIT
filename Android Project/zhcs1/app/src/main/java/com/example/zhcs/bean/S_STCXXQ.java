package com.example.zhcs.bean;

import com.google.gson.annotations.SerializedName;

public class S_STCXXQ {
    @SerializedName("searchValue")
    private Object searchValue;
    @SerializedName("createBy")
    private Object createBy;
    @SerializedName("createTime")
    private String createTime;
    @SerializedName("updateBy")
    private Object updateBy;
    @SerializedName("updateTime")
    private String updateTime;
    @SerializedName("remark")
    private Object remark;
    @SerializedName("params")
    private ParamsDTO params;
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("seq")
    private Integer seq;
    @SerializedName("lineId")
    private Integer lineId;
    @SerializedName("firstCh")
    private String firstCh;

    public Object getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(Object searchValue) {
        this.searchValue = searchValue;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Object getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Object updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public ParamsDTO getParams() {
        return params;
    }

    public void setParams(ParamsDTO params) {
        this.params = params;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getLineId() {
        return lineId;
    }

    public void setLineId(Integer lineId) {
        this.lineId = lineId;
    }

    public String getFirstCh() {
        return firstCh;
    }

    public void setFirstCh(String firstCh) {
        this.firstCh = firstCh;
    }

    public static class ParamsDTO {
    }
}
