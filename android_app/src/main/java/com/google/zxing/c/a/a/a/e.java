package com.google.zxing.c.a.a.a;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.a;

/* compiled from: AI013x0x1xDecoder */
final class e extends i {
    private final String a;
    private final String b;

    e(a aVar, String str, String str2) {
        super(aVar);
        this.a = str2;
        this.b = str;
    }

    public String a() throws NotFoundException {
        if (b().a() != 84) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder stringBuilder = new StringBuilder();
        b(stringBuilder, 8);
        b(stringBuilder, 48, 20);
        c(stringBuilder, 68);
        return stringBuilder.toString();
    }

    private void c(StringBuilder stringBuilder, int i) {
        int a = c().a(i, 16);
        if (a != 38400) {
            stringBuilder.append('(');
            stringBuilder.append(this.a);
            stringBuilder.append(')');
            int i2 = a % 32;
            a /= 32;
            int i3 = (a % 12) + 1;
            a /= 12;
            if (a / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(a);
            if (i3 / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(i3);
            if (i2 / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(i2);
        }
    }

    protected void a(StringBuilder stringBuilder, int i) {
        int i2 = i / 100000;
        stringBuilder.append('(');
        stringBuilder.append(this.b);
        stringBuilder.append(i2);
        stringBuilder.append(')');
    }

    protected int a(int i) {
        return i % 100000;
    }
}
