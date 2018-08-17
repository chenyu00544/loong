package com.google.zxing.datamatrix.a;

import com.baidu.location.b.g;
import com.google.zxing.FormatException;

/* compiled from: Version */
public final class e {
    private static final e[] a = h();
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final b g;
    private final int h;

    /* compiled from: Version */
    static final class a {
        private final int a;
        private final int b;

        private a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        int a() {
            return this.a;
        }

        int b() {
            return this.b;
        }
    }

    /* compiled from: Version */
    static final class b {
        private final int a;
        private final a[] b;

        private b(int i, a aVar) {
            this.a = i;
            this.b = new a[]{aVar};
        }

        private b(int i, a aVar, a aVar2) {
            this.a = i;
            this.b = new a[]{aVar, aVar2};
        }

        int a() {
            return this.a;
        }

        a[] b() {
            return this.b;
        }
    }

    private e(int i, int i2, int i3, int i4, int i5, b bVar) {
        int i6 = 0;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = i5;
        this.g = bVar;
        int a = bVar.a();
        a[] b = bVar.b();
        int length = b.length;
        int i7 = 0;
        while (i6 < length) {
            a aVar = b[i6];
            i7 += (aVar.b() + a) * aVar.a();
            i6++;
        }
        this.h = i7;
    }

    public int a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public int e() {
        return this.f;
    }

    public int f() {
        return this.h;
    }

    b g() {
        return this.g;
    }

    public static e a(int i, int i2) throws FormatException {
        if ((i & 1) == 0 && (i2 & 1) == 0) {
            for (e eVar : a) {
                if (eVar.c == i && eVar.d == i2) {
                    return eVar;
                }
            }
            throw FormatException.getFormatInstance();
        }
        throw FormatException.getFormatInstance();
    }

    public String toString() {
        return String.valueOf(this.b);
    }

    private static e[] h() {
        return new e[]{new e(1, 10, 10, 8, 8, new b(5, new a(1, 3))), new e(2, 12, 12, 10, 10, new b(7, new a(1, 5))), new e(3, 14, 14, 12, 12, new b(10, new a(1, 8))), new e(4, 16, 16, 14, 14, new b(12, new a(1, 12))), new e(5, 18, 18, 16, 16, new b(14, new a(1, 18))), new e(6, 20, 20, 18, 18, new b(18, new a(1, 22))), new e(7, 22, 22, 20, 20, new b(20, new a(1, 30))), new e(8, 24, 24, 22, 22, new b(24, new a(1, 36))), new e(9, 26, 26, 24, 24, new b(28, new a(1, 44))), new e(10, 32, 32, 14, 14, new b(36, new a(1, 62))), new e(11, 36, 36, 16, 16, new b(42, new a(1, 86))), new e(12, 40, 40, 18, 18, new b(48, new a(1, 114))), new e(13, 44, 44, 20, 20, new b(56, new a(1, 144))), new e(14, 48, 48, 22, 22, new b(68, new a(1, 174))), new e(15, 52, 52, 24, 24, new b(42, new a(2, 102))), new e(16, 64, 64, 14, 14, new b(56, new a(2, 140))), new e(17, 72, 72, 16, 16, new b(36, new a(4, 92))), new e(18, 80, 80, 18, 18, new b(48, new a(4, 114))), new e(19, 88, 88, 20, 20, new b(56, new a(4, 144))), new e(20, 96, 96, 22, 22, new b(68, new a(4, 174))), new e(21, 104, 104, 24, 24, new b(56, new a(6, 136))), new e(22, g.L, g.L, 18, 18, new b(68, new a(6, 175))), new e(23, 132, 132, 20, 20, new b(62, new a(8, 163))), new e(24, 144, 144, 22, 22, new b(62, new a(8, 156), new a(2, 155))), new e(25, 8, 18, 6, 16, new b(7, new a(1, 5))), new e(26, 8, 32, 6, 14, new b(11, new a(1, 10))), new e(27, 12, 26, 10, 24, new b(14, new a(1, 16))), new e(28, 12, 36, 10, 16, new b(18, new a(1, 22))), new e(29, 16, 36, 14, 16, new b(24, new a(1, 32))), new e(30, 16, 48, 14, 22, new b(28, new a(1, 49)))};
    }
}
