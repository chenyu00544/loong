package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.unionpay.tsmservice.a.m;

public class g implements Parcelable {
    public static final Creator<g> CREATOR = new h();
    private m a;

    public g(Parcel parcel) {
        this.a = (m) parcel.readParcelable(m.class.getClassLoader());
    }

    public m a() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
    }
}
