package com.google.zxing.datamatrix.a;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.google.zxing.FormatException;
import com.google.zxing.common.d;
import com.umeng.socialize.common.SocializeConstants;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/* compiled from: DecodedBitStreamParser */
final class c {
    private static final char[] a = new char[]{'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] b = new char[]{'!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_'};
    private static final char[] c = new char[]{'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] d = new char[]{'\'', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', ''};

    /* compiled from: DecodedBitStreamParser */
    private enum a {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    static d a(byte[] bArr) throws FormatException {
        String stringBuilder;
        List list;
        com.google.zxing.common.c cVar = new com.google.zxing.common.c(bArr);
        StringBuilder stringBuilder2 = new StringBuilder(100);
        StringBuilder stringBuilder3 = new StringBuilder(0);
        Collection arrayList = new ArrayList(1);
        a aVar = a.ASCII_ENCODE;
        do {
            if (aVar == a.ASCII_ENCODE) {
                aVar = a(cVar, stringBuilder2, stringBuilder3);
            } else {
                switch (aVar) {
                    case C40_ENCODE:
                        a(cVar, stringBuilder2);
                        break;
                    case TEXT_ENCODE:
                        b(cVar, stringBuilder2);
                        break;
                    case ANSIX12_ENCODE:
                        c(cVar, stringBuilder2);
                        break;
                    case EDIFACT_ENCODE:
                        d(cVar, stringBuilder2);
                        break;
                    case BASE256_ENCODE:
                        a(cVar, stringBuilder2, arrayList);
                        break;
                    default:
                        throw FormatException.getFormatInstance();
                }
                aVar = a.ASCII_ENCODE;
            }
            if (aVar != a.PAD_ENCODE) {
            }
            if (stringBuilder3.length() > 0) {
                stringBuilder2.append(stringBuilder3.toString());
            }
            stringBuilder = stringBuilder2.toString();
            if (arrayList.isEmpty()) {
                Collection collection = arrayList;
            } else {
                list = null;
            }
            return new d(bArr, stringBuilder, list, null);
        } while (cVar.c() > 0);
        if (stringBuilder3.length() > 0) {
            stringBuilder2.append(stringBuilder3.toString());
        }
        stringBuilder = stringBuilder2.toString();
        if (arrayList.isEmpty()) {
            Collection collection2 = arrayList;
        } else {
            list = null;
        }
        return new d(bArr, stringBuilder, list, null);
    }

    private static a a(com.google.zxing.common.c cVar, StringBuilder stringBuilder, StringBuilder stringBuilder2) throws FormatException {
        int i = 0;
        do {
            int a = cVar.a(8);
            if (a == 0) {
                throw FormatException.getFormatInstance();
            } else if (a <= 128) {
                if (i != 0) {
                    i = a + 128;
                } else {
                    i = a;
                }
                stringBuilder.append((char) (i - 1));
                return a.ASCII_ENCODE;
            } else if (a == 129) {
                return a.PAD_ENCODE;
            } else {
                if (a <= 229) {
                    a -= 130;
                    if (a < 10) {
                        stringBuilder.append('0');
                    }
                    stringBuilder.append(a);
                } else if (a == 230) {
                    return a.C40_ENCODE;
                } else {
                    if (a == 231) {
                        return a.BASE256_ENCODE;
                    }
                    if (a == 232) {
                        stringBuilder.append('\u001d');
                    } else if (!(a == 233 || a == 234)) {
                        if (a == 235) {
                            i = 1;
                        } else if (a == 236) {
                            stringBuilder.append("[)>\u001e05\u001d");
                            stringBuilder2.insert(0, "\u001e\u0004");
                        } else if (a == 237) {
                            stringBuilder.append("[)>\u001e06\u001d");
                            stringBuilder2.insert(0, "\u001e\u0004");
                        } else if (a == 238) {
                            return a.ANSIX12_ENCODE;
                        } else {
                            if (a == 239) {
                                return a.TEXT_ENCODE;
                            }
                            if (a == SocializeConstants.MASK_USER_CENTER_HIDE_AREA) {
                                return a.EDIFACT_ENCODE;
                            }
                            if (!(a == 241 || a < 242 || (a == 254 && cVar.c() == 0))) {
                                throw FormatException.getFormatInstance();
                            }
                        }
                    }
                }
            }
        } while (cVar.c() > 0);
        return a.ASCII_ENCODE;
    }

    private static void a(com.google.zxing.common.c cVar, StringBuilder stringBuilder) throws FormatException {
        int[] iArr = new int[3];
        int i = 0;
        Object obj = null;
        while (cVar.c() != 8) {
            int a = cVar.a(8);
            if (a != 254) {
                a(a, cVar.a(8), iArr);
                for (a = 0; a < 3; a++) {
                    int i2 = iArr[a];
                    switch (i) {
                        case 0:
                            if (i2 < 3) {
                                i = i2 + 1;
                                break;
                            } else if (i2 < a.length) {
                                char c = a[i2];
                                if (obj == null) {
                                    stringBuilder.append(c);
                                    break;
                                }
                                stringBuilder.append((char) (c + 128));
                                obj = null;
                                break;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                        case 1:
                            if (obj != null) {
                                stringBuilder.append((char) (i2 + 128));
                                obj = null;
                            } else {
                                stringBuilder.append((char) i2);
                            }
                            i = 0;
                            break;
                        case 2:
                            if (i2 < b.length) {
                                char c2 = b[i2];
                                if (obj != null) {
                                    stringBuilder.append((char) (c2 + 128));
                                    obj = null;
                                } else {
                                    stringBuilder.append(c2);
                                }
                            } else if (i2 == 27) {
                                stringBuilder.append('\u001d');
                            } else if (i2 == 30) {
                                obj = 1;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            i = 0;
                            break;
                        case 3:
                            if (obj != null) {
                                stringBuilder.append((char) (i2 + 224));
                                obj = null;
                            } else {
                                stringBuilder.append((char) (i2 + 96));
                            }
                            i = 0;
                            break;
                        default:
                            throw FormatException.getFormatInstance();
                    }
                }
                if (cVar.c() <= 0) {
                    return;
                }
            }
            return;
        }
    }

    private static void b(com.google.zxing.common.c cVar, StringBuilder stringBuilder) throws FormatException {
        int[] iArr = new int[3];
        int i = 0;
        Object obj = null;
        while (cVar.c() != 8) {
            int a = cVar.a(8);
            if (a != 254) {
                a(a, cVar.a(8), iArr);
                for (a = 0; a < 3; a++) {
                    int i2 = iArr[a];
                    char c;
                    switch (i) {
                        case 0:
                            if (i2 < 3) {
                                i = i2 + 1;
                                break;
                            } else if (i2 < c.length) {
                                char c2 = c[i2];
                                if (obj == null) {
                                    stringBuilder.append(c2);
                                    break;
                                }
                                stringBuilder.append((char) (c2 + 128));
                                obj = null;
                                break;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                        case 1:
                            if (obj != null) {
                                stringBuilder.append((char) (i2 + 128));
                                obj = null;
                            } else {
                                stringBuilder.append((char) i2);
                            }
                            i = 0;
                            break;
                        case 2:
                            if (i2 < b.length) {
                                c = b[i2];
                                if (obj != null) {
                                    stringBuilder.append((char) (c + 128));
                                    obj = null;
                                } else {
                                    stringBuilder.append(c);
                                }
                            } else if (i2 == 27) {
                                stringBuilder.append('\u001d');
                            } else if (i2 == 30) {
                                obj = 1;
                            } else {
                                throw FormatException.getFormatInstance();
                            }
                            i = 0;
                            break;
                        case 3:
                            if (i2 < d.length) {
                                c = d[i2];
                                if (obj != null) {
                                    stringBuilder.append((char) (c + 128));
                                    obj = null;
                                } else {
                                    stringBuilder.append(c);
                                }
                                i = 0;
                                break;
                            }
                            throw FormatException.getFormatInstance();
                        default:
                            throw FormatException.getFormatInstance();
                    }
                }
                if (cVar.c() <= 0) {
                    return;
                }
            }
            return;
        }
    }

    private static void c(com.google.zxing.common.c cVar, StringBuilder stringBuilder) throws FormatException {
        int[] iArr = new int[3];
        while (cVar.c() != 8) {
            int a = cVar.a(8);
            if (a != 254) {
                a(a, cVar.a(8), iArr);
                for (a = 0; a < 3; a++) {
                    int i = iArr[a];
                    if (i == 0) {
                        stringBuilder.append('\r');
                    } else if (i == 1) {
                        stringBuilder.append('*');
                    } else if (i == 2) {
                        stringBuilder.append('>');
                    } else if (i == 3) {
                        stringBuilder.append(' ');
                    } else if (i < 14) {
                        stringBuilder.append((char) (i + 44));
                    } else if (i < 40) {
                        stringBuilder.append((char) (i + 51));
                    } else {
                        throw FormatException.getFormatInstance();
                    }
                }
                if (cVar.c() <= 0) {
                    return;
                }
            }
            return;
        }
    }

    private static void a(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        i3 -= i4 * 1600;
        i4 = i3 / 40;
        iArr[1] = i4;
        iArr[2] = i3 - (i4 * 40);
    }

    private static void d(com.google.zxing.common.c cVar, StringBuilder stringBuilder) {
        while (cVar.c() > 16) {
            for (int i = 0; i < 4; i++) {
                int a = cVar.a(6);
                if (a == 31) {
                    a = 8 - cVar.a();
                    if (a != 8) {
                        cVar.a(a);
                        return;
                    }
                    return;
                }
                if ((a & 32) == 0) {
                    a |= 64;
                }
                stringBuilder.append((char) a);
            }
            if (cVar.c() <= 0) {
                return;
            }
        }
    }

    private static void a(com.google.zxing.common.c cVar, StringBuilder stringBuilder, Collection<byte[]> collection) throws FormatException {
        int c;
        int b = cVar.b() + 1;
        int i = b + 1;
        b = a(cVar.a(8), b);
        if (b == 0) {
            c = cVar.c() / 8;
        } else if (b < Callback.DEFAULT_SWIPE_ANIMATION_DURATION) {
            c = b;
        } else {
            c = ((b - 249) * Callback.DEFAULT_SWIPE_ANIMATION_DURATION) + a(cVar.a(8), i);
            i++;
        }
        if (c < 0) {
            throw FormatException.getFormatInstance();
        }
        Object obj = new byte[c];
        b = 0;
        while (b < c) {
            if (cVar.c() < 8) {
                throw FormatException.getFormatInstance();
            }
            int i2 = i + 1;
            obj[b] = (byte) a(cVar.a(8), i);
            b++;
            i = i2;
        }
        collection.add(obj);
        try {
            stringBuilder.append(new String(obj, "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Platform does not support required encoding: " + e);
        }
    }

    private static int a(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + 256;
    }
}
