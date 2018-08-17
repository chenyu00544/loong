package com.google.zxing.c.a.a.a;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.a;

/* compiled from: AI01392xDecoder */
final class c extends h {
    c(a aVar) {
        super(aVar);
    }

    public String a() throws NotFoundException {
        if (b().a() < 48) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, 8);
        int a = c().a(48, 2);
        stringBuilder.append("(392");
        stringBuilder.append(a);
        stringBuilder.append(')');
        stringBuilder.append(c().a(50, null).a());
        return stringBuilder.toString();
    }
}
