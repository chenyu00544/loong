package com.google.zxing.c.a;

import com.google.zxing.NotFoundException;
import com.google.zxing.c.k;

/* compiled from: AbstractRSSReader */
public abstract class a extends k {
    private final int[] a = new int[4];
    private final int[] b = new int[8];
    private final float[] c = new float[4];
    private final float[] d = new float[4];
    private final int[] e = new int[(this.b.length / 2)];
    private final int[] f = new int[(this.b.length / 2)];

    protected a() {
    }

    protected final int[] b() {
        return this.a;
    }

    protected final int[] c() {
        return this.b;
    }

    protected final float[] d() {
        return this.c;
    }

    protected final float[] e() {
        return this.d;
    }

    protected final int[] f() {
        return this.e;
    }

    protected final int[] g() {
        return this.f;
    }

    protected static int a(int[] iArr, int[][] iArr2) throws NotFoundException {
        for (int i = 0; i < iArr2.length; i++) {
            if (k.a(iArr, iArr2[i], 115) < 51) {
                return i;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static int a(int[] iArr) {
        int i = 0;
        int i2 = 0;
        while (i < iArr.length) {
            i2 += iArr[i];
            i++;
        }
        return i2;
    }

    protected static void a(int[] iArr, float[] fArr) {
        int i = 0;
        float f = fArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] > f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] + 1;
    }

    protected static void b(int[] iArr, float[] fArr) {
        int i = 0;
        float f = fArr[0];
        for (int i2 = 1; i2 < iArr.length; i2++) {
            if (fArr[i2] < f) {
                f = fArr[i2];
                i = i2;
            }
        }
        iArr[i] = iArr[i] - 1;
    }

    protected static boolean b(int[] iArr) {
        int i = iArr[0] + iArr[1];
        float f = ((float) i) / ((float) ((iArr[2] + i) + iArr[3]));
        if (f < 0.7916667f || f > 0.89285713f) {
            return false;
        }
        int i2 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i3 = Integer.MIN_VALUE;
        int length = iArr.length;
        int i4 = 0;
        while (i4 < length) {
            i = iArr[i4];
            if (i > i3) {
                i3 = i;
            }
            if (i >= i2) {
                i = i2;
            }
            i4++;
            i2 = i;
        }
        if (i3 < i2 * 10) {
            return true;
        }
        return false;
    }
}
