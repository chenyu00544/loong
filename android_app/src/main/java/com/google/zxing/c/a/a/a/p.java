package com.google.zxing.c.a.a.a;

/* compiled from: DecodedNumeric */
final class p extends q {
    private final int a;
    private final int b;

    p(int i, int i2, int i3) {
        super(i);
        this.a = i2;
        this.b = i3;
        if (this.a < 0 || this.a > 10) {
            throw new IllegalArgumentException("Invalid firstDigit: " + i2);
        } else if (this.b < 0 || this.b > 10) {
            throw new IllegalArgumentException("Invalid secondDigit: " + i3);
        }
    }

    int a() {
        return this.a;
    }

    int b() {
        return this.b;
    }

    boolean c() {
        return this.a == 10;
    }

    boolean d() {
        return this.b == 10;
    }
}
