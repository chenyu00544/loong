package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class cb implements Creator<ca> {
    cb() {
    }

    public final ca a(Parcel parcel) {
        return new ca(parcel);
    }

    public final ca[] a(int i) {
        return new ca[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
