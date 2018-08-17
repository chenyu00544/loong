package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class l implements Creator<k> {
    l() {
    }

    public final k a(Parcel parcel) {
        return new k(parcel);
    }

    public final k[] a(int i) {
        return new k[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
