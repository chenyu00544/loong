package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class s implements Parcelable {
    public static final Creator<s> CREATOR = new t();
    private String a;
    private String b;

    public s(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
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
