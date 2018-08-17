package com.google.zxing.c.a.a.a;

/* compiled from: CurrentParsingState */
final class m {
    private int a = 0;
    private a b = a.NUMERIC;

    /* compiled from: CurrentParsingState */
    private enum a {
        NUMERIC,
        ALPHA,
        ISO_IEC_646
    }

    m() {
    }

    int a() {
        return this.a;
    }

    void a(int i) {
        this.a = i;
    }

    void b(int i) {
        this.a += i;
    }

    boolean b() {
        return this.b == a.ALPHA;
    }

    boolean c() {
        return this.b == a.ISO_IEC_646;
    }

    void d() {
        this.b = a.NUMERIC;
    }

    void e() {
        this.b = a.ALPHA;
    }

    void f() {
        this.b = a.ISO_IEC_646;
    }
}
