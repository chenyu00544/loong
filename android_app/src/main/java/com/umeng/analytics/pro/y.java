package com.umeng.analytics.pro;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: AbstractIdTracker */
public abstract class y {
    private final int a = 10;
    private final int b = 20;
    private final String c;
    private List<bi> d;
    private bj e;

    public abstract String f();

    public y(String str) {
        this.c = str;
    }

    public boolean a() {
        return g();
    }

    public String b() {
        return this.c;
    }

    public boolean c() {
        if (this.e == null || this.e.i() <= 20) {
            return true;
        }
        return false;
    }

    private boolean g() {
        bj bjVar = this.e;
        String c = bjVar == null ? null : bjVar.c();
        int i = bjVar == null ? 0 : bjVar.i();
        String a = a(f());
        if (a == null || a.equals(c)) {
            return false;
        }
        if (bjVar == null) {
            bjVar = new bj();
        }
        bjVar.a(a);
        bjVar.a(System.currentTimeMillis());
        bjVar.a(i + 1);
        bi biVar = new bi();
        biVar.a(this.c);
        biVar.c(a);
        biVar.b(c);
        biVar.a(bjVar.f());
        if (this.d == null) {
            this.d = new ArrayList(2);
        }
        this.d.add(biVar);
        if (this.d.size() > 10) {
            this.d.remove(0);
        }
        this.e = bjVar;
        return true;
    }

    public bj d() {
        return this.e;
    }

    public void a(bj bjVar) {
        this.e = bjVar;
    }

    public List<bi> e() {
        return this.d;
    }

    public void a(List<bi> list) {
        this.d = list;
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.length() == 0 || "0".equals(trim) || "unknown".equals(trim.toLowerCase(Locale.US))) {
            return null;
        }
        return trim;
    }

    public void a(bk bkVar) {
        this.e = (bj) bkVar.d().get(this.c);
        List<bi> i = bkVar.i();
        if (i != null && i.size() > 0) {
            if (this.d == null) {
                this.d = new ArrayList();
            }
            for (bi biVar : i) {
                if (this.c.equals(biVar.a)) {
                    this.d.add(biVar);
                }
            }
        }
    }
}
