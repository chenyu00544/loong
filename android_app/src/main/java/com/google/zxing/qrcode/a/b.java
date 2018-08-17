package com.google.zxing.qrcode.a;

import com.google.zxing.NotFoundException;
import com.google.zxing.h;
import com.google.zxing.i;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AlignmentPatternFinder */
final class b {
    private final com.google.zxing.common.b a;
    private final List<a> b = new ArrayList(5);
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final float g;
    private final int[] h;
    private final i i;

    b(com.google.zxing.common.b bVar, int i, int i2, int i3, int i4, float f, i iVar) {
        this.a = bVar;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = f;
        this.h = new int[3];
        this.i = iVar;
    }

    a a() throws NotFoundException {
        int i = this.c;
        int i2 = this.f;
        int i3 = i + this.e;
        int i4 = this.d + (i2 >> 1);
        int[] iArr = new int[3];
        for (int i5 = 0; i5 < i2; i5++) {
            int i6;
            if ((i5 & 1) == 0) {
                i6 = (i5 + 1) >> 1;
            } else {
                i6 = -((i5 + 1) >> 1);
            }
            int i7 = i4 + i6;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            i6 = i;
            while (i6 < i3 && !this.a.a(i6, i7)) {
                i6++;
            }
            i6 = 0;
            for (int i8 = i6; i8 < i3; i8++) {
                a a;
                if (!this.a.a(i8, i7)) {
                    if (i6 == 1) {
                        i6++;
                    }
                    iArr[i6] = iArr[i6] + 1;
                } else if (i6 == 1) {
                    iArr[i6] = iArr[i6] + 1;
                } else if (i6 == 2) {
                    if (a(iArr)) {
                        a = a(iArr, i7, i8);
                        if (a != null) {
                            return a;
                        }
                    }
                    iArr[0] = iArr[2];
                    iArr[1] = 1;
                    iArr[2] = 0;
                    i6 = 1;
                } else {
                    i6++;
                    iArr[i6] = iArr[i6] + 1;
                }
            }
            if (a(iArr)) {
                a = a(iArr, i7, i3);
                if (a != null) {
                    return a;
                }
            }
        }
        if (!this.b.isEmpty()) {
            return (a) this.b.get(0);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static float a(int[] iArr, int i) {
        return ((float) (i - iArr[2])) - (((float) iArr[1]) / 2.0f);
    }

    private boolean a(int[] iArr) {
        float f = this.g;
        float f2 = f / 2.0f;
        for (int i = 0; i < 3; i++) {
            if (Math.abs(f - ((float) iArr[i])) >= f2) {
                return false;
            }
        }
        return true;
    }

    private float a(int i, int i2, int i3, int i4) {
        com.google.zxing.common.b bVar = this.a;
        int e = bVar.e();
        int[] iArr = this.h;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        int i5 = i;
        while (i5 >= 0 && bVar.a(i2, i5) && iArr[1] <= i3) {
            iArr[1] = iArr[1] + 1;
            i5--;
        }
        if (i5 < 0 || iArr[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bVar.a(i2, i5) && iArr[0] <= i3) {
            iArr[0] = iArr[0] + 1;
            i5--;
        }
        if (iArr[0] > i3) {
            return Float.NaN;
        }
        i5 = i + 1;
        while (i5 < e && bVar.a(i2, i5) && iArr[1] <= i3) {
            iArr[1] = iArr[1] + 1;
            i5++;
        }
        if (i5 == e || iArr[1] > i3) {
            return Float.NaN;
        }
        while (i5 < e && !bVar.a(i2, i5) && iArr[2] <= i3) {
            iArr[2] = iArr[2] + 1;
            i5++;
        }
        if (iArr[2] > i3 || Math.abs(((iArr[0] + iArr[1]) + iArr[2]) - i4) * 5 >= i4 * 2 || !a(iArr)) {
            return Float.NaN;
        }
        return a(iArr, i5);
    }

    private a a(int[] iArr, int i, int i2) {
        int i3 = (iArr[0] + iArr[1]) + iArr[2];
        float a = a(iArr, i2);
        float a2 = a(i, (int) a, iArr[1] * 2, i3);
        if (!Float.isNaN(a2)) {
            float f = ((float) ((iArr[0] + iArr[1]) + iArr[2])) / 3.0f;
            for (a aVar : this.b) {
                if (aVar.a(f, a2, a)) {
                    return aVar.b(a2, a, f);
                }
            }
            h aVar2 = new a(a, a2, f);
            this.b.add(aVar2);
            if (this.i != null) {
                this.i.a(aVar2);
            }
        }
        return null;
    }
}
