package com.unionpay.tsmservice.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class e implements Parcelable {
    public static final Creator<e> CREATOR = new f();
    private String a = "";

    public e(Parcel parcel) {
        this.a = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
    }
}
