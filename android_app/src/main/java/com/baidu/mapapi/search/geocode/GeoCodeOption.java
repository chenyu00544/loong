package com.baidu.mapapi.search.geocode;

public class GeoCodeOption {
    String a = null;
    String b = null;

    public GeoCodeOption address(String str) {
        this.b = str;
        return this;
    }

    public GeoCodeOption city(String str) {
        this.a = str;
        return this;
    }
}
