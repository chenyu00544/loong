package com.google.zxing.c;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.g;
import com.google.zxing.h;
import java.util.Arrays;
import java.util.Map;

/* compiled from: CodaBarReader */
public final class a extends k {
    static final char[] a = "0123456789-$:/.+ABCD".toCharArray();
    static final int[] b = new int[]{3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final char[] c = new char[]{'A', 'B', 'C', 'D'};
    private final StringBuilder d = new StringBuilder(20);
    private int[] e = new int[80];
    private int f = 0;

    public g a(int i, com.google.zxing.common.a aVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        Arrays.fill(this.e, 0);
        a(aVar);
        int b = b();
        this.d.setLength(0);
        int i2 = b;
        do {
            int c = c(i2);
            if (c != -1) {
                this.d.append((char) c);
                i2 += 8;
                if (this.d.length() > 1 && a(c, a[c])) {
                    break;
                }
            } else {
                throw NotFoundException.getNotFoundInstance();
            }
        } while (i2 < this.f);
        int i3 = this.e[i2 - 1];
        int i4 = 0;
        for (c = -8; c < -1; c++) {
            i4 += this.e[i2 + c];
        }
        if (i2 >= this.f || i3 >= i4 / 2) {
            a(b);
            for (c = 0; c < this.d.length(); c++) {
                this.d.setCharAt(c, a[this.d.charAt(c)]);
            }
            if (a(c, this.d.charAt(0))) {
                if (!a(c, this.d.charAt(this.d.length() - 1))) {
                    throw NotFoundException.getNotFoundInstance();
                } else if (this.d.length() <= 3) {
                    throw NotFoundException.getNotFoundInstance();
                } else {
                    this.d.deleteCharAt(this.d.length() - 1);
                    this.d.deleteCharAt(0);
                    i4 = 0;
                    c = 0;
                    while (i4 < b) {
                        i3 = this.e[i4] + c;
                        i4++;
                        c = i3;
                    }
                    float f = (float) c;
                    while (b < i2 - 1) {
                        c += this.e[b];
                        b++;
                    }
                    float f2 = (float) c;
                    return new g(this.d.toString(), null, new h[]{new h(f, (float) i), new h(f2, (float) i)}, BarcodeFormat.CODABAR);
                }
            }
            throw NotFoundException.getNotFoundInstance();
        }
        throw NotFoundException.getNotFoundInstance();
    }

    void a(int i) throws NotFoundException {
        int i2 = 0;
        int[] iArr = new int[]{0, 0, 0, 0};
        int[] iArr2 = new int[]{0, 0, 0, 0};
        int length = this.d.length() - 1;
        int i3 = 0;
        int i4 = i;
        while (true) {
            int i5 = b[this.d.charAt(i3)];
            for (int i6 = 6; i6 >= 0; i6--) {
                int i7 = (i6 & 1) + ((i5 & 1) * 2);
                iArr[i7] = iArr[i7] + this.e[i4 + i6];
                iArr2[i7] = iArr2[i7] + 1;
                i5 >>= 1;
            }
            if (i3 >= length) {
                break;
            }
            i4 += 8;
            i3++;
        }
        int[] iArr3 = new int[4];
        int[] iArr4 = new int[4];
        for (i3 = 0; i3 < 2; i3++) {
            iArr4[i3] = 0;
            iArr4[i3 + 2] = (((iArr[i3] << 8) / iArr2[i3]) + ((iArr[i3 + 2] << 8) / iArr2[i3 + 2])) >> 1;
            iArr3[i3] = iArr4[i3 + 2];
            iArr3[i3 + 2] = ((iArr[i3 + 2] * 512) + 384) / iArr2[i3 + 2];
        }
        loop3:
        while (true) {
            i4 = b[this.d.charAt(i2)];
            i3 = 6;
            while (i3 >= 0) {
                int i8 = (i3 & 1) + ((i4 & 1) * 2);
                int i9 = this.e[i + i3] << 8;
                if (i9 >= iArr4[i8] && i9 <= iArr3[i8]) {
                    i4 >>= 1;
                    i3--;
                }
            }
            if (i2 < length) {
                i += 8;
                i2++;
            } else {
                return;
            }
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void a(com.google.zxing.common.a aVar) throws NotFoundException {
        this.f = 0;
        int d = aVar.d(0);
        int a = aVar.a();
        if (d >= a) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i = 1;
        d = 0;
        for (int i2 = d; i2 < a; i2++) {
            if ((aVar.a(i2) ^ i) != 0) {
                d++;
            } else {
                b(d);
                i = i == 0 ? 1 : 0;
                d = 1;
            }
        }
        b(d);
    }

    private void b(int i) {
        this.e[this.f] = i;
        this.f++;
        if (this.f >= this.e.length) {
            Object obj = new int[(this.f * 2)];
            System.arraycopy(this.e, 0, obj, 0, this.f);
            this.e = obj;
        }
    }

    private int b() throws NotFoundException {
        int i = 1;
        while (i < this.f) {
            int c = c(i);
            if (c != -1 && a(c, a[c])) {
                int i2 = 0;
                for (c = i; c < i + 7; c++) {
                    i2 += this.e[c];
                }
                if (i == 1 || this.e[i - 1] >= i2 / 2) {
                    return i;
                }
            }
            i += 2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    static boolean a(char[] cArr, char c) {
        if (cArr == null) {
            return false;
        }
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    private int c(int i) {
        int i2 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i3 = i + 7;
        if (i3 >= this.f) {
            return -1;
        }
        int[] iArr = this.e;
        int i4 = i;
        int i5 = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i6 = 0;
        while (i4 < i3) {
            int i7 = iArr[i4];
            if (i7 < i5) {
                i5 = i7;
            }
            if (i7 <= i6) {
                i7 = i6;
            }
            i4 += 2;
            i6 = i7;
        }
        i5 = (i5 + i6) / 2;
        i4 = i + 1;
        i6 = 0;
        while (i4 < i3) {
            i7 = iArr[i4];
            if (i7 < i2) {
                i2 = i7;
            }
            if (i7 <= i6) {
                i7 = i6;
            }
            i4 += 2;
            i6 = i7;
        }
        i6 = (i2 + i6) / 2;
        i4 = 0;
        i2 = 0;
        i3 = 128;
        while (i4 < 7) {
            if ((i4 & 1) == 0) {
                i7 = i5;
            } else {
                i7 = i6;
            }
            i3 >>= 1;
            if (iArr[i + i4] > i7) {
                i7 = i2 | i3;
            } else {
                i7 = i2;
            }
            i4++;
            i2 = i7;
        }
        for (i7 = 0; i7 < b.length; i7++) {
            if (b[i7] == i2) {
                return i7;
            }
        }
        return -1;
    }
}
