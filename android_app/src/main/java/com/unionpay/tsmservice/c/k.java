package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.unionpay.tsmservice.a.i;

public class k implements Parcelable {
    public static final Creator<k> CREATOR = new l();
    private i[] a;
    private String b = "";

    public k(Parcel parcel) {
        this.a = (i[]) parcel.createTypedArray(i.CREATOR);
        this.b = parcel.readString();
    }

    public i[] a() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.a, i);
        parcel.writeString(this.b);
    }
}
