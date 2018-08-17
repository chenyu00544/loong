package com.google.zxing.pdf417.a;

import android.support.v4.widget.AutoScrollHelper;
import com.google.zxing.pdf417.a;
import java.lang.reflect.Array;

/* compiled from: PDF417CodewordDecoder */
final class i {
    private static final float[][] a = ((float[][]) Array.newInstance(Float.TYPE, new int[]{a.a.length, 8}));

    static {
        for (int i = 0; i < a.a.length; i++) {
            int i2 = a.a[i];
            int i3 = i2 & 1;
            for (int i4 = 0; i4 < 8; i4++) {
                float f = 0.0f;
                while ((i2 & 1) == i3) {
                    f += 1.0f;
                    i2 >>= 1;
                }
                i3 = i2 & 1;
                a[i][(8 - i4) - 1] = f / 17.0f;
            }
        }
    }

    static int a(int[] iArr) {
        int c = c(b(iArr));
        return c != -1 ? c : e(iArr);
    }

    private static int[] b(int[] iArr) {
        int i = 0;
        float a = (float) a.a(iArr);
        int[] iArr2 = new int[8];
        int i2 = 0;
        for (int i3 = 0; i3 < 17; i3++) {
            if (((float) (iArr[i2] + i)) <= (a / 34.0f) + ((((float) i3) * a) / 17.0f)) {
                i += iArr[i2];
                i2++;
            }
            iArr2[i2] = iArr2[i2] + 1;
        }
        return iArr2;
    }

    private static int c(int[] iArr) {
        int d = d(iArr);
        if (a.a((long) d) == -1) {
            return -1;
        }
        return d;
    }

    private static int d(int[] iArr) {
        long j = 0;
        for (int i = 0; i < iArr.length; i++) {
            int i2 = 0;
            while (i2 < iArr[i]) {
                int i3;
                long j2 = j << 1;
                if (i % 2 == 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
                i2++;
                j = j2 | ((long) i3);
            }
        }
        return (int) j;
    }

    private static int e(int[] iArr) {
        int i;
        int a = a.a(iArr);
        float[] fArr = new float[8];
        for (i = 0; i < fArr.length; i++) {
            fArr[i] = ((float) iArr[i]) / ((float) a);
        }
        float f = AutoScrollHelper.NO_MAX;
        a = -1;
        for (i = 0; i < a.length; i++) {
            float f2 = 0.0f;
            int i2 = 0;
            while (i2 < 8) {
                float f3 = a[i][i2] - fArr[i2];
                i2++;
                f2 = (f3 * f3) + f2;
            }
            if (f2 < f) {
                a = a.a[i];
                f = f2;
            }
        }
        return a;
    }
}
