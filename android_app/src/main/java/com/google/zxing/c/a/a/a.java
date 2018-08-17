package com.google.zxing.c.a.a;

import java.util.List;

/* compiled from: BitArrayBuilder */
final class a {
    static com.google.zxing.common.a a(List<b> list) {
        int i;
        int size = (list.size() << 1) - 1;
        if (((b) list.get(list.size() - 1)).b() == null) {
            i = size - 1;
        } else {
            i = size;
        }
        com.google.zxing.common.a aVar = new com.google.zxing.common.a(i * 12);
        int a = ((b) list.get(0)).b().a();
        size = 11;
        i = 0;
        while (size >= 0) {
            if (((1 << size) & a) != 0) {
                aVar.b(i);
            }
            size--;
            i++;
        }
        int i2 = i;
        for (size = 1; size < list.size(); size++) {
            b bVar = (b) list.get(size);
            int a2 = bVar.a().a();
            a = 11;
            while (a >= 0) {
                if (((1 << a) & a2) != 0) {
                    aVar.b(i2);
                }
                a--;
                i2++;
            }
            if (bVar.b() != null) {
                a = bVar.b().a();
                for (i = 11; i >= 0; i--) {
                    if (((1 << i) & a) != 0) {
                        aVar.b(i2);
                    }
                    i2++;
                }
            }
        }
        return aVar;
    }
}
