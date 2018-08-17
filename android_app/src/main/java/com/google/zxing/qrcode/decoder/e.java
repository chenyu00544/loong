package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.b;
import com.google.zxing.common.d;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.google.zxing.common.reedsolomon.a;
import com.google.zxing.common.reedsolomon.c;
import java.util.Map;

/* compiled from: Decoder */
public final class e {
    private final c a = new c(a.e);

    public d a(b bVar, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        a aVar = new a(bVar);
        g b = aVar.b();
        ErrorCorrectionLevel a = aVar.a().a();
        b[] a2 = b.a(aVar.c(), b, a);
        int i = 0;
        for (b a3 : a2) {
            i += a3.a();
        }
        byte[] bArr = new byte[i];
        int length = a2.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            b bVar2 = a2[i2];
            byte[] b2 = bVar2.b();
            int a4 = bVar2.a();
            a(b2, a4);
            i = i3;
            i3 = 0;
            while (i3 < a4) {
                int i4 = i + 1;
                bArr[i] = b2[i3];
                i3++;
                i = i4;
            }
            i2++;
            i3 = i;
        }
        return d.a(bArr, b, a, (Map) map);
    }

    private void a(byte[] bArr, int i) throws ChecksumException {
        int i2 = 0;
        int length = bArr.length;
        int[] iArr = new int[length];
        for (int i3 = 0; i3 < length; i3++) {
            iArr[i3] = bArr[i3] & 255;
        }
        try {
            this.a.a(iArr, bArr.length - i);
            while (i2 < i) {
                bArr[i2] = (byte) iArr[i2];
                i2++;
            }
        } catch (ReedSolomonException e) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
