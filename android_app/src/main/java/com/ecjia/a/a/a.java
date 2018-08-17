package com.ecjia.a.a;

/* compiled from: ECJiaEvent */
public abstract class a {
    protected boolean a;
    protected int b;
    protected String c;
    protected String d;

    public a(boolean z, int i) {
        this.a = z;
        this.b = i;
    }

    public a(String str) {
        this.c = str;
    }

    public a(String str, String str2) {
        this.c = str;
        this.d = str2;
    }

    public boolean a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }
}
