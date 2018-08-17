package com.unionpay.tsmservice.a;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.unionpay.tsmservice.a;

public class c implements Parcelable {
    public static final Creator<c> CREATOR = new d();
    private String A = "";
    private String B = "";
    private String C = "";
    private String D = "";
    private String E = "";
    private String F = "";
    private String G = "";
    private a a;
    private String b = "";
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private String h = "";
    private String i = "";
    private String j = "";
    private long k = 0;
    private String l = "";
    private String m = "";
    private String n = "";
    private String o = "";
    private e p;
    private String q;
    private String r = "";
    private String s = "";
    private String t = "";
    private String u = "";
    private String v = "";
    private String w = "";
    private String x = "";
    private String y = "";
    private String z = "";

    public c(Parcel parcel) {
        this.a = (a) parcel.readParcelable(a.class.getClassLoader());
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readString();
        this.f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readString();
        this.i = parcel.readString();
        this.j = parcel.readString();
        this.k = parcel.readLong();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.q = parcel.readString();
        this.p = (e) parcel.readParcelable(e.class.getClassLoader());
        this.r = parcel.readString();
        this.s = parcel.readString();
        this.t = parcel.readString();
        this.u = parcel.readString();
        this.v = parcel.readString();
        this.w = parcel.readString();
        this.x = parcel.readString();
        this.y = parcel.readString();
        this.z = parcel.readString();
        this.A = parcel.readString();
        this.B = parcel.readString();
        this.C = parcel.readString();
        this.D = parcel.readString();
        this.E = parcel.readString();
        this.F = parcel.readString();
        this.G = parcel.readString();
    }

    public a a() {
        return this.a;
    }

    public String b() {
        return this.s;
    }

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "AppDetail [mAppID=" + this.a + ", mAppName=" + this.b + ", mAppIcon=" + this.c + ", mAppDesc=" + this.d + ", mAppProviderLogo=" + this.e + ", mAppProviderName=" + this.f + ", mAppProviderAgreement=" + this.g + ", mUpAgreement=" + this.h + ", mApplyMode=" + this.i + ", mServicePhone=" + this.j + ", mDownloadTimes=" + this.k + ", mPublishData=" + this.l + ", mPublishStatus=" + this.m + ", mRechargeMode=" + this.n + ", mRechargeLowerLimit=" + this.o + ", mStatus=" + this.p + ", mAppApplyId=" + this.q + ", mMpanId=" + this.r + ", mMpan=" + this.s + ", mCardType=" + this.t + ", mIssuerName=" + this.u + ", mLastDigits=" + this.v + ", mMpanStatus=" + this.w + ", mOpStatus=" + this.x + ", mQuota=" + this.y + ", mCallCenterNumber=" + this.z + ", mEmail=" + this.A + ", mWebsite=" + this.B + ", mApkIcon=" + this.C + ", mApkName=" + this.D + ", mApkPackageName=" + this.E + ", mApkDownloadUrl=" + this.F + ", mApkSign=" + this.G + "]";
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.a, i);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeString(this.e);
        parcel.writeString(this.f);
        parcel.writeString(this.g);
        parcel.writeString(this.h);
        parcel.writeString(this.i);
        parcel.writeString(this.j);
        parcel.writeLong(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.q);
        parcel.writeParcelable(this.p, i);
        parcel.writeString(this.r);
        parcel.writeString(this.s);
        parcel.writeString(this.t);
        parcel.writeString(this.u);
        parcel.writeString(this.v);
        parcel.writeString(this.w);
        parcel.writeString(this.x);
        parcel.writeString(this.y);
        parcel.writeString(this.z);
        parcel.writeString(this.A);
        parcel.writeString(this.B);
        parcel.writeString(this.C);
        parcel.writeString(this.D);
        parcel.writeString(this.E);
        parcel.writeString(this.F);
        parcel.writeString(this.G);
    }
}
