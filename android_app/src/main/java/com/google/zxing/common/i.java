package com.google.zxing.common;

import com.google.zxing.NotFoundException;
import com.google.zxing.a;
import com.google.zxing.c;
import java.lang.reflect.Array;

/* compiled from: HybridBinarizer */
public final class i extends g {
    private b a;

    public i(c cVar) {
        super(cVar);
    }

    public b b() throws NotFoundException {
        if (this.a != null) {
            return this.a;
        }
        c a = a();
        int b = a.b();
        int c = a.c();
        if (b < 40 || c < 40) {
            this.a = super.b();
        } else {
            byte[] a2 = a.a();
            int i = b >> 3;
            if ((b & 7) != 0) {
                i++;
            }
            int i2 = c >> 3;
            if ((c & 7) != 0) {
                i2++;
            }
            int[][] a3 = a(a2, i, i2, b, c);
            b bVar = new b(b, c);
            a(a2, i, i2, b, c, a3, bVar);
            this.a = bVar;
        }
        return this.a;
    }

    public a a(c cVar) {
        return new i(cVar);
    }

    private static void a(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, b bVar) {
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i5 << 3;
            int i7 = i4 - 8;
            if (i6 <= i7) {
                i7 = i6;
            }
            for (int i8 = 0; i8 < i; i8++) {
                i6 = i8 << 3;
                int i9 = i3 - 8;
                if (i6 <= i9) {
                    i9 = i6;
                }
                int a = a(i8, 2, i - 3);
                int a2 = a(i5, 2, i2 - 3);
                int i10 = 0;
                for (i6 = -2; i6 <= 2; i6++) {
                    int[] iArr2 = iArr[a2 + i6];
                    i10 += iArr2[a + 2] + (((iArr2[a - 2] + iArr2[a - 1]) + iArr2[a]) + iArr2[a + 1]);
                }
                a(bArr, i9, i7, i10 / 25, i3, bVar);
            }
        }
    }

    private static int a(int i, int i2, int i3) {
        if (i < i2) {
            return i2;
        }
        return i > i3 ? i3 : i;
    }

    private static void a(byte[] bArr, int i, int i2, int i3, int i4, b bVar) {
        int i5 = (i2 * i4) + i;
        int i6 = 0;
        while (i6 < 8) {
            for (int i7 = 0; i7 < 8; i7++) {
                if ((bArr[i5 + i7] & 255) <= i3) {
                    bVar.b(i + i7, i2 + i6);
                }
            }
            i6++;
            i5 += i4;
        }
    }

    private static int[][] a(byte[] bArr, int i, int i2, int i3, int i4) {
        int[][] iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i2, i});
        for (int i5 = 0; i5 < i2; i5++) {
            int i6 = i5 << 3;
            int i7 = i4 - 8;
            if (i6 <= i7) {
                i7 = i6;
            }
            int i8 = 0;
            while (i8 < i) {
                int i9 = i8 << 3;
                i6 = i3 - 8;
                if (i9 <= i6) {
                    i6 = i9;
                }
                int i10 = 0;
                int i11 = 255;
                i9 = 0;
                int i12 = 0;
                int i13 = (i7 * i3) + i6;
                while (i12 < 8) {
                    int i14 = 0;
                    while (i14 < 8) {
                        i6 = bArr[i13 + i14] & 255;
                        int i15 = i10 + i6;
                        if (i6 < i11) {
                            i10 = i6;
                        } else {
                            i10 = i11;
                        }
                        if (i6 <= i9) {
                            i6 = i9;
                        }
                        i14++;
                        i11 = i10;
                        i9 = i6;
                        i10 = i15;
                    }
                    if (i9 - i11 > 24) {
                        i6 = i13 + i3;
                        i13 = i12 + 1;
                        i12 = i10;
                        while (i13 < 8) {
                            i10 = i12;
                            for (i12 = 0; i12 < 8; i12++) {
                                i10 += bArr[i6 + i12] & 255;
                            }
                            i13++;
                            i6 += i3;
                            i12 = i10;
                        }
                    } else {
                        i6 = i13;
                        i13 = i12;
                        i12 = i10;
                    }
                    i10 = i13 + 1;
                    i13 = i6 + i3;
                    int i16 = i10;
                    i10 = i12;
                    i12 = i16;
                }
                i6 = i10 >> 6;
                if (i9 - i11 <= 24) {
                    i9 = i11 >> 1;
                    if (i5 > 0 && i8 > 0) {
                        i6 = ((iArr[i5 - 1][i8] + (iArr[i5][i8 - 1] * 2)) + iArr[i5 - 1][i8 - 1]) >> 2;
                        if (i11 < i6) {
                        }
                    }
                    i6 = i9;
                }
                iArr[i5][i8] = i6;
                i8++;
            }
        }
        return iArr;
    }
}
