package com.umeng.analytics.pro;

import java.io.ByteArrayOutputStream;

/* compiled from: TByteArrayOutputStream */
public class cg extends ByteArrayOutputStream {
    public cg(int i) {
        super(i);
    }

    public byte[] a() {
        return this.buf;
    }

    public int b() {
        return this.count;
    }
}
