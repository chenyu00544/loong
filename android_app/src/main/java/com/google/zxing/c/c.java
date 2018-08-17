package com.google.zxing.c;

import com.baidu.location.b.g;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.a;
import com.google.zxing.h;
import java.util.Arrays;
import java.util.Map;

/* compiled from: Code39Reader */
public final class c extends k {
    static final int[] a = new int[]{52, 289, 97, 352, 49, 304, g.if, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, g.new, 133, 388, 196, 148, 168, 162, 138, 42};
    private static final char[] b = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
    private static final int c = a[39];
    private final boolean d;
    private final boolean e;
    private final StringBuilder f;
    private final int[] g;

    public c() {
        this(false);
    }

    public c(boolean z) {
        this(z, false);
    }

    public c(boolean z, boolean z2) {
        this.d = z;
        this.e = z2;
        this.f = new StringBuilder(20);
        this.g = new int[9];
    }

    public com.google.zxing.g a(int i, a aVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int a;
        int i2;
        int[] iArr = this.g;
        Arrays.fill(iArr, 0);
        CharSequence charSequence = this.f;
        charSequence.setLength(0);
        int c = aVar.c(a(aVar, iArr)[1]);
        int a2 = aVar.a();
        while (true) {
            k.a(aVar, c, iArr);
            a = a(iArr);
            if (a < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            char a3 = a(a);
            charSequence.append(a3);
            i2 = c;
            for (int i3 : iArr) {
                i2 += i3;
            }
            a = aVar.c(i2);
            if (a3 == '*') {
                break;
            }
            c = a;
        }
        charSequence.setLength(charSequence.length() - 1);
        int i4 = 0;
        for (int i32 : iArr) {
            i4 += i32;
        }
        i2 = (a - c) - i4;
        if (a == a2 || (i2 >> 1) >= i4) {
            if (this.d) {
                int length = charSequence.length() - 1;
                i4 = 0;
                for (i2 = 0; i2 < length; i2++) {
                    i4 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(this.f.charAt(i2));
                }
                if (charSequence.charAt(length) != b[i4 % 43]) {
                    throw ChecksumException.getChecksumInstance();
                }
                charSequence.setLength(length);
            }
            if (charSequence.length() == 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            String a4;
            if (this.e) {
                a4 = a(charSequence);
            } else {
                a4 = charSequence.toString();
            }
            float f = ((float) (c + a)) / 2.0f;
            return new com.google.zxing.g(a4, null, new h[]{new h(((float) (r6[1] + r6[0])) / 2.0f, (float) i), new h(f, (float) i)}, BarcodeFormat.CODE_39);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int[] a(a aVar, int[] iArr) throws NotFoundException {
        int a = aVar.a();
        int c = aVar.c(0);
        int length = iArr.length;
        int i = c;
        int i2 = 0;
        int i3 = 0;
        while (i < a) {
            if ((aVar.a(i) ^ i2) != 0) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 != length - 1) {
                    i3++;
                } else if (a(iArr) == c && aVar.a(Math.max(0, c - ((i - c) >> 1)), c, false)) {
                    return new int[]{c, i};
                } else {
                    c += iArr[0] + iArr[1];
                    System.arraycopy(iArr, 2, iArr, 0, length - 2);
                    iArr[length - 2] = 0;
                    iArr[length - 1] = 0;
                    i3--;
                }
                iArr[i3] = 1;
                if (i2 == 0) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
            }
            i++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int a(int[] iArr) {
        int i;
        int i2;
        int length = iArr.length;
        int i3 = 0;
        while (true) {
            int i4 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            for (int i5 : iArr) {
                if (i5 < i4 && i5 > r0) {
                    i4 = i5;
                }
            }
            i3 = 0;
            int i52 = 0;
            i = 0;
            for (i2 = 0; i2 < length; i2++) {
                int i6 = iArr[i2];
                if (i6 > i4) {
                    i3 |= 1 << ((length - 1) - i2);
                    i++;
                    i52 += i6;
                }
            }
            if (i == 3) {
                break;
            } else if (i <= 3) {
                return -1;
            } else {
                i3 = i4;
            }
        }
        int i7 = i;
        for (i = 0; i < length && i7 > 0; i++) {
            i2 = iArr[i];
            if (i2 > i4) {
                i7--;
                if ((i2 << 1) >= i52) {
                    return -1;
                }
            }
        }
        return i3;
    }

    private static char a(int i) throws NotFoundException {
        for (int i2 = 0; i2 < a.length; i2++) {
            if (a[i2] == i) {
                return b[i2];
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static String a(CharSequence charSequence) throws FormatException {
        int length = charSequence.length();
        StringBuilder stringBuilder = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            int i2;
            char charAt = charSequence.charAt(i);
            if (charAt == '+' || charAt == '$' || charAt == '%' || charAt == '/') {
                char charAt2 = charSequence.charAt(i + 1);
                switch (charAt) {
                    case '$':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 - 64);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                        break;
                    case '%':
                        if (charAt2 < 'A' || charAt2 > 'E') {
                            if (charAt2 >= 'F' && charAt2 <= 'W') {
                                charAt = (char) (charAt2 - 11);
                                break;
                            }
                            throw FormatException.getFormatInstance();
                        }
                        charAt = (char) (charAt2 - 38);
                        break;
                        break;
                    case '+':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 + 32);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                        break;
                    case '/':
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            charAt = (char) (charAt2 - 32);
                            break;
                        } else if (charAt2 == 'Z') {
                            charAt = ':';
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    default:
                        charAt = '\u0000';
                        break;
                }
                stringBuilder.append(charAt);
                i2 = i + 1;
            } else {
                stringBuilder.append(charAt);
                i2 = i;
            }
            i = i2 + 1;
        }
        return stringBuilder.toString();
    }
}
