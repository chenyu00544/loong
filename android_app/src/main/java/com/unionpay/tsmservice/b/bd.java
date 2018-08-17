package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bd implements Creator<bc> {
    bd() {
    }

    public final bc a(Parcel parcel) {
        return new bc(parcel);
    }

    public final bc[] a(int i) {
        return new bc[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
