package com.google.zxing.c.a.a.a;

import com.google.zxing.common.a;

/* compiled from: AI01320xDecoder */
final class b extends f {
    b(a aVar) {
        super(aVar);
    }

    protected void a(StringBuilder stringBuilder, int i) {
        if (i < 10000) {
            stringBuilder.append("(3202)");
        } else {
            stringBuilder.append("(3203)");
        }
    }

    protected int a(int i) {
        return i < 10000 ? i : i - 10000;
    }
}
