package com.umeng.analytics.pro;

import com.umeng.analytics.pro.cx.a;

/* compiled from: TProtocolUtil */
public class dg {
    private static int a = ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;

    public static void a(int i) {
        a = i;
    }

    public static void a(dd ddVar, byte b) throws ck {
        a(ddVar, b, a);
    }

    public static void a(dd ddVar, byte b, int i) throws ck {
        int i2 = 0;
        if (i <= 0) {
            throw new ck("Maximum skip depth exceeded");
        }
        switch (b) {
            case (byte) 2:
                ddVar.t();
                return;
            case (byte) 3:
                ddVar.u();
                return;
            case (byte) 4:
                ddVar.y();
                return;
            case (byte) 6:
                ddVar.v();
                return;
            case (byte) 8:
                ddVar.w();
                return;
            case (byte) 10:
                ddVar.x();
                return;
            case (byte) 11:
                ddVar.A();
                return;
            case (byte) 12:
                ddVar.j();
                while (true) {
                    cy l = ddVar.l();
                    if (l.b == (byte) 0) {
                        ddVar.k();
                        return;
                    } else {
                        a(ddVar, l.b, i - 1);
                        ddVar.m();
                    }
                }
            case (byte) 13:
                da n = ddVar.n();
                while (i2 < n.c) {
                    a(ddVar, n.a, i - 1);
                    a(ddVar, n.b, i - 1);
                    i2++;
                }
                ddVar.o();
                return;
            case (byte) 14:
                dh r = ddVar.r();
                while (i2 < r.b) {
                    a(ddVar, r.a, i - 1);
                    i2++;
                }
                ddVar.s();
                return;
            case (byte) 15:
                cz p = ddVar.p();
                while (i2 < p.b) {
                    a(ddVar, p.a, i - 1);
                    i2++;
                }
                ddVar.q();
                return;
            default:
                return;
        }
    }

    public static df a(byte[] bArr, df dfVar) {
        if (bArr[0] > dk.n) {
            return new a();
        }
        if (bArr.length <= 1 || (bArr[1] & 128) == 0) {
            return dfVar;
        }
        return new a();
    }
}
