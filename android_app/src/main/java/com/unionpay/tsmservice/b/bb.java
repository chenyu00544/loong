package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bb implements Creator<ba> {
    bb() {
    }

    public final ba a(Parcel parcel) {
        return new ba(parcel);
    }

    public final ba[] a(int i) {
        return new ba[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
