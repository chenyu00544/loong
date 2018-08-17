package com.google.zxing.qrcode.a;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.a.a;
import com.google.zxing.common.b;
import com.google.zxing.common.f;
import com.google.zxing.common.j;
import com.google.zxing.h;
import com.google.zxing.i;
import com.google.zxing.qrcode.decoder.g;
import java.util.Map;

/* compiled from: Detector */
public class c {
    private final b a;
    private i b;

    public c(b bVar) {
        this.a = bVar;
    }

    public final f a(Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        this.b = map == null ? null : (i) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        return a(new e(this.a, this.b).a((Map) map));
    }

    protected final f a(f fVar) throws NotFoundException, FormatException {
        h b = fVar.b();
        h c = fVar.c();
        h a = fVar.a();
        float a2 = a(b, c, a);
        if (a2 < 1.0f) {
            throw NotFoundException.getNotFoundInstance();
        }
        h[] hVarArr;
        int a3 = a(b, c, a, a2);
        g a4 = g.a(a3);
        int d = a4.d() - 7;
        h hVar = null;
        if (a4.b().length > 0) {
            float a5 = (c.a() - b.a()) + a.a();
            float b2 = (c.b() - b.b()) + a.b();
            float f = 1.0f - (3.0f / ((float) d));
            int a6 = (int) (((a5 - b.a()) * f) + b.a());
            d = (int) (b.b() + (f * (b2 - b.b())));
            int i = 4;
            while (i <= 16) {
                try {
                    hVar = a(a2, a6, d, (float) i);
                    break;
                } catch (NotFoundException e) {
                    i <<= 1;
                }
            }
        }
        b a7 = a(this.a, a(b, c, a, hVar, a3), a3);
        if (hVar == null) {
            hVarArr = new h[]{a, b, c};
        } else {
            hVarArr = new h[]{a, b, c, hVar};
        }
        return new f(a7, hVarArr);
    }

    private static j a(h hVar, h hVar2, h hVar3, h hVar4, int i) {
        float a;
        float b;
        float f;
        float f2;
        float f3 = ((float) i) - 3.5f;
        if (hVar4 != null) {
            a = hVar4.a();
            b = hVar4.b();
            f = f3 - 3.0f;
            f2 = f;
        } else {
            a = (hVar2.a() - hVar.a()) + hVar3.a();
            b = (hVar2.b() - hVar.b()) + hVar3.b();
            f = f3;
            f2 = f3;
        }
        return j.a(3.5f, 3.5f, f3, 3.5f, f2, f, 3.5f, f3, hVar.a(), hVar.b(), hVar2.a(), hVar2.b(), a, b, hVar3.a(), hVar3.b());
    }

    private static b a(b bVar, j jVar, int i) throws NotFoundException {
        return com.google.zxing.common.h.a().a(bVar, i, i, jVar);
    }

    private static int a(h hVar, h hVar2, h hVar3, float f) throws NotFoundException {
        int a = ((a.a(h.a(hVar, hVar2) / f) + a.a(h.a(hVar, hVar3) / f)) >> 1) + 7;
        switch (a & 3) {
            case 0:
                return a + 1;
            case 2:
                return a - 1;
            case 3:
                throw NotFoundException.getNotFoundInstance();
            default:
                return a;
        }
    }

    protected final float a(h hVar, h hVar2, h hVar3) {
        return (a(hVar, hVar2) + a(hVar, hVar3)) / 2.0f;
    }

    private float a(h hVar, h hVar2) {
        float a = a((int) hVar.a(), (int) hVar.b(), (int) hVar2.a(), (int) hVar2.b());
        float a2 = a((int) hVar2.a(), (int) hVar2.b(), (int) hVar.a(), (int) hVar.b());
        if (Float.isNaN(a)) {
            return a2 / 7.0f;
        }
        if (Float.isNaN(a2)) {
            return a / 7.0f;
        }
        return (a + a2) / 14.0f;
    }

    private float a(int i, int i2, int i3, int i4) {
        float f;
        int i5;
        int i6 = 0;
        float b = b(i, i2, i3, i4);
        int i7 = i - (i3 - i);
        if (i7 < 0) {
            f = ((float) i) / ((float) (i - i7));
            i5 = 0;
        } else if (i7 >= this.a.d()) {
            f = ((float) ((this.a.d() - 1) - i)) / ((float) (i7 - i));
            i5 = this.a.d() - 1;
        } else {
            i5 = i7;
            f = 1.0f;
        }
        i7 = (int) (((float) i2) - (f * ((float) (i4 - i2))));
        if (i7 < 0) {
            f = ((float) i2) / ((float) (i2 - i7));
        } else if (i7 >= this.a.e()) {
            f = ((float) ((this.a.e() - 1) - i2)) / ((float) (i7 - i2));
            i6 = this.a.e() - 1;
        } else {
            i6 = i7;
            f = 1.0f;
        }
        return (b(i, i2, (int) ((f * ((float) (i5 - i))) + ((float) i)), i6) + b) - 1.0f;
    }

    private float b(int i, int i2, int i3, int i4) {
        Object obj;
        if (Math.abs(i4 - i2) > Math.abs(i3 - i)) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            int i5 = i4;
            i4 = i3;
            i3 = i5;
            int i6 = i2;
            i2 = i;
            i = i6;
        }
        int abs = Math.abs(i4 - i2);
        int abs2 = Math.abs(i3 - i);
        int i7 = (-abs) >> 1;
        int i8 = i2 < i4 ? 1 : -1;
        int i9 = i < i3 ? 1 : -1;
        int i10 = 0;
        int i11 = i4 + i8;
        int i12 = i2;
        int i13 = i7;
        i7 = i;
        while (i12 != i11) {
            int i14;
            int i15;
            int i16;
            if (obj != null) {
                i14 = i7;
            } else {
                i14 = i12;
            }
            if (obj != null) {
                i15 = i12;
            } else {
                i15 = i7;
            }
            if ((i10 == 1) != this.a.a(i14, i15)) {
                i15 = i10;
            } else if (i10 == 2) {
                return a.a(i12, i7, i2, i);
            } else {
                i15 = i10 + 1;
            }
            i10 = i13 + abs2;
            if (i10 <= 0) {
                i16 = i7;
                i7 = i10;
            } else if (i7 == i3) {
                i9 = i15;
                break;
            } else {
                i16 = i7 + i9;
                i7 = i10 - abs;
            }
            i12 += i8;
            i10 = i15;
            i13 = i7;
            i7 = i16;
        }
        i9 = i10;
        if (i9 == 2) {
            return a.a(i4 + i8, i3, i2, i);
        }
        return Float.NaN;
    }

    protected final a a(float f, int i, int i2, float f2) throws NotFoundException {
        int i3 = (int) (f2 * f);
        int max = Math.max(0, i - i3);
        int min = Math.min(this.a.d() - 1, i + i3);
        if (((float) (min - max)) < f * 3.0f) {
            throw NotFoundException.getNotFoundInstance();
        }
        int max2 = Math.max(0, i2 - i3);
        int min2 = Math.min(this.a.e() - 1, i3 + i2);
        if (((float) (min2 - max2)) < f * 3.0f) {
            throw NotFoundException.getNotFoundInstance();
        }
        return new b(this.a, max, max2, min - max, min2 - max2, f, this.b).a();
    }
}
