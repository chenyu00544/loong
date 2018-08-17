package com.google.zxing.c;

import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.b;
import com.google.zxing.common.a;
import com.google.zxing.f;
import com.google.zxing.g;
import com.google.zxing.h;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

/* compiled from: OneDReader */
public abstract class k implements f {
    public abstract g a(int i, a aVar, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException;

    public g a(b bVar, Map<DecodeHintType, ?> map) throws NotFoundException, FormatException {
        try {
            return b(bVar, map);
        } catch (NotFoundException e) {
            Object obj = (map == null || !map.containsKey(DecodeHintType.TRY_HARDER)) ? null : 1;
            if (obj == null || !bVar.d()) {
                throw e;
            }
            int i;
            b e2 = bVar.e();
            g b = b(e2, map);
            Map e3 = b.e();
            if (e3 == null || !e3.containsKey(ResultMetadataType.ORIENTATION)) {
                i = 270;
            } else {
                i = (((Integer) e3.get(ResultMetadataType.ORIENTATION)).intValue() + 270) % com.umeng.analytics.a.p;
            }
            b.a(ResultMetadataType.ORIENTATION, Integer.valueOf(i));
            h[] c = b.c();
            if (c != null) {
                int b2 = e2.b();
                for (i = 0; i < c.length; i++) {
                    c[i] = new h((((float) b2) - c[i].b()) - 1.0f, c[i].a());
                }
            }
            return b;
        }
    }

    public void a() {
    }

    private g b(b bVar, Map<DecodeHintType, ?> map) throws NotFoundException {
        Object obj;
        int max;
        int i;
        a aVar;
        Map map2;
        int i2;
        int i3;
        int i4;
        Map enumMap;
        g a;
        h[] c;
        int a2 = bVar.a();
        int b = bVar.b();
        a aVar2 = new a(a2);
        int i5 = b >> 1;
        if (map != null) {
            if (map.containsKey(DecodeHintType.TRY_HARDER)) {
                obj = 1;
                max = Math.max(1, b >> (obj == null ? 8 : 5));
                if (obj == null) {
                    i = b;
                } else {
                    i = 15;
                }
                aVar = aVar2;
                map2 = map;
                for (i2 = 0; i2 < i; i2++) {
                    i3 = (i2 + 1) >> 1;
                    if (((i2 & 1) != 0 ? 1 : null) == null) {
                        i3 = -i3;
                    }
                    i4 = i5 + (i3 * max);
                    if (i4 < 0 || i4 >= b) {
                        break;
                    }
                    try {
                        aVar = bVar.a(i4, aVar);
                        i3 = 0;
                        while (i3 < 2) {
                            if (i3 == 1) {
                                aVar.d();
                                if (map2 != null && map2.containsKey(DecodeHintType.NEED_RESULT_POINT_CALLBACK)) {
                                    enumMap = new EnumMap(DecodeHintType.class);
                                    enumMap.putAll(map2);
                                    enumMap.remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                                    a = a(i4, aVar, enumMap);
                                    if (i3 == 1) {
                                        a.a(ResultMetadataType.ORIENTATION, Integer.valueOf(180));
                                        c = a.c();
                                        if (c != null) {
                                            c[0] = new h((((float) a2) - c[0].a()) - 1.0f, c[0].b());
                                            c[1] = new h((((float) a2) - c[1].a()) - 1.0f, c[1].b());
                                        }
                                    }
                                    return a;
                                }
                            }
                            enumMap = map2;
                            try {
                                a = a(i4, aVar, enumMap);
                                if (i3 == 1) {
                                    a.a(ResultMetadataType.ORIENTATION, Integer.valueOf(180));
                                    c = a.c();
                                    if (c != null) {
                                        c[0] = new h((((float) a2) - c[0].a()) - 1.0f, c[0].b());
                                        c[1] = new h((((float) a2) - c[1].a()) - 1.0f, c[1].b());
                                    }
                                }
                                return a;
                            } catch (ReaderException e) {
                                i3++;
                                map2 = enumMap;
                            }
                        }
                        continue;
                    } catch (NotFoundException e2) {
                    }
                }
                throw NotFoundException.getNotFoundInstance();
            }
        }
        obj = null;
        if (obj == null) {
        }
        max = Math.max(1, b >> (obj == null ? 8 : 5));
        if (obj == null) {
            i = 15;
        } else {
            i = b;
        }
        aVar = aVar2;
        map2 = map;
        for (i2 = 0; i2 < i; i2++) {
            i3 = (i2 + 1) >> 1;
            if ((i2 & 1) != 0) {
            }
            if (((i2 & 1) != 0 ? 1 : null) == null) {
                i3 = -i3;
            }
            i4 = i5 + (i3 * max);
            aVar = bVar.a(i4, aVar);
            i3 = 0;
            while (i3 < 2) {
                if (i3 == 1) {
                    aVar.d();
                    enumMap = new EnumMap(DecodeHintType.class);
                    enumMap.putAll(map2);
                    enumMap.remove(DecodeHintType.NEED_RESULT_POINT_CALLBACK);
                    a = a(i4, aVar, enumMap);
                    if (i3 == 1) {
                        a.a(ResultMetadataType.ORIENTATION, Integer.valueOf(180));
                        c = a.c();
                        if (c != null) {
                            c[0] = new h((((float) a2) - c[0].a()) - 1.0f, c[0].b());
                            c[1] = new h((((float) a2) - c[1].a()) - 1.0f, c[1].b());
                        }
                    }
                    return a;
                }
                enumMap = map2;
                a = a(i4, aVar, enumMap);
                if (i3 == 1) {
                    a.a(ResultMetadataType.ORIENTATION, Integer.valueOf(180));
                    c = a.c();
                    if (c != null) {
                        c[0] = new h((((float) a2) - c[0].a()) - 1.0f, c[0].b());
                        c[1] = new h((((float) a2) - c[1].a()) - 1.0f, c[1].b());
                    }
                }
                return a;
            }
            continue;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    protected static void a(a aVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        Arrays.fill(iArr, 0, length, 0);
        int a = aVar.a();
        if (i >= a) {
            throw NotFoundException.getNotFoundInstance();
        }
        int i2;
        int i3 = !aVar.a(i) ? 1 : 0;
        int i4 = 0;
        while (i < a) {
            if ((aVar.a(i) ^ i3) != 0) {
                iArr[i4] = iArr[i4] + 1;
                i2 = i3;
            } else {
                i2 = i4 + 1;
                if (i2 == length) {
                    break;
                }
                iArr[i2] = 1;
                int i5 = i2;
                i2 = i3 == 0 ? 1 : 0;
                i4 = i5;
            }
            i++;
            i3 = i2;
        }
        i2 = i4;
        if (i2 == length) {
            return;
        }
        if (i2 != length - 1 || i != a) {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    protected static void b(a aVar, int i, int[] iArr) throws NotFoundException {
        int length = iArr.length;
        boolean a = aVar.a(i);
        while (i > 0 && length >= 0) {
            i--;
            if (aVar.a(i) != a) {
                length--;
                a = !a;
            }
        }
        if (length >= 0) {
            throw NotFoundException.getNotFoundInstance();
        }
        a(aVar, i + 1, iArr);
    }

    protected static int a(int[] iArr, int[] iArr2, int i) {
        int i2;
        int length = iArr.length;
        int i3 = 0;
        int i4 = 0;
        for (i2 = 0; i2 < length; i2++) {
            i4 += iArr[i2];
            i3 += iArr2[i2];
        }
        if (i4 < i3) {
            return ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        }
        int i5 = (i4 << 8) / i3;
        int i6 = (i * i5) >> 8;
        i3 = 0;
        for (i2 = 0; i2 < length; i2++) {
            int i7 = iArr[i2] << 8;
            int i8 = iArr2[i2] * i5;
            i7 = i7 > i8 ? i7 - i8 : i8 - i7;
            if (i7 > i6) {
                return ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            i3 += i7;
        }
        return i3 / i4;
    }
}
