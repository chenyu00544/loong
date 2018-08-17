package com.google.zxing.common.reedsolomon;

/* compiled from: GenericGF */
public final class a {
    public static final a a = new a(4201, 4096, 1);
    public static final a b = new a(1033, 1024, 1);
    public static final a c = new a(67, 64, 1);
    public static final a d = new a(19, 16, 1);
    public static final a e = new a(285, 256, 0);
    public static final a f = new a(301, 256, 1);
    public static final a g = f;
    public static final a h = c;
    private int[] i;
    private int[] j;
    private b k;
    private b l;
    private final int m;
    private final int n;
    private final int o;
    private boolean p = false;

    public a(int i, int i2, int i3) {
        this.n = i;
        this.m = i2;
        this.o = i3;
        if (i2 <= 0) {
            e();
        }
    }

    private void e() {
        int i;
        this.i = new int[this.m];
        this.j = new int[this.m];
        int i2 = 1;
        for (i = 0; i < this.m; i++) {
            this.i[i] = i2;
            i2 <<= 1;
            if (i2 >= this.m) {
                i2 = (i2 ^ this.n) & (this.m - 1);
            }
        }
        for (i = 0; i < this.m - 1; i++) {
            this.j[this.i[i]] = i;
        }
        this.k = new b(this, new int[]{0});
        this.l = new b(this, new int[]{1});
        this.p = true;
    }

    private void f() {
        if (!this.p) {
            e();
        }
    }

    b a() {
        f();
        return this.k;
    }

    b b() {
        f();
        return this.l;
    }

    b a(int i, int i2) {
        f();
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.k;
        } else {
            int[] iArr = new int[(i + 1)];
            iArr[0] = i2;
            return new b(this, iArr);
        }
    }

    static int b(int i, int i2) {
        return i ^ i2;
    }

    int a(int i) {
        f();
        return this.i[i];
    }

    int b(int i) {
        f();
        if (i != 0) {
            return this.j[i];
        }
        throw new IllegalArgumentException();
    }

    int c(int i) {
        f();
        if (i != 0) {
            return this.i[(this.m - this.j[i]) - 1];
        }
        throw new ArithmeticException();
    }

    int c(int i, int i2) {
        f();
        if (i == 0 || i2 == 0) {
            return 0;
        }
        return this.i[(this.j[i] + this.j[i2]) % (this.m - 1)];
    }

    public int c() {
        return this.m;
    }

    public int d() {
        return this.o;
    }

    public String toString() {
        return "GF(0x" + Integer.toHexString(this.n) + ',' + this.m + ')';
    }
}
