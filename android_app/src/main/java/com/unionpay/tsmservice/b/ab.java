package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class ab implements Creator<aa> {
    ab() {
    }

    public final aa a(Parcel parcel) {
        return new aa(parcel);
    }

    public final aa[] a(int i) {
        return new aa[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
