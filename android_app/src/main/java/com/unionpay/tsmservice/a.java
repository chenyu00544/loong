package com.unionpay.tsmservice;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class a implements Parcelable {
    public static final Creator<a> CREATOR = new b();
    String a = "";
    String b = "";

    public a(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
    }

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
