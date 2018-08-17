package com.unionpay.tsmservice.b;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class bq extends bu {
    public static final Creator<bq> CREATOR = new br();
    private Bundle a;
    private String b;
    private String c;

    public bq(Parcel parcel) {
        super(parcel);
        this.a = parcel.readBundle();
        this.b = parcel.readString();
        this.c = parcel.readString();
    }

    public Bundle a() {
        return this.a;
    }

    public void a(Bundle bundle) {
        this.a = bundle;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBundle(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
    }
}
