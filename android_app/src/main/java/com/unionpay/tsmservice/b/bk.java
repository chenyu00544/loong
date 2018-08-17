package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class bk extends bu {
    public static final Creator<bk> CREATOR = new bl();

    public bk(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
