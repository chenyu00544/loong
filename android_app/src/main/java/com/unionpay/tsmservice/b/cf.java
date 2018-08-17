package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class cf implements Creator<ce> {
    cf() {
    }

    public final ce a(Parcel parcel) {
        return new ce(parcel);
    }

    public final ce[] a(int i) {
        return new ce[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
