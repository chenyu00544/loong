package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bv implements Creator<bu> {
    bv() {
    }

    public final bu a(Parcel parcel) {
        return new bu(parcel);
    }

    public final bu[] a(int i) {
        return new bu[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
