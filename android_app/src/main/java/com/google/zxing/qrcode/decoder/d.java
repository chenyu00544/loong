package com.google.zxing.qrcode.decoder;

import android.support.v4.media.TransportMediator;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.c;
import com.google.zxing.common.k;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* compiled from: DecodedBitStreamParser */
final class d {
    private static final char[] a = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' ', '$', '%', '*', '+', '-', '.', '/', ':'};

    static com.google.zxing.common.d a(byte[] bArr, g gVar, ErrorCorrectionLevel errorCorrectionLevel, Map<DecodeHintType, ?> map) throws FormatException {
        String str;
        c cVar = new c(bArr);
        StringBuilder stringBuilder = new StringBuilder(50);
        List arrayList = new ArrayList(1);
        boolean z = false;
        CharacterSetECI characterSetECI = null;
        while (true) {
            Mode mode;
            boolean z2;
            if (cVar.c() < 4) {
                mode = Mode.TERMINATOR;
            } else {
                try {
                    mode = Mode.forBits(cVar.a(4));
                } catch (IllegalArgumentException e) {
                    throw FormatException.getFormatInstance();
                }
            }
            if (mode == Mode.TERMINATOR) {
                z2 = z;
            } else if (mode == Mode.FNC1_FIRST_POSITION || mode == Mode.FNC1_SECOND_POSITION) {
                z2 = true;
            } else if (mode == Mode.STRUCTURED_APPEND) {
                if (cVar.c() < 16) {
                    throw FormatException.getFormatInstance();
                }
                cVar.a(16);
                z2 = z;
            } else if (mode == Mode.ECI) {
                characterSetECI = CharacterSetECI.getCharacterSetECIByValue(a(cVar));
                if (characterSetECI == null) {
                    throw FormatException.getFormatInstance();
                }
                z2 = z;
            } else if (mode == Mode.HANZI) {
                r2 = cVar.a(4);
                int a = cVar.a(mode.getCharacterCountBits(gVar));
                if (r2 == 1) {
                    a(cVar, stringBuilder, a);
                }
                z2 = z;
            } else {
                r2 = cVar.a(mode.getCharacterCountBits(gVar));
                if (mode == Mode.NUMERIC) {
                    c(cVar, stringBuilder, r2);
                    z2 = z;
                } else if (mode == Mode.ALPHANUMERIC) {
                    a(cVar, stringBuilder, r2, z);
                    z2 = z;
                } else if (mode == Mode.BYTE) {
                    a(cVar, stringBuilder, r2, characterSetECI, arrayList, map);
                    z2 = z;
                } else if (mode == Mode.KANJI) {
                    b(cVar, stringBuilder, r2);
                    z2 = z;
                } else {
                    throw FormatException.getFormatInstance();
                }
            }
            if (mode == Mode.TERMINATOR) {
                break;
            }
            z = z2;
        }
        String stringBuilder2 = stringBuilder.toString();
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        if (errorCorrectionLevel == null) {
            str = null;
        } else {
            str = errorCorrectionLevel.toString();
        }
        return new com.google.zxing.common.d(bArr, stringBuilder2, arrayList, str);
    }

    private static void a(c cVar, StringBuilder stringBuilder, int i) throws FormatException {
        if (i * 13 > cVar.c()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[(i * 2)];
        int i2 = 0;
        while (i > 0) {
            int a = cVar.a(13);
            a = (a % 96) | ((a / 96) << 8);
            if (a < 959) {
                a += 41377;
            } else {
                a += 42657;
            }
            bArr[i2] = (byte) ((a >> 8) & 255);
            bArr[i2 + 1] = (byte) (a & 255);
            i--;
            i2 += 2;
        }
        try {
            stringBuilder.append(new String(bArr, "GB2312"));
        } catch (UnsupportedEncodingException e) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void b(c cVar, StringBuilder stringBuilder, int i) throws FormatException {
        if (i * 13 > cVar.c()) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[(i * 2)];
        int i2 = 0;
        while (i > 0) {
            int a = cVar.a(13);
            a = (a % 192) | ((a / 192) << 8);
            if (a < 7936) {
                a += 33088;
            } else {
                a += 49472;
            }
            bArr[i2] = (byte) (a >> 8);
            bArr[i2 + 1] = (byte) a;
            i--;
            i2 += 2;
        }
        try {
            stringBuilder.append(new String(bArr, "SJIS"));
        } catch (UnsupportedEncodingException e) {
            throw FormatException.getFormatInstance();
        }
    }

    private static void a(c cVar, StringBuilder stringBuilder, int i, CharacterSetECI characterSetECI, Collection<byte[]> collection, Map<DecodeHintType, ?> map) throws FormatException {
        if ((i << 3) > cVar.c()) {
            throw FormatException.getFormatInstance();
        }
        String a;
        Object obj = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            obj[i2] = (byte) cVar.a(8);
        }
        if (characterSetECI == null) {
            a = k.a(obj, map);
        } else {
            a = characterSetECI.name();
        }
        try {
            stringBuilder.append(new String(obj, a));
            collection.add(obj);
        } catch (UnsupportedEncodingException e) {
            throw FormatException.getFormatInstance();
        }
    }

    private static char a(int i) throws FormatException {
        if (i < a.length) {
            return a[i];
        }
        throw FormatException.getFormatInstance();
    }

    private static void a(c cVar, StringBuilder stringBuilder, int i, boolean z) throws FormatException {
        int length = stringBuilder.length();
        while (i > 1) {
            if (cVar.c() < 11) {
                throw FormatException.getFormatInstance();
            }
            int a = cVar.a(11);
            stringBuilder.append(a(a / 45));
            stringBuilder.append(a(a % 45));
            i -= 2;
        }
        if (i == 1) {
            if (cVar.c() < 6) {
                throw FormatException.getFormatInstance();
            }
            stringBuilder.append(a(cVar.a(6)));
        }
        if (z) {
            while (length < stringBuilder.length()) {
                if (stringBuilder.charAt(length) == '%') {
                    if (length >= stringBuilder.length() - 1 || stringBuilder.charAt(length + 1) != '%') {
                        stringBuilder.setCharAt(length, '\u001d');
                    } else {
                        stringBuilder.deleteCharAt(length + 1);
                    }
                }
                length++;
            }
        }
    }

    private static void c(c cVar, StringBuilder stringBuilder, int i) throws FormatException {
        while (i >= 3) {
            if (cVar.c() < 10) {
                throw FormatException.getFormatInstance();
            }
            int a = cVar.a(10);
            if (a >= 1000) {
                throw FormatException.getFormatInstance();
            }
            stringBuilder.append(a(a / 100));
            stringBuilder.append(a((a / 10) % 10));
            stringBuilder.append(a(a % 10));
            i -= 3;
        }
        if (i == 2) {
            if (cVar.c() < 7) {
                throw FormatException.getFormatInstance();
            }
            a = cVar.a(7);
            if (a >= 100) {
                throw FormatException.getFormatInstance();
            }
            stringBuilder.append(a(a / 10));
            stringBuilder.append(a(a % 10));
        } else if (i != 1) {
        } else {
            if (cVar.c() < 4) {
                throw FormatException.getFormatInstance();
            }
            a = cVar.a(4);
            if (a >= 10) {
                throw FormatException.getFormatInstance();
            }
            stringBuilder.append(a(a));
        }
    }

    private static int a(c cVar) throws FormatException {
        int a = cVar.a(8);
        if ((a & 128) == 0) {
            return a & TransportMediator.KEYCODE_MEDIA_PAUSE;
        }
        if ((a & 192) == 128) {
            return ((a & 63) << 8) | cVar.a(8);
        } else if ((a & 224) == 192) {
            return ((a & 31) << 16) | cVar.a(16);
        } else {
            throw FormatException.getFormatInstance();
        }
    }
}
