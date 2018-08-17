package com.unionpay.tsmservice.c;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class a implements Parcelable {
    public static final Creator<a> CREATOR = new b();
    private Bundle a;

    public a(Parcel parcel) {
        this.a = parcel.readBundle();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.a);
    }
}
