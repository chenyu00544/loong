package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class p implements Creator<o> {
    p() {
    }

    public final o a(Parcel parcel) {
        return new o(parcel);
    }

    public final o[] a(int i) {
        return new o[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
