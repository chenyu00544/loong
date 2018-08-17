package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bx implements Creator<bw> {
    bx() {
    }

    public final bw a(Parcel parcel) {
        return new bw(parcel);
    }

    public final bw[] a(int i) {
        return new bw[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
