package com.unionpay.mobile.android.pboctransaction;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class a implements Parcelable, Comparable<a> {
    public static final Creator<a> CREATOR = new b();
    private String a;
    private String b;

    private a() {
        this.a = "";
        this.b = "";
    }

    public a(String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        try {
            return this.a.substring(14, 16);
        } catch (Exception e) {
            return "";
        }
    }

    public final boolean c() {
        return this.a != null ? this.a.startsWith("A000000333") : false;
    }

    public /* synthetic */ int compareTo(Object obj) {
        a aVar = (a) obj;
        return !this.a.equalsIgnoreCase(aVar.a) ? this.a.compareTo(aVar.a) : !this.b.equalsIgnoreCase(aVar.b) ? this.b.compareTo(aVar.b) : 0;
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.a.equalsIgnoreCase(aVar.a) && this.b.equalsIgnoreCase(aVar.b);
    }

    public int hashCode() {
        return ((this.a.hashCode() + 31) * 31) + this.b.hashCode();
    }

    public String toString() {
        return "{appId:" + this.a + ", appVersion:" + this.b + "}";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.a);
        parcel.writeString(this.b);
    }
}
