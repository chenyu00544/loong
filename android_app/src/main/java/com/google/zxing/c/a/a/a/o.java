package com.google.zxing.c.a.a.a;

/* compiled from: DecodedInformation */
final class o extends q {
    private final String a;
    private final int b;
    private final boolean c;

    o(int i, String str) {
        super(i);
        this.a = str;
        this.c = false;
        this.b = 0;
    }

    o(int i, String str, int i2) {
        super(i);
        this.c = true;
        this.b = i2;
        this.a = str;
    }

    String a() {
        return this.a;
    }

    boolean b() {
        return this.c;
    }

    int c() {
        return this.b;
    }
}
