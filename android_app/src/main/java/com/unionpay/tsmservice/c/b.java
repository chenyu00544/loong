package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<a> {
    b() {
    }

    public final a a(Parcel parcel) {
        return new a(parcel);
    }

    public final a[] a(int i) {
        return new a[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
