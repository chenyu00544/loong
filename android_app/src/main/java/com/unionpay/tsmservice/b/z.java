package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class z implements Creator<y> {
    z() {
    }

    public final y a(Parcel parcel) {
        return new y(parcel);
    }

    public final y[] a(int i) {
        return new y[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
