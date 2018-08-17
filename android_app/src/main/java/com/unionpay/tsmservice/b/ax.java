package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class ax implements Creator<aw> {
    ax() {
    }

    public final aw a(Parcel parcel) {
        return new aw(parcel);
    }

    public final aw[] a(int i) {
        return new aw[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
