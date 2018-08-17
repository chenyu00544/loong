package com.unionpay.tsmservice.c;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class w implements Parcelable {
    public static final Creator<w> CREATOR = new x();
    private Bundle a;

    public w(Parcel parcel) {
        this.a = parcel.readBundle();
    }

    public Bundle a() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.a);
    }
}
