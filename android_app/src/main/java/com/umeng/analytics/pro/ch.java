package com.umeng.analytics.pro;

import com.umeng.analytics.pro.cx.a;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* compiled from: TDeserializer */
public class ch {
    private final dd a;
    private final dq b;

    public ch() {
        this(new a());
    }

    public ch(df dfVar) {
        this.b = new dq();
        this.a = dfVar.a(this.b);
    }

    public void a(ce ceVar, byte[] bArr) throws ck {
        try {
            this.b.a(bArr);
            ceVar.a(this.a);
        } finally {
            this.b.e();
            this.a.B();
        }
    }

    public void a(ce ceVar, String str, String str2) throws ck {
        try {
            a(ceVar, str.getBytes(str2));
            this.a.B();
        } catch (UnsupportedEncodingException e) {
            throw new ck("JVM DOES NOT SUPPORT ENCODING: " + str2);
        } catch (Throwable th) {
            this.a.B();
        }
    }

    public void a(ce ceVar, byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        try {
            if (j(bArr, clVar, clVarArr) != null) {
                ceVar.a(this.a);
            }
            this.b.e();
            this.a.B();
        } catch (Throwable e) {
            throw new ck(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
        }
    }

    public Boolean a(byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        return (Boolean) a((byte) 2, bArr, clVar, clVarArr);
    }

    public Byte b(byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        return (Byte) a((byte) 3, bArr, clVar, clVarArr);
    }

    public Double c(byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        return (Double) a((byte) 4, bArr, clVar, clVarArr);
    }

    public Short d(byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        return (Short) a((byte) 6, bArr, clVar, clVarArr);
    }

    public Integer e(byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        return (Integer) a((byte) 8, bArr, clVar, clVarArr);
    }

    public Long f(byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        return (Long) a((byte) 10, bArr, clVar, clVarArr);
    }

    public String g(byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        return (String) a((byte) 11, bArr, clVar, clVarArr);
    }

    public ByteBuffer h(byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        return (ByteBuffer) a((byte) 100, bArr, clVar, clVarArr);
    }

    public Short i(byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        try {
            if (j(bArr, clVar, clVarArr) != null) {
                this.a.j();
                Short valueOf = Short.valueOf(this.a.l().c);
                this.b.e();
                this.a.B();
                return valueOf;
            }
            this.b.e();
            this.a.B();
            return null;
        } catch (Throwable e) {
            throw new ck(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
        }
    }

    private Object a(byte b, byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        try {
            cy j = j(bArr, clVar, clVarArr);
            if (j != null) {
                Object valueOf;
                switch (b) {
                    case (byte) 2:
                        if (j.b == (byte) 2) {
                            valueOf = Boolean.valueOf(this.a.t());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 3:
                        if (j.b == (byte) 3) {
                            valueOf = Byte.valueOf(this.a.u());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 4:
                        if (j.b == (byte) 4) {
                            valueOf = Double.valueOf(this.a.y());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 6:
                        if (j.b == (byte) 6) {
                            valueOf = Short.valueOf(this.a.v());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 8:
                        if (j.b == (byte) 8) {
                            valueOf = Integer.valueOf(this.a.w());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 10:
                        if (j.b == (byte) 10) {
                            valueOf = Long.valueOf(this.a.x());
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 11:
                        if (j.b == (byte) 11) {
                            valueOf = this.a.z();
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                    case (byte) 100:
                        if (j.b == (byte) 11) {
                            valueOf = this.a.A();
                            this.b.e();
                            this.a.B();
                            return valueOf;
                        }
                        break;
                }
            }
            this.b.e();
            this.a.B();
            return null;
        } catch (Throwable e) {
            throw new ck(e);
        } catch (Throwable th) {
            this.b.e();
            this.a.B();
        }
    }

    private cy j(byte[] bArr, cl clVar, cl... clVarArr) throws ck {
        int i = 0;
        this.b.a(bArr);
        cl[] clVarArr2 = new cl[(clVarArr.length + 1)];
        clVarArr2[0] = clVar;
        for (int i2 = 0; i2 < clVarArr.length; i2++) {
            clVarArr2[i2 + 1] = clVarArr[i2];
        }
        this.a.j();
        cy cyVar = null;
        while (i < clVarArr2.length) {
            cyVar = this.a.l();
            if (cyVar.b == (byte) 0 || cyVar.c > clVarArr2[i].a()) {
                return null;
            }
            if (cyVar.c != clVarArr2[i].a()) {
                dg.a(this.a, cyVar.b);
                this.a.m();
            } else {
                i++;
                if (i < clVarArr2.length) {
                    this.a.j();
                }
            }
        }
        return cyVar;
    }

    public void a(ce ceVar, String str) throws ck {
        a(ceVar, str.getBytes());
    }
}
