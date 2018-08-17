package com.unionpay.tsmservice.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class i implements Parcelable {
    public static final Creator<i> CREATOR = new j();
    private c a;

    public i(Parcel parcel) {
        this.a = (c) parcel.readParcelable(c.class.getClassLoader());
    }

    public c a() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
    }
}
