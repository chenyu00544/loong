package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.b;

/* compiled from: BitMatrixParser */
final class a {
    private final b a;
    private g b;
    private f c;

    a(b bVar) throws FormatException {
        int e = bVar.e();
        if (e < 21 || (e & 3) != 1) {
            throw FormatException.getFormatInstance();
        }
        this.a = bVar;
    }

    f a() throws FormatException {
        int i = 0;
        if (this.c != null) {
            return this.c;
        }
        int i2;
        int i3 = 0;
        for (i2 = 0; i2 < 6; i2++) {
            i3 = a(i2, 8, i3);
        }
        i3 = a(8, 7, a(8, 8, a(7, 8, i3)));
        for (i2 = 5; i2 >= 0; i2--) {
            i3 = a(8, i2, i3);
        }
        int e = this.a.e();
        int i4 = e - 7;
        for (i2 = e - 1; i2 >= i4; i2--) {
            i = a(8, i2, i);
        }
        for (i2 = e - 8; i2 < e; i2++) {
            i = a(i2, 8, i);
        }
        this.c = f.b(i3, i);
        if (this.c != null) {
            return this.c;
        }
        throw FormatException.getFormatInstance();
    }

    g b() throws FormatException {
        if (this.b != null) {
            return this.b;
        }
        int e = this.a.e();
        int i = (e - 17) >> 2;
        if (i <= 6) {
            return g.b(i);
        }
        int i2 = e - 11;
        int i3 = 0;
        for (int i4 = 5; i4 >= 0; i4--) {
            for (i = e - 9; i >= i2; i--) {
                i3 = a(i, i4, i3);
            }
        }
        g c = g.c(i3);
        if (c == null || c.d() != e) {
            int i5 = 0;
            for (int i6 = 5; i6 >= 0; i6--) {
                for (i = e - 9; i >= i2; i--) {
                    i5 = a(i6, i, i5);
                }
            }
            c = g.c(i5);
            if (c == null || c.d() != e) {
                throw FormatException.getFormatInstance();
            }
            this.b = c;
            return c;
        }
        this.b = c;
        return c;
    }

    private int a(int i, int i2, int i3) {
        return this.a.a(i, i2) ? (i3 << 1) | 1 : i3 << 1;
    }

    byte[] c() throws FormatException {
        f a = a();
        g b = b();
        c a2 = c.a(a.b());
        int e = this.a.e();
        a2.a(this.a, e);
        b e2 = b.e();
        byte[] bArr = new byte[b.c()];
        int i = e - 1;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        while (i > 0) {
            if (i == 6) {
                i--;
            }
            for (int i6 = 0; i6 < e; i6++) {
                int i7;
                if (i5 != 0) {
                    i7 = (e - 1) - i6;
                } else {
                    i7 = i6;
                }
                for (int i8 = 0; i8 < 2; i8++) {
                    if (!e2.a(i - i8, i7)) {
                        i2++;
                        i3 <<= 1;
                        if (this.a.a(i - i8, i7)) {
                            i3 |= 1;
                        }
                        if (i2 == 8) {
                            i2 = i4 + 1;
                            bArr[i4] = (byte) i3;
                            i3 = 0;
                            i4 = i2;
                            i2 = 0;
                        }
                    }
                }
            }
            i -= 2;
            i5 ^= 1;
        }
        if (i4 == b.c()) {
            return bArr;
        }
        throw FormatException.getFormatInstance();
    }
}
