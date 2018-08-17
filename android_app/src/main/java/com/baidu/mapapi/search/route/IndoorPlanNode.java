package com.baidu.mapapi.search.route;

import com.baidu.mapapi.model.LatLng;

public class IndoorPlanNode {
    private LatLng a = null;
    private String b = null;

    public IndoorPlanNode(LatLng latLng, String str) {
        this.a = latLng;
        this.b = str;
    }

    LatLng a() {
        return this.a;
    }

    String b() {
        return this.b;
    }
}
