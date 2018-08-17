package com.unionpay.tsmservice.b;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.unionpay.tsmservice.a;
import java.util.HashMap;
import java.util.Map;

public class i extends bu {
    public static final Creator<i> CREATOR = new j();
    private a a;
    private HashMap<String, String> b;

    public i(Parcel parcel) {
        super(parcel);
        this.a = (a) parcel.readParcelable(a.class.getClassLoader());
        this.b = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    public a a() {
        return this.a;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(HashMap<String, String> hashMap) {
        this.b = hashMap;
    }

    public Map<String, String> b() {
        return this.b;
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.a, i);
        parcel.writeMap(this.b);
    }
}
