package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class ap implements Creator<ao> {
    ap() {
    }

    public final ao a(Parcel parcel) {
        return new ao(parcel);
    }

    public final ao[] a(int i) {
        return new ao[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
