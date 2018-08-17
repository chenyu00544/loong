package com.unionpay.tsmservice.c;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public class e implements Parcelable {
    public static final Creator<e> CREATOR = new f();
    private List<String> a;

    public e(Parcel parcel) {
        this.a = new ArrayList();
        parcel.readList(this.a, ClassLoader.getSystemClassLoader());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(this.a);
    }
}
