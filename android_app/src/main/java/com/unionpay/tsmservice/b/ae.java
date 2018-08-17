package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.unionpay.tsmservice.a;

public class ae extends bu {
    public static final Creator<ae> CREATOR = new af();
    private a a;
    private String b;

    public ae(Parcel parcel) {
        super(parcel);
        this.a = (a) parcel.readParcelable(a.class.getClassLoader());
        this.b = parcel.readString();
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

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, i);
        parcel.writeString(this.b);
    }
}
