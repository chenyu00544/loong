package com.unionpay.tsmservice.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class a implements Parcelable {
    public static final Creator<a> CREATOR = new b();
    private String a = "CNY";
    private String b = "0.0";

    public a(Parcel parcel) {
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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
