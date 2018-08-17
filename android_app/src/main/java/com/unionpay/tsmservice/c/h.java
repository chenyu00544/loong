package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class h implements Creator<g> {
    h() {
    }

    public final g a(Parcel parcel) {
        return new g(parcel);
    }

    public final g[] a(int i) {
        return new g[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
