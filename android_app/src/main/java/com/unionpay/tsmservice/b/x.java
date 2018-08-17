package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class x implements Creator<w> {
    x() {
    }

    public final w a(Parcel parcel) {
        return new w(parcel);
    }

    public final w[] a(int i) {
        return new w[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
