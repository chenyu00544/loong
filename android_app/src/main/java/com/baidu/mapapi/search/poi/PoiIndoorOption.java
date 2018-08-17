package com.baidu.mapapi.search.poi;

public class PoiIndoorOption {
    String a;
    String b;
    String c;
    int d = 0;
    int e = 10;

    public PoiIndoorOption poiCurrentPage(int i) {
        this.d = i;
        return this;
    }

    public PoiIndoorOption poiFloor(String str) {
        this.c = str;
        return this;
    }

    public PoiIndoorOption poiIndoorBid(String str) {
        this.a = str;
        return this;
    }

    public PoiIndoorOption poiIndoorWd(String str) {
        this.b = str;
        return this;
    }

    public PoiIndoorOption poiPageSize(int i) {
        this.e = i;
        return this;
    }
}
