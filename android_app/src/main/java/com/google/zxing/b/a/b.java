package com.google.zxing.b.a;

import com.google.zxing.common.d;
import com.umeng.analytics.pro.dk;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/* compiled from: DecodedBitStreamParser */
final class b {
    private static final NumberFormat a = new DecimalFormat("000000000");
    private static final NumberFormat b = new DecimalFormat("000");
    private static final String[] c = new String[]{"\nABCDEFGHIJKLMNOPQRSTUVWXYZ￺\u001c\u001d\u001e￻ ￼\"#$%&'()*+,-./0123456789:￱￲￳￴￸", "`abcdefghijklmnopqrstuvwxyz￺\u001c\u001d\u001e￻{￼}~;<=>?[\\]^_ ,./:@!|￼￵￶￼￰￲￳￴￷", "ÀÁÂÃÄÅÆÇÈÉÊËÌÍÎÏÐÑÒÓÔÕÖ×ØÙÚ￺\u001c\u001d\u001eÛÜÝÞßª¬±²³µ¹º¼½¾￷ ￹￳￴￸", "àáâãäåæçèéêëìíîïðñòóôõö÷øùú￺\u001c\u001d\u001e￻ûüýþÿ¡¨«¯°´·¸»¿￷ ￲￹￴￸", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a￺￼￼\u001b￻\u001c\u001d\u001e\u001f ¢£¤¥¦§©­®¶￷ ￲￳￹￸", "\u0000\u0001\u0002\u0003\u0004\u0005\u0006\u0007\b\t\n\u000b\f\r\u000e\u000f\u0010\u0011\u0012\u0013\u0014\u0015\u0016\u0017\u0018\u0019\u001a\u001b\u001c\u001d\u001e\u001f !\"#$%&'()*+,-./0123456789:;<=>?"};

    static d a(byte[] bArr, int i) {
        StringBuilder stringBuilder = new StringBuilder(144);
        switch (i) {
            case 2:
            case 3:
                String format;
                if (i == 2) {
                    format = new DecimalFormat("0000000000".substring(0, c(bArr))).format((long) d(bArr));
                } else {
                    format = e(bArr);
                }
                String format2 = b.format((long) a(bArr));
                String format3 = b.format((long) b(bArr));
                stringBuilder.append(a(bArr, 10, 84));
                if (!stringBuilder.toString().startsWith("[)>\u001e01\u001d")) {
                    stringBuilder.insert(0, format + '\u001d' + format2 + '\u001d' + format3 + '\u001d');
                    break;
                }
                stringBuilder.insert(9, format + '\u001d' + format2 + '\u001d' + format3 + '\u001d');
                break;
            case 4:
                stringBuilder.append(a(bArr, 1, 93));
                break;
            case 5:
                stringBuilder.append(a(bArr, 1, 77));
                break;
        }
        return new d(bArr, stringBuilder.toString(), null, String.valueOf(i));
    }

    private static int a(int i, byte[] bArr) {
        int i2 = i - 1;
        if (((1 << (5 - (i2 % 6))) & bArr[i2 / 6]) == 0) {
            return 0;
        }
        return 1;
    }

    private static int a(byte[] bArr, byte[] bArr2) {
        int i = 0;
        int i2 = 0;
        while (i < bArr2.length) {
            i2 += a(bArr2[i], bArr) << ((bArr2.length - i) - 1);
            i++;
        }
        return i2;
    }

    private static int a(byte[] bArr) {
        return a(bArr, new byte[]{(byte) 53, (byte) 54, (byte) 43, (byte) 44, (byte) 45, (byte) 46, (byte) 47, (byte) 48, (byte) 37, (byte) 38});
    }

    private static int b(byte[] bArr) {
        return a(bArr, new byte[]{(byte) 55, (byte) 56, (byte) 57, (byte) 58, (byte) 59, (byte) 60, (byte) 49, (byte) 50, (byte) 51, (byte) 52});
    }

    private static int c(byte[] bArr) {
        return a(bArr, new byte[]{(byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 31, (byte) 32});
    }

    private static int d(byte[] bArr) {
        return a(bArr, new byte[]{(byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 25, (byte) 26, (byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 19, (byte) 20, (byte) 21, (byte) 22, (byte) 23, (byte) 24, dk.k, dk.l, dk.m, dk.n, (byte) 17, (byte) 18, (byte) 7, (byte) 8, (byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 1, (byte) 2});
    }

    private static String e(byte[] bArr) {
        return String.valueOf(new char[]{c[0].charAt(a(bArr, new byte[]{(byte) 39, (byte) 40, (byte) 41, (byte) 42, (byte) 31, (byte) 32})), c[0].charAt(a(bArr, new byte[]{(byte) 33, (byte) 34, (byte) 35, (byte) 36, (byte) 25, (byte) 26})), c[0].charAt(a(bArr, new byte[]{(byte) 27, (byte) 28, (byte) 29, (byte) 30, (byte) 19, (byte) 20})), c[0].charAt(a(bArr, new byte[]{(byte) 21, (byte) 22, (byte) 23, (byte) 24, dk.k, dk.l})), c[0].charAt(a(bArr, new byte[]{dk.m, dk.n, (byte) 17, (byte) 18, (byte) 7, (byte) 8})), c[0].charAt(a(bArr, new byte[]{(byte) 9, (byte) 10, (byte) 11, (byte) 12, (byte) 1, (byte) 2}))});
    }

    private static String a(byte[] bArr, int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        int i3 = i;
        int i4 = 0;
        int i5 = 0;
        int i6 = -1;
        while (i3 < i + i2) {
            int i7;
            char charAt = c[i5].charAt(bArr[i3]);
            switch (charAt) {
                case '￰':
                case '￱':
                case '￲':
                case '￳':
                case '￴':
                    i6 = 1;
                    i7 = i5;
                    i5 = i3;
                    i3 = charAt - 65520;
                    i4 = i7;
                    break;
                case '￵':
                    i6 = 2;
                    i4 = i5;
                    i5 = i3;
                    i3 = 0;
                    break;
                case '￶':
                    i6 = 3;
                    i4 = i5;
                    i5 = i3;
                    i3 = 0;
                    break;
                case '￷':
                    i5 = i3;
                    i6 = -1;
                    i3 = 0;
                    break;
                case '￸':
                    i5 = i3;
                    i6 = -1;
                    i3 = 1;
                    break;
                case '￹':
                    i6 = -1;
                    i7 = i5;
                    i5 = i3;
                    i3 = i7;
                    break;
                case '￻':
                    i3++;
                    i3++;
                    i3++;
                    i3++;
                    i3++;
                    stringBuilder.append(a.format((long) (((((bArr[i3] << 24) + (bArr[i3] << 18)) + (bArr[i3] << 12)) + (bArr[i3] << 6)) + bArr[i3])));
                    i7 = i3;
                    i3 = i5;
                    i5 = i7;
                    break;
                default:
                    stringBuilder.append(charAt);
                    i7 = i3;
                    i3 = i5;
                    i5 = i7;
                    break;
            }
            int i8 = i6 - 1;
            if (i6 == 0) {
                i3 = i4;
            }
            i6 = i8;
            i7 = i3;
            i3 = i5 + 1;
            i5 = i7;
        }
        while (stringBuilder.length() > 0 && stringBuilder.charAt(stringBuilder.length() - 1) == '￼') {
            stringBuilder.setLength(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
