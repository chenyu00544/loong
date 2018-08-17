package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class j implements Creator<i> {
    j() {
    }

    public final i a(Parcel parcel) {
        return new i(parcel);
    }

    public final i[] a(int i) {
        return new i[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
