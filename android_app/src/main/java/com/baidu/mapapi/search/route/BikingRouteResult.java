package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import java.util.ArrayList;
import java.util.List;

public class BikingRouteResult extends SearchResult implements Parcelable {
    public static final Creator<BikingRouteLine> CREATOR = new c();
    private List<BikingRouteLine> a;
    private SuggestAddrInfo b;

    BikingRouteResult() {
    }

    protected BikingRouteResult(Parcel parcel) {
        this.a = new ArrayList();
        parcel.readList(this.a, BikingRouteLine.class.getClassLoader());
        this.b = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    BikingRouteResult(ERRORNO errorno) {
        super(errorno);
    }

    void a(SuggestAddrInfo suggestAddrInfo) {
        this.b = suggestAddrInfo;
    }

    void a(List<BikingRouteLine> list) {
        this.a = list;
    }

    public int describeContents() {
        return 0;
    }

    public List<BikingRouteLine> getRouteLines() {
        return this.a;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.a);
        parcel.writeParcelable(this.b, 1);
    }
}
