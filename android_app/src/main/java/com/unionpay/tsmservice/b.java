package com.unionpay.tsmservice;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<a> {
    b() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return new a(parcel);
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new a[i];
    }
}
