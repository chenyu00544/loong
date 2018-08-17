package com.google.zxing.pdf417.b;

import com.baidu.location.b.g;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.b;
import com.google.zxing.h;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* compiled from: Detector */
public final class a {
    private static final int[] a = new int[]{0, 4, 1, 5};
    private static final int[] b = new int[]{6, 2, 7, 3};
    private static final int[] c = new int[]{8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] d = new int[]{7, 1, 1, 3, 1, 1, 1, 2, 1};

    public static b a(b bVar, Map<DecodeHintType, ?> map, boolean z) throws NotFoundException {
        com.google.zxing.common.b c = bVar.c();
        List a = a(z, c);
        if (a.isEmpty()) {
            a(c);
            a = a(z, c);
        }
        return new b(c, a);
    }

    private static List<h[]> a(boolean z, com.google.zxing.common.b bVar) {
        List<h[]> arrayList = new ArrayList();
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i3 < bVar.e()) {
            Object a = a(bVar, i3, i2);
            if (a[0] != null || a[3] != null) {
                arrayList.add(a);
                if (!z) {
                    break;
                }
                if (a[2] != null) {
                    i = (int) a[2].a();
                    i2 = (int) a[2].b();
                } else {
                    i = (int) a[4].a();
                    i2 = (int) a[4].b();
                }
                i3 = i2;
                i2 = i;
                i = 1;
            } else if (i == 0) {
                break;
            } else {
                for (h[] hVarArr : arrayList) {
                    if (hVarArr[1] != null) {
                        i3 = (int) Math.max((float) i3, hVarArr[1].b());
                    }
                    if (hVarArr[3] != null) {
                        i3 = Math.max(i3, (int) hVarArr[3].b());
                    }
                }
                i2 = 0;
                i3 += 5;
                i = 0;
            }
        }
        return arrayList;
    }

    static void a(com.google.zxing.common.b bVar) {
        int d = bVar.d();
        int e = bVar.e();
        com.google.zxing.common.a aVar = new com.google.zxing.common.a(d);
        com.google.zxing.common.a aVar2 = new com.google.zxing.common.a(d);
        com.google.zxing.common.a aVar3 = new com.google.zxing.common.a(d);
        for (d = 0; d < ((e + 1) >> 1); d++) {
            aVar = bVar.a(d, aVar);
            bVar.b(d, a(bVar.a((e - 1) - d, aVar2), aVar3));
            bVar.b((e - 1) - d, a(aVar, aVar3));
        }
    }

    static com.google.zxing.common.a a(com.google.zxing.common.a aVar, com.google.zxing.common.a aVar2) {
        aVar2.b();
        int a = aVar.a();
        for (int i = 0; i < a; i++) {
            if (aVar.a(i)) {
                aVar2.b((a - 1) - i);
            }
        }
        return aVar2;
    }

    private static h[] a(com.google.zxing.common.b bVar, int i, int i2) {
        int a;
        int b;
        int e = bVar.e();
        int d = bVar.d();
        h[] hVarArr = new h[8];
        a(hVarArr, a(bVar, e, d, i, i2, c), a);
        if (hVarArr[4] != null) {
            a = (int) hVarArr[4].a();
            b = (int) hVarArr[4].b();
        } else {
            a = i2;
            b = i;
        }
        a(hVarArr, a(bVar, e, d, b, a, d), b);
        return hVarArr;
    }

    private static void a(h[] hVarArr, h[] hVarArr2, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            hVarArr[iArr[i]] = hVarArr2[i];
        }
    }

    private static h[] a(com.google.zxing.common.b bVar, int i, int i2, int i3, int i4, int[] iArr) {
        int[] iArr2;
        int i5;
        Object obj;
        int i6;
        h[] hVarArr = new h[4];
        int[] iArr3 = new int[iArr.length];
        int i7 = i3;
        while (i7 < i) {
            int[] a = a(bVar, i4, i7, i2, false, iArr, iArr3);
            int i8;
            int i9;
            if (a != null) {
                iArr2 = a;
                i5 = i7;
                while (i5 > 0) {
                    i7 = i5 - 1;
                    a = a(bVar, i4, i7, i2, false, iArr, iArr3);
                    if (a == null) {
                        i5 = i7 + 1;
                        break;
                    }
                    iArr2 = a;
                    i5 = i7;
                }
                hVarArr[0] = new h((float) iArr2[0], (float) i5);
                hVarArr[1] = new h((float) iArr2[1], (float) i5);
                obj = 1;
                i6 = i5;
                i5 = i6 + 1;
                if (obj != null) {
                    iArr2 = new int[]{(int) hVarArr[0].a(), (int) hVarArr[1].a()};
                    i8 = 0;
                    i7 = i5;
                    while (i7 < i) {
                        a = a(bVar, iArr2[0], i7, i2, false, iArr, iArr3);
                        if (a == null && Math.abs(iArr2[0] - a[0]) < 5 && Math.abs(iArr2[1] - a[1]) < 5) {
                            i9 = 0;
                        } else if (i8 > 25) {
                            break;
                        } else {
                            i9 = i8 + 1;
                            a = iArr2;
                        }
                        i7++;
                        iArr2 = a;
                        i8 = i9;
                    }
                    i5 = i7 - (i8 + 1);
                    hVarArr[2] = new h((float) iArr2[0], (float) i5);
                    hVarArr[3] = new h((float) iArr2[1], (float) i5);
                }
                if (i5 - i6 < 10) {
                    for (i5 = 0; i5 < hVarArr.length; i5++) {
                        hVarArr[i5] = null;
                    }
                }
                return hVarArr;
            }
            i7 += 5;
        }
        obj = null;
        i6 = i7;
        i5 = i6 + 1;
        if (obj != null) {
            iArr2 = new int[]{(int) hVarArr[0].a(), (int) hVarArr[1].a()};
            i8 = 0;
            i7 = i5;
            while (i7 < i) {
                a = a(bVar, iArr2[0], i7, i2, false, iArr, iArr3);
                if (a == null) {
                }
                if (i8 > 25) {
                    break;
                }
                i9 = i8 + 1;
                a = iArr2;
                i7++;
                iArr2 = a;
                i8 = i9;
            }
            i5 = i7 - (i8 + 1);
            hVarArr[2] = new h((float) iArr2[0], (float) i5);
            hVarArr[3] = new h((float) iArr2[1], (float) i5);
        }
        if (i5 - i6 < 10) {
            for (i5 = 0; i5 < hVarArr.length; i5++) {
                hVarArr[i5] = null;
            }
        }
        return hVarArr;
    }

    private static int[] a(com.google.zxing.common.b bVar, int i, int i2, int i3, boolean z, int[] iArr, int[] iArr2) {
        int i4;
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int length = iArr.length;
        int i5 = 0;
        while (bVar.a(i, i2) && i > 0) {
            i4 = i5 + 1;
            if (i5 >= 3) {
                break;
            }
            i--;
            i5 = i4;
        }
        i5 = 0;
        i4 = i;
        int i6 = z;
        while (i < i3) {
            if ((bVar.a(i, i2) ^ i6) != 0) {
                iArr2[i5] = iArr2[i5] + 1;
            } else {
                if (i5 != length - 1) {
                    i5++;
                } else if (a(iArr2, iArr, (int) g.c) < 107) {
                    return new int[]{i4, i};
                } else {
                    i4 += iArr2[0] + iArr2[1];
                    System.arraycopy(iArr2, 2, iArr2, 0, length - 2);
                    iArr2[length - 2] = 0;
                    iArr2[length - 1] = 0;
                    i5--;
                }
                iArr2[i5] = 1;
                i6 = i6 == 0 ? 1 : 0;
            }
            i++;
        }
        if (i5 != length - 1 || a(iArr2, iArr, (int) g.c) >= 107) {
            return null;
        }
        return new int[]{i4, i - 1};
    }

    private static int a(int[] iArr, int[] iArr2, int i) {
        int i2;
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        for (i2 = 0; i2 < length; i2++) {
            i4 += iArr[i2];
            i3 += iArr2[i2];
        }
        if (i4 < i3) {
            return ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        int i5 = (i4 << 8) / i3;
        int i6 = (i * i5) >> 8;
        i3 = 0;
        for (i2 = 0; i2 < length; i2++) {
            int i7 = iArr[i2] << 8;
            int i8 = iArr2[i2] * i5;
            i7 = i7 > i8 ? i7 - i8 : i8 - i7;
            if (i7 > i6) {
                return ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            i3 += i7;
        }
        return i3 / i4;
    }
}
