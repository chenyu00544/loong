package com.google.zxing.c.a.a;

import android.support.v4.media.TransportMediator;
import com.baidu.location.BDLocation;
import com.baidu.location.b.g;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.c.a.a;
import com.google.zxing.c.a.b;
import com.google.zxing.c.a.c;
import com.google.zxing.c.a.f;
import com.google.zxing.c.k;
import com.google.zxing.h;
import com.umeng.analytics.pro.j;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: RSSExpandedReader */
public final class d extends a {
    private static final int[] a = new int[]{7, 5, 4, 3, 1};
    private static final int[] b = new int[]{4, 20, 52, 104, g.c};
    private static final int[] c = new int[]{0, 348, 1388, 2948, 3988};
    private static final int[][] d = new int[][]{new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};
    private static final int[][] e = new int[][]{new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, 145, 13, 39, 117, 140, g.f, g.aa}, new int[]{193, 157, 49, 147, 19, 57, 171, 91}, new int[]{62, 186, 136, 197, 169, 85, 44, 132}, new int[]{185, 133, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, 158, 52, 156}, new int[]{46, 138, g.a, 187, 139, 206, 196, 166}, new int[]{76, 17, 51, 153, 37, 111, g.K, 155}, new int[]{43, 129, 176, 106, 107, 110, 119, 146}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, 178, g.if, 125, 164}, new int[]{70, 210, g.new, g.void, 184, 130, 179, 115}, new int[]{134, 191, 151, 31, 93, 68, g.c, 190}, new int[]{148, 22, 66, 198, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{g.L, 149, 25, 75, 14, 42, TransportMediator.KEYCODE_MEDIA_PLAY, BDLocation.TypeServerError}, new int[]{79, 26, 78, 23, 69, g.T, 199, 175}, new int[]{103, 98, 83, 38, 114, 131, 182, 124}, new int[]{BDLocation.TypeNetWorkLocation, 61, 183, TransportMediator.KEYCODE_MEDIA_PAUSE, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, 194, j.b, 58, 174, 100, 89}};
    private static final int[][] f = new int[][]{new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};
    private final List<b> g = new ArrayList(11);
    private final List<c> h = new ArrayList();
    private final int[] i = new int[2];
    private boolean j = false;

    public com.google.zxing.g a(int i, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        this.g.clear();
        this.j = false;
        try {
            return a(a(i, aVar));
        } catch (NotFoundException e) {
            this.g.clear();
            this.j = true;
            return a(a(i, aVar));
        }
    }

    public void a() {
        this.g.clear();
        this.h.clear();
    }

    List<b> a(int i, com.google.zxing.common.a aVar) throws NotFoundException {
        while (true) {
            try {
                this.g.add(a(aVar, this.g, i));
            } catch (NotFoundException e) {
                if (this.g.isEmpty()) {
                    throw e;
                } else if (h()) {
                    return this.g;
                } else {
                    boolean z = !this.h.isEmpty();
                    a(i, false);
                    if (z) {
                        List<b> a = a(false);
                        if (a != null) {
                            return a;
                        }
                        a = a(true);
                        if (a != null) {
                            return a;
                        }
                    }
                    throw NotFoundException.getNotFoundInstance();
                }
            }
        }
    }

    private List<b> a(boolean z) {
        List<b> list = null;
        if (this.h.size() > 25) {
            this.h.clear();
        } else {
            this.g.clear();
            if (z) {
                Collections.reverse(this.h);
            }
            try {
                list = a(new ArrayList(), 0);
            } catch (NotFoundException e) {
            }
            if (z) {
                Collections.reverse(this.h);
            }
        }
        return list;
    }

    private List<b> a(List<c> list, int i) throws NotFoundException {
        while (i < this.h.size()) {
            c cVar = (c) this.h.get(i);
            this.g.clear();
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                this.g.addAll(((c) list.get(i2)).a());
            }
            this.g.addAll(cVar.a());
            if (b(this.g)) {
                if (h()) {
                    return this.g;
                }
                List arrayList = new ArrayList();
                arrayList.addAll(list);
                arrayList.add(cVar);
                try {
                    return a(arrayList, i + 1);
                } catch (NotFoundException e) {
                }
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static boolean b(List<b> list) {
        for (int[] iArr : f) {
            if (list.size() <= iArr.length) {
                boolean z;
                for (int i = 0; i < list.size(); i++) {
                    if (((b) list.get(i)).c().a() != iArr[i]) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    private void a(int i, boolean z) {
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        while (i2 < this.h.size()) {
            c cVar = (c) this.h.get(i2);
            if (cVar.b() > i) {
                z2 = cVar.a(this.g);
                break;
            }
            i2++;
            z3 = cVar.a(this.g);
        }
        if (!z2 && !r1 && !a(this.g, this.h)) {
            this.h.add(i2, new c(this.g, i, z));
            a(this.g, this.h);
        }
    }

    private static void a(List<b> list, List<c> list2) {
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            c cVar = (c) it.next();
            if (cVar.a().size() != list.size()) {
                Object obj;
                for (b bVar : cVar.a()) {
                    for (b equals : list) {
                        if (bVar.equals(equals)) {
                            obj = 1;
                            continue;
                            break;
                        }
                    }
                    obj = null;
                    continue;
                    if (obj == null) {
                        obj = null;
                        break;
                    }
                }
                int i = 1;
                if (obj != null) {
                    it.remove();
                }
            }
        }
    }

    private static boolean a(Iterable<b> iterable, Iterable<c> iterable2) {
        for (c cVar : iterable2) {
            for (b bVar : iterable) {
                Object obj;
                for (b equals : cVar.a()) {
                    if (bVar.equals(equals)) {
                        obj = 1;
                        continue;
                        break;
                    }
                }
                obj = null;
                continue;
                if (obj == null) {
                    Object obj2 = null;
                    continue;
                    break;
                }
            }
            int i = 1;
            continue;
            if (obj2 != null) {
                return true;
            }
        }
        return false;
    }

    static com.google.zxing.g a(List<b> list) throws NotFoundException {
        String a = com.google.zxing.c.a.a.a.j.a(a.a(list)).a();
        h[] c = ((b) list.get(0)).c().c();
        h[] c2 = ((b) list.get(list.size() - 1)).c().c();
        return new com.google.zxing.g(a, null, new h[]{c[0], c[1], c2[0], c2[1]}, BarcodeFormat.RSS_EXPANDED);
    }

    private boolean h() {
        boolean z = true;
        b bVar = (b) this.g.get(0);
        b a = bVar.a();
        b b = bVar.b();
        if (b == null) {
            return false;
        }
        int i = 2;
        int b2 = b.b();
        for (int i2 = 1; i2 < this.g.size(); i2++) {
            bVar = (b) this.g.get(i2);
            b2 += bVar.a().b();
            i++;
            b = bVar.b();
            if (b != null) {
                b2 += b.b();
                i++;
            }
        }
        if ((b2 % 211) + ((i - 4) * 211) != a.a()) {
            z = false;
        }
        return z;
    }

    private static int a(com.google.zxing.common.a aVar, int i) {
        if (aVar.a(i)) {
            return aVar.c(aVar.d(i));
        }
        return aVar.d(aVar.c(i));
    }

    b a(com.google.zxing.common.a aVar, List<b> list, int i) throws NotFoundException {
        boolean z;
        c a;
        boolean z2 = list.size() % 2 == 0;
        if (this.j) {
            if (z2) {
                z2 = false;
            } else {
                z2 = true;
            }
            z = z2;
        } else {
            z = z2;
        }
        int i2 = -1;
        boolean z3 = true;
        do {
            b(aVar, list, i2);
            a = a(aVar, i, z);
            if (a == null) {
                i2 = a(aVar, this.i[0]);
                continue;
            } else {
                z3 = false;
                continue;
            }
        } while (z3);
        b a2 = a(aVar, a, z, true);
        if (list.isEmpty() || !((b) list.get(list.size() - 1)).d()) {
            b a3;
            try {
                a3 = a(aVar, a, z, false);
            } catch (NotFoundException e) {
                a3 = null;
            }
            return new b(a2, a3, a, true);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void b(com.google.zxing.common.a aVar, List<b> list, int i) throws NotFoundException {
        int[] b = b();
        b[0] = 0;
        b[1] = 0;
        b[2] = 0;
        b[3] = 0;
        int a = aVar.a();
        if (i < 0) {
            if (list.isEmpty()) {
                i = 0;
            } else {
                i = ((b) list.get(list.size() - 1)).c().b()[1];
            }
        }
        Object obj = list.size() % 2 != 0 ? 1 : null;
        if (this.j) {
            obj = obj == null ? 1 : null;
        }
        int i2 = 0;
        int i3 = i;
        while (i3 < a) {
            i2 = !aVar.a(i3) ? 1 : 0;
            if (i2 == 0) {
                break;
            }
            i3++;
        }
        int i4 = i3;
        i3 = 0;
        int i5 = i2;
        i2 = i4;
        for (int i6 = i3; i6 < a; i6++) {
            if ((aVar.a(i6) ^ i5) != 0) {
                b[i3] = b[i3] + 1;
            } else {
                if (i3 == 3) {
                    if (obj != null) {
                        c(b);
                    }
                    if (a.b(b)) {
                        this.i[0] = i2;
                        this.i[1] = i6;
                        return;
                    }
                    if (obj != null) {
                        c(b);
                    }
                    i2 += b[0] + b[1];
                    b[0] = b[2];
                    b[1] = b[3];
                    b[2] = 0;
                    b[3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                b[i3] = 1;
                i5 = i5 == 0 ? 1 : 0;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static void c(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            iArr[i] = iArr[(length - i) - 1];
            iArr[(length - i) - 1] = i2;
        }
    }

    private c a(com.google.zxing.common.a aVar, int i, boolean z) {
        int i2;
        int i3;
        int i4;
        if (z) {
            i2 = this.i[0] - 1;
            while (i2 >= 0 && !aVar.a(i2)) {
                i2--;
            }
            i3 = i2 + 1;
            i2 = this.i[0] - i3;
            i4 = this.i[1];
        } else {
            i3 = this.i[0];
            i4 = aVar.d(this.i[1] + 1);
            i2 = i4 - this.i[1];
        }
        Object b = b();
        System.arraycopy(b, 0, b, 1, b.length - 1);
        b[0] = i2;
        try {
            return new c(a.a((int[]) b, d), new int[]{i3, i4}, i3, i4, i);
        } catch (NotFoundException e) {
            return null;
        }
    }

    b a(com.google.zxing.common.a aVar, c cVar, boolean z, boolean z2) throws NotFoundException {
        int i;
        int length;
        int[] c = c();
        c[0] = 0;
        c[1] = 0;
        c[2] = 0;
        c[3] = 0;
        c[4] = 0;
        c[5] = 0;
        c[6] = 0;
        c[7] = 0;
        if (z2) {
            k.b(aVar, cVar.b()[0], c);
        } else {
            k.a(aVar, cVar.b()[1], c);
            i = 0;
            for (length = c.length - 1; i < length; length--) {
                int i2 = c[i];
                c[i] = c[length];
                c[length] = i2;
                i++;
            }
        }
        float a = ((float) a.a(c)) / ((float) 17);
        float f = ((float) (cVar.b()[1] - cVar.b()[0])) / 15.0f;
        if (Math.abs(a - f) / f > 0.3f) {
            throw NotFoundException.getNotFoundInstance();
        }
        int length2;
        int[] f2 = f();
        int[] g = g();
        float[] d = d();
        float[] e = e();
        for (length = 0; length < c.length; length++) {
            float f3 = (1.0f * ((float) c[length])) / a;
            i = (int) (0.5f + f3);
            if (i < 1) {
                if (f3 < 0.3f) {
                    throw NotFoundException.getNotFoundInstance();
                }
                i = 1;
            } else if (i > 8) {
                if (f3 > 8.7f) {
                    throw NotFoundException.getNotFoundInstance();
                }
                i = 8;
            }
            int i3 = length >> 1;
            if ((length & 1) == 0) {
                f2[i3] = i;
                d[i3] = f3 - ((float) i);
            } else {
                g[i3] = i;
                e[i3] = f3 - ((float) i);
            }
        }
        a(17);
        i = (cVar.a() * 4) + (z ? 0 : 2);
        if (z2) {
            length = 0;
        } else {
            length = 1;
        }
        int i4 = (length + i) - 1;
        i2 = 0;
        i = f2.length - 1;
        length = 0;
        while (i >= 0) {
            if (a(cVar, z, z2)) {
                length += e[i4][i * 2] * f2[i];
            }
            i--;
            i2 = f2[i] + i2;
        }
        i = 0;
        for (length2 = g.length - 1; length2 >= 0; length2--) {
            if (a(cVar, z, z2)) {
                i += e[i4][(length2 * 2) + 1] * g[length2];
            }
        }
        length += i;
        if ((i2 & 1) != 0 || i2 > 13 || i2 < 4) {
            throw NotFoundException.getNotFoundInstance();
        }
        i = (13 - i2) / 2;
        length2 = a[i];
        i2 = 9 - length2;
        return new b(c[i] + ((f.a(f2, length2, true) * b[i]) + f.a(g, i2, false)), length);
    }

    private static boolean a(c cVar, boolean z, boolean z2) {
        return (cVar.a() == 0 && z && z2) ? false : true;
    }

    private void a(int i) throws NotFoundException {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5 = null;
        Object obj6 = 1;
        int a = a.a(f());
        int a2 = a.a(g());
        int i2 = (a + a2) - i;
        Object obj7 = (a & 1) == 1 ? 1 : null;
        if ((a2 & 1) == 0) {
            obj = 1;
        } else {
            obj = null;
        }
        if (a > 13) {
            obj2 = 1;
            obj3 = null;
        } else if (a < 4) {
            obj2 = null;
            int i3 = 1;
        } else {
            obj2 = null;
            obj3 = null;
        }
        if (a2 > 13) {
            obj4 = null;
            obj5 = 1;
        } else if (a2 < 4) {
            int i4 = 1;
        } else {
            obj4 = null;
        }
        int i5;
        if (i2 == 1) {
            if (obj7 != null) {
                if (obj != null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                obj2 = obj3;
                obj6 = obj4;
                obj4 = 1;
            } else if (obj == null) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                i5 = 1;
                obj6 = obj4;
                obj4 = obj2;
                obj2 = obj3;
            }
        } else if (i2 == -1) {
            if (obj7 != null) {
                if (obj != null) {
                    throw NotFoundException.getNotFoundInstance();
                }
                r12 = obj4;
                obj4 = obj2;
                r3 = 1;
                obj6 = r12;
            } else if (obj == null) {
                throw NotFoundException.getNotFoundInstance();
            } else {
                obj4 = obj2;
                obj2 = obj3;
            }
        } else if (i2 != 0) {
            throw NotFoundException.getNotFoundInstance();
        } else if (obj7 != null) {
            if (obj == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (a < a2) {
                i5 = 1;
                r12 = obj4;
                obj4 = obj2;
                r3 = 1;
                obj6 = r12;
            } else {
                i4 = 1;
                obj2 = obj3;
            }
        } else if (obj != null) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            obj6 = obj4;
            obj4 = obj2;
            obj2 = obj3;
        }
        if (obj2 != null) {
            if (obj4 != null) {
                throw NotFoundException.getNotFoundInstance();
            }
            a.a(f(), d());
        }
        if (obj4 != null) {
            a.b(f(), d());
        }
        if (obj6 != null) {
            if (obj5 != null) {
                throw NotFoundException.getNotFoundInstance();
            }
            a.a(g(), d());
        }
        if (obj5 != null) {
            a.b(g(), e());
        }
    }
}
