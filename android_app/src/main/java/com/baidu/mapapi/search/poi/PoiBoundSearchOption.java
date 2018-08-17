package com.baidu.mapapi.search.poi;

import com.baidu.mapapi.model.LatLngBounds;

public class PoiBoundSearchOption {
    LatLngBounds a = null;
    String b = null;
    float c = 12.0f;
    int d = 0;
    int e = 10;

    public PoiBoundSearchOption bound(LatLngBounds latLngBounds) {
        this.a = latLngBounds;
        return this;
    }

    public PoiBoundSearchOption keyword(String str) {
        this.b = str;
        return this;
    }

    public PoiBoundSearchOption pageCapacity(int i) {
        this.e = i;
        return this;
    }

    public PoiBoundSearchOption pageNum(int i) {
        this.d = i;
        return this;
    }
}
