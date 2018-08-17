package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class af implements Creator<ae> {
    af() {
    }

    public final ae a(Parcel parcel) {
        return new ae(parcel);
    }

    public final ae[] a(int i) {
        return new ae[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
