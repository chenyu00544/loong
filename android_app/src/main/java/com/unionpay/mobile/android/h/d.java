package com.unionpay.mobile.android.h;

import android.content.Context;
import android.text.TextUtils;
import com.unionpay.mobile.android.utils.c;
import java.util.HashMap;

public final class d {
    private int a;
    private String b;
    private HashMap<String, String> c;
    private byte[] d;
    private String e;
    private String f;

    public d(int i, String str, byte[] bArr) {
        this.a = i;
        this.b = str;
        this.c = null;
        this.d = bArr;
    }

    public d(String str) {
        this.a = 1;
        this.b = str;
        this.c = null;
        this.d = null;
    }

    public final int a() {
        return this.a;
    }

    public final void a(Context context, String str, String str2) {
        this.f = "?" + c.d(str) + "&0," + c.b(context) + "&" + c.e(str2);
    }

    public final void a(String str) {
        if (str != null) {
            this.e = str;
            this.d = str.getBytes();
        }
    }

    public final void a(HashMap<String, String> hashMap) {
        this.c = hashMap;
    }

    public final String b() {
        return !TextUtils.isEmpty(this.f) ? this.b + this.f : this.b;
    }

    public final String c() {
        return this.e;
    }

    public final HashMap<String, String> d() {
        return this.c;
    }

    public final byte[] e() {
        return this.d;
    }
}
