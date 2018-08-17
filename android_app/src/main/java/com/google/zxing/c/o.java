package com.google.zxing.c;

import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.common.a;
import com.google.zxing.g;

/* compiled from: UPCEANExtensionSupport */
final class o {
    private static final int[] a = new int[]{1, 1, 2};
    private final m b = new m();
    private final n c = new n();

    o() {
    }

    g a(int i, a aVar, int i2) throws NotFoundException {
        int[] a = p.a(aVar, i2, false, a);
        try {
            return this.c.a(i, aVar, a);
        } catch (ReaderException e) {
            return this.b.a(i, aVar, a);
        }
    }
}
