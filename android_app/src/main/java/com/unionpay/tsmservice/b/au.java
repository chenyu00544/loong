package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class au extends bu {
    public static final Creator<au> CREATOR = new av();

    public au(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
