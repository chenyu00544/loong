package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class t implements Creator<s> {
    t() {
    }

    public final s a(Parcel parcel) {
        return new s(parcel);
    }

    public final s[] a(int i) {
        return new s[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
