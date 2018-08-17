package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bj implements Creator<bi> {
    bj() {
    }

    public final bi a(Parcel parcel) {
        return new bi(parcel);
    }

    public final bi[] a(int i) {
        return new bi[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
