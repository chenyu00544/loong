package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class al implements Creator<ak> {
    al() {
    }

    public final ak a(Parcel parcel) {
        return new ak(parcel);
    }

    public final ak[] a(int i) {
        return new ak[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
