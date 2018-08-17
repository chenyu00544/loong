package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class v implements Creator<u> {
    v() {
    }

    public final u a(Parcel parcel) {
        return new u(parcel);
    }

    public final u[] a(int i) {
        return new u[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
