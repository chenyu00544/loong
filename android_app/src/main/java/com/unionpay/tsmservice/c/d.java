package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class d implements Creator<c> {
    d() {
    }

    public final c a(Parcel parcel) {
        return new c(parcel);
    }

    public final c[] a(int i) {
        return new c[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
