package com.google.zxing.common.a;

import com.google.zxing.NotFoundException;
import com.google.zxing.h;

/* compiled from: WhiteRectangleDetector */
public final class b {
    private final com.google.zxing.common.b a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int g;

    public b(com.google.zxing.common.b bVar) throws NotFoundException {
        this.a = bVar;
        this.b = bVar.e();
        this.c = bVar.d();
        this.d = (this.c - 30) >> 1;
        this.e = (this.c + 30) >> 1;
        this.g = (this.b - 30) >> 1;
        this.f = (this.b + 30) >> 1;
        if (this.g < 0 || this.d < 0 || this.f >= this.b || this.e >= this.c) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public b(com.google.zxing.common.b bVar, int i, int i2, int i3) throws NotFoundException {
        this.a = bVar;
        this.b = bVar.e();
        this.c = bVar.d();
        int i4 = i >> 1;
        this.d = i2 - i4;
        this.e = i2 + i4;
        this.g = i3 - i4;
        this.f = i4 + i3;
        if (this.g < 0 || this.d < 0 || this.f >= this.b || this.e >= this.c) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    public h[] a() throws NotFoundException {
        int i;
        boolean z = false;
        int i2 = 1;
        int i3 = this.d;
        int i4 = this.e;
        int i5 = this.g;
        int i6 = this.f;
        boolean z2 = false;
        int i7 = 1;
        while (i7 != 0) {
            boolean z3 = true;
            boolean z4 = false;
            while (z3 && i4 < this.c) {
                z3 = a(i5, i6, i4, false);
                if (z3) {
                    i4++;
                    z4 = true;
                }
            }
            if (i4 >= this.c) {
                z = true;
                i = i4;
                i4 = i6;
                i6 = i3;
                i3 = i5;
                break;
            }
            z3 = true;
            while (z3 && i6 < this.b) {
                z3 = a(i3, i4, i6, true);
                if (z3) {
                    i6++;
                    z4 = true;
                }
            }
            if (i6 >= this.b) {
                z = true;
                i = i4;
                i4 = i6;
                i6 = i3;
                i3 = i5;
                break;
            }
            z3 = true;
            while (z3 && i3 >= 0) {
                z3 = a(i5, i6, i3, false);
                if (z3) {
                    i3--;
                    z4 = true;
                }
            }
            if (i3 < 0) {
                z = true;
                i = i4;
                i4 = i6;
                i6 = i3;
                i3 = i5;
                break;
            }
            i7 = z4;
            z4 = true;
            while (z4 && i5 >= 0) {
                z4 = a(i3, i4, i5, true);
                if (z4) {
                    i5--;
                    i7 = 1;
                }
            }
            if (i5 < 0) {
                z = true;
                i = i4;
                i4 = i6;
                i6 = i3;
                i3 = i5;
                break;
            } else if (i7 != 0) {
                z2 = true;
            }
        }
        i = i4;
        i4 = i6;
        i6 = i3;
        i3 = i5;
        if (z || !r0) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i8;
        h hVar;
        i7 = i - i6;
        h hVar2 = null;
        for (i8 = 1; i8 < i7; i8++) {
            hVar2 = a((float) i6, (float) (i4 - i8), (float) (i6 + i8), (float) i4);
            if (hVar2 != null) {
                hVar = hVar2;
                break;
            }
        }
        hVar = hVar2;
        if (hVar == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        h hVar3;
        hVar2 = null;
        for (i8 = 1; i8 < i7; i8++) {
            hVar2 = a((float) i6, (float) (i3 + i8), (float) (i6 + i8), (float) i3);
            if (hVar2 != null) {
                hVar3 = hVar2;
                break;
            }
        }
        hVar3 = hVar2;
        if (hVar3 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        h hVar4;
        hVar2 = null;
        for (i8 = 1; i8 < i7; i8++) {
            hVar2 = a((float) i, (float) (i3 + i8), (float) (i - i8), (float) i3);
            if (hVar2 != null) {
                hVar4 = hVar2;
                break;
            }
        }
        hVar4 = hVar2;
        if (hVar4 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        hVar2 = null;
        while (i2 < i7) {
            hVar2 = a((float) i, (float) (i4 - i2), (float) (i - i2), (float) i4);
            if (hVar2 != null) {
                break;
            }
            i2++;
        }
        if (hVar2 != null) {
            return a(hVar2, hVar, hVar4, hVar3);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private h a(float f, float f2, float f3, float f4) {
        int a = a.a(a.a(f, f2, f3, f4));
        float f5 = (f3 - f) / ((float) a);
        float f6 = (f4 - f2) / ((float) a);
        for (int i = 0; i < a; i++) {
            int a2 = a.a((((float) i) * f5) + f);
            int a3 = a.a((((float) i) * f6) + f2);
            if (this.a.a(a2, a3)) {
                return new h((float) a2, (float) a3);
            }
        }
        return null;
    }

    private h[] a(h hVar, h hVar2, h hVar3, h hVar4) {
        float a = hVar.a();
        float b = hVar.b();
        float a2 = hVar2.a();
        float b2 = hVar2.b();
        float a3 = hVar3.a();
        float b3 = hVar3.b();
        float a4 = hVar4.a();
        float b4 = hVar4.b();
        if (a < ((float) this.c) / 2.0f) {
            return new h[]{new h(a4 - 1.0f, b4 + 1.0f), new h(a2 + 1.0f, b2 + 1.0f), new h(a3 - 1.0f, b3 - 1.0f), new h(a + 1.0f, b - 1.0f)};
        }
        return new h[]{new h(a4 + 1.0f, b4 + 1.0f), new h(a2 + 1.0f, b2 - 1.0f), new h(a3 - 1.0f, b3 + 1.0f), new h(a - 1.0f, b - 1.0f)};
    }

    private boolean a(int i, int i2, int i3, boolean z) {
        if (z) {
            while (i <= i2) {
                if (this.a.a(i, i3)) {
                    return true;
                }
                i++;
            }
        } else {
            while (i <= i2) {
                if (this.a.a(i3, i)) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }
}
