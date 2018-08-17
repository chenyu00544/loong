package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class ak extends bu implements Parcelable {
    public static final Creator<ak> CREATOR = new al();
    private String a;
    private String[] b;

    public ak(Parcel parcel) {
        super(parcel);
        this.a = parcel.readString();
        this.b = parcel.createStringArray();
    }

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public void a(String[] strArr) {
        this.b = strArr;
    }

    public String[] b() {
        return this.b;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.a);
        parcel.writeStringArray(this.b);
    }
}
