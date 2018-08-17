package com.google.zxing.datamatrix.b;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.f;
import com.google.zxing.h;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: Detector */
public final class a {
    private final com.google.zxing.common.b a;
    private final com.google.zxing.common.a.b b;

    /* compiled from: Detector */
    private static final class a {
        private final h a;
        private final h b;
        private final int c;

        private a(h hVar, h hVar2, int i) {
            this.a = hVar;
            this.b = hVar2;
            this.c = i;
        }

        h a() {
            return this.a;
        }

        h b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }

        public String toString() {
            return this.a + "/" + this.b + '/' + this.c;
        }
    }

    /* compiled from: Detector */
    private static final class b implements Serializable, Comparator<a> {
        private b() {
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((a) obj, (a) obj2);
        }

        public int a(a aVar, a aVar2) {
            return aVar.c() - aVar2.c();
        }
    }

    public a(com.google.zxing.common.b bVar) throws NotFoundException {
        this.a = bVar;
        this.b = new com.google.zxing.common.a.b(bVar);
    }

    public f a() throws NotFoundException {
        h[] a = this.b.a();
        h hVar = a[0];
        h hVar2 = a[1];
        h hVar3 = a[2];
        h hVar4 = a[3];
        List arrayList = new ArrayList(4);
        arrayList.add(b(hVar, hVar2));
        arrayList.add(b(hVar, hVar3));
        arrayList.add(b(hVar2, hVar4));
        arrayList.add(b(hVar3, hVar4));
        Collections.sort(arrayList, new b());
        a aVar = (a) arrayList.get(0);
        a aVar2 = (a) arrayList.get(1);
        Map hashMap = new HashMap();
        a(hashMap, aVar.a());
        a(hashMap, aVar.b());
        a(hashMap, aVar2.a());
        a(hashMap, aVar2.b());
        h hVar5 = null;
        h hVar6 = null;
        h hVar7 = null;
        for (Entry entry : hashMap.entrySet()) {
            h hVar8;
            h hVar9 = (h) entry.getKey();
            if (((Integer) entry.getValue()).intValue() == 2) {
                hVar8 = hVar9;
                hVar9 = hVar7;
                hVar7 = hVar5;
            } else if (hVar5 == null) {
                hVar8 = hVar6;
                h hVar10 = hVar7;
                hVar7 = hVar9;
                hVar9 = hVar10;
            } else {
                hVar8 = hVar6;
                hVar7 = hVar5;
            }
            hVar6 = hVar8;
            hVar5 = hVar7;
            hVar7 = hVar9;
        }
        if (hVar5 == null || hVar6 == null || hVar7 == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        com.google.zxing.common.b a2;
        a = new h[]{hVar5, hVar6, hVar7};
        h.a(a);
        hVar7 = a[0];
        hVar9 = a[1];
        hVar6 = a[2];
        if (!hashMap.containsKey(hVar)) {
            hVar5 = hVar;
        } else if (!hashMap.containsKey(hVar2)) {
            hVar5 = hVar2;
        } else if (hashMap.containsKey(hVar3)) {
            hVar5 = hVar4;
        } else {
            hVar5 = hVar3;
        }
        int c = b(hVar6, hVar5).c();
        int c2 = b(hVar7, hVar5).c();
        if ((c & 1) == 1) {
            c++;
        }
        c += 2;
        if ((c2 & 1) == 1) {
            c2++;
        }
        int i = c2 + 2;
        int c3;
        if (c * 4 >= i * 7 || i * 4 >= c * 7) {
            hVar4 = a(hVar9, hVar7, hVar6, hVar5, c, i);
            if (hVar4 == null) {
                hVar4 = hVar5;
            }
            c3 = b(hVar6, hVar4).c();
            int c4 = b(hVar7, hVar4).c();
            if ((c3 & 1) == 1) {
                c3++;
            }
            if ((c4 & 1) == 1) {
                c4++;
            }
            a2 = a(this.a, hVar6, hVar9, hVar7, hVar4, c3, c4);
        } else {
            hVar4 = a(hVar9, hVar7, hVar6, hVar5, Math.min(i, c));
            if (hVar4 == null) {
                hVar4 = hVar5;
            }
            c3 = Math.max(b(hVar6, hVar4).c(), b(hVar7, hVar4).c()) + 1;
            if ((c3 & 1) == 1) {
                c3++;
            }
            a2 = a(this.a, hVar6, hVar9, hVar7, hVar4, c3, c3);
        }
        return new f(a2, new h[]{hVar6, hVar9, hVar7, hVar4});
    }

    private h a(h hVar, h hVar2, h hVar3, h hVar4, int i, int i2) {
        float a = ((float) a(hVar, hVar2)) / ((float) i);
        int a2 = a(hVar3, hVar4);
        h hVar5 = new h((((hVar4.a() - hVar3.a()) / ((float) a2)) * a) + hVar4.a(), (a * ((hVar4.b() - hVar3.b()) / ((float) a2))) + hVar4.b());
        float a3 = ((float) a(hVar, hVar3)) / ((float) i2);
        int a4 = a(hVar2, hVar4);
        h hVar6 = new h((((hVar4.a() - hVar2.a()) / ((float) a4)) * a3) + hVar4.a(), (a3 * ((hVar4.b() - hVar2.b()) / ((float) a4))) + hVar4.b());
        if (a(hVar5)) {
            if (!a(hVar6)) {
                return hVar5;
            }
            if (Math.abs(i - b(hVar3, hVar5).c()) + Math.abs(i2 - b(hVar2, hVar5).c()) <= Math.abs(i - b(hVar3, hVar6).c()) + Math.abs(i2 - b(hVar2, hVar6).c())) {
                return hVar5;
            }
            return hVar6;
        } else if (a(hVar6)) {
            return hVar6;
        } else {
            return null;
        }
    }

    private h a(h hVar, h hVar2, h hVar3, h hVar4, int i) {
        float a = ((float) a(hVar, hVar2)) / ((float) i);
        int a2 = a(hVar3, hVar4);
        h hVar5 = new h((((hVar4.a() - hVar3.a()) / ((float) a2)) * a) + hVar4.a(), (a * ((hVar4.b() - hVar3.b()) / ((float) a2))) + hVar4.b());
        float a3 = ((float) a(hVar, hVar3)) / ((float) i);
        int a4 = a(hVar2, hVar4);
        h hVar6 = new h((((hVar4.a() - hVar2.a()) / ((float) a4)) * a3) + hVar4.a(), (a3 * ((hVar4.b() - hVar2.b()) / ((float) a4))) + hVar4.b());
        if (a(hVar5)) {
            return (!a(hVar6) || Math.abs(b(hVar3, hVar5).c() - b(hVar2, hVar5).c()) <= Math.abs(b(hVar3, hVar6).c() - b(hVar2, hVar6).c())) ? hVar5 : hVar6;
        } else {
            if (a(hVar6)) {
                return hVar6;
            }
            return null;
        }
    }

    private boolean a(h hVar) {
        return hVar.a() >= 0.0f && hVar.a() < ((float) this.a.d()) && hVar.b() > 0.0f && hVar.b() < ((float) this.a.e());
    }

    private static int a(h hVar, h hVar2) {
        return com.google.zxing.common.a.a.a(h.a(hVar, hVar2));
    }

    private static void a(Map<h, Integer> map, h hVar) {
        Integer num = (Integer) map.get(hVar);
        map.put(hVar, Integer.valueOf(num == null ? 1 : num.intValue() + 1));
    }

    private static com.google.zxing.common.b a(com.google.zxing.common.b bVar, h hVar, h hVar2, h hVar3, h hVar4, int i, int i2) throws NotFoundException {
        return com.google.zxing.common.h.a().a(bVar, i, i2, 0.5f, 0.5f, ((float) i) - 0.5f, 0.5f, ((float) i) - 0.5f, ((float) i2) - 0.5f, 0.5f, ((float) i2) - 0.5f, hVar.a(), hVar.b(), hVar4.a(), hVar4.b(), hVar3.a(), hVar3.b(), hVar2.a(), hVar2.b());
    }

    private a b(h hVar, h hVar2) {
        Object obj;
        int i;
        int a = (int) hVar.a();
        int b = (int) hVar.b();
        int a2 = (int) hVar2.a();
        int b2 = (int) hVar2.b();
        if (Math.abs(b2 - b) > Math.abs(a2 - a)) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj == null) {
            int i2 = b2;
            b2 = a2;
            a2 = i2;
            int i3 = b;
            b = a;
            a = i3;
        }
        int abs = Math.abs(b2 - b);
        int abs2 = Math.abs(a2 - a);
        int i4 = (-abs) >> 1;
        int i5 = a < a2 ? 1 : -1;
        int i6 = b < b2 ? 1 : -1;
        int i7 = 0;
        com.google.zxing.common.b bVar = this.a;
        if (obj != null) {
            i = a;
        } else {
            i = b;
        }
        boolean a3 = bVar.a(i, obj != null ? b : a);
        int i8 = a;
        int i9 = i4;
        while (b != b2) {
            com.google.zxing.common.b bVar2 = this.a;
            if (obj != null) {
                i4 = i8;
            } else {
                i4 = b;
            }
            boolean a4 = bVar2.a(i4, obj != null ? b : i8);
            if (a4 != a3) {
                i7++;
                a3 = a4;
            }
            a = i9 + abs2;
            if (a > 0) {
                if (i8 == a2) {
                    a2 = i7;
                    break;
                }
                i8 += i5;
                a -= abs;
            }
            b += i6;
            i9 = a;
        }
        a2 = i7;
        return new a(hVar, hVar2, a2);
    }
}
