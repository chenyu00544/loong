package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bh implements Creator<bg> {
    bh() {
    }

    public final bg a(Parcel parcel) {
        return new bg(parcel);
    }

    public final bg[] a(int i) {
        return new bg[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
