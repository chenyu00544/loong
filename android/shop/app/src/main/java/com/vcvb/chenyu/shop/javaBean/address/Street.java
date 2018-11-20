package com.vcvb.chenyu.shop.javaBean.address;

import org.json.JSONObject;

import java.util.List;

public class Street {
    private Integer region_id;
    private Integer parent_id;
    private Integer region_type;
    private Integer agency_id;
    private String region_name;
    private List<Province> provinces;

    public Integer getRegion_id() {
        return region_id;
    }

    public void setRegion_id(Integer region_id) {
        this.region_id = region_id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getRegion_type() {
        return region_type;
    }

    public void setRegion_type(Integer region_type) {
        this.region_type = region_type;
    }

    public Integer getAgency_id() {
        return agency_id;
    }

    public void setAgency_id(Integer agency_id) {
        this.agency_id = agency_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }
}