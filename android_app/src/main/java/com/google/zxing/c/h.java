package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.a;
import com.google.zxing.g;
import java.util.Map;

/* compiled from: ITFReader */
public final class h extends k {
    static final int[][] a = new int[][]{new int[]{1, 1, 3, 3, 1}, new int[]{3, 1, 1, 1, 3}, new int[]{1, 3, 1, 1, 3}, new int[]{3, 3, 1, 1, 1}, new int[]{1, 1, 3, 1, 3}, new int[]{3, 1, 3, 1, 1}, new int[]{1, 3, 3, 1, 1}, new int[]{1, 1, 1, 3, 3}, new int[]{3, 1, 1, 3, 1}, new int[]{1, 3, 1, 3, 1}};
    private static final int[] b = new int[]{48, 44, 24, 20, 18, 16, 14, 12, 10, 8, 6};
    private static final int[] d = new int[]{1, 1, 1, 1};
    private static final int[] e = new int[]{1, 1, 3};
    private int c = -1;

    public g a(int i, a aVar, Map<DecodeHintType, ?> map) throws FormatException, NotFoundException {
        int[] iArr;
        int i2;
        int[] a = a(aVar);
        int[] b = b(aVar);
        StringBuilder stringBuilder = new StringBuilder(20);
        a(aVar, a[1], b[0], stringBuilder);
        String stringBuilder2 = stringBuilder.toString();
        if (map != null) {
            iArr = (int[]) map.get(DecodeHintType.ALLOWED_LENGTHS);
        } else {
            iArr = null;
        }
        if (iArr == null) {
            iArr = b;
        }
        int length = stringBuilder2.length();
        for (int i3 : r0) {
            if (length == i3) {
                i2 = 1;
                break;
            }
        }
        i2 = 0;
        if (i2 == 0) {
            throw FormatException.getFormatInstance();
        }
        return new g(stringBuilder2, null, new com.google.zxing.h[]{new com.google.zxing.h((float) a[1], (float) i), new com.google.zxing.h((float) b[0], (float) i)}, BarcodeFormat.ITF);
    }

    private static void a(a aVar, int i, int i2, StringBuilder stringBuilder) throws NotFoundException {
        int[] iArr = new int[10];
        int[] iArr2 = new int[5];
        int[] iArr3 = new int[5];
        int i3 = i;
        while (i3 < i2) {
            int i4;
            k.a(aVar, i3, iArr);
            for (i4 = 0; i4 < 5; i4++) {
                int i5 = i4 << 1;
                iArr2[i4] = iArr[i5];
                iArr3[i4] = iArr[i5 + 1];
            }
            stringBuilder.append((char) (a(iArr2) + 48));
            stringBuilder.append((char) (a(iArr3) + 48));
            i4 = i3;
            for (int i6 : iArr) {
                i4 += i6;
            }
            i3 = i4;
        }
    }

    int[] a(a aVar) throws NotFoundException {
        int[] c = c(aVar, c(aVar), d);
        this.c = (c[1] - c[0]) >> 2;
        a(aVar, c[0]);
        return c;
    }

    private void a(a aVar, int i) throws NotFoundException {
        int i2 = this.c * 10;
        int i3 = i - 1;
        while (i2 > 0 && i3 >= 0 && !aVar.a(i3)) {
            i2--;
            i3--;
        }
        if (i2 != 0) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static int c(a aVar) throws NotFoundException {
        int a = aVar.a();
        int c = aVar.c(0);
        if (c != a) {
            return c;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    int[] b(a aVar) throws NotFoundException {
        aVar.d();
        try {
            int[] c = c(aVar, c(aVar), e);
            a(aVar, c[0]);
            int i = c[0];
            c[0] = aVar.a() - c[1];
            c[1] = aVar.a() - i;
            return c;
        } finally {
            aVar.d();
        }
    }

    private static int[] c(a aVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        Object obj = new int[length];
        int a = aVar.a();
        int i2 = i;
        int i3 = 0;
        int i4 = 0;
        while (i < a) {
            if ((aVar.a(i) ^ i4) != 0) {
                obj[i3] = obj[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else if (k.a((int[]) obj, iArr, (int) com.baidu.location.b.g.c) < 107) {
                    return new int[]{i2, i};
                } else {
                    i2 += obj[0] + obj[1];
                    System.arraycopy(obj, 2, obj, 0, length - 2);
                    obj[length - 2] = null;
                    obj[length - 1] = null;
                    i3--;
                }
                obj[i3] = 1;
                if (i4 == 0) {
                    i4 = 1;
                } else {
                    i4 = 0;
                }
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int a(int[] iArr) throws NotFoundException {
        int i = 107;
        int i2 = -1;
        int length = a.length;
        int i3 = 0;
        while (i3 < length) {
            int a = k.a(iArr, a[i3], (int) com.baidu.location.b.g.c);
            if (a < i) {
                i2 = i3;
            } else {
                a = i;
            }
            i3++;
            i = a;
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
