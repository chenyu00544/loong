package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class an implements Creator<am> {
    an() {
    }

    public final am a(Parcel parcel) {
        return new am(parcel);
    }

    public final am[] a(int i) {
        return new am[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
