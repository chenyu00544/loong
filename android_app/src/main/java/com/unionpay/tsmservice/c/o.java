package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.unionpay.tsmservice.a.k;

public class o implements Parcelable {
    public static final Creator<o> CREATOR = new p();
    private k a;

    public o(Parcel parcel) {
        this.a = (k) parcel.readParcelable(k.class.getClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
    }
}
