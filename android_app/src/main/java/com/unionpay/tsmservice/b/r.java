package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class r implements Creator<q> {
    r() {
    }

    public final q a(Parcel parcel) {
        return new q(parcel);
    }

    public final q[] a(int i) {
        return new q[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
