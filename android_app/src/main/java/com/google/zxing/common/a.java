package com.google.zxing.common;

/* compiled from: BitArray */
public final class a {
    private int[] a;
    private int b;

    public a() {
        this.b = 0;
        this.a = new int[1];
    }

    public a(int i) {
        this.b = i;
        this.a = e(i);
    }

    public int a() {
        return this.b;
    }

    public boolean a(int i) {
        return (this.a[i >> 5] & (1 << (i & 31))) != 0;
    }

    public void b(int i) {
        int[] iArr = this.a;
        int i2 = i >> 5;
        iArr[i2] = iArr[i2] | (1 << (i & 31));
    }

    public int c(int i) {
        if (i >= this.b) {
            return this.b;
        }
        int i2 = i >> 5;
        int i3 = this.a[i2] & (((1 << (i & 31)) - 1) ^ -1);
        while (i3 == 0) {
            i2++;
            if (i2 == this.a.length) {
                return this.b;
            }
            i3 = this.a[i2];
        }
        i3 = Integer.numberOfTrailingZeros(i3) + (i2 << 5);
        return i3 > this.b ? this.b : i3;
    }

    public int d(int i) {
        if (i >= this.b) {
            return this.b;
        }
        int i2 = i >> 5;
        int i3 = (this.a[i2] ^ -1) & (((1 << (i & 31)) - 1) ^ -1);
        while (i3 == 0) {
            i2++;
            if (i2 == this.a.length) {
                return this.b;
            }
            i3 = this.a[i2] ^ -1;
        }
        i3 = Integer.numberOfTrailingZeros(i3) + (i2 << 5);
        return i3 > this.b ? this.b : i3;
    }

    public void a(int i, int i2) {
        this.a[i >> 5] = i2;
    }

    public void b() {
        int length = this.a.length;
        for (int i = 0; i < length; i++) {
            this.a[i] = 0;
        }
    }

    public boolean a(int i, int i2, boolean z) {
        if (i2 < i) {
            throw new IllegalArgumentException();
        } else if (i2 == i) {
            return true;
        } else {
            int i3 = i2 - 1;
            int i4 = i >> 5;
            int i5 = i3 >> 5;
            int i6 = i4;
            while (i6 <= i5) {
                int i7;
                int i8 = i6 > i4 ? 0 : i & 31;
                int i9 = i6 < i5 ? 31 : i3 & 31;
                if (i8 == 0 && i9 == 31) {
                    i8 = -1;
                } else {
                    i7 = i8;
                    i8 = 0;
                    while (i7 <= i9) {
                        int i10 = (1 << i7) | i8;
                        i7++;
                        i8 = i10;
                    }
                }
                i7 = this.a[i6] & i8;
                if (!z) {
                    i8 = 0;
                }
                if (i7 != i8) {
                    return false;
                }
                i6++;
            }
            return true;
        }
    }

    public int[] c() {
        return this.a;
    }

    public void d() {
        int[] iArr = new int[this.a.length];
        int i = this.b;
        for (int i2 = 0; i2 < i; i2++) {
            if (a((i - i2) - 1)) {
                int i3 = i2 >> 5;
                iArr[i3] = iArr[i3] | (1 << (i2 & 31));
            }
        }
        this.a = iArr;
    }

    private static int[] e(int i) {
        return new int[((i + 31) >> 5)];
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.b);
        for (int i = 0; i < this.b; i++) {
            if ((i & 7) == 0) {
                stringBuilder.append(' ');
            }
            stringBuilder.append(a(i) ? 'X' : '.');
        }
        return stringBuilder.toString();
    }
}
