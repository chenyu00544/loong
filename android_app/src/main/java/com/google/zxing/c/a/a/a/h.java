package com.google.zxing.c.a.a.a;

import com.google.zxing.common.a;

/* compiled from: AI01decoder */
abstract class h extends j {
    h(a aVar) {
        super(aVar);
    }

    protected final void b(StringBuilder stringBuilder, int i) {
        stringBuilder.append("(01)");
        int length = stringBuilder.length();
        stringBuilder.append('9');
        a(stringBuilder, i, length);
    }

    protected final void a(StringBuilder stringBuilder, int i, int i2) {
        for (int i3 = 0; i3 < 4; i3++) {
            int a = c().a((i3 * 10) + i, 10);
            if (a / 100 == 0) {
                stringBuilder.append('0');
            }
            if (a / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(a);
        }
        a(stringBuilder, i2);
    }

    private static void a(StringBuilder stringBuilder, int i) {
        int charAt;
        int i2 = 0;
        for (int i3 = 0; i3 < 13; i3++) {
            charAt = stringBuilder.charAt(i3 + i) - 48;
            if ((i3 & 1) == 0) {
                charAt *= 3;
            }
            i2 += charAt;
        }
        charAt = 10 - (i2 % 10);
        if (charAt == 10) {
            charAt = 0;
        }
        stringBuilder.append(charAt);
    }
}
