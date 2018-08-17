package com.google.zxing.c.a;

import com.google.zxing.h;

/* compiled from: FinderPattern */
public final class c {
    private final int a;
    private final int[] b;
    private final h[] c;

    public c(int i, int[] iArr, int i2, int i3, int i4) {
        this.a = i;
        this.b = iArr;
        this.c = new h[]{new h((float) i2, (float) i4), new h((float) i3, (float) i4)};
    }

    public int a() {
        return this.a;
    }

    public int[] b() {
        return this.b;
    }

    public h[] c() {
        return this.c;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        if (this.a == ((c) obj).a) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a;
    }
}
