package com.google.zxing.pdf417.a;

/* compiled from: Codeword */
final class d {
    private final int a;
    private final int b;
    private final int c;
    private final int d;
    private int e = -1;

    d(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    boolean a() {
        return a(this.e);
    }

    boolean a(int i) {
        return i != -1 && this.c == (i % 3) * 3;
    }

    void b() {
        this.e = ((this.d / 30) * 3) + (this.c / 3);
    }

    int c() {
        return this.b - this.a;
    }

    int d() {
        return this.a;
    }

    int e() {
        return this.b;
    }

    int f() {
        return this.c;
    }

    int g() {
        return this.d;
    }

    int h() {
        return this.e;
    }

    void b(int i) {
        this.e = i;
    }

    public String toString() {
        return this.e + "|" + this.d;
    }
}
