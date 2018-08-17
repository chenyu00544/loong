package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class br implements Creator<bq> {
    br() {
    }

    public final bq a(Parcel parcel) {
        return new bq(parcel);
    }

    public final bq[] a(int i) {
        return new bq[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
