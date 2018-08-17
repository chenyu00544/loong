package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bn implements Creator<bm> {
    bn() {
    }

    public final bm a(Parcel parcel) {
        return new bm(parcel);
    }

    public final bm[] a(int i) {
        return new bm[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
