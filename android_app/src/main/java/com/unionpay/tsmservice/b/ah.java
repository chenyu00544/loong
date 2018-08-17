package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class ah implements Creator<ag> {
    ah() {
    }

    public final ag a(Parcel parcel) {
        return new ag(parcel);
    }

    public final ag[] a(int i) {
        return new ag[i];
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
