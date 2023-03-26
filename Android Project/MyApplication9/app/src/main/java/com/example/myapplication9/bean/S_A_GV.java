package com.example.myapplication9.bean;

public class S_A_GV {
    private Integer serviceid;
    private String serviceName;
    private String icon;
    private String url;
    private String serviceType;
    private String desc;

    public Integer getServiceid() {
        return serviceid;
    }

    public void setServiceid(Integer serviceid) {
        this.serviceid = serviceid;
    }

    public String getServiceName() {
        return serviceName;
    }

    public S_A_GV setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return null;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
