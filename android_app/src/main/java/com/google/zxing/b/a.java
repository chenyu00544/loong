package com.google.zxing.b;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.b;
import com.google.zxing.b.a.c;
import com.google.zxing.common.d;
import com.google.zxing.f;
import com.google.zxing.g;
import com.google.zxing.h;
import java.util.Map;

/* compiled from: MaxiCodeReader */
public final class a implements f {
    private static final h[] a = new h[0];
    private final c b = new c();

    public g a(b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        if (map == null || !map.containsKey(DecodeHintType.PURE_BARCODE)) {
            throw NotFoundException.getNotFoundInstance();
        }
        d a = this.b.a(a(bVar.c()), map);
        g gVar = new g(a.b(), a.a(), a, BarcodeFormat.MAXICODE);
        String d = a.d();
        if (d != null) {
            gVar.a(ResultMetadataType.ERROR_CORRECTION_LEVEL, d);
        }
        return gVar;
    }

    public void a() {
    }

    private static com.google.zxing.common.b a(com.google.zxing.common.b bVar) throws NotFoundException {
        int[] a = bVar.a();
        if (a == null) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i = a[0];
        int i2 = a[1];
        int i3 = a[2];
        int i4 = a[3];
        com.google.zxing.common.b bVar2 = new com.google.zxing.common.b(30, 33);
        for (int i5 = 0; i5 < 33; i5++) {
            int i6 = i2 + (((i5 * i4) + (i4 / 2)) / 33);
            for (int i7 = 0; i7 < 30; i7++) {
                if (bVar.a(((((i7 * i3) + (i3 / 2)) + (((i5 & 1) * i3) / 2)) / 30) + i, i6)) {
                    bVar2.b(i7, i5);
                }
            }
        }
        return bVar2;
    }
}
