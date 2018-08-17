package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.a;

/* compiled from: EAN13Reader */
public final class e extends p {
    static final int[] a = new int[]{0, 11, 13, 14, 19, 25, 28, 21, 22, 26};
    private final int[] f = new int[4];

    protected int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws NotFoundException {
        int i;
        int[] iArr2 = this.f;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int a = aVar.a();
        int i2 = iArr[1];
        int i3 = 0;
        int i4 = 0;
        while (i3 < 6 && i2 < a) {
            int a2 = p.a(aVar, iArr2, i2, e);
            stringBuilder.append((char) ((a2 % 10) + 48));
            i = i2;
            for (int i5 : iArr2) {
                i += i5;
            }
            if (a2 >= 10) {
                i4 |= 1 << (5 - i3);
            }
            i3++;
            i2 = i;
        }
        a(stringBuilder, i4);
        i4 = p.a(aVar, i2, true, c)[1];
        i = 0;
        while (i < 6 && i4 < a) {
            stringBuilder.append((char) (p.a(aVar, iArr2, i4, d) + 48));
            i2 = i4;
            for (int a22 : iArr2) {
                i2 += a22;
            }
            i++;
            i4 = i2;
        }
        return i4;
    }

    BarcodeFormat b() {
        return BarcodeFormat.EAN_13;
    }

    private static void a(StringBuilder stringBuilder, int i) throws NotFoundException {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == a[i2]) {
                stringBuilder.insert(0, (char) (i2 + 48));
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
