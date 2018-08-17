package com.google.zxing.a.b;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.b;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.google.zxing.common.reedsolomon.c;
import com.google.zxing.h;

/* compiled from: Detector */
public final class a {
    private final b a;
    private boolean b;
    private int c;
    private int d;
    private int e;
    private int f;

    /* compiled from: Detector */
    static final class a {
        private final int a;
        private final int b;

        h a() {
            return new h((float) b(), (float) c());
        }

        a(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        int b() {
            return this.a;
        }

        int c() {
            return this.b;
        }
    }

    public a(b bVar) {
        this.a = bVar;
    }

    public com.google.zxing.a.a a() throws NotFoundException {
        a[] a = a(b());
        a(a);
        h[] b = b(a);
        return new com.google.zxing.a.a(a(this.a, b[this.f % 4], b[(this.f + 3) % 4], b[(this.f + 2) % 4], b[(this.f + 1) % 4]), b, this.b, this.d, this.c);
    }

    private void a(a[] aVarArr) throws NotFoundException {
        boolean[] zArr;
        int i = 0;
        int i2 = this.e * 2;
        boolean[] a = a(aVarArr[0], aVarArr[1], i2 + 1);
        boolean[] a2 = a(aVarArr[1], aVarArr[2], i2 + 1);
        boolean[] a3 = a(aVarArr[2], aVarArr[3], i2 + 1);
        boolean[] a4 = a(aVarArr[3], aVarArr[0], i2 + 1);
        if (a[0] && a[i2]) {
            this.f = 0;
        } else if (a2[0] && a2[i2]) {
            this.f = 1;
        } else if (a3[0] && a3[i2]) {
            this.f = 2;
        } else if (a4[0] && a4[i2]) {
            this.f = 3;
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
        boolean[] zArr2;
        if (this.b) {
            zArr2 = new boolean[28];
            for (i2 = 0; i2 < 7; i2++) {
                zArr2[i2] = a[i2 + 2];
                zArr2[i2 + 7] = a2[i2 + 2];
                zArr2[i2 + 14] = a3[i2 + 2];
                zArr2[i2 + 21] = a4[i2 + 2];
            }
            zArr = new boolean[28];
            while (i < 28) {
                zArr[i] = zArr2[((this.f * 7) + i) % 28];
                i++;
            }
        } else {
            zArr2 = new boolean[40];
            for (i2 = 0; i2 < 11; i2++) {
                if (i2 < 5) {
                    zArr2[i2] = a[i2 + 2];
                    zArr2[i2 + 10] = a2[i2 + 2];
                    zArr2[i2 + 20] = a3[i2 + 2];
                    zArr2[i2 + 30] = a4[i2 + 2];
                }
                if (i2 > 5) {
                    zArr2[i2 - 1] = a[i2 + 2];
                    zArr2[i2 + 9] = a2[i2 + 2];
                    zArr2[i2 + 19] = a3[i2 + 2];
                    zArr2[i2 + 29] = a4[i2 + 2];
                }
            }
            zArr = new boolean[40];
            while (i < 40) {
                zArr[i] = zArr2[((this.f * 10) + i) % 40];
                i++;
            }
        }
        a(zArr, this.b);
        a(zArr);
    }

    private h[] b(a[] aVarArr) throws NotFoundException {
        int i;
        int i2 = -1;
        float f = ((float) (((this.c > 4 ? 1 : 0) + (this.c * 2)) + ((this.c - 4) / 8))) / (2.0f * ((float) this.e));
        int b = aVarArr[0].b() - aVarArr[2].b();
        if (b > 0) {
            i = 1;
        } else {
            i = -1;
        }
        b += i;
        int c = aVarArr[0].c() - aVarArr[2].c();
        if (c > 0) {
            i = 1;
        } else {
            i = -1;
        }
        i += c;
        c = com.google.zxing.common.a.a.a(((float) aVarArr[2].b()) - (((float) b) * f));
        int a = com.google.zxing.common.a.a.a(((float) aVarArr[2].c()) - (((float) i) * f));
        b = com.google.zxing.common.a.a.a((((float) b) * f) + ((float) aVarArr[0].b()));
        int a2 = com.google.zxing.common.a.a.a((((float) i) * f) + ((float) aVarArr[0].c()));
        int b2 = aVarArr[1].b() - aVarArr[3].b();
        if (b2 > 0) {
            i = 1;
        } else {
            i = -1;
        }
        i += b2;
        b2 = aVarArr[1].c() - aVarArr[3].c();
        if (b2 > 0) {
            i2 = 1;
        }
        i2 += b2;
        b2 = com.google.zxing.common.a.a.a(((float) aVarArr[3].b()) - (((float) i) * f));
        int a3 = com.google.zxing.common.a.a.a(((float) aVarArr[3].c()) - (((float) i2) * f));
        i = com.google.zxing.common.a.a.a((((float) i) * f) + ((float) aVarArr[1].b()));
        i2 = com.google.zxing.common.a.a.a((((float) i2) * f) + ((float) aVarArr[1].c()));
        if (a(b, a2) && a(i, i2) && a(c, a) && a(b2, a3)) {
            return new h[]{new h((float) b, (float) a2), new h((float) i, (float) i2), new h((float) c, (float) a), new h((float) b2, (float) a3)};
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void a(boolean[] zArr, boolean z) throws NotFoundException {
        int i;
        int i2;
        int i3;
        int i4;
        if (z) {
            i = 7;
            i2 = 2;
        } else {
            i = 10;
            i2 = 4;
        }
        int i5 = i - i2;
        int[] iArr = new int[i];
        for (i3 = 0; i3 < i; i3++) {
            int i6 = 1;
            for (i4 = 1; i4 <= 4; i4++) {
                if (zArr[((4 * i3) + 4) - i4]) {
                    iArr[i3] = iArr[i3] + i6;
                }
                i6 <<= 1;
            }
        }
        try {
            new c(com.google.zxing.common.reedsolomon.a.d).a(iArr, i5);
            for (i3 = 0; i3 < i2; i3++) {
                i6 = 1;
                for (i4 = 1; i4 <= 4; i4++) {
                    boolean z2;
                    i5 = ((i3 * 4) + 4) - i4;
                    if ((iArr[i3] & i6) == i6) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    zArr[i5] = z2;
                    i6 <<= 1;
                }
            }
        } catch (ReedSolomonException e) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private a[] a(a aVar) throws NotFoundException {
        boolean z = true;
        this.e = 1;
        a aVar2 = aVar;
        a aVar3 = aVar;
        a aVar4 = aVar;
        while (this.e < 9) {
            a a = a(aVar4, z, 1, -1);
            a a2 = a(aVar3, z, 1, 1);
            a a3 = a(aVar2, z, -1, 1);
            a a4 = a(aVar, z, -1, -1);
            if (this.e > 2) {
                float b = (b(a4, a) * ((float) this.e)) / (b(aVar, aVar4) * ((float) (this.e + 2)));
                if (((double) b) >= 0.75d) {
                    if (((double) b) <= 1.25d) {
                        if (!a(a, a2, a3, a4)) {
                            break;
                        }
                    }
                    break;
                }
                break;
            }
            if (z) {
                z = false;
            } else {
                z = true;
            }
            this.e++;
            aVar = a4;
            aVar2 = a3;
            aVar3 = a2;
            aVar4 = a;
        }
        if (this.e == 5 || this.e == 7) {
            this.b = this.e == 5;
            float f = 1.5f / ((float) ((this.e * 2) - 3));
            int b2 = aVar4.b() - aVar2.b();
            int c = aVar4.c() - aVar2.c();
            int a5 = com.google.zxing.common.a.a.a(((float) aVar2.b()) - (((float) b2) * f));
            int a6 = com.google.zxing.common.a.a.a(((float) aVar2.c()) - (((float) c) * f));
            b2 = com.google.zxing.common.a.a.a((((float) b2) * f) + ((float) aVar4.b()));
            c = com.google.zxing.common.a.a.a((((float) c) * f) + ((float) aVar4.c()));
            int b3 = aVar3.b() - aVar.b();
            int c2 = aVar3.c() - aVar.c();
            int a7 = com.google.zxing.common.a.a.a(((float) aVar.b()) - (((float) b3) * f));
            int a8 = com.google.zxing.common.a.a.a(((float) aVar.c()) - (((float) c2) * f));
            b3 = com.google.zxing.common.a.a.a((((float) b3) * f) + ((float) aVar3.b()));
            int a9 = com.google.zxing.common.a.a.a((f * ((float) c2)) + ((float) aVar3.c()));
            if (a(b2, c) && a(b3, a9) && a(a5, a6) && a(a7, a8)) {
                return new a[]{new a(b2, c), new a(b3, a9), new a(a5, a6), new a(a7, a8)};
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private a b() {
        h hVar;
        h hVar2;
        h hVar3;
        h hVar4;
        int d;
        int e;
        try {
            h[] a = new com.google.zxing.common.a.b(this.a).a();
            hVar = a[0];
            hVar2 = a[1];
            hVar3 = a[2];
            hVar4 = a[3];
        } catch (NotFoundException e2) {
            d = this.a.d() / 2;
            e = this.a.e() / 2;
            hVar = a(new a(d + 7, e - 7), false, 1, -1).a();
            hVar2 = a(new a(d + 7, e + 7), false, 1, 1).a();
            hVar3 = a(new a(d - 7, e + 7), false, -1, 1).a();
            hVar4 = a(new a(d - 7, e - 7), false, -1, -1).a();
        }
        e = com.google.zxing.common.a.a.a((((hVar.a() + hVar4.a()) + hVar2.a()) + hVar3.a()) / 4.0f);
        d = com.google.zxing.common.a.a.a((((hVar4.b() + hVar.b()) + hVar2.b()) + hVar3.b()) / 4.0f);
        try {
            h[] a2 = new com.google.zxing.common.a.b(this.a, 15, e, d).a();
            hVar = a2[0];
            hVar2 = a2[1];
            hVar3 = a2[2];
            hVar4 = a2[3];
        } catch (NotFoundException e3) {
            hVar = a(new a(e + 7, d - 7), false, 1, -1).a();
            hVar2 = a(new a(e + 7, d + 7), false, 1, 1).a();
            hVar3 = a(new a(e - 7, d + 7), false, -1, 1).a();
            hVar4 = a(new a(e - 7, d - 7), false, -1, -1).a();
        }
        return new a(com.google.zxing.common.a.a.a((((hVar.a() + hVar4.a()) + hVar2.a()) + hVar3.a()) / 4.0f), com.google.zxing.common.a.a.a((((hVar4.b() + hVar.b()) + hVar2.b()) + hVar3.b()) / 4.0f));
    }

    private b a(b bVar, h hVar, h hVar2, h hVar3, h hVar4) throws NotFoundException {
        int i;
        if (this.b) {
            i = (this.c * 4) + 11;
        } else if (this.c <= 4) {
            i = (this.c * 4) + 15;
        } else {
            i = ((this.c * 4) + ((((this.c - 4) / 8) + 1) * 2)) + 15;
        }
        return com.google.zxing.common.h.a().a(bVar, i, i, 0.5f, 0.5f, ((float) i) - 0.5f, 0.5f, ((float) i) - 0.5f, ((float) i) - 0.5f, 0.5f, ((float) i) - 0.5f, hVar.a(), hVar.b(), hVar4.a(), hVar4.b(), hVar3.a(), hVar3.b(), hVar2.a(), hVar2.b());
    }

    private void a(boolean[] zArr) {
        int i;
        int i2;
        int i3;
        if (this.b) {
            i = 2;
            i2 = 6;
        } else {
            i = 5;
            i2 = 11;
        }
        for (i3 = 0; i3 < i; i3++) {
            this.c <<= 1;
            if (zArr[i3]) {
                this.c++;
            }
        }
        for (i3 = i; i3 < i + i2; i3++) {
            this.d <<= 1;
            if (zArr[i3]) {
                this.d++;
            }
        }
        this.c++;
        this.d++;
    }

    private boolean[] a(a aVar, a aVar2, int i) {
        boolean[] zArr = new boolean[i];
        float b = b(aVar, aVar2);
        float f = b / ((float) (i - 1));
        float b2 = (((float) (aVar2.b() - aVar.b())) * f) / b;
        float c = (f * ((float) (aVar2.c() - aVar.c()))) / b;
        float b3 = (float) aVar.b();
        f = (float) aVar.c();
        for (int i2 = 0; i2 < i; i2++) {
            zArr[i2] = this.a.a(com.google.zxing.common.a.a.a(b3), com.google.zxing.common.a.a.a(f));
            b3 += b2;
            f += c;
        }
        return zArr;
    }

    private boolean a(a aVar, a aVar2, a aVar3, a aVar4) {
        a aVar5 = new a(aVar.b() - 3, aVar.c() + 3);
        a aVar6 = new a(aVar2.b() - 3, aVar2.c() - 3);
        a aVar7 = new a(aVar3.b() + 3, aVar3.c() - 3);
        a aVar8 = new a(aVar4.b() + 3, 3 + aVar4.c());
        int a = a(aVar8, aVar5);
        if (a != 0 && a(aVar5, aVar6) == a && a(aVar6, aVar7) == a && a(aVar7, aVar8) == a) {
            return true;
        }
        return false;
    }

    private int a(a aVar, a aVar2) {
        float b = b(aVar, aVar2);
        float b2 = ((float) (aVar2.b() - aVar.b())) / b;
        float c = ((float) (aVar2.c() - aVar.c())) / b;
        float b3 = (float) aVar.b();
        float c2 = (float) aVar.c();
        boolean a = this.a.a(aVar.b(), aVar.c());
        int i = 0;
        float f = b3;
        b3 = c2;
        for (int i2 = 0; ((float) i2) < b; i2++) {
            f += b2;
            b3 += c;
            if (this.a.a(com.google.zxing.common.a.a.a(f), com.google.zxing.common.a.a.a(b3)) != a) {
                i++;
            }
        }
        float f2 = ((float) i) / b;
        if (f2 > 0.1f && f2 < 0.9f) {
            return 0;
        }
        return ((f2 > 0.1f ? 1 : (f2 == 0.1f ? 0 : -1)) <= 0) == a ? 1 : -1;
    }

    private a a(a aVar, boolean z, int i, int i2) {
        int b = aVar.b() + i;
        int c = aVar.c() + i2;
        while (a(b, c) && this.a.a(b, c) == z) {
            b += i;
            c += i2;
        }
        int i3 = c - i2;
        c = b - i;
        while (a(c, i3) && this.a.a(c, i3) == z) {
            c += i;
        }
        b = c - i;
        c = i3;
        while (a(b, c) && this.a.a(b, c) == z) {
            c += i2;
        }
        return new a(b, c - i2);
    }

    private boolean a(int i, int i2) {
        return i >= 0 && i < this.a.d() && i2 > 0 && i2 < this.a.e();
    }

    private static float b(a aVar, a aVar2) {
        return com.google.zxing.common.a.a.a(aVar.b(), aVar.c(), aVar2.b(), aVar2.c());
    }
}
