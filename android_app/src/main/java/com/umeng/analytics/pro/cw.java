package com.umeng.analytics.pro;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TBinaryProtocol */
public class cw extends dd {
    protected static final int a = -65536;
    protected static final int b = -2147418112;
    private static final di h = new di();
    protected boolean c;
    protected boolean d;
    protected int e;
    protected boolean f;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;

    /* compiled from: TBinaryProtocol */
    public static class a implements df {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.a = false;
            this.b = true;
            this.a = z;
            this.b = z2;
            this.c = i;
        }

        public dd a(dr drVar) {
            dd cwVar = new cw(drVar, this.a, this.b);
            if (this.c != 0) {
                cwVar.c(this.c);
            }
            return cwVar;
        }
    }

    public cw(dr drVar) {
        this(drVar, false, true);
    }

    public cw(dr drVar, boolean z, boolean z2) {
        super(drVar);
        this.c = false;
        this.d = true;
        this.f = false;
        this.i = new byte[1];
        this.j = new byte[2];
        this.k = new byte[4];
        this.l = new byte[8];
        this.m = new byte[1];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[8];
        this.c = z;
        this.d = z2;
    }

    public void a(db dbVar) throws ck {
        if (this.d) {
            a(b | dbVar.b);
            a(dbVar.a);
            a(dbVar.c);
            return;
        }
        a(dbVar.a);
        a(dbVar.b);
        a(dbVar.c);
    }

    public void a() {
    }

    public void a(di diVar) {
    }

    public void b() {
    }

    public void a(cy cyVar) throws ck {
        a(cyVar.b);
        a(cyVar.c);
    }

    public void c() {
    }

    public void d() throws ck {
        a((byte) 0);
    }

    public void a(da daVar) throws ck {
        a(daVar.a);
        a(daVar.b);
        a(daVar.c);
    }

    public void e() {
    }

    public void a(cz czVar) throws ck {
        a(czVar.a);
        a(czVar.b);
    }

    public void f() {
    }

    public void a(dh dhVar) throws ck {
        a(dhVar.a);
        a(dhVar.b);
    }

    public void g() {
    }

    public void a(boolean z) throws ck {
        a(z ? (byte) 1 : (byte) 0);
    }

    public void a(byte b) throws ck {
        this.i[0] = b;
        this.g.b(this.i, 0, 1);
    }

    public void a(short s) throws ck {
        this.j[0] = (byte) ((s >> 8) & 255);
        this.j[1] = (byte) (s & 255);
        this.g.b(this.j, 0, 2);
    }

    public void a(int i) throws ck {
        this.k[0] = (byte) ((i >> 24) & 255);
        this.k[1] = (byte) ((i >> 16) & 255);
        this.k[2] = (byte) ((i >> 8) & 255);
        this.k[3] = (byte) (i & 255);
        this.g.b(this.k, 0, 4);
    }

    public void a(long j) throws ck {
        this.l[0] = (byte) ((int) ((j >> 56) & 255));
        this.l[1] = (byte) ((int) ((j >> 48) & 255));
        this.l[2] = (byte) ((int) ((j >> 40) & 255));
        this.l[3] = (byte) ((int) ((j >> 32) & 255));
        this.l[4] = (byte) ((int) ((j >> 24) & 255));
        this.l[5] = (byte) ((int) ((j >> 16) & 255));
        this.l[6] = (byte) ((int) ((j >> 8) & 255));
        this.l[7] = (byte) ((int) (255 & j));
        this.g.b(this.l, 0, 8);
    }

    public void a(double d) throws ck {
        a(Double.doubleToLongBits(d));
    }

    public void a(String str) throws ck {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.g.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new ck("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public void a(ByteBuffer byteBuffer) throws ck {
        int limit = byteBuffer.limit() - byteBuffer.position();
        a(limit);
        this.g.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), limit);
    }

    public db h() throws ck {
        int w = w();
        if (w < 0) {
            if ((-65536 & w) == b) {
                return new db(z(), (byte) (w & 255), w());
            }
            throw new de(4, "Bad version in readMessageBegin");
        } else if (!this.c) {
            return new db(b(w), u(), w());
        } else {
            throw new de(4, "Missing version in readMessageBegin, old client?");
        }
    }

    public void i() {
    }

    public di j() {
        return h;
    }

    public void k() {
    }

    public cy l() throws ck {
        byte u = u();
        return new cy("", u, u == (byte) 0 ? (short) 0 : v());
    }

    public void m() {
    }

    public da n() throws ck {
        return new da(u(), u(), w());
    }

    public void o() {
    }

    public cz p() throws ck {
        return new cz(u(), w());
    }

    public void q() {
    }

    public dh r() throws ck {
        return new dh(u(), w());
    }

    public void s() {
    }

    public boolean t() throws ck {
        return u() == (byte) 1;
    }

    public byte u() throws ck {
        if (this.g.h() >= 1) {
            byte b = this.g.f()[this.g.g()];
            this.g.a(1);
            return b;
        }
        a(this.m, 0, 1);
        return this.m[0];
    }

    public short v() throws ck {
        int i = 0;
        byte[] bArr = this.n;
        if (this.g.h() >= 2) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(2);
        } else {
            a(this.n, 0, 2);
        }
        return (short) ((bArr[i + 1] & 255) | ((bArr[i] & 255) << 8));
    }

    public int w() throws ck {
        int i = 0;
        byte[] bArr = this.o;
        if (this.g.h() >= 4) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(4);
        } else {
            a(this.o, 0, 4);
        }
        return (bArr[i + 3] & 255) | ((((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16)) | ((bArr[i + 2] & 255) << 8));
    }

    public long x() throws ck {
        int i = 0;
        byte[] bArr = this.p;
        if (this.g.h() >= 8) {
            bArr = this.g.f();
            i = this.g.g();
            this.g.a(8);
        } else {
            a(this.p, 0, 8);
        }
        return ((long) (bArr[i + 7] & 255)) | (((((((((long) (bArr[i] & 255)) << 56) | (((long) (bArr[i + 1] & 255)) << 48)) | (((long) (bArr[i + 2] & 255)) << 40)) | (((long) (bArr[i + 3] & 255)) << 32)) | (((long) (bArr[i + 4] & 255)) << 24)) | (((long) (bArr[i + 5] & 255)) << 16)) | (((long) (bArr[i + 6] & 255)) << 8));
    }

    public double y() throws ck {
        return Double.longBitsToDouble(x());
    }

    public String z() throws ck {
        int w = w();
        if (this.g.h() < w) {
            return b(w);
        }
        try {
            String str = new String(this.g.f(), this.g.g(), w, "UTF-8");
            this.g.a(w);
            return str;
        } catch (UnsupportedEncodingException e) {
            throw new ck("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public String b(int i) throws ck {
        try {
            d(i);
            byte[] bArr = new byte[i];
            this.g.d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new ck("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public ByteBuffer A() throws ck {
        int w = w();
        d(w);
        if (this.g.h() >= w) {
            ByteBuffer wrap = ByteBuffer.wrap(this.g.f(), this.g.g(), w);
            this.g.a(w);
            return wrap;
        }
        byte[] bArr = new byte[w];
        this.g.d(bArr, 0, w);
        return ByteBuffer.wrap(bArr);
    }

    private int a(byte[] bArr, int i, int i2) throws ck {
        d(i2);
        return this.g.d(bArr, i, i2);
    }

    public void c(int i) {
        this.e = i;
        this.f = true;
    }

    protected void d(int i) throws ck {
        if (i < 0) {
            throw new de("Negative length: " + i);
        } else if (this.f) {
            this.e -= i;
            if (this.e < 0) {
                throw new de("Message length exceeded: " + i);
            }
        }
    }
}
