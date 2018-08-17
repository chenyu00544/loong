package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class bu implements Parcelable {
    public static final Creator<bu> CREATOR = new bv();
    private String a = "";

    public bu(Parcel parcel) {
        this.a = parcel.readString();
    }

    public void d(String str) {
        this.a = str;
    }

    public int describeContents() {
        return 0;
    }

    public String e() {
        return this.a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }
}
