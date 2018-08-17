package com.google.zxing.qrcode.a;

import com.google.zxing.h;

/* compiled from: FinderPattern */
public final class d extends h {
    private final float a;
    private int b;

    d(float f, float f2, float f3) {
        this(f, f2, f3, 1);
    }

    private d(float f, float f2, float f3, int i) {
        super(f, f2);
        this.a = f3;
        this.b = i;
    }

    public float c() {
        return this.a;
    }

    int d() {
        return this.b;
    }

    boolean a(float f, float f2, float f3) {
        if (Math.abs(f2 - b()) > f || Math.abs(f3 - a()) > f) {
            return false;
        }
        float abs = Math.abs(f - this.a);
        if (abs <= 1.0f || abs <= this.a) {
            return true;
        }
        return false;
    }

    d b(float f, float f2, float f3) {
        int i = this.b + 1;
        return new d(((((float) this.b) * a()) + f2) / ((float) i), ((((float) this.b) * b()) + f) / ((float) i), ((((float) this.b) * this.a) + f3) / ((float) i), i);
    }
}
