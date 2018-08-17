package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import java.util.List;

public class PoiResult extends SearchResult implements Parcelable {
    public static final Creator<PoiResult> CREATOR = new c();
    private int a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private List<PoiInfo> e;
    private List<CityInfo> f;
    private List<PoiAddrInfo> g;
    private boolean h = false;

    PoiResult() {
    }

    PoiResult(Parcel parcel) {
        this.a = parcel.readInt();
        this.b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readArrayList(PoiInfo.class.getClassLoader());
        this.f = parcel.readArrayList(CityInfo.class.getClassLoader());
    }

    PoiResult(ERRORNO errorno) {
        super(errorno);
    }

    void a(int i) {
        this.a = i;
    }

    void a(List<PoiInfo> list) {
        this.e = list;
    }

    void a(boolean z) {
        this.h = z;
    }

    void b(int i) {
        this.b = i;
    }

    void b(List<PoiAddrInfo> list) {
        this.g = list;
    }

    void c(int i) {
        this.c = i;
    }

    void c(List<CityInfo> list) {
        this.f = list;
    }

    void d(int i) {
        this.d = i;
    }

    public int describeContents() {
        return 0;
    }

    public List<PoiAddrInfo> getAllAddr() {
        return this.g;
    }

    public List<PoiInfo> getAllPoi() {
        return this.e;
    }

    public int getCurrentPageCapacity() {
        return this.c;
    }

    public int getCurrentPageNum() {
        return this.a;
    }

    public List<CityInfo> getSuggestCityList() {
        return this.f;
    }

    public int getTotalPageNum() {
        return this.b;
    }

    public int getTotalPoiNum() {
        return this.d;
    }

    public boolean isHasAddrInfo() {
        return this.h;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeList(this.e);
        parcel.writeList(this.f);
    }
}
