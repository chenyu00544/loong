package com.baidu.mapapi.search.poi;

import com.baidu.mapapi.model.LatLng;

public class PoiCitySearchOption {
    String a = null;
    String b = null;
    float c = 12.0f;
    LatLng d = null;
    int e = 0;
    int f = 10;

    public PoiCitySearchOption city(String str) {
        this.a = str;
        return this;
    }

    public PoiCitySearchOption keyword(String str) {
        this.b = str;
        return this;
    }

    public PoiCitySearchOption pageCapacity(int i) {
        this.f = i;
        return this;
    }

    public PoiCitySearchOption pageNum(int i) {
        this.e = i;
        return this;
    }
}
