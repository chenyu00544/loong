package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bf implements Creator<be> {
    bf() {
    }

    public final be a(Parcel parcel) {
        return new be(parcel);
    }

    public final be[] a(int i) {
        return new be[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
