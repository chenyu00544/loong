package com.baidu.mapapi.search.poi;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import java.util.List;

public class PoiIndoorResult extends SearchResult implements Parcelable {
    public static final Creator<PoiIndoorResult> CREATOR = new b();
    int a;
    int b;
    private List<PoiIndoorInfo> c;

    PoiIndoorResult() {
    }

    protected PoiIndoorResult(Parcel parcel) {
        super(parcel);
        this.a = parcel.readInt();
        this.b = parcel.readInt();
    }

    public PoiIndoorResult(ERRORNO errorno) {
        super(errorno);
    }

    public int describeContents() {
        return 0;
    }

    public int getPageNum() {
        return this.b;
    }

    public int getPoiNum() {
        return this.a;
    }

    public List<PoiIndoorInfo> getmArrayPoiInfo() {
        return this.c;
    }

    public void setPageNum(int i) {
        this.b = i;
    }

    public void setPoiNum(int i) {
        this.a = i;
    }

    public void setmArrayPoiInfo(List<PoiIndoorInfo> list) {
        this.c = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.a);
        parcel.writeInt(this.b);
    }
}
