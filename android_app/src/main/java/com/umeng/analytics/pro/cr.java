package com.umeng.analytics.pro;

import java.io.Serializable;

/* compiled from: FieldValueMetaData */
public class cr implements Serializable {
    private final boolean a;
    public final byte b;
    private final String c;
    private final boolean d;

    public cr(byte b, boolean z) {
        this.b = b;
        this.a = false;
        this.c = null;
        this.d = z;
    }

    public cr(byte b) {
        this(b, false);
    }

    public cr(byte b, String str) {
        this.b = b;
        this.a = true;
        this.c = str;
        this.d = false;
    }

    public boolean a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        return this.b == (byte) 12;
    }

    public boolean d() {
        return this.b == dk.m || this.b == dk.k || this.b == dk.l;
    }

    public boolean e() {
        return this.d;
    }
}
