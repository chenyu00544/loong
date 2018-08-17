package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.unionpay.tsmservice.a;

public class y extends bu {
    public static final Creator<y> CREATOR = new z();
    private a a;
    private String b = "0";
    private String c;
    private String d;

    public y(Parcel parcel) {
        super(parcel);
        this.a = (a) parcel.readParcelable(a.class.getClassLoader());
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
    }

    public a a() {
        return this.a;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(String str) {
        this.b = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.c = str;
    }

    public String c() {
        return this.c;
    }

    public void c(String str) {
        this.d = str;
    }

    public String d() {
        return this.d;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, i);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
    }
}
