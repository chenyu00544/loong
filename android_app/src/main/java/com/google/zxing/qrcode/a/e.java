package com.google.zxing.qrcode.a;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.h;
import com.google.zxing.i;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* compiled from: FinderPatternFinder */
public class e {
    private final com.google.zxing.common.b a;
    private final List<d> b = new ArrayList();
    private boolean c;
    private final int[] d = new int[5];
    private final i e;

    /* compiled from: FinderPatternFinder */
    private static final class a implements Serializable, Comparator<d> {
        private final float a;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((d) obj, (d) obj2);
        }

        private a(float f) {
            this.a = f;
        }

        public int a(d dVar, d dVar2) {
            if (dVar2.d() != dVar.d()) {
                return dVar2.d() - dVar.d();
            }
            float abs = Math.abs(dVar2.c() - this.a);
            float abs2 = Math.abs(dVar.c() - this.a);
            if (abs < abs2) {
                return 1;
            }
            return abs == abs2 ? 0 : -1;
        }
    }

    /* compiled from: FinderPatternFinder */
    private static final class b implements Serializable, Comparator<d> {
        private final float a;

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((d) obj, (d) obj2);
        }

        private b(float f) {
            this.a = f;
        }

        public int a(d dVar, d dVar2) {
            float abs = Math.abs(dVar2.c() - this.a);
            float abs2 = Math.abs(dVar.c() - this.a);
            if (abs < abs2) {
                return -1;
            }
            return abs == abs2 ? 0 : 1;
        }
    }

    public e(com.google.zxing.common.b bVar, i iVar) {
        this.a = bVar;
        this.e = iVar;
    }

    final f a(Map<DecodeHintType, ?> map) throws NotFoundException {
        int i = (map == null || !map.containsKey(DecodeHintType.TRY_HARDER)) ? 0 : 1;
        int e = this.a.e();
        int d = this.a.d();
        int i2 = (e * 3) / 228;
        if (i2 < 3 || i != 0) {
            i = 3;
        } else {
            i = i2;
        }
        int[] iArr = new int[5];
        int i3 = i - 1;
        boolean z = false;
        int i4 = i;
        while (i3 < e && !r6) {
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[2] = 0;
            iArr[3] = 0;
            iArr[4] = 0;
            i2 = 0;
            i = 0;
            while (i2 < d) {
                if (this.a.a(i2, i3)) {
                    if ((i & 1) == 1) {
                        i++;
                    }
                    iArr[i] = iArr[i] + 1;
                } else if ((i & 1) != 0) {
                    iArr[i] = iArr[i] + 1;
                } else if (i != 4) {
                    i++;
                    iArr[i] = iArr[i] + 1;
                } else if (!a(iArr)) {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i = 3;
                } else if (a(iArr, i3, i2)) {
                    boolean c;
                    if (this.c) {
                        c = c();
                    } else {
                        i = b();
                        if (i > iArr[2]) {
                            i2 = i3 + ((i - iArr[2]) - 2);
                            i = d - 1;
                        } else {
                            i = i2;
                            i2 = i3;
                        }
                        i3 = i2;
                        i2 = i;
                        c = z;
                    }
                    iArr[0] = 0;
                    iArr[1] = 0;
                    iArr[2] = 0;
                    iArr[3] = 0;
                    iArr[4] = 0;
                    z = c;
                    i4 = 2;
                    i = 0;
                } else {
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = iArr[4];
                    iArr[3] = 1;
                    iArr[4] = 0;
                    i = 3;
                }
                i2++;
            }
            if (a(iArr) && a(iArr, i3, d)) {
                i4 = iArr[0];
                if (this.c) {
                    z = c();
                }
            }
            i3 += i4;
        }
        h[] d2 = d();
        h.a(d2);
        return new f(d2);
    }

    private static float a(int[] iArr, int i) {
        return ((float) ((i - iArr[4]) - iArr[3])) - (((float) iArr[2]) / 2.0f);
    }

    protected static boolean a(int[] iArr) {
        int i;
        boolean z = true;
        int i2 = 0;
        for (i = 0; i < 5; i++) {
            int i3 = iArr[i];
            if (i3 == 0) {
                return false;
            }
            i2 += i3;
        }
        if (i2 < 7) {
            return false;
        }
        i = (i2 << 8) / 7;
        i2 = i / 2;
        if (Math.abs(i - (iArr[0] << 8)) >= i2 || Math.abs(i - (iArr[1] << 8)) >= i2 || Math.abs((i * 3) - (iArr[2] << 8)) >= i2 * 3 || Math.abs(i - (iArr[3] << 8)) >= i2 || Math.abs(i - (iArr[4] << 8)) >= i2) {
            z = false;
        }
        return z;
    }

    private int[] a() {
        this.d[0] = 0;
        this.d[1] = 0;
        this.d[2] = 0;
        this.d[3] = 0;
        this.d[4] = 0;
        return this.d;
    }

    private float a(int i, int i2, int i3, int i4) {
        com.google.zxing.common.b bVar = this.a;
        int e = bVar.e();
        int[] a = a();
        int i5 = i;
        while (i5 >= 0 && bVar.a(i2, i5)) {
            a[2] = a[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bVar.a(i2, i5) && a[1] <= i3) {
            a[1] = a[1] + 1;
            i5--;
        }
        if (i5 < 0 || a[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && bVar.a(i2, i5) && a[0] <= i3) {
            a[0] = a[0] + 1;
            i5--;
        }
        if (a[0] > i3) {
            return Float.NaN;
        }
        i5 = i + 1;
        while (i5 < e && bVar.a(i2, i5)) {
            a[2] = a[2] + 1;
            i5++;
        }
        if (i5 == e) {
            return Float.NaN;
        }
        while (i5 < e && !bVar.a(i2, i5) && a[3] < i3) {
            a[3] = a[3] + 1;
            i5++;
        }
        if (i5 == e || a[3] >= i3) {
            return Float.NaN;
        }
        while (i5 < e && bVar.a(i2, i5) && a[4] < i3) {
            a[4] = a[4] + 1;
            i5++;
        }
        if (a[4] >= i3 || Math.abs(((((a[0] + a[1]) + a[2]) + a[3]) + a[4]) - i4) * 5 >= i4 * 2 || !a(a)) {
            return Float.NaN;
        }
        return a(a, i5);
    }

    private float b(int i, int i2, int i3, int i4) {
        com.google.zxing.common.b bVar = this.a;
        int d = bVar.d();
        int[] a = a();
        int i5 = i;
        while (i5 >= 0 && bVar.a(i5, i2)) {
            a[2] = a[2] + 1;
            i5--;
        }
        if (i5 < 0) {
            return Float.NaN;
        }
        while (i5 >= 0 && !bVar.a(i5, i2) && a[1] <= i3) {
            a[1] = a[1] + 1;
            i5--;
        }
        if (i5 < 0 || a[1] > i3) {
            return Float.NaN;
        }
        while (i5 >= 0 && bVar.a(i5, i2) && a[0] <= i3) {
            a[0] = a[0] + 1;
            i5--;
        }
        if (a[0] > i3) {
            return Float.NaN;
        }
        i5 = i + 1;
        while (i5 < d && bVar.a(i5, i2)) {
            a[2] = a[2] + 1;
            i5++;
        }
        if (i5 == d) {
            return Float.NaN;
        }
        while (i5 < d && !bVar.a(i5, i2) && a[3] < i3) {
            a[3] = a[3] + 1;
            i5++;
        }
        if (i5 == d || a[3] >= i3) {
            return Float.NaN;
        }
        while (i5 < d && bVar.a(i5, i2) && a[4] < i3) {
            a[4] = a[4] + 1;
            i5++;
        }
        if (a[4] >= i3 || Math.abs(((((a[0] + a[1]) + a[2]) + a[3]) + a[4]) - i4) * 5 >= i4 || !a(a)) {
            return Float.NaN;
        }
        return a(a, i5);
    }

    protected final boolean a(int[] iArr, int i, int i2) {
        boolean z = false;
        int i3 = (((iArr[0] + iArr[1]) + iArr[2]) + iArr[3]) + iArr[4];
        float a = a(iArr, i2);
        float a2 = a(i, (int) a, iArr[2], i3);
        if (Float.isNaN(a2)) {
            return false;
        }
        float b = b((int) a, (int) a2, iArr[2], i3);
        if (Float.isNaN(b)) {
            return false;
        }
        float f = ((float) i3) / 7.0f;
        for (int i4 = 0; i4 < this.b.size(); i4++) {
            d dVar = (d) this.b.get(i4);
            if (dVar.a(f, a2, b)) {
                this.b.set(i4, dVar.b(a2, b, f));
                z = true;
                break;
            }
        }
        if (!z) {
            h dVar2 = new d(b, a2, f);
            this.b.add(dVar2);
            if (this.e != null) {
                this.e.a(dVar2);
            }
        }
        return true;
    }

    private int b() {
        if (this.b.size() <= 1) {
            return 0;
        }
        d dVar = null;
        for (d dVar2 : this.b) {
            d dVar22;
            if (dVar22.d() < 2) {
                dVar22 = dVar;
            } else if (dVar != null) {
                this.c = true;
                return ((int) (Math.abs(dVar.a() - dVar22.a()) - Math.abs(dVar.b() - dVar22.b()))) / 2;
            }
            dVar = dVar22;
        }
        return 0;
    }

    private boolean c() {
        float f = 0.0f;
        int size = this.b.size();
        float f2 = 0.0f;
        int i = 0;
        for (d dVar : this.b) {
            float c;
            int i2;
            if (dVar.d() >= 2) {
                c = dVar.c() + f2;
                i2 = i + 1;
            } else {
                c = f2;
                i2 = i;
            }
            i = i2;
            f2 = c;
        }
        if (i < 3) {
            return false;
        }
        float f3 = f2 / ((float) size);
        for (d dVar2 : this.b) {
            f += Math.abs(dVar2.c() - f3);
        }
        if (f <= 0.05f * f2) {
            return true;
        }
        return false;
    }

    private d[] d() throws NotFoundException {
        float f = 0.0f;
        int size = this.b.size();
        if (size < 3) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (size > 3) {
            float c;
            float f2 = 0.0f;
            float f3 = 0.0f;
            for (d c2 : this.b) {
                c = c2.c();
                f3 += c;
                f2 = (c * c) + f2;
            }
            f3 /= (float) size;
            c = (float) Math.sqrt((double) ((f2 / ((float) size)) - (f3 * f3)));
            Collections.sort(this.b, new b(f3));
            float max = Math.max(0.2f * f3, c);
            int i = 0;
            while (i < this.b.size() && this.b.size() > 3) {
                if (Math.abs(((d) this.b.get(i)).c() - f3) > max) {
                    this.b.remove(i);
                    i--;
                }
                i++;
            }
        }
        if (this.b.size() > 3) {
            for (d c22 : this.b) {
                f += c22.c();
            }
            Collections.sort(this.b, new a(f / ((float) this.b.size())));
            this.b.subList(3, this.b.size()).clear();
        }
        return new d[]{(d) this.b.get(0), (d) this.b.get(1), (d) this.b.get(2)};
    }
}
