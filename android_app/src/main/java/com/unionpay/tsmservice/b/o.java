package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.unionpay.tsmservice.a;

public class o extends bu {
    public static final Creator<o> CREATOR = new p();
    private a a;

    public o(Parcel parcel) {
        super(parcel);
        this.a = (a) parcel.readParcelable(a.class.getClassLoader());
    }

    public a a() {
        return this.a;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, i);
    }
}
