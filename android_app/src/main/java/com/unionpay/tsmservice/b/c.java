package com.unionpay.tsmservice.b;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public class c extends bu {
    public static final Creator<c> CREATOR = new d();
    private Bundle a;

    public c(Parcel parcel) {
        super(parcel);
        this.a = parcel.readBundle();
    }

    public Bundle a() {
        return this.a;
    }

    public void a(Bundle bundle) {
        this.a = bundle;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBundle(this.a);
    }
}
