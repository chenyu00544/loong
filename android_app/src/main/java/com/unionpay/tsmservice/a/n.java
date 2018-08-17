package com.unionpay.tsmservice.a;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class n implements Creator<m> {
    n() {
    }

    public final m a(Parcel parcel) {
        return new m(parcel);
    }

    public final m[] a(int i) {
        return new m[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
