package com.baidu.mapapi.search.poi;

import com.baidu.mapapi.model.LatLng;

public class PoiNearbySearchOption {
    String a = null;
    LatLng b = null;
    int c = -1;
    float d = 12.0f;
    int e = 0;
    int f = 10;
    PoiSortType g = PoiSortType.comprehensive;

    public PoiNearbySearchOption keyword(String str) {
        this.a = str;
        return this;
    }

    public PoiNearbySearchOption location(LatLng latLng) {
        this.b = latLng;
        return this;
    }

    public PoiNearbySearchOption pageCapacity(int i) {
        this.f = i;
        return this;
    }

    public PoiNearbySearchOption pageNum(int i) {
        this.e = i;
        return this;
    }

    public PoiNearbySearchOption radius(int i) {
        this.c = i;
        return this;
    }

    public PoiNearbySearchOption sortType(PoiSortType poiSortType) {
        if (poiSortType != null) {
            this.g = poiSortType;
        }
        return this;
    }
}
