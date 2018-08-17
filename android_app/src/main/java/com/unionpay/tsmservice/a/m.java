package com.unionpay.tsmservice.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.unionpay.tsmservice.a;

public class m implements Parcelable {
    public static final Creator<m> CREATOR = new n();
    private a a;
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";

    public m(Parcel parcel) {
        this.a = (a) parcel.readParcelable(a.class.getClassLoader());
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
    }

    public a a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
    }
}
