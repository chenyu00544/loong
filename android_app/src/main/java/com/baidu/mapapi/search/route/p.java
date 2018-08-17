package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class p implements Creator<TransitRouteLine> {
    p() {
    }

    public TransitRouteLine a(Parcel parcel) {
        return new TransitRouteLine(parcel);
    }

    public TransitRouteLine[] a(int i) {
        return new TransitRouteLine[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
