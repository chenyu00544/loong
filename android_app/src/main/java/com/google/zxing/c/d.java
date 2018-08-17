package com.google.zxing.c;

import android.support.v4.media.TransportMediator;
import com.baidu.mapapi.UIMsg.l_ErrorNo;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.g;
import com.google.zxing.h;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.flowcontrol.FlowControl;
import com.umeng.analytics.a;
import java.util.Arrays;
import java.util.Map;

/* compiled from: Code93Reader */
public final class d extends k {
    private static final char[] a = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".toCharArray();
    private static final int[] b = new int[]{276, 328, 324, 322, 296, 292, 290, 336, 274, 266, 424, FlowControl.STATUS_FLOW_CTRL_ALL, 418, l_ErrorNo.NETWORK_ERROR_404, 402, 394, a.p, 356, 354, 308, 282, 344, 332, 326, ErrorCode.APP_NOT_BIND, 278, 436, 434, 428, FlowControl.STATUS_FLOW_CTRL_BRUSH, 406, 410, 364, 358, 310, 314, 302, 468, 466, 458, 366, 374, 430, 294, 474, 470, 306, 350};
    private static final int c = b[47];
    private final StringBuilder d = new StringBuilder(20);
    private final int[] e = new int[6];

    public g a(int i, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        int[] a = a(aVar);
        int c = aVar.c(a[1]);
        int a2 = aVar.a();
        int[] iArr = this.e;
        Arrays.fill(iArr, 0);
        CharSequence charSequence = this.d;
        charSequence.setLength(0);
        while (true) {
            k.a(aVar, c, iArr);
            int a3 = a(iArr);
            if (a3 < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            char a4 = a(a3);
            charSequence.append(a4);
            int i2 = c;
            for (int i3 : iArr) {
                i2 += i3;
            }
            a3 = aVar.c(i2);
            if (a4 == '*') {
                break;
            }
            c = a3;
        }
        charSequence.deleteCharAt(charSequence.length() - 1);
        if (a3 == a2 || !aVar.a(a3)) {
            throw NotFoundException.getNotFoundInstance();
        } else if (charSequence.length() < 2) {
            throw NotFoundException.getNotFoundInstance();
        } else {
            b(charSequence);
            charSequence.setLength(charSequence.length() - 2);
            String a5 = a(charSequence);
            a2 = a[1];
            float f = ((float) (c + a3)) / 2.0f;
            return new g(a5, null, new h[]{new h(((float) (a[0] + a2)) / 2.0f, (float) i), new h(f, (float) i)}, BarcodeFormat.CODE_93);
        }
    }

    private int[] a(com.google.zxing.common.a aVar) throws NotFoundException {
        int a = aVar.a();
        int c = aVar.c(0);
        Arrays.fill(this.e, 0);
        int[] iArr = this.e;
        int length = iArr.length;
        int i = 0;
        int i2 = c;
        c = 0;
        for (int i3 = c; i3 < a; i3++) {
            if ((aVar.a(i3) ^ i) != 0) {
                iArr[c] = iArr[c] + 1;
            } else {
                if (c != length - 1) {
                    c++;
                } else if (a(iArr) == c) {
                    return new int[]{i2, i3};
                } else {
                    i2 += iArr[0] + iArr[1];
                    System.arraycopy(iArr, 2, iArr, 0, length - 2);
                    iArr[length - 2] = 0;
                    iArr[length - 1] = 0;
                    c--;
                }
                iArr[c] = 1;
                if (i == 0) {
                    i = 1;
                } else {
                    i = 0;
                }
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int a(int[] iArr) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        while (i < iArr.length) {
            i++;
            i2 = iArr[i] + i2;
        }
        i = 0;
        for (int i3 = 0; i3 < length; i3++) {
            int i4;
            int i5 = ((iArr[i3] << 8) * 9) / i2;
            int i6 = i5 >> 8;
            if ((i5 & 255) > TransportMediator.KEYCODE_MEDIA_PAUSE) {
                i4 = i6 + 1;
            } else {
                i4 = i6;
            }
            if (i4 < 1 || i4 > 4) {
                return -1;
            }
            if ((i3 & 1) == 0) {
                i6 = 0;
                while (i6 < i4) {
                    i6++;
                    i = (i << 1) | 1;
                }
            } else {
                i <<= i4;
            }
        }
        return i;
    }

    private static char a(int i) throws NotFoundException {
        for (int i2 = 0; i2 < b.length; i2++) {
            if (b[i2] == i) {
                return a[i2];
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
            if (charAt < 'a' || charAt > 'd') {
                stringBuilder.append(charAt);
                i2 = i;
            } else if (i >= length - 1) {
                throw FormatException.getFormatInstance();
            } else {
                char charAt2 = charSequence.charAt(i + 1);
                switch (charAt) {
                    case 'a':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 - 64);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                        break;
                    case 'b':
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
                    case 'c':
                        if (charAt2 >= 'A' && charAt2 <= 'O') {
                            charAt = (char) (charAt2 - 32);
                            break;
                        } else if (charAt2 == 'Z') {
                            charAt = ':';
                            break;
                        } else {
                            throw FormatException.getFormatInstance();
                        }
                    case 'd':
                        if (charAt2 >= 'A' && charAt2 <= 'Z') {
                            charAt = (char) (charAt2 + 32);
                            break;
                        }
                        throw FormatException.getFormatInstance();
                        break;
                    default:
                        charAt = '\u0000';
                        break;
                }
                stringBuilder.append(charAt);
                i2 = i + 1;
            }
            i = i2 + 1;
        }
        return stringBuilder.toString();
    }

    private static void b(CharSequence charSequence) throws ChecksumException {
        int length = charSequence.length();
        a(charSequence, length - 2, 20);
        a(charSequence, length - 1, 15);
    }

    private static void a(CharSequence charSequence, int i, int i2) throws ChecksumException {
        int i3 = 1;
        int i4 = i - 1;
        int i5 = 0;
        while (i4 >= 0) {
            int indexOf = ("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(charSequence.charAt(i4)) * i3) + i5;
            i5 = i3 + 1;
            if (i5 > i2) {
                i5 = 1;
            }
            i4--;
            i3 = i5;
            i5 = indexOf;
        }
        if (charSequence.charAt(i) != a[i5 % 47]) {
            throw ChecksumException.getChecksumInstance();
        }
    }
}
