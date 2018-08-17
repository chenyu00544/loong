package com.google.zxing.c.a.a.a;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.a;

/* compiled from: AI01393xDecoder */
final class d extends h {
    d(a aVar) {
        super(aVar);
    }

    public String a() throws NotFoundException {
        if (b().a() < 48) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, 8);
        int a = c().a(48, 2);
        stringBuilder.append("(393");
        stringBuilder.append(a);
        stringBuilder.append(')');
        a = c().a(50, 10);
        if (a / 100 == 0) {
            stringBuilder.append('0');
        }
        if (a / 10 == 0) {
            stringBuilder.append('0');
        }
        stringBuilder.append(a);
        stringBuilder.append(c().a(60, null).a());
        return stringBuilder.toString();
    }
}
