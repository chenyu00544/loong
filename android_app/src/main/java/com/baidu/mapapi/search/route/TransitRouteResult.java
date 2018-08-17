package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.TaxiInfo;
import java.util.ArrayList;
import java.util.List;

public final class TransitRouteResult extends SearchResult implements Parcelable {
    public static final Creator<TransitRouteResult> CREATOR = new r();
    private TaxiInfo a;
    private List<TransitRouteLine> b;
    private SuggestAddrInfo c;

    TransitRouteResult() {
    }

    protected TransitRouteResult(Parcel parcel) {
        this.a = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.b = new ArrayList();
        parcel.readList(this.b, TransitRouteLine.class.getClassLoader());
        this.c = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    TransitRouteResult(ERRORNO errorno) {
        super(errorno);
    }

    void a(TaxiInfo taxiInfo) {
        this.a = taxiInfo;
    }

    void a(SuggestAddrInfo suggestAddrInfo) {
        this.c = suggestAddrInfo;
    }

    void a(List<TransitRouteLine> list) {
        this.b = list;
    }

    public int describeContents() {
        return 0;
    }

    public List<TransitRouteLine> getRouteLines() {
        return this.b;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.c;
    }

    public TaxiInfo getTaxiInfo() {
        return this.a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, 1);
        parcel.writeList(this.b);
        parcel.writeParcelable(this.c, 1);
    }
}
