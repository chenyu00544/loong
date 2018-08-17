package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class bl implements Creator<bk> {
    bl() {
    }

    public final bk a(Parcel parcel) {
        return new bk(parcel);
    }

    public final bk[] a(int i) {
        return new bk[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
