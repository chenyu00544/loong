package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bz implements Creator<by> {
    bz() {
    }

    public final by a(Parcel parcel) {
        return new by(parcel);
    }

    public final by[] a(int i) {
        return new by[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
