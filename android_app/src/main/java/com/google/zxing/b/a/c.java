package com.google.zxing.b.a;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.b;
import com.google.zxing.common.d;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.google.zxing.common.reedsolomon.a;
import java.util.Map;

/* compiled from: Decoder */
public final class c {
    private final com.google.zxing.common.reedsolomon.c a = new com.google.zxing.common.reedsolomon.c(a.h);

    public d a(b bVar, Map<DecodeHintType, ?> map) throws FormatException, ChecksumException {
        byte[] bArr;
        Object a = new a(bVar).a();
        a(a, 0, 10, 10, 0);
        int i = a[0] & 15;
        switch (i) {
            case 2:
            case 3:
            case 4:
                a(a, 20, 84, 40, 1);
                a(a, 20, 84, 40, 2);
                bArr = new byte[94];
                break;
            case 5:
                a(a, 20, 68, 56, 1);
                a(a, 20, 68, 56, 2);
                bArr = new byte[78];
                break;
            default:
                throw FormatException.getFormatInstance();
        }
        System.arraycopy(a, 0, bArr, 0, 10);
        System.arraycopy(a, 20, bArr, 10, bArr.length - 10);
        return b.a(bArr, i);
    }

    private void a(byte[] bArr, int i, int i2, int i3, int i4) throws ChecksumException {
        int i5 = 0;
        int i6 = i2 + i3;
        int i7 = i4 == 0 ? 1 : 2;
        int[] iArr = new int[(i6 / i7)];
        int i8 = 0;
        while (i8 < i6) {
            if (i4 == 0 || i8 % 2 == i4 - 1) {
                iArr[i8 / i7] = bArr[i8 + i] & 255;
            }
            i8++;
        }
        try {
            this.a.a(iArr, i3 / i7);
            while (i5 < i2) {
                if (i4 == 0 || i5 % 2 == i4 - 1) {
                    bArr[i5 + i] = (byte) iArr[i5 / i7];
                }
                i5++;
            }
        } catch (ReedSolomonException e) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
