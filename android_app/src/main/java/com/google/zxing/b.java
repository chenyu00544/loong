package com.google.zxing;

import com.google.zxing.common.a;

/* compiled from: BinaryBitmap */
public final class b {
    private final a a;
    private com.google.zxing.common.b b;

    public b(a aVar) {
        if (aVar == null) {
            throw new IllegalArgumentException("Binarizer must be non-null.");
        }
        this.a = aVar;
    }

    public int a() {
        return this.a.c();
    }

    public int b() {
        return this.a.d();
    }

    public a a(int i, a aVar) throws NotFoundException {
        return this.a.a(i, aVar);
    }

    public com.google.zxing.common.b c() throws NotFoundException {
        if (this.b == null) {
            this.b = this.a.b();
        }
        return this.b;
    }

    public boolean d() {
        return this.a.a().d();
    }

    public b e() {
        return new b(this.a.a(this.a.a().e()));
    }
}
