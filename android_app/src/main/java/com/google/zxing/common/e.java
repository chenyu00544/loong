package com.google.zxing.common;

import com.google.zxing.NotFoundException;

/* compiled from: DefaultGridSampler */
public final class e extends h {
    public b a(b bVar, int i, int i2, float f, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, float f12, float f13, float f14, float f15, float f16) throws NotFoundException {
        return a(bVar, i, i2, j.a(f, f2, f3, f4, f5, f6, f7, f8, f9, f10, f11, f12, f13, f14, f15, f16));
    }

    public b a(b bVar, int i, int i2, j jVar) throws NotFoundException {
        if (i <= 0 || i2 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        b bVar2 = new b(i, i2);
        float[] fArr = new float[(i << 1)];
        for (int i3 = 0; i3 < i2; i3++) {
            int i4;
            int length = fArr.length;
            float f = ((float) i3) + 0.5f;
            for (i4 = 0; i4 < length; i4 += 2) {
                fArr[i4] = ((float) (i4 >> 1)) + 0.5f;
                fArr[i4 + 1] = f;
            }
            jVar.a(fArr);
            h.a(bVar, fArr);
            i4 = 0;
            while (i4 < length) {
                try {
                    if (bVar.a((int) fArr[i4], (int) fArr[i4 + 1])) {
                        bVar2.b(i4 >> 1, i3);
                    }
                    i4 += 2;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
        return bVar2;
    }
}
