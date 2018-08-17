package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class as extends bu implements Parcelable {
    public static final Creator<as> CREATOR = new at();
    private String[] a;

    public as(Parcel parcel) {
        super(parcel);
        this.a = parcel.createStringArray();
    }

    public void a(String[] strArr) {
        this.a = strArr;
    }

    public String[] a() {
        return this.a;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeStringArray(this.a);
    }
}
