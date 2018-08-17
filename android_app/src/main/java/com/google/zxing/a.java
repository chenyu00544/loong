package com.google.zxing;

import com.google.zxing.common.b;

/* compiled from: Binarizer */
public abstract class a {
    private final c a;

    public abstract a a(c cVar);

    public abstract com.google.zxing.common.a a(int i, com.google.zxing.common.a aVar) throws NotFoundException;

    public abstract b b() throws NotFoundException;

    protected a(c cVar) {
        this.a = cVar;
    }

    public final c a() {
        return this.a;
    }

    public final int c() {
        return this.a.b();
    }

    public final int d() {
        return this.a.c();
    }
}
