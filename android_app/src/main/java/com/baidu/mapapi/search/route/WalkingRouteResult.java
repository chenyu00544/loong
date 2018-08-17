package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.TaxiInfo;
import java.util.ArrayList;
import java.util.List;

public class WalkingRouteResult extends SearchResult implements Parcelable {
    public static final Creator<WalkingRouteResult> CREATOR = new u();
    private List<WalkingRouteLine> a;
    private TaxiInfo b;
    private SuggestAddrInfo c;

    WalkingRouteResult() {
    }

    protected WalkingRouteResult(Parcel parcel) {
        this.a = new ArrayList();
        parcel.readList(this.a, WalkingRouteLine.class.getClassLoader());
        this.b = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.c = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    WalkingRouteResult(ERRORNO errorno) {
        super(errorno);
    }

    void a(TaxiInfo taxiInfo) {
        this.b = taxiInfo;
    }

    void a(SuggestAddrInfo suggestAddrInfo) {
        this.c = suggestAddrInfo;
    }

    void a(List<WalkingRouteLine> list) {
        this.a = list;
    }

    public int describeContents() {
        return 0;
    }

    public List<WalkingRouteLine> getRouteLines() {
        return this.a;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.c;
    }

    public TaxiInfo getTaxiInfo() {
        return this.b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.a);
        parcel.writeParcelable(this.b, 1);
        parcel.writeParcelable(this.c, 1);
    }
}
