package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class s implements Creator<WalkingRouteLine> {
    s() {
    }

    public WalkingRouteLine a(Parcel parcel) {
        return new WalkingRouteLine(parcel);
    }

    public WalkingRouteLine[] a(int i) {
        return new WalkingRouteLine[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
