package com.google.zxing;

import java.util.EnumMap;
import java.util.Map;

/* compiled from: Result */
public final class g {
    private final String a;
    private final byte[] b;
    private h[] c;
    private final BarcodeFormat d;
    private Map<ResultMetadataType, Object> e;
    private final long f;

    public g(String str, byte[] bArr, h[] hVarArr, BarcodeFormat barcodeFormat) {
        this(str, bArr, hVarArr, barcodeFormat, System.currentTimeMillis());
    }

    public g(String str, byte[] bArr, h[] hVarArr, BarcodeFormat barcodeFormat, long j) {
        this.a = str;
        this.b = bArr;
        this.c = hVarArr;
        this.d = barcodeFormat;
        this.e = null;
        this.f = j;
    }

    public String a() {
        return this.a;
    }

    public byte[] b() {
        return this.b;
    }

    public h[] c() {
        return this.c;
    }

    public BarcodeFormat d() {
        return this.d;
    }

    public Map<ResultMetadataType, Object> e() {
        return this.e;
    }

    public void a(ResultMetadataType resultMetadataType, Object obj) {
        if (this.e == null) {
            this.e = new EnumMap(ResultMetadataType.class);
        }
        this.e.put(resultMetadataType, obj);
    }

    public void a(Map<ResultMetadataType, Object> map) {
        if (map == null) {
            return;
        }
        if (this.e == null) {
            this.e = map;
        } else {
            this.e.putAll(map);
        }
    }

    public void a(h[] hVarArr) {
        Object obj = this.c;
        if (obj == null) {
            this.c = hVarArr;
        } else if (hVarArr != null && hVarArr.length > 0) {
            Object obj2 = new h[(obj.length + hVarArr.length)];
            System.arraycopy(obj, 0, obj2, 0, obj.length);
            System.arraycopy(hVarArr, 0, obj2, obj.length, hVarArr.length);
            this.c = obj2;
        }
    }

    public String toString() {
        return this.a;
    }
}
