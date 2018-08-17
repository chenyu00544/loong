package com.alipay.android.phone.mrpc.core;

public final class t extends y {
    private int c;
    private String d;
    private long e;
    private long f;
    private String g;
    private HttpUrlHeader h;

    public t(HttpUrlHeader httpUrlHeader, int i, String str, byte[] bArr) {
        this.h = httpUrlHeader;
        this.c = i;
        this.d = str;
        this.a = bArr;
    }

    public final HttpUrlHeader a() {
        return this.h;
    }

    public final void a(long j) {
        this.e = j;
    }

    public final void a(String str) {
        this.g = str;
    }

    public final void b(long j) {
        this.f = j;
    }
}
