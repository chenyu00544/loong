package com.baidu.mapapi.search.share;

import com.baidu.mapapi.model.LatLng;

public class LocationShareURLOption {
    LatLng a = null;
    String b = null;
    String c = null;

    public LocationShareURLOption location(LatLng latLng) {
        this.a = latLng;
        return this;
    }

    public LocationShareURLOption name(String str) {
        this.b = str;
        return this;
    }

    public LocationShareURLOption snippet(String str) {
        this.c = str;
        return this;
    }
}
