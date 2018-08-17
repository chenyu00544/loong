package com.baidu.mapapi.search.sug;

import com.baidu.mapapi.model.LatLng;

public class SuggestionSearchOption {
    String a = null;
    String b = null;
    LatLng c = null;

    public SuggestionSearchOption city(String str) {
        this.a = str;
        return this;
    }

    public SuggestionSearchOption keyword(String str) {
        this.b = str;
        return this;
    }

    public SuggestionSearchOption location(LatLng latLng) {
        this.c = latLng;
        return this;
    }
}
