package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class bm extends bu {
    public static final Creator<bm> CREATOR = new bn();
    private String a;

    public bm(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
    }
}
