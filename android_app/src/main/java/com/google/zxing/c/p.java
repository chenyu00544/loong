package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.a;
import com.google.zxing.g;
import com.google.zxing.h;
import com.google.zxing.i;
import java.util.Arrays;
import java.util.Map;

/* compiled from: UPCEANReader */
public abstract class p extends k {
    static final int[] b = new int[]{1, 1, 1};
    static final int[] c = new int[]{1, 1, 1, 1, 1};
    static final int[][] d = new int[][]{new int[]{3, 2, 1, 1}, new int[]{2, 2, 2, 1}, new int[]{2, 1, 2, 2}, new int[]{1, 4, 1, 1}, new int[]{1, 1, 3, 2}, new int[]{1, 2, 3, 1}, new int[]{1, 1, 1, 4}, new int[]{1, 3, 1, 2}, new int[]{1, 2, 1, 3}, new int[]{3, 1, 1, 2}};
    static final int[][] e = new int[20][];
    private final StringBuilder a = new StringBuilder(20);
    private final o f = new o();
    private final g g = new g();

    protected abstract int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws NotFoundException;

    abstract BarcodeFormat b();

    static {
        System.arraycopy(d, 0, e, 0, 10);
        for (int i = 10; i < 20; i++) {
            int[] iArr = d[i - 10];
            int[] iArr2 = new int[iArr.length];
            for (int i2 = 0; i2 < iArr.length; i2++) {
                iArr2[i2] = iArr[(iArr.length - i2) - 1];
            }
            e[i] = iArr2;
        }
    }

    protected p() {
    }

    static int[] a(a aVar) throws NotFoundException {
        int[] iArr = new int[b.length];
        int i = 0;
        int[] iArr2 = null;
        boolean z = false;
        while (!z) {
            Arrays.fill(iArr, 0, b.length, 0);
            iArr2 = a(aVar, i, false, b, iArr);
            int i2 = iArr2[0];
            i = iArr2[1];
            int i3 = i2 - (i - i2);
            if (i3 >= 0) {
                z = aVar.a(i3, i2, false);
            }
        }
        return iArr2;
    }

    public g a(int i, a aVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        return a(i, aVar, a(aVar), (Map) map);
    }

    public g a(int i, a aVar, int[] iArr, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        i iVar;
        if (map == null) {
            iVar = null;
        } else {
            iVar = (i) map.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
        }
        if (iVar != null) {
            iVar.a(new h(((float) (iArr[0] + iArr[1])) / 2.0f, (float) i));
        }
        StringBuilder stringBuilder = this.a;
        stringBuilder.setLength(0);
        int a = a(aVar, iArr, stringBuilder);
        if (iVar != null) {
            iVar.a(new h((float) a, (float) i));
        }
        int[] a2 = a(aVar, a);
        if (iVar != null) {
            iVar.a(new h(((float) (a2[0] + a2[1])) / 2.0f, (float) i));
        }
        int i2 = a2[1];
        int i3 = (i2 - a2[0]) + i2;
        if (i3 >= aVar.a() || !aVar.a(i2, i3, false)) {
            throw NotFoundException.getNotFoundInstance();
        }
        String stringBuilder2 = stringBuilder.toString();
        if (a(stringBuilder2)) {
            float f = ((float) (iArr[1] + iArr[0])) / 2.0f;
            float f2 = ((float) (a2[1] + a2[0])) / 2.0f;
            BarcodeFormat b = b();
            g gVar = new g(stringBuilder2, null, new h[]{new h(f, (float) i), new h(f2, (float) i)}, b);
            try {
                g a3 = this.f.a(i, aVar, a2[1]);
                gVar.a(ResultMetadataType.UPC_EAN_EXTENSION, a3.a());
                gVar.a(a3.e());
                gVar.a(a3.c());
            } catch (ReaderException e) {
            }
            if (b == BarcodeFormat.EAN_13 || b == BarcodeFormat.UPC_A) {
                stringBuilder2 = this.g.a(stringBuilder2);
                if (stringBuilder2 != null) {
                    gVar.a(ResultMetadataType.POSSIBLE_COUNTRY, stringBuilder2);
                }
            }
            return gVar;
        }
        throw ChecksumException.getChecksumInstance();
    }

    boolean a(String str) throws ChecksumException, FormatException {
        return a((CharSequence) str);
    }

    static boolean a(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        if (length == 0) {
            return false;
        }
        int i;
        int i2 = 0;
        for (i = length - 2; i >= 0; i -= 2) {
            int charAt = charSequence.charAt(i) - 48;
            if (charAt < 0 || charAt > 9) {
                throw FormatException.getFormatInstance();
            }
            i2 += charAt;
        }
        i2 *= 3;
        for (i = length - 1; i >= 0; i -= 2) {
            length = charSequence.charAt(i) - 48;
            if (length < 0 || length > 9) {
                throw FormatException.getFormatInstance();
            }
            i2 += length;
        }
        if (i2 % 10 == 0) {
            return true;
        }
        return false;
    }

    int[] a(a aVar, int i) throws NotFoundException {
        return a(aVar, i, false, b);
    }

    static int[] a(a aVar, int i, boolean z, int[] iArr) throws NotFoundException {
        return a(aVar, i, z, iArr, new int[iArr.length]);
    }

    private static int[] a(a aVar, int i, boolean z, int[] iArr, int[] iArr2) throws NotFoundException {
        int length = iArr.length;
        int a = aVar.a();
        int d = z ? aVar.d(i) : aVar.c(i);
        int i2 = 0;
        int i3 = z;
        for (int i4 = d; i4 < a; i4++) {
            if ((aVar.a(i4) ^ i3) != 0) {
                iArr2[i2] = iArr2[i2] + 1;
            } else {
                if (i2 != length - 1) {
                    i2++;
                } else if (k.a(iArr2, iArr, 179) < com.baidu.location.b.g.K) {
                    return new int[]{d, i4};
                } else {
                    d += iArr2[0] + iArr2[1];
                    System.arraycopy(iArr2, 2, iArr2, 0, length - 2);
                    iArr2[length - 2] = 0;
                    iArr2[length - 1] = 0;
                    i2--;
                }
                iArr2[i2] = 1;
                if (i3 == 0) {
                    i3 = 1;
                } else {
                    i3 = 0;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static int a(a aVar, int[] iArr, int i, int[][] iArr2) throws NotFoundException {
        k.a(aVar, i, iArr);
        int i2 = com.baidu.location.b.g.K;
        int i3 = -1;
        int length = iArr2.length;
        int i4 = 0;
        while (i4 < length) {
            int a = k.a(iArr, iArr2[i4], 179);
            if (a < i2) {
                i3 = i4;
            } else {
                a = i2;
            }
            i4++;
            i2 = a;
        }
        if (i3 >= 0) {
            return i3;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
