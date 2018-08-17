package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.a;
import com.google.zxing.g;
import com.google.zxing.h;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: UPCEANExtension2Support */
final class m {
    private final int[] a = new int[4];
    private final StringBuilder b = new StringBuilder();

    m() {
    }

    g a(int i, a aVar, int[] iArr) throws NotFoundException {
        StringBuilder stringBuilder = this.b;
        stringBuilder.setLength(0);
        int a = a(aVar, iArr, stringBuilder);
        String stringBuilder2 = stringBuilder.toString();
        Map a2 = a(stringBuilder2);
        g gVar = new g(stringBuilder2, null, new h[]{new h(((float) (iArr[0] + iArr[1])) / 2.0f, (float) i), new h((float) a, (float) i)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (a2 != null) {
            gVar.a(a2);
        }
        return gVar;
    }

    int a(a aVar, int[] iArr, StringBuilder stringBuilder) throws NotFoundException {
        int[] iArr2 = this.a;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int a = aVar.a();
        int i = iArr[1];
        int i2 = 0;
        for (int i3 = 0; i3 < 2 && i < a; i3++) {
            int a2 = p.a(aVar, iArr2, i, p.e);
            stringBuilder.append((char) ((a2 % 10) + 48));
            int i4 = 0;
            while (i4 < iArr2.length) {
                int i5 = iArr2[i4] + i;
                i4++;
                i = i5;
            }
            if (a2 >= 10) {
                i2 |= 1 << (1 - i3);
            }
            if (i3 != 1) {
                i = aVar.d(aVar.c(i));
            }
        }
        if (stringBuilder.length() != 2) {
            throw NotFoundException.getNotFoundInstance();
        } else if (Integer.parseInt(stringBuilder.toString()) % 4 == i2) {
            return i;
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static Map<ResultMetadataType, Object> a(String str) {
        if (str.length() != 2) {
            return null;
        }
        Map<ResultMetadataType, Object> enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(str));
        return enumMap;
    }
}
