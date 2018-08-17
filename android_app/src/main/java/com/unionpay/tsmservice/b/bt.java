package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bt implements Creator<bs> {
    bt() {
    }

    public final bs a(Parcel parcel) {
        return new bs(parcel);
    }

    public final bs[] a(int i) {
        return new bs[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
