package com.google.zxing.common;

import com.google.zxing.NotFoundException;
import com.google.zxing.a;
import com.google.zxing.c;

/* compiled from: GlobalHistogramBinarizer */
public class g extends a {
    private static final byte[] a = new byte[0];
    private byte[] b = a;
    private final int[] c = new int[32];

    public g(c cVar) {
        super(cVar);
    }

    public a a(int i, a aVar) throws NotFoundException {
        int i2;
        int i3;
        int i4 = 1;
        c a = a();
        int b = a.b();
        if (aVar == null || aVar.a() < b) {
            aVar = new a(b);
        } else {
            aVar.b();
        }
        a(b);
        byte[] a2 = a.a(i, this.b);
        int[] iArr = this.c;
        for (i2 = 0; i2 < b; i2++) {
            i3 = (a2[i2] & 255) >> 3;
            iArr[i3] = iArr[i3] + 1;
        }
        i3 = a(iArr);
        i2 = a2[1] & 255;
        int i5 = a2[0] & 255;
        while (i4 < b - 1) {
            int i6 = a2[i4 + 1] & 255;
            if (((((i2 << 2) - i5) - i6) >> 1) < i3) {
                aVar.b(i4);
            }
            i4++;
            i5 = i2;
            i2 = i6;
        }
        return aVar;
    }

    public b b() throws NotFoundException {
        int i;
        int i2;
        c a = a();
        int b = a.b();
        int c = a.c();
        b bVar = new b(b, c);
        a(b);
        int[] iArr = this.c;
        for (i = 1; i < 5; i++) {
            byte[] a2 = a.a((c * i) / 5, this.b);
            int i3 = (b << 2) / 5;
            for (i2 = b / 5; i2 < i3; i2++) {
                int i4 = (a2[i2] & 255) >> 3;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int a3 = a(iArr);
        byte[] a4 = a.a();
        for (i = 0; i < c; i++) {
            int i5 = i * b;
            for (i2 = 0; i2 < b; i2++) {
                if ((a4[i5 + i2] & 255) < a3) {
                    bVar.b(i2, i);
                }
            }
        }
        return bVar;
    }

    public a a(c cVar) {
        return new g(cVar);
    }

    private void a(int i) {
        if (this.b.length < i) {
            this.b = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.c[i2] = 0;
        }
    }

    private static int a(int[] iArr) throws NotFoundException {
        int i;
        int i2;
        int i3 = 0;
        int length = iArr.length;
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        for (i = 0; i < length; i++) {
            if (iArr[i] > i4) {
                i4 = iArr[i];
                i5 = i;
            }
            if (iArr[i] > i6) {
                i6 = iArr[i];
            }
        }
        i = 0;
        int i7 = 0;
        while (i3 < length) {
            i4 = i3 - i5;
            i4 *= iArr[i3] * i4;
            if (i4 > i) {
                i = i3;
            } else {
                i4 = i;
                i = i7;
            }
            i3++;
            i7 = i;
            i = i4;
        }
        if (i5 > i7) {
            i2 = i7;
            i7 = i5;
        } else {
            i2 = i5;
        }
        if (i7 - i2 <= (length >> 4)) {
            throw NotFoundException.getNotFoundInstance();
        }
        i3 = i7 - 1;
        i5 = -1;
        i = i7 - 1;
        while (i > i2) {
            i4 = i - i2;
            i4 = ((i4 * i4) * (i7 - i)) * (i6 - iArr[i]);
            if (i4 > i5) {
                i5 = i;
            } else {
                i4 = i5;
                i5 = i3;
            }
            i--;
            i3 = i5;
            i5 = i4;
        }
        return i3 << 3;
    }
}
