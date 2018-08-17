package com.baidu.mapapi.search.district;

public class DistrictSearchOption {
    String a;
    String b;

    public DistrictSearchOption cityName(String str) {
        this.a = str;
        return this;
    }

    public DistrictSearchOption districtName(String str) {
        this.b = str;
        return this;
    }
}
