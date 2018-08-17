package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class av implements Creator<au> {
    av() {
    }

    public final au a(Parcel parcel) {
        return new au(parcel);
    }

    public final au[] a(int i) {
        return new au[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
