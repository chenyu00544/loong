package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class ar implements Creator<aq> {
    ar() {
    }

    public final aq a(Parcel parcel) {
        return new aq(parcel);
    }

    public final aq[] a(int i) {
        return new aq[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
