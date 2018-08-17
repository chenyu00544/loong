package com.google.zxing.datamatrix.a;

/* compiled from: DataBlock */
final class b {
    private final int a;
    private final byte[] b;

    private b(int i, byte[] bArr) {
        this.a = i;
        this.b = bArr;
    }

    static b[] a(byte[] bArr, e eVar) {
        int i;
        int i2;
        b g = eVar.g();
        a[] b = g.b();
        int i3 = 0;
        for (a a : b) {
            i3 += a.a();
        }
        b[] bVarArr = new b[i3];
        int length = b.length;
        int i4 = 0;
        int i5 = 0;
        while (i4 < length) {
            a aVar = b[i4];
            i3 = i5;
            i5 = 0;
            while (i5 < aVar.a()) {
                int b2 = aVar.b();
                i = i3 + 1;
                bVarArr[i3] = new b(b2, new byte[(g.a() + b2)]);
                i5++;
                i3 = i;
            }
            i4++;
            i5 = i3;
        }
        i = bVarArr[0].b.length - g.a();
        length = i - 1;
        int i6 = 0;
        for (i2 = 0; i2 < length; i2++) {
            i3 = 0;
            while (i3 < i5) {
                i4 = i6 + 1;
                bVarArr[i3].b[i2] = bArr[i6];
                i3++;
                i6 = i4;
            }
        }
        if (eVar.a() == 24) {
            length = 1;
        } else {
            length = 0;
        }
        if (length != 0) {
            i3 = 8;
        } else {
            i3 = i5;
        }
        i4 = 0;
        while (i4 < i3) {
            i2 = i6 + 1;
            bVarArr[i4].b[i - 1] = bArr[i6];
            i4++;
            i6 = i2;
        }
        int length2 = bVarArr[0].b.length;
        i3 = i6;
        while (i < length2) {
            i4 = 0;
            i6 = i3;
            while (i4 < i5) {
                if (length == 0 || i4 <= 7) {
                    i3 = i;
                } else {
                    i3 = i - 1;
                }
                i2 = i6 + 1;
                bVarArr[i4].b[i3] = bArr[i6];
                i4++;
                i6 = i2;
            }
            i++;
            i3 = i6;
        }
        if (i3 == bArr.length) {
            return bVarArr;
        }
        throw new IllegalArgumentException();
    }

    int a() {
        return this.a;
    }

    byte[] b() {
        return this.b;
    }
}
