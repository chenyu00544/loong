package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class aj implements Creator<ai> {
    aj() {
    }

    public final ai a(Parcel parcel) {
        return new ai(parcel);
    }

    public final ai[] a(int i) {
        return new ai[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
