package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import java.util.List;

public class IndoorRouteResult extends SearchResult {
    public static final Creator<IndoorRouteResult> CREATOR = new h();
    private List<IndoorRouteLine> a;

    IndoorRouteResult() {
    }

    protected IndoorRouteResult(Parcel parcel) {
        super(parcel);
        this.a = parcel.createTypedArrayList(IndoorRouteLine.CREATOR);
    }

    IndoorRouteResult(ERRORNO errorno) {
        super(errorno);
    }

    void a(List<IndoorRouteLine> list) {
        this.a = list;
    }

    public int describeContents() {
        return 0;
    }

    public List<IndoorRouteLine> getRouteLines() {
        return this.a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeTypedList(this.a);
    }
}
