package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ce extends bu {
    public static final Creator<ce> CREATOR = new cf();

    public ce(Parcel parcel) {
        super(parcel);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
