package com.baidu.mapapi.search.route;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep;

final class q implements Creator<TransitStep> {
    q() {
    }

    public TransitStep a(Parcel parcel) {
        return new TransitStep(parcel);
    }

    public TransitStep[] a(int i) {
        return new TransitStep[i];
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return a(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return a(i);
    }
}
