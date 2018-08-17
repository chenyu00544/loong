package com.google.zxing.c.a.a.a;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.a;

/* compiled from: AI013x0xDecoder */
abstract class f extends i {
    f(a aVar) {
        super(aVar);
    }

    public String a() throws NotFoundException {
        if (b().a() != 60) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, 5);
        b(stringBuilder, 45, 15);
        return stringBuilder.toString();
    }
}
