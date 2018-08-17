package com.baidu.mapapi.search.geocode;

import com.baidu.mapapi.model.LatLng;

public class ReverseGeoCodeOption {
    LatLng a = null;

    public ReverseGeoCodeOption location(LatLng latLng) {
        this.a = latLng;
        return this;
    }
}
