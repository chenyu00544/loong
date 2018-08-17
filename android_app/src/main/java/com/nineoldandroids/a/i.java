package com.nineoldandroids.a;

import android.view.View;
import com.nineoldandroids.b.a.a;
import com.nineoldandroids.util.c;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ObjectAnimator */
public final class i extends m {
    private static final Map<String, c> h = new HashMap();
    private Object i;
    private String j;
    private c k;

    public /* synthetic */ a a(long j) {
        return b(j);
    }

    public /* synthetic */ a c() {
        return e();
    }

    public /* synthetic */ m c(long j) {
        return b(j);
    }

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return e();
    }

    public /* synthetic */ m f() {
        return e();
    }

    static {
        h.put("alpha", j.a);
        h.put("pivotX", j.b);
        h.put("pivotY", j.c);
        h.put("translationX", j.d);
        h.put("translationY", j.e);
        h.put("rotation", j.f);
        h.put("rotationX", j.g);
        h.put("rotationY", j.h);
        h.put("scaleX", j.i);
        h.put("scaleY", j.j);
        h.put("scrollX", j.k);
        h.put("scrollY", j.l);
        h.put("x", j.m);
        h.put("y", j.n);
    }

    public void a(String str) {
        if (this.f != null) {
            k kVar = this.f[0];
            String c = kVar.c();
            kVar.a(str);
            this.g.remove(c);
            this.g.put(str, kVar);
        }
        this.j = str;
        this.e = false;
    }

    public void a(c cVar) {
        if (this.f != null) {
            k kVar = this.f[0];
            String c = kVar.c();
            kVar.a(cVar);
            this.g.remove(c);
            this.g.put(this.j, kVar);
        }
        if (this.k != null) {
            this.j = cVar.a();
        }
        this.k = cVar;
        this.e = false;
    }

    private i(Object obj, String str) {
        this.i = obj;
        a(str);
    }

    public static i a(Object obj, String str, float... fArr) {
        i iVar = new i(obj, str);
        iVar.a(fArr);
        return iVar;
    }

    public void a(float... fArr) {
        if (this.f != null && this.f.length != 0) {
            super.a(fArr);
        } else if (this.k != null) {
            a(k.a(this.k, fArr));
        } else {
            a(k.a(this.j, fArr));
        }
    }

    public void a() {
        super.a();
    }

    void d() {
        if (!this.e) {
            if (this.k == null && a.a && (this.i instanceof View) && h.containsKey(this.j)) {
                a((c) h.get(this.j));
            }
            for (k a : this.f) {
                a.a(this.i);
            }
            super.d();
        }
    }

    public i b(long j) {
        super.c(j);
        return this;
    }

    void a(float f) {
        super.a(f);
        for (k b : this.f) {
            b.b(this.i);
        }
    }

    public i e() {
        return (i) super.f();
    }

    public String toString() {
        String str = "ObjectAnimator@" + Integer.toHexString(hashCode()) + ", target " + this.i;
        if (this.f != null) {
            for (k kVar : this.f) {
                str = str + "\n    " + kVar.toString();
            }
        }
        return str;
    }
}
