package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ay extends bu {
    public static final Creator<ay> CREATOR = new az();
    private int a;
    private String b;

    public ay(Parcel parcel) {
        super(parcel);
        this.a = parcel.readInt();
        this.b = parcel.readString();
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.a);
        parcel.writeString(this.b);
    }
}
