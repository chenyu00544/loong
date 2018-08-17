package com.google.zxing.a.a;

import anet.channel.strategy.dispatch.c;
import anet.channel.util.HttpConstant;
import com.baidu.location.b.g;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.google.zxing.FormatException;
import com.google.zxing.common.b;
import com.google.zxing.common.d;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.sina.weibo.sdk.constant.WBConstants;
import com.tencent.connect.common.Constants;
import com.umeng.socialize.common.SocializeConstants;

/* compiled from: Decoder */
public final class a {
    private static final int[] a = new int[]{0, 104, SocializeConstants.MASK_USER_CENTER_HIDE_AREA, 408, 608};
    private static final int[] b = new int[]{0, 128, 288, 480, 704, 960, 1248, 1568, WBConstants.SDK_NEW_PAY_VERSION, 2304, 2720, 3168, 3648, 4160, 4704, 5280, 5888, 6528, 7200, 7904, 8640, 9408, 10208, 11040, 11904, 12800, 13728, 14688, 15680, 16704, 17760, 18848, 19968};
    private static final int[] c = new int[]{0, 17, 40, 51, 76};
    private static final int[] d = new int[]{0, 21, 48, 60, 88, g.L, 156, 196, SocializeConstants.MASK_USER_CENTER_HIDE_AREA, 230, 272, 316, 364, HttpConstant.SC_REQUESTED_RANGE_NOT_SATISFIABLE, 470, 528, 588, 652, 720, 790, 864, 940, m_AppUI.MSG_GET_GL_OK, 920, 992, 1066, 1144, 1224, 1306, 1392, 1480, 1570, 1664};
    private static final String[] e = new String[]{"CTRL_PS", " ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "CTRL_LL", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] f = new String[]{"CTRL_PS", " ", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", WidgetRequestParam.REQ_PARAM_COMMENT_TOPIC, "r", "s", c.TIMESTAMP, "u", c.VERSION, "w", "x", "y", "z", "CTRL_US", "CTRL_ML", "CTRL_DL", "CTRL_BS"};
    private static final String[] g = new String[]{"CTRL_PS", " ", "\u0001", "\u0002", "\u0003", "\u0004", "\u0005", "\u0006", "\u0007", "\b", "\t", "\n", "\u000b", "\f", "\r", "\u001b", "\u001c", "\u001d", "\u001e", "\u001f", "@", "\\", "^", "_", "`", "|", "~", "", "CTRL_LL", "CTRL_UL", "CTRL_PL", "CTRL_BS"};
    private static final String[] h = new String[]{"", "\r", "\r\n", ". ", ", ", ": ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", SocializeConstants.OP_DIVIDER_PLUS, ",", SocializeConstants.OP_DIVIDER_MINUS, ".", "/", ":", ";", "<", "=", ">", "?", "[", "]", "{", "}", "CTRL_UL"};
    private static final String[] i = new String[]{"CTRL_PS", " ", "0", "1", "2", "3", "4", "5", Constants.VIA_SHARE_TYPE_INFO, "7", "8", "9", ",", ".", "CTRL_UL", "CTRL_US"};
    private int j;
    private int k;
    private com.google.zxing.a.a l;
    private int m;

    /* compiled from: Decoder */
    private enum a {
        UPPER,
        LOWER,
        MIXED,
        DIGIT,
        PUNCT,
        BINARY
    }

    public d a(com.google.zxing.a.a aVar) throws FormatException {
        this.l = aVar;
        b d = aVar.d();
        if (!this.l.c()) {
            d = b(this.l.d());
        }
        return new d(null, a(b(a(d))), null, null);
    }

    private String a(boolean[] zArr) throws FormatException {
        int b = (this.k * this.l.b()) - this.m;
        if (b > zArr.length) {
            throw FormatException.getFormatInstance();
        }
        a aVar = a.UPPER;
        a aVar2 = a.UPPER;
        StringBuilder stringBuilder = new StringBuilder(20);
        Object obj = null;
        Object obj2 = null;
        Object obj3 = null;
        Object obj4 = null;
        int i = 0;
        a aVar3 = aVar;
        while (obj == null) {
            Object obj5;
            int i2;
            if (obj2 != null) {
                obj5 = 1;
                aVar = aVar3;
            } else {
                obj5 = obj3;
                aVar = aVar2;
            }
            int i3;
            int i4;
            if (obj4 == null) {
                if (aVar2 == a.BINARY) {
                    if (b - i < 8) {
                        break;
                    }
                    i3 = i + 8;
                    stringBuilder.append((char) a(zArr, i, 8));
                    aVar3 = aVar2;
                    int i5 = i3;
                    obj3 = obj2;
                    i2 = i5;
                } else {
                    i3 = 5;
                    if (aVar2 == a.DIGIT) {
                        i3 = 4;
                    }
                    if (b - i < i3) {
                        break;
                    }
                    i4 = i + i3;
                    String a = a(aVar2, a(zArr, i, i3));
                    if (a.startsWith("CTRL_")) {
                        aVar2 = a(a.charAt(5));
                        if (a.charAt(6) == 'S') {
                            obj3 = 1;
                            if (a.charAt(5) == 'B') {
                                obj4 = 1;
                                i2 = i4;
                                aVar3 = aVar2;
                            } else {
                                i2 = i4;
                                aVar3 = aVar2;
                            }
                        }
                    } else {
                        stringBuilder.append(a);
                    }
                    obj3 = obj2;
                    i2 = i4;
                    aVar3 = aVar2;
                }
            } else if (b - i < 5) {
                break;
            } else {
                int a2 = a(zArr, i, 5);
                i3 = i + 5;
                if (a2 == 0) {
                    if (b - i3 < 11) {
                        break;
                    }
                    a2 = a(zArr, i3, 11) + 31;
                    i3 += 11;
                }
                i4 = i3;
                for (i3 = 0; i3 < a2; i3++) {
                    if (b - i4 < 8) {
                        obj4 = 1;
                        break;
                    }
                    stringBuilder.append((char) a(zArr, i4, 8));
                    i4 += 8;
                }
                obj4 = obj;
                obj = obj4;
                obj4 = null;
                obj3 = obj2;
                i2 = i4;
                aVar3 = aVar2;
            }
            if (obj5 != null) {
                obj3 = null;
                i = i2;
                aVar2 = aVar;
                obj2 = null;
                aVar3 = aVar;
            } else {
                i = i2;
                aVar2 = aVar3;
                obj2 = obj3;
                aVar3 = aVar;
                obj3 = obj5;
            }
        }
        return stringBuilder.toString();
    }

    private static a a(char c) {
        switch (c) {
            case 'B':
                return a.BINARY;
            case 'D':
                return a.DIGIT;
            case 'L':
                return a.LOWER;
            case 'M':
                return a.MIXED;
            case 'P':
                return a.PUNCT;
            default:
                return a.UPPER;
        }
    }

    private static String a(a aVar, int i) {
        switch (aVar) {
            case UPPER:
                return e[i];
            case LOWER:
                return f[i];
            case MIXED:
                return g[i];
            case PUNCT:
                return h[i];
            case DIGIT:
                return i[i];
            default:
                return "";
        }
    }

    private boolean[] b(boolean[] zArr) throws FormatException {
        com.google.zxing.common.reedsolomon.a aVar;
        int i;
        int i2;
        int i3;
        if (this.l.a() <= 2) {
            this.k = 6;
            aVar = com.google.zxing.common.reedsolomon.a.c;
        } else if (this.l.a() <= 8) {
            this.k = 8;
            aVar = com.google.zxing.common.reedsolomon.a.g;
        } else if (this.l.a() <= 22) {
            this.k = 10;
            aVar = com.google.zxing.common.reedsolomon.a.b;
        } else {
            this.k = 12;
            aVar = com.google.zxing.common.reedsolomon.a.a;
        }
        int b = this.l.b();
        if (this.l.c()) {
            i = a[this.l.a()] - (this.j * this.k);
            i2 = c[this.l.a()] - b;
        } else {
            i = b[this.l.a()] - (this.j * this.k);
            i2 = d[this.l.a()] - b;
        }
        int[] iArr = new int[this.j];
        for (i3 = 0; i3 < this.j; i3++) {
            int i4;
            int i5 = 1;
            for (i4 = 1; i4 <= this.k; i4++) {
                if (zArr[(((this.k * i3) + this.k) - i4) + i]) {
                    iArr[i3] = iArr[i3] + i5;
                }
                i5 <<= 1;
            }
        }
        try {
            new com.google.zxing.common.reedsolomon.c(aVar).a(iArr, i2);
            this.m = 0;
            boolean[] zArr2 = new boolean[(this.k * b)];
            int i6 = 0;
            i = 0;
            while (i6 < b) {
                i2 = 0;
                i3 = 0;
                i4 = i;
                i = 1 << (this.k - 1);
                for (int i7 = 0; i7 < this.k; i7++) {
                    boolean z = (iArr[i6] & i) == i;
                    if (i2 != this.k - 1) {
                        if (i3 == z) {
                            i2++;
                        } else {
                            i2 = 1;
                            boolean z2 = z;
                        }
                        zArr2[((this.k * i6) + i7) - i4] = z;
                    } else if (z == i3) {
                        throw FormatException.getFormatInstance();
                    } else {
                        i4++;
                        this.m++;
                        i2 = 0;
                        i3 = 0;
                    }
                    i >>>= 1;
                }
                i6++;
                i = i4;
            }
            return zArr2;
        } catch (ReedSolomonException e) {
            throw FormatException.getFormatInstance();
        }
    }

    private boolean[] a(b bVar) throws FormatException {
        boolean[] zArr;
        if (this.l.c()) {
            if (this.l.a() > a.length) {
                throw FormatException.getFormatInstance();
            }
            zArr = new boolean[a[this.l.a()]];
            this.j = c[this.l.a()];
        } else if (this.l.a() > b.length) {
            throw FormatException.getFormatInstance();
        } else {
            zArr = new boolean[b[this.l.a()]];
            this.j = d[this.l.a()];
        }
        int a = this.l.a();
        int i = 0;
        int i2 = 0;
        int e = bVar.e();
        int i3 = a;
        while (i3 != 0) {
            int i4;
            a = 0;
            for (i4 = 0; i4 < (e * 2) - 4; i4++) {
                zArr[i2 + i4] = bVar.a(i + a, (i4 / 2) + i);
                zArr[(((e * 2) + i2) - 4) + i4] = bVar.a((i4 / 2) + i, ((i + e) - 1) - a);
                a = (a + 1) % 2;
            }
            a = 0;
            for (i4 = (e * 2) + 1; i4 > 5; i4--) {
                zArr[((((e * 4) + i2) - 8) + ((e * 2) - i4)) + 1] = bVar.a(((i + e) - 1) - a, ((i4 / 2) + i) - 1);
                zArr[((((e * 6) + i2) - 12) + ((e * 2) - i4)) + 1] = bVar.a(((i4 / 2) + i) - 1, i + a);
                a = (a + 1) % 2;
            }
            a = ((e * 8) - 16) + i2;
            e -= 4;
            i3--;
            i += 2;
            i2 = a;
        }
        return zArr;
    }

    private static b b(b bVar) {
        int d = ((((bVar.d() - 1) / 2) / 16) * 2) + 1;
        b bVar2 = new b(bVar.d() - d, bVar.e() - d);
        int i = 0;
        for (d = 0; d < bVar.d(); d++) {
            if (((bVar.d() / 2) - d) % 16 != 0) {
                int i2 = 0;
                for (int i3 = 0; i3 < bVar.e(); i3++) {
                    if (((bVar.d() / 2) - i3) % 16 != 0) {
                        if (bVar.a(d, i3)) {
                            bVar2.b(i, i2);
                        }
                        i2++;
                    }
                }
                i++;
            }
        }
        return bVar2;
    }

    private static int a(boolean[] zArr, int i, int i2) {
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            i3 <<= 1;
            if (zArr[i4]) {
                i3++;
            }
        }
        return i3;
    }
}
