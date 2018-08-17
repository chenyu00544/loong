package com.google.zxing.pdf417.a;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.b;
import com.google.zxing.common.d;
import com.google.zxing.h;
import com.google.zxing.pdf417.a.a.a;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: PDF417ScanningDecoder */
public final class j {
    private static final a a = new a();

    public static d a(b bVar, h hVar, h hVar2, h hVar3, h hVar4, int i, int i2) throws NotFoundException, FormatException, ChecksumException {
        g gVar = null;
        int i3 = 0;
        c cVar = new c(bVar, hVar, hVar2, hVar3, hVar4);
        f fVar = null;
        g gVar2 = null;
        while (i3 < 2) {
            g a;
            g a2;
            if (hVar != null) {
                a = a(bVar, cVar, hVar, true, i, i2);
            } else {
                a = gVar2;
            }
            if (hVar3 != null) {
                a2 = a(bVar, cVar, hVar3, false, i, i2);
            } else {
                a2 = gVar;
            }
            f a3 = a((h) a, (h) a2);
            if (a3 == null) {
                throw NotFoundException.getNotFoundInstance();
            } else if (i3 != 0 || (a3.e().c() >= cVar.c() && a3.e().d() <= cVar.d())) {
                a3.a(cVar);
                gVar = a2;
                fVar = a3;
                gVar2 = a;
                break;
            } else {
                i3++;
                gVar = a2;
                fVar = a3;
                cVar = a3.e();
                gVar2 = a;
            }
        }
        int b = fVar.b() + 1;
        fVar.a(0, gVar2);
        fVar.a(b, gVar);
        boolean z = gVar2 != null;
        int i4 = 1;
        int i5 = i2;
        i3 = i;
        while (i4 <= b) {
            int i6 = z ? i4 : b - i4;
            if (fVar.a(i6) == null) {
                g hVar5;
                if (i6 == 0 || i6 == b) {
                    hVar5 = new h(cVar, i6 == 0);
                } else {
                    hVar5 = new g(cVar);
                }
                fVar.a(i6, hVar5);
                int c = cVar.c();
                int i7 = -1;
                while (c <= cVar.d()) {
                    int i8;
                    int a4 = a(fVar, i6, c, z);
                    if (a4 < 0 || a4 > cVar.b()) {
                        if (i7 == -1) {
                            i8 = i7;
                            c++;
                            i7 = i8;
                        } else {
                            a4 = i7;
                        }
                    }
                    d a5 = a(bVar, cVar.a(), cVar.b(), z, a4, c, i3, i5);
                    if (a5 != null) {
                        hVar5.a(c, a5);
                        i3 = Math.min(i3, a5.c());
                        i5 = Math.max(i5, a5.c());
                        i8 = a4;
                    } else {
                        i8 = i7;
                    }
                    c++;
                    i7 = i8;
                }
            }
            i4++;
        }
        return a(fVar);
    }

    private static f a(h hVar, h hVar2) throws NotFoundException {
        if (hVar == null && hVar2 == null) {
            return null;
        }
        a b = b(hVar, hVar2);
        if (b != null) {
            return new f(b, c.a(a(hVar), a(hVar2)));
        }
        return null;
    }

    private static c a(h hVar) throws NotFoundException {
        if (hVar == null) {
            return null;
        }
        int length;
        int[] d = hVar.d();
        int a = a(d);
        int i = 0;
        for (int i2 : d) {
            i += a - i2;
            if (i2 > 0) {
                break;
            }
        }
        d[] b = hVar.b();
        int i3 = i;
        i = 0;
        while (i3 > 0 && b[i] == null) {
            i3--;
            i++;
        }
        i = 0;
        for (length = d.length - 1; length >= 0; length--) {
            i += a - d[length];
            if (d[length] > 0) {
                break;
            }
        }
        length = i;
        i = b.length - 1;
        while (length > 0 && b[i] == null) {
            length--;
            i--;
        }
        return hVar.a().a(i3, length, hVar.f());
    }

    private static int a(int[] iArr) {
        int i = -1;
        for (int max : iArr) {
            i = Math.max(i, max);
        }
        return i;
    }

    private static a b(h hVar, h hVar2) {
        if (hVar == null || hVar.e() == null) {
            if (hVar2 == null) {
                return null;
            }
            return hVar2.e();
        } else if (hVar2 != null && hVar2.e() != null) {
            a e = hVar.e();
            a e2 = hVar2.e();
            if (e.a() == e2.a() || e.b() == e2.b() || e.c() == e2.c()) {
                return e;
            }
            return null;
        } else if (hVar != null) {
            return hVar.e();
        } else {
            return null;
        }
    }

    private static h a(b bVar, c cVar, h hVar, boolean z, int i, int i2) {
        h hVar2 = new h(cVar, z);
        for (int i3 = 0; i3 < 2; i3++) {
            int i4;
            if (i3 == 0) {
                i4 = 1;
            } else {
                i4 = -1;
            }
            int a = (int) hVar.a();
            int b = (int) hVar.b();
            while (b <= cVar.d() && b >= cVar.c()) {
                d a2 = a(bVar, 0, bVar.d(), z, a, b, i, i2);
                if (a2 != null) {
                    hVar2.a(b, a2);
                    if (z) {
                        a = a2.d();
                    } else {
                        a = a2.e();
                    }
                }
                b += i4;
            }
        }
        return hVar2;
    }

    private static void a(f fVar, b[][] bVarArr) throws NotFoundException {
        int[] a = bVarArr[0][1].a();
        int b = (fVar.b() * fVar.c()) - a(fVar.d());
        if (a.length == 0) {
            if (b < 1 || b > 928) {
                throw NotFoundException.getNotFoundInstance();
            }
            bVarArr[0][1].a(b);
        } else if (a[0] != b) {
            bVarArr[0][1].a(b);
        }
    }

    private static d a(f fVar) throws FormatException, ChecksumException, NotFoundException {
        int i = 0;
        b[][] b = b(fVar);
        a(fVar, b);
        Collection arrayList = new ArrayList();
        int[] iArr = new int[(fVar.c() * fVar.b())];
        List arrayList2 = new ArrayList();
        Collection arrayList3 = new ArrayList();
        for (int i2 = 0; i2 < fVar.c(); i2++) {
            for (int i3 = 0; i3 < fVar.b(); i3++) {
                Object a = b[i2][i3 + 1].a();
                int b2 = (fVar.b() * i2) + i3;
                if (a.length == 0) {
                    arrayList.add(Integer.valueOf(b2));
                } else if (a.length == 1) {
                    iArr[b2] = a[0];
                } else {
                    arrayList3.add(Integer.valueOf(b2));
                    arrayList2.add(a);
                }
            }
        }
        int[][] iArr2 = new int[arrayList2.size()][];
        while (i < iArr2.length) {
            iArr2[i] = (int[]) arrayList2.get(i);
            i++;
        }
        return a(fVar.d(), iArr, com.google.zxing.pdf417.a.a(arrayList), com.google.zxing.pdf417.a.a(arrayList3), iArr2);
    }

    private static d a(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) throws FormatException, ChecksumException {
        int[] iArr5 = new int[iArr3.length];
        int i2 = 100;
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                for (i2 = 0; i2 < iArr5.length; i2++) {
                    iArr[iArr3[i2]] = iArr4[i2][iArr5[i2]];
                }
                try {
                    break;
                } catch (ChecksumException e) {
                    if (iArr5.length == 0) {
                        throw ChecksumException.getChecksumInstance();
                    }
                    for (i2 = 0; i2 < iArr5.length; i2++) {
                        if (iArr5[i2] < iArr4[i2].length - 1) {
                            iArr5[i2] = iArr5[i2] + 1;
                            break;
                        }
                        iArr5[i2] = 0;
                        if (i2 == iArr5.length - 1) {
                            throw ChecksumException.getChecksumInstance();
                        }
                    }
                    i2 = i3;
                }
            } else {
                throw ChecksumException.getChecksumInstance();
            }
        }
        return a(iArr, i, iArr2);
    }

    private static b[][] b(f fVar) {
        int i;
        int i2;
        b[][] bVarArr = (b[][]) Array.newInstance(b.class, new int[]{fVar.c(), fVar.b() + 2});
        for (i = 0; i < bVarArr.length; i++) {
            for (i2 = 0; i2 < bVarArr[i].length; i2++) {
                bVarArr[i][i2] = new b();
            }
        }
        g[] a = fVar.a();
        int length = a.length;
        i2 = 0;
        i = -1;
        while (i2 < length) {
            g gVar = a[i2];
            int i3 = i + 1;
            if (gVar != null) {
                for (d dVar : gVar.b()) {
                    if (!(dVar == null || dVar.h() == -1)) {
                        bVarArr[dVar.h()][i3].a(dVar.g());
                    }
                }
            }
            i2++;
            i = i3;
        }
        return bVarArr;
    }

    private static boolean a(f fVar, int i) {
        return i >= 0 && i <= fVar.b() + 1;
    }

    private static int a(f fVar, int i, int i2, boolean z) {
        int i3 = z ? 1 : -1;
        d dVar = null;
        if (a(fVar, i - i3)) {
            dVar = fVar.a(i - i3).c(i2);
        }
        if (dVar == null) {
            dVar = fVar.a(i).a(i2);
            if (dVar != null) {
                return z ? dVar.d() : dVar.e();
            } else {
                if (a(fVar, i - i3)) {
                    dVar = fVar.a(i - i3).a(i2);
                }
                if (dVar != null) {
                    return z ? dVar.e() : dVar.d();
                } else {
                    int i4 = 0;
                    while (a(fVar, i - i3)) {
                        i -= i3;
                        for (d dVar2 : fVar.a(i).b()) {
                            if (dVar2 != null) {
                                return ((i3 * i4) * (dVar2.e() - dVar2.d())) + (z ? dVar2.e() : dVar2.d());
                            }
                        }
                        i4++;
                    }
                    return z ? fVar.e().a() : fVar.e().b();
                }
            }
        } else if (z) {
            return dVar.e();
        } else {
            return dVar.d();
        }
    }

    private static d a(b bVar, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        int b = b(bVar, i, i2, z, i3, i4);
        int[] a = a(bVar, i, i2, z, b, i4);
        if (a == null) {
            return null;
        }
        int i7;
        int a2 = com.google.zxing.pdf417.a.a(a);
        if (z) {
            i7 = b;
            b += a2;
        } else {
            for (i7 = 0; i7 < (a.length >> 1); i7++) {
                int i8 = a[i7];
                a[i7] = a[(a.length - 1) - i7];
                a[(a.length - 1) - i7] = i8;
            }
            i7 = b - a2;
        }
        if (!a(a2, i5, i6)) {
            return null;
        }
        a2 = i.a(a);
        i8 = com.google.zxing.pdf417.a.a((long) a2);
        if (i8 == -1) {
            return null;
        }
        return new d(i7, b, c(a2), i8);
    }

    private static int[] a(b bVar, int i, int i2, boolean z, int i3, int i4) {
        int[] iArr = new int[8];
        int i5 = z ? 1 : -1;
        boolean z2 = z;
        int i6 = 0;
        while (true) {
            if (((z && i3 < i2) || (!z && i3 >= i)) && i6 < iArr.length) {
                if (bVar.a(i3, i4) == z2) {
                    iArr[i6] = iArr[i6] + 1;
                    i3 += i5;
                } else {
                    i6++;
                    z2 = !z2;
                }
            }
        }
        if (i6 == iArr.length || (((z && i3 == i2) || (!z && i3 == i)) && i6 == iArr.length - 1)) {
            return iArr;
        }
        return null;
    }

    private static int a(int i) {
        return 2 << i;
    }

    private static int b(b bVar, int i, int i2, boolean z, int i3, int i4) {
        int i5 = 0;
        int i6 = z ? -1 : 1;
        int i7 = i3;
        while (i5 < 2) {
            int i8 = i7;
            while (true) {
                if (((!z || i8 < i) && (z || i8 >= i2)) || z != bVar.a(i8, i4)) {
                    i6 = -i6;
                } else if (Math.abs(i3 - i8) > 2) {
                    return i3;
                } else {
                    i8 += i6;
                }
            }
            i6 = -i6;
            i5++;
            z = !z;
            i7 = i8;
        }
        return i7;
    }

    private static boolean a(int i, int i2, int i3) {
        return i2 + -2 <= i && i <= i3 + 2;
    }

    private static d a(int[] iArr, int i, int[] iArr2) throws FormatException, ChecksumException {
        if (iArr.length == 0) {
            throw FormatException.getFormatInstance();
        }
        int i2 = 1 << (i + 1);
        int a = a(iArr, iArr2, i2);
        a(iArr, i2);
        d a2 = e.a(iArr, String.valueOf(i));
        a2.a(Integer.valueOf(a));
        a2.b(Integer.valueOf(iArr2.length));
        return a2;
    }

    private static int a(int[] iArr, int[] iArr2, int i) throws ChecksumException {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return a.a(iArr, i, iArr2);
        }
        throw ChecksumException.getChecksumInstance();
    }

    private static void a(int[] iArr, int i) throws FormatException {
        if (iArr.length < 4) {
            throw FormatException.getFormatInstance();
        }
        int i2 = iArr[0];
        if (i2 > iArr.length) {
            throw FormatException.getFormatInstance();
        } else if (i2 != 0) {
        } else {
            if (i < iArr.length) {
                iArr[0] = iArr.length - i;
                return;
            }
            throw FormatException.getFormatInstance();
        }
    }

    private static int[] b(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int length = iArr.length - 1;
        while (true) {
            if ((i & 1) != i2) {
                i2 = i & 1;
                length--;
                if (length < 0) {
                    return iArr;
                }
            }
            iArr[length] = iArr[length] + 1;
            i >>= 1;
        }
    }

    private static int c(int i) {
        return b(b(i));
    }

    private static int b(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }
}
