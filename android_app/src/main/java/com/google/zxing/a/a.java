package com.google.zxing.a;

import com.google.zxing.common.b;
import com.google.zxing.common.f;
import com.google.zxing.h;

/* compiled from: AztecDetectorResult */
public final class a extends f {
    private final boolean a;
    private final int b;
    private final int c;

    public a(b bVar, h[] hVarArr, boolean z, int i, int i2) {
        super(bVar, hVarArr);
        this.a = z;
        this.b = i;
        this.c = i2;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.b;
    }

    public boolean c() {
        return this.a;
    }
}
