package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.a;

/* compiled from: EAN8Reader */
public final class f extends p {
    private final int[] a = new int[4];

    protected int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws NotFoundException {
        int[] iArr2 = this.a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int a = aVar.a();
        int i = iArr[1];
        int i2 = 0;
        while (i2 < 4 && i < a) {
            stringBuilder.append((char) (p.a(aVar, iArr2, i, d) + 48));
            int i3 = i;
            for (int i4 : iArr2) {
                i3 += i4;
            }
            i2++;
            i = i3;
        }
        i = p.a(aVar, i, true, c)[1];
        i2 = 0;
        while (i2 < 4 && i < a) {
            stringBuilder.append((char) (p.a(aVar, iArr2, i, d) + 48));
            i3 = i;
            for (int i42 : iArr2) {
                i3 += i42;
            }
            i2++;
            i = i3;
        }
        return i;
    }

    BarcodeFormat b() {
        return BarcodeFormat.EAN_8;
    }
}
