package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.common.a;
import com.google.zxing.g;
import com.google.zxing.h;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: UPCEANExtension5Support */
final class n {
    private static final int[] a = new int[]{24, 20, 18, 17, 12, 6, 3, 10, 9, 5};
    private final int[] b = new int[4];
    private final StringBuilder c = new StringBuilder();

    n() {
    }

    g a(int i, a aVar, int[] iArr) throws NotFoundException {
        StringBuilder stringBuilder = this.c;
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
        int[] iArr2 = this.b;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int a = aVar.a();
        int i = iArr[1];
        int i2 = 0;
        for (int i3 = 0; i3 < 5 && i < a; i3++) {
            int a2 = p.a(aVar, iArr2, i, p.e);
            stringBuilder.append((char) ((a2 % 10) + 48));
            int i4 = 0;
            while (i4 < iArr2.length) {
                int i5 = iArr2[i4] + i;
                i4++;
                i = i5;
            }
            if (a2 >= 10) {
                i2 |= 1 << (4 - i3);
            }
            if (i3 != 4) {
                i = aVar.d(aVar.c(i));
            }
        }
        if (stringBuilder.length() != 5) {
            throw NotFoundException.getNotFoundInstance();
        }
        if (a(stringBuilder.toString()) == a(i2)) {
            return i;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int a(CharSequence charSequence) {
        int i;
        int length = charSequence.length();
        int i2 = 0;
        for (i = length - 2; i >= 0; i -= 2) {
            i2 += charSequence.charAt(i) - 48;
        }
        i2 *= 3;
        for (i = length - 1; i >= 0; i -= 2) {
            i2 += charSequence.charAt(i) - 48;
        }
        return (i2 * 3) % 10;
    }

    private static int a(int i) throws NotFoundException {
        for (int i2 = 0; i2 < 10; i2++) {
            if (i == a[i2]) {
                return i2;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static Map<ResultMetadataType, Object> a(String str) {
        if (str.length() != 5) {
            return null;
        }
        String b = b(str);
        if (b == null) {
            return null;
        }
        Map<ResultMetadataType, Object> enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.SUGGESTED_PRICE, b);
        return enumMap;
    }

    private static String b(String str) {
        String str2;
        switch (str.charAt(0)) {
            case '0':
                str2 = "£";
                break;
            case '5':
                str2 = "$";
                break;
            case '9':
                if (!"90000".equals(str)) {
                    if (!"99991".equals(str)) {
                        if (!"99990".equals(str)) {
                            str2 = "";
                            break;
                        }
                        return "Used";
                    }
                    return "0.00";
                }
                return null;
            default:
                str2 = "";
                break;
        }
        int parseInt = Integer.parseInt(str.substring(1));
        String valueOf = String.valueOf(parseInt / 100);
        parseInt %= 100;
        return str2 + valueOf + '.' + (parseInt < 10 ? "0" + parseInt : String.valueOf(parseInt));
    }
}
