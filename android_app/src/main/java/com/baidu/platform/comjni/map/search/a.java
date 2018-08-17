package com.baidu.platform.comjni.map.search;

import android.os.Bundle;

public class a {
    private static final String a = a.class.getSimpleName();
    private long b;
    private JNISearch c;

    public a() {
        this.b = 0;
        this.c = null;
        this.c = new JNISearch();
    }

    public long a() {
        this.b = this.c.Create();
        return this.b;
    }

    public String a(int i) {
        return this.c.GetSearchResult(this.b, i);
    }

    public boolean a(int i, int i2) {
        return this.c.ReverseGeocodeSearch(this.b, i, i2);
    }

    public boolean a(int i, int i2, String str, String str2) {
        return this.c.PoiRGCShareUrlSearch(this.b, i, i2, str, str2);
    }

    public boolean a(Bundle bundle) {
        return this.c.ForceSearchByCityName(this.b, bundle);
    }

    public boolean a(String str) {
        return this.c.POIDetailSearchPlace(this.b, str);
    }

    public boolean a(String str, String str2) {
        return this.c.BusLineDetailSearch(this.b, str, str2);
    }

    public int b() {
        return this.c.Release(this.b);
    }

    public boolean b(Bundle bundle) {
        return this.c.AreaSearch(this.b, bundle);
    }

    public boolean b(String str) {
        return this.c.PoiDetailShareUrlSearch(this.b, str);
    }

    public boolean b(String str, String str2) {
        return this.c.geocode(this.b, str, str2);
    }

    public boolean c(Bundle bundle) {
        return this.c.indoorSearch(this.b, bundle);
    }

    public boolean c(String str, String str2) {
        return this.c.districtSearch(this.b, str, str2);
    }

    public boolean d(Bundle bundle) {
        return this.c.RoutePlanByBus(this.b, bundle);
    }

    public boolean e(Bundle bundle) {
        return this.c.RoutePlanByTransit(this.b, bundle);
    }

    public boolean f(Bundle bundle) {
        return this.c.RoutePlanByCar(this.b, bundle);
    }

    public boolean g(Bundle bundle) {
        return this.c.RoutePlanByFoot(this.b, bundle);
    }

    public boolean h(Bundle bundle) {
        return this.c.routePlanByBike(this.b, bundle);
    }

    public boolean i(Bundle bundle) {
        return this.c.routePlanIndoor(this.b, bundle);
    }

    public boolean j(Bundle bundle) {
        return this.c.SuggestionSearch(this.b, bundle);
    }

    public boolean k(Bundle bundle) {
        return this.c.routeShareUrlSearch(this.b, bundle);
    }

    public boolean l(Bundle bundle) {
        return this.c.MapBoundSearch(this.b, bundle);
    }
}
