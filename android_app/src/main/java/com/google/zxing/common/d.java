package com.google.zxing.common;

import java.util.List;

/* compiled from: DecoderResult */
public final class d {
    private final byte[] a;
    private final String b;
    private final List<byte[]> c;
    private final String d;
    private Integer e;
    private Integer f;
    private Object g;

    public d(byte[] bArr, String str, List<byte[]> list, String str2) {
        this.a = bArr;
        this.b = str;
        this.c = list;
        this.d = str2;
    }

    public byte[] a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public List<byte[]> c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public void a(Integer num) {
        this.e = num;
    }

    public void b(Integer num) {
        this.f = num;
    }

    public Object e() {
        return this.g;
    }

    public void a(Object obj) {
        this.g = obj;
    }
}
