package com.umeng.analytics.pro;

/* compiled from: TApplicationException */
public class cd extends ck {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    public static final int g = 6;
    public static final int h = 7;
    private static final di j = new di("TApplicationException");
    private static final cy k = new cy("message", (byte) 11, (short) 1);
    private static final cy l = new cy("type", (byte) 8, (short) 2);
    private static final long m = 1;
    protected int i = 0;

    public cd(int i) {
        this.i = i;
    }

    public cd(int i, String str) {
        super(str);
        this.i = i;
    }

    public cd(String str) {
        super(str);
    }

    public int a() {
        return this.i;
    }

    public static cd a(dd ddVar) throws ck {
        ddVar.j();
        String str = null;
        int i = 0;
        while (true) {
            cy l = ddVar.l();
            if (l.b == (byte) 0) {
                ddVar.k();
                return new cd(i, str);
            }
            switch (l.c) {
                case (short) 1:
                    if (l.b != (byte) 11) {
                        dg.a(ddVar, l.b);
                        break;
                    }
                    str = ddVar.z();
                    break;
                case (short) 2:
                    if (l.b != (byte) 8) {
                        dg.a(ddVar, l.b);
                        break;
                    }
                    i = ddVar.w();
                    break;
                default:
                    dg.a(ddVar, l.b);
                    break;
            }
            ddVar.m();
        }
    }

    public void b(dd ddVar) throws ck {
        ddVar.a(j);
        if (getMessage() != null) {
            ddVar.a(k);
            ddVar.a(getMessage());
            ddVar.c();
        }
        ddVar.a(l);
        ddVar.a(this.i);
        ddVar.c();
        ddVar.d();
        ddVar.b();
    }
}
