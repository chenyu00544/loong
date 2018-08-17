package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class u implements Parcelable {
    public static final Creator<u> CREATOR = new v();
    private String a;

    public u(Parcel parcel) {
        this.a = parcel.readString();
    }

    public String a() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }
}
