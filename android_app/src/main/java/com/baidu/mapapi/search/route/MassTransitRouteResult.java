package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.TransitResultNode;
import java.util.ArrayList;
import java.util.List;

public final class MassTransitRouteResult extends SearchResult implements Parcelable {
    public static final Creator<MassTransitRouteResult> CREATOR = new l();
    private TransitResultNode a;
    private TransitResultNode b;
    private TaxiInfo c;
    private int d;
    private List<MassTransitRouteLine> e;
    private SuggestAddrInfo f;

    MassTransitRouteResult() {
    }

    MassTransitRouteResult(Parcel parcel) {
        this.a = (TransitResultNode) parcel.readParcelable(TransitResultNode.class.getClassLoader());
        this.b = (TransitResultNode) parcel.readParcelable(TransitResultNode.class.getClassLoader());
        this.c = (TaxiInfo) parcel.readParcelable(TaxiInfo.class.getClassLoader());
        this.d = parcel.readInt();
        this.e = new ArrayList();
        parcel.readList(this.e, MassTransitRouteLine.class.getClassLoader());
        this.f = (SuggestAddrInfo) parcel.readParcelable(SuggestAddrInfo.class.getClassLoader());
    }

    MassTransitRouteResult(ERRORNO errorno) {
        super(errorno);
    }

    void a(int i) {
        this.d = i;
    }

    void a(TransitResultNode transitResultNode) {
        this.a = transitResultNode;
    }

    void a(SuggestAddrInfo suggestAddrInfo) {
        this.f = suggestAddrInfo;
    }

    void a(List<MassTransitRouteLine> list) {
        this.e = list;
    }

    void b(TransitResultNode transitResultNode) {
        this.b = transitResultNode;
    }

    public int describeContents() {
        return 0;
    }

    public TransitResultNode getDestination() {
        return this.b;
    }

    public TransitResultNode getOrigin() {
        return this.a;
    }

    public List<MassTransitRouteLine> getRouteLines() {
        return this.e;
    }

    public SuggestAddrInfo getSuggestAddrInfo() {
        return this.f;
    }

    public TaxiInfo getTaxiInfo() {
        return this.c;
    }

    public int getTotal() {
        return this.d;
    }

    public void setTaxiInfo(TaxiInfo taxiInfo) {
        this.c = taxiInfo;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, 1);
        parcel.writeParcelable(this.b, 1);
        parcel.writeParcelable(this.c, 1);
        parcel.writeInt(this.d);
        parcel.writeList(this.e);
        parcel.writeParcelable(this.f, 1);
    }
}
