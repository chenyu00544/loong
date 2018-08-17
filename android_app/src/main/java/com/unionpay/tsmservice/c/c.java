package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class c implements Parcelable {
    public static final Creator<c> CREATOR = new d();

    public c(Parcel parcel) {
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
    }
}
