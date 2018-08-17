package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class aa extends bu {
    public static final Creator<aa> CREATOR = new ab();
    private List<String> a;

    public aa(Parcel parcel) {
        super(parcel);
        this.a = new ArrayList();
        parcel.readList(this.a, ClassLoader.getSystemClassLoader());
    }

    public List<String> a() {
        return this.a;
    }

    public void a(List<String> list) {
        this.a = list;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeList(this.a);
    }
}
