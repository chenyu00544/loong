package com.unionpay.mobile.android.pboctransaction;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class b implements Creator<a> {
    b() {
    }

    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        a aVar = new a();
        aVar.a = parcel.readString();
        aVar.b = parcel.readString();
        return aVar;
    }

    public final /* bridge */ /* synthetic */ Object[] newArray(int i) {
        return new a[i];
    }
}
