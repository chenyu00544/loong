package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class ad implements Creator<ac> {
    ad() {
    }

    public final ac a(Parcel parcel) {
        return new ac(parcel);
    }

    public final ac[] a(int i) {
        return new ac[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
