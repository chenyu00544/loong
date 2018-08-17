package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import java.util.List;

public class SuggestAddrInfo implements Parcelable {
    public static final Creator<SuggestAddrInfo> CREATOR = new o();
    private List<PoiInfo> a;
    private List<PoiInfo> b;
    private List<List<PoiInfo>> c;
    private List<CityInfo> d;
    private List<CityInfo> e;
    private List<List<CityInfo>> f;

    protected SuggestAddrInfo() {
    }

    SuggestAddrInfo(Parcel parcel) {
        this.a = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.b = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.c = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.d = parcel.readArrayList(CityInfo.class.getClassLoader());
        this.e = parcel.readArrayList(CityInfo.class.getClassLoader());
        this.f = parcel.readArrayList(CityInfo.class.getClassLoader());
    }

    void a(List<PoiInfo> list) {
        this.a = list;
    }

    void b(List<PoiInfo> list) {
        this.b = list;
    }

    void c(List<List<PoiInfo>> list) {
        this.c = list;
    }

    void d(List<CityInfo> list) {
        this.d = list;
    }

    public int describeContents() {
        return 0;
    }

    void e(List<CityInfo> list) {
        this.e = list;
    }

    void f(List<List<CityInfo>> list) {
        this.f = list;
    }

    public List<CityInfo> getSuggestEndCity() {
        return this.e;
    }

    public List<PoiInfo> getSuggestEndNode() {
        return this.b;
    }

    public List<CityInfo> getSuggestStartCity() {
        return this.d;
    }

    public List<PoiInfo> getSuggestStartNode() {
        return this.a;
    }

    public List<List<CityInfo>> getSuggestWpCity() {
        return this.f;
    }

    public List<List<PoiInfo>> getSuggestWpNode() {
        return this.c;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.a);
        parcel.writeList(this.b);
        parcel.writeList(this.c);
        parcel.writeList(this.d);
        parcel.writeList(this.e);
        parcel.writeList(this.f);
    }
}
