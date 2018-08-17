package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.b;
import com.google.zxing.common.d;
import com.google.zxing.f;
import com.google.zxing.g;
import com.google.zxing.h;
import com.google.zxing.qrcode.a.c;
import com.google.zxing.qrcode.decoder.e;
import java.util.List;
import java.util.Map;

/* compiled from: QRCodeReader */
public class a implements f {
    private static final h[] a = new h[0];
    private final e b = new e();

    public final g a(b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        d a;
        h[] e;
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            com.google.zxing.common.f a2 = new c(bVar.c()).a((Map) map);
            a = this.b.a(a2.d(), (Map) map);
            e = a2.e();
        } else {
            a = this.b.a(a(bVar.c()), (Map) map);
            e = a;
        }
        g gVar = new g(a.b(), a.a(), e, BarcodeFormat.QR_CODE);
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
        float a = a(b, bVar);
        int i = b[1];
        int i2 = c[1];
        int i3 = b[0];
        int i4 = c[0];
        if (i3 >= i4 || i >= i2) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (i2 - i != i4 - i3) {
            i4 = (i2 - i) + i3;
        }
        int round = Math.round(((float) ((i4 - i3) + 1)) / a);
        int round2 = Math.round(((float) ((i2 - i) + 1)) / a);
        if (round <= 0 || round2 <= 0) {
            throw NotFoundException.getNotFoundInstance();
        } else if (round2 != round) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            int i5 = (int) (a / 2.0f);
            int i6 = i + i5;
            i = i3 + i5;
            i4 = (((int) (((float) (round - 1)) * a)) + i) - (i4 - 1);
            if (i4 > 0) {
                i3 = i - i4;
            } else {
                i3 = i;
            }
            i4 = (((int) (((float) (round2 - 1)) * a)) + i6) - (i2 - 1);
            if (i4 > 0) {
                i4 = i6 - i4;
            } else {
                i4 = i6;
            }
            com.google.zxing.common.b bVar2 = new com.google.zxing.common.b(round, round2);
            for (i = 0; i < round2; i++) {
                i5 = i4 + ((int) (((float) i) * a));
                for (i6 = 0; i6 < round; i6++) {
                    if (bVar.a(((int) (((float) i6) * a)) + i3, i5)) {
                        bVar2.b(i6, i);
                    }
                }
            }
            return bVar2;
        }
    }

    private static float a(int[] iArr, com.google.zxing.common.b bVar) throws NotFoundException {
        int e = bVar.e();
        int d = bVar.d();
        int i = iArr[0];
        boolean z = true;
        int i2 = iArr[1];
        int i3 = i;
        int i4 = 0;
        while (i3 < d && i2 < e) {
            boolean z2;
            if (z != bVar.a(i3, i2)) {
                i = i4 + 1;
                if (i == 5) {
                    break;
                }
                boolean z3;
                if (z) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                int i5 = i;
                z2 = z3;
                i4 = i5;
            } else {
                z2 = z;
            }
            i3++;
            i2++;
            z = z2;
        }
        if (i3 != d && i2 != e) {
            return ((float) (i3 - iArr[0])) / 7.0f;
        }
        throw NotFoundException.getNotFoundInstance();
    }
}
