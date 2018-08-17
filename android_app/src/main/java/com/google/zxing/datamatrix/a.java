package com.google.zxing.datamatrix;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.b;
import com.google.zxing.datamatrix.a.d;
import com.google.zxing.f;
import com.google.zxing.g;
import com.google.zxing.h;
import java.util.List;
import java.util.Map;

/* compiled from: DataMatrixReader */
public final class a implements f {
    private static final h[] a = new h[0];
    private final d b = new d();

    public g a(b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        com.google.zxing.common.d a;
        h[] e;
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            com.google.zxing.common.f a2 = new com.google.zxing.datamatrix.b.a(bVar.c()).a();
            a = this.b.a(a2.d());
            e = a2.e();
        } else {
            a = this.b.a(a(bVar.c()));
            e = a;
        }
        g gVar = new g(a.b(), a.a(), e, BarcodeFormat.DATA_MATRIX);
        List c = a.c();
        if (c != null) {
            gVar.a(ResultMetadataType.BYTE_SEGMENTS, c);
        }
        String d = a.d();
        if (d != null) {
            gVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, d);
        }
        return gVar;
    }

    public void a() {
    }

    private static com.google.zxing.common.b a(com.google.zxing.common.b bVar) throws NotFoundException {
        int[] b = bVar.b();
        int[] c = bVar.c();
        if (b == null || c == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int a = a(b, bVar);
        int i = b[1];
        int i2 = c[1];
        int i3 = b[0];
        int i4 = ((c[0] - i3) + 1) / a;
        i2 = ((i2 - i) + 1) / a;
        if (i4 <= 0 || i2 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i5 = a >> 1;
        i += i5;
        int i6 = i3 + i5;
        com.google.zxing.common.b bVar2 = new com.google.zxing.common.b(i4, i2);
        for (i5 = 0; i5 < i2; i5++) {
            int i7 = i + (i5 * a);
            for (i3 = 0; i3 < i4; i3++) {
                if (bVar.a((i3 * a) + i6, i7)) {
                    bVar2.b(i3, i5);
                }
            }
        }
        return bVar2;
    }

    private static int a(int[] iArr, com.google.zxing.common.b bVar) throws NotFoundException {
        int d = bVar.d();
        int i = iArr[0];
        int i2 = iArr[1];
        while (i < d && bVar.a(i, i2)) {
            i++;
        }
        if (i == d) {
            throw NotFoundException.getNotFoundInstance();
        }
        i -= iArr[0];
        if (i != 0) {
            return i;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
