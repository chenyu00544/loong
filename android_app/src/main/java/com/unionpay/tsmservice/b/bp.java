package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bp implements Creator<bo> {
    bp() {
    }

    public final bo a(Parcel parcel) {
        return new bo(parcel);
    }

    public final bo[] a(int i) {
        return new bo[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
