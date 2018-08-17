package com.baidu.mapapi.search.district;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import java.util.ArrayList;
import java.util.List;

public class DistrictResult extends SearchResult implements Parcelable {
    public static final Creator<DistrictResult> CREATOR = new a();
    private LatLng a = null;
    private List<List<LatLng>> b = null;
    private int c;
    private String d = null;

    DistrictResult() {
    }

    protected DistrictResult(Parcel parcel) {
        super(parcel);
        this.a = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
        int readInt = parcel.readInt();
        if (readInt > 0) {
            this.b = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                this.b.add(parcel.createTypedArrayList(LatLng.CREATOR));
            }
        }
        this.c = parcel.readInt();
        this.d = parcel.readString();
    }

    DistrictResult(ERRORNO errorno) {
        super(errorno);
    }

    void a(int i) {
        this.c = i;
    }

    void a(LatLng latLng) {
        this.a = latLng;
    }

    void a(String str) {
        this.d = str;
    }

    void a(List<List<LatLng>> list) {
        this.b = list;
    }

    public int describeContents() {
        return 0;
    }

    public LatLng getCenterPt() {
        return this.a;
    }

    public int getCityCode() {
        return this.c;
    }

    public String getCityName() {
        return this.d;
    }

    public List<List<LatLng>> getPolylines() {
        return this.b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, i);
        parcel.writeInt(this.b == null ? 0 : this.b.size());
        for (List writeTypedList : this.b) {
            parcel.writeTypedList(writeTypedList);
        }
        parcel.writeInt(this.c);
        parcel.writeString(this.d);
    }
}
