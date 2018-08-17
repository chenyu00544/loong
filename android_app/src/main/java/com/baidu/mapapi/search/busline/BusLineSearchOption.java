package com.baidu.mapapi.search.busline;

public class BusLineSearchOption {
    String a = null;
    String b = null;

    public BusLineSearchOption city(String str) {
        this.b = str;
        return this;
    }

    public BusLineSearchOption uid(String str) {
        this.a = str;
        return this;
    }
}
