package com.google.zxing.c.a.a.a;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.a;

/* compiled from: AI01AndOtherAIs */
final class g extends h {
    g(a aVar) {
        super(aVar);
    }

    public String a() throws NotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(01)");
        int length = stringBuilder.length();
        stringBuilder.append(c().a(4, 4));
        a(stringBuilder, 8, length);
        return c().a(stringBuilder, 48);
    }
}
