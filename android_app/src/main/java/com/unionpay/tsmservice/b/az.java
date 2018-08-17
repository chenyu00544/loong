package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class az implements Creator<ay> {
    az() {
    }

    public final ay a(Parcel parcel) {
        return new ay(parcel);
    }

    public final ay[] a(int i) {
        return new ay[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
