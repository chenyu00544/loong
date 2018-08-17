package com.google.zxing.common;

/* compiled from: BitMatrix */
public final class b {
    private final int a;
    private final int b;
    private final int c;
    private final int[] d;

    public b(int i) {
        this(i, i);
    }

    public b(int i, int i2) {
        if (i < 1 || i2 < 1) {
            throw new IllegalArgumentException("Both dimensions must be greater than 0");
        }
        this.a = i;
        this.b = i2;
        this.c = (i + 31) >> 5;
        this.d = new int[(this.c * i2)];
    }

    public boolean a(int i, int i2) {
        return ((this.d[(this.c * i2) + (i >> 5)] >>> (i & 31)) & 1) != 0;
    }

    public void b(int i, int i2) {
        int i3 = (this.c * i2) + (i >> 5);
        int[] iArr = this.d;
        iArr[i3] = iArr[i3] | (1 << (i & 31));
    }

    public void c(int i, int i2) {
        int i3 = (this.c * i2) + (i >> 5);
        int[] iArr = this.d;
        iArr[i3] = iArr[i3] ^ (1 << (i & 31));
    }

    public void a(int i, int i2, int i3, int i4) {
        if (i2 < 0 || i < 0) {
            throw new IllegalArgumentException("Left and top must be nonnegative");
        } else if (i4 < 1 || i3 < 1) {
            throw new IllegalArgumentException("Height and width must be at least 1");
        } else {
            int i5 = i + i3;
            int i6 = i2 + i4;
            if (i6 > this.b || i5 > this.a) {
                throw new IllegalArgumentException("The region must fit inside the matrix");
            }
            while (i2 < i6) {
                int i7 = i2 * this.c;
                for (int i8 = i; i8 < i5; i8++) {
                    int[] iArr = this.d;
                    int i9 = (i8 >> 5) + i7;
                    iArr[i9] = iArr[i9] | (1 << (i8 & 31));
                }
                i2++;
            }
        }
    }

    public a a(int i, a aVar) {
        if (aVar == null || aVar.a() < this.a) {
            aVar = new a(this.a);
        }
        int i2 = i * this.c;
        for (int i3 = 0; i3 < this.c; i3++) {
            aVar.a(i3 << 5, this.d[i2 + i3]);
        }
        return aVar;
    }

    public void b(int i, a aVar) {
        System.arraycopy(aVar.c(), 0, this.d, this.c * i, this.c);
    }

    public int[] a() {
        int i = this.a;
        int i2 = this.b;
        int i3 = i;
        i = -1;
        int i4 = -1;
        for (int i5 = 0; i5 < this.b; i5++) {
            int i6 = 0;
            while (i6 < this.c) {
                int i7;
                int i8 = this.d[(this.c * i5) + i6];
                if (i8 != 0) {
                    if (i5 < i2) {
                        i2 = i5;
                    }
                    if (i5 > i4) {
                        i4 = i5;
                    }
                    if (i6 * 32 < i3) {
                        i7 = 0;
                        while ((i8 << (31 - i7)) == 0) {
                            i7++;
                        }
                        if ((i6 * 32) + i7 < i3) {
                            i7 = (i6 * 32) + i7;
                            if ((i6 * 32) + 31 > i) {
                                i3 = 31;
                                while ((i8 >>> i3) == 0) {
                                    i3--;
                                }
                                if ((i6 * 32) + i3 > i) {
                                    i = (i6 * 32) + i3;
                                    i3 = i2;
                                    i2 = i;
                                    i = i4;
                                }
                            }
                            i3 = i2;
                            i2 = i;
                            i = i4;
                        }
                    }
                    i7 = i3;
                    if ((i6 * 32) + 31 > i) {
                        i3 = 31;
                        while ((i8 >>> i3) == 0) {
                            i3--;
                        }
                        if ((i6 * 32) + i3 > i) {
                            i = (i6 * 32) + i3;
                            i3 = i2;
                            i2 = i;
                            i = i4;
                        }
                    }
                    i3 = i2;
                    i2 = i;
                    i = i4;
                } else {
                    i7 = i3;
                    i3 = i2;
                    i2 = i;
                    i = i4;
                }
                i6++;
                i4 = i;
                i = i2;
                i2 = i3;
                i3 = i7;
            }
        }
        i4 -= i2;
        if (i - i3 < 0 || i4 < 0) {
            return null;
        }
        return new int[]{i3, i2, i, i4};
    }

    public int[] b() {
        int i = 0;
        while (i < this.d.length && this.d[i] == 0) {
            i++;
        }
        if (i == this.d.length) {
            return null;
        }
        int i2 = i / this.c;
        int i3 = (i % this.c) << 5;
        int i4 = this.d[i];
        i = 0;
        while ((i4 << (31 - i)) == 0) {
            i++;
        }
        i3 += i;
        return new int[]{i3, i2};
    }

    public int[] c() {
        int length = this.d.length - 1;
        while (length >= 0 && this.d[length] == 0) {
            length--;
        }
        if (length < 0) {
            return null;
        }
        int i = length / this.c;
        int i2 = (length % this.c) << 5;
        int i3 = this.d[length];
        length = 31;
        while ((i3 >>> length) == 0) {
            length--;
        }
        i2 += length;
        return new int[]{i2, i};
    }

    public int d() {
        return this.a;
    }

    public int e() {
        return this.b;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a != bVar.a || this.b != bVar.b || this.c != bVar.c || this.d.length != bVar.d.length) {
            return false;
        }
        for (int i = 0; i < this.d.length; i++) {
            if (this.d[i] != bVar.d[i]) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = this.c + (((((this.a * 31) + this.a) * 31) + this.b) * 31);
        for (int i2 : this.d) {
            i = (i * 31) + i2;
        }
        return i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(this.b * (this.a + 1));
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.a; i2++) {
                stringBuilder.append(a(i2, i) ? "X " : "  ");
            }
            stringBuilder.append('\n');
        }
        return stringBuilder.toString();
    }
}
