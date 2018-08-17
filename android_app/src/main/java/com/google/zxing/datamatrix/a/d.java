package com.google.zxing.datamatrix.a;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.common.b;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.google.zxing.common.reedsolomon.a;
import com.google.zxing.common.reedsolomon.c;

/* compiled from: Decoder */
public final class d {
    private final c a = new c(a.f);

    public com.google.zxing.common.d a(b bVar) throws FormatException, ChecksumException {
        int i;
        a aVar = new a(bVar);
        b[] a = b.a(aVar.b(), aVar.a());
        int length = a.length;
        int i2 = 0;
        for (b a2 : a) {
            i2 += a2.a();
        }
        byte[] bArr = new byte[i2];
        for (i = 0; i < length; i++) {
            b bVar2 = a[i];
            byte[] b = bVar2.b();
            int a3 = bVar2.a();
            a(b, a3);
            for (i2 = 0; i2 < a3; i2++) {
                bArr[(i2 * length) + i] = b[i2];
            }
        }
        return c.a(bArr);
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
