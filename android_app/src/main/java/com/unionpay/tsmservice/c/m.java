package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class m implements Parcelable {
    public static final Creator<m> CREATOR = new n();
    private String a;

    public m(Parcel parcel) {
        this.a = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }
}
