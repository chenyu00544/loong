package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ca extends bu {
    public static final Creator<ca> CREATOR = new cb();
    private String a;

    public ca(Parcel parcel) {
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
