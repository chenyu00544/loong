package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class by extends bu {
    public static final Creator<by> CREATOR = new bz();
    private String a;
    private String b;

    public by(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
        this.b = parcel.readString();
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
