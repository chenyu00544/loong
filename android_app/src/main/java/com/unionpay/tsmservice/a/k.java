package com.unionpay.tsmservice.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class k implements Parcelable {
    public static final Creator<k> CREATOR = new l();
    private String a;
    private String b;
    private String c;
    private String[] d;

    public k(Parcel parcel) {
        this.a = parcel.readString();
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.createStringArray();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeStringArray(this.d);
    }
}
