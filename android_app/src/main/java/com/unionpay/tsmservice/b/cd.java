package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class cd implements Creator<cc> {
    cd() {
    }

    public final cc a(Parcel parcel) {
        return new cc(parcel);
    }

    public final cc[] a(int i) {
        return new cc[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
