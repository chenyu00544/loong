package com.umeng.analytics.pro;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: TUnion */
public abstract class co<T extends co<?, ?>, F extends cl> implements ce<T, F> {
    private static final Map<Class<? extends dl>, dm> c = new HashMap();
    protected Object a;
    protected F b;

    /* compiled from: TUnion */
    private static class a extends dn<co> {
        private a() {
        }

        public /* synthetic */ void a(dd ddVar, ce ceVar) throws ck {
            b(ddVar, (co) ceVar);
        }

        public /* synthetic */ void b(dd ddVar, ce ceVar) throws ck {
            a(ddVar, (co) ceVar);
        }

        public void a(dd ddVar, co coVar) throws ck {
            coVar.b = null;
            coVar.a = null;
            ddVar.j();
            cy l = ddVar.l();
            coVar.a = coVar.a(ddVar, l);
            if (coVar.a != null) {
                coVar.b = coVar.a(l.c);
            }
            ddVar.m();
            ddVar.l();
            ddVar.k();
        }

        public void b(dd ddVar, co coVar) throws ck {
            if (coVar.a() == null || coVar.c() == null) {
                throw new de("Cannot write a TUnion with no set value!");
            }
            ddVar.a(coVar.e());
            ddVar.a(coVar.c(coVar.b));
            coVar.c(ddVar);
            ddVar.c();
            ddVar.d();
            ddVar.b();
        }
    }

    /* compiled from: TUnion */
    private static class b implements dm {
        private b() {
        }

        public /* synthetic */ dl b() {
            return a();
        }

        public a a() {
            return new a();
        }
    }

    /* compiled from: TUnion */
    private static class c extends do<co> {
        private c() {
        }

        public /* synthetic */ void a(dd ddVar, ce ceVar) throws ck {
            b(ddVar, (co) ceVar);
        }

        public /* synthetic */ void b(dd ddVar, ce ceVar) throws ck {
            a(ddVar, (co) ceVar);
        }

        public void a(dd ddVar, co coVar) throws ck {
            coVar.b = null;
            coVar.a = null;
            short v = ddVar.v();
            coVar.a = coVar.a(ddVar, v);
            if (coVar.a != null) {
                coVar.b = coVar.a(v);
            }
        }

        public void b(dd ddVar, co coVar) throws ck {
            if (coVar.a() == null || coVar.c() == null) {
                throw new de("Cannot write a TUnion with no set value!");
            }
            ddVar.a(coVar.b.a());
            coVar.d(ddVar);
        }
    }

    /* compiled from: TUnion */
    private static class d implements dm {
        private d() {
        }

        public /* synthetic */ dl b() {
            return a();
        }

        public c a() {
            return new c();
        }
    }

    protected abstract F a(short s);

    protected abstract Object a(dd ddVar, cy cyVar) throws ck;

    protected abstract Object a(dd ddVar, short s) throws ck;

    protected abstract void b(F f, Object obj) throws ClassCastException;

    protected abstract cy c(F f);

    protected abstract void c(dd ddVar) throws ck;

    protected abstract void d(dd ddVar) throws ck;

    protected abstract di e();

    protected co() {
        this.b = null;
        this.a = null;
    }

    static {
        c.put(dn.class, new b());
        c.put(do.class, new d());
    }

    protected co(F f, Object obj) {
        a((cl) f, obj);
    }

    protected co(co<T, F> coVar) {
        if (coVar.getClass().equals(getClass())) {
            this.b = coVar.b;
            this.a = a(coVar.a);
            return;
        }
        throw new ClassCastException();
    }

    private static Object a(Object obj) {
        if (obj instanceof ce) {
            return ((ce) obj).p();
        }
        if (obj instanceof ByteBuffer) {
            return cf.d((ByteBuffer) obj);
        }
        if (obj instanceof List) {
            return a((List) obj);
        }
        if (obj instanceof Set) {
            return a((Set) obj);
        }
        if (obj instanceof Map) {
            return a((Map) obj);
        }
        return obj;
    }

    private static Map a(Map<Object, Object> map) {
        Map hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            hashMap.put(a(entry.getKey()), a(entry.getValue()));
        }
        return hashMap;
    }

    private static Set a(Set set) {
        Set hashSet = new HashSet();
        for (Object a : set) {
            hashSet.add(a(a));
        }
        return hashSet;
    }

    private static List a(List list) {
        List arrayList = new ArrayList(list.size());
        for (Object a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    public F a() {
        return this.b;
    }

    public Object c() {
        return this.a;
    }

    public Object a(F f) {
        if (f == this.b) {
            return c();
        }
        throw new IllegalArgumentException("Cannot get the value of field " + f + " because union's set field is " + this.b);
    }

    public Object a(int i) {
        return a(a((short) i));
    }

    public boolean d() {
        return this.b != null;
    }

    public boolean b(F f) {
        return this.b == f;
    }

    public boolean c(int i) {
        return b(a((short) i));
    }

    public void a(dd ddVar) throws ck {
        ((dm) c.get(ddVar.D())).b().b(ddVar, this);
    }

    public void a(F f, Object obj) {
        b(f, obj);
        this.b = f;
        this.a = obj;
    }

    public void a(int i, Object obj) {
        a(a((short) i), obj);
    }

    public void b(dd ddVar) throws ck {
        ((dm) c.get(ddVar.D())).b().a(ddVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<");
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(" ");
        if (a() != null) {
            Object c = c();
            stringBuilder.append(c(a()).a);
            stringBuilder.append(":");
            if (c instanceof ByteBuffer) {
                cf.a((ByteBuffer) c, stringBuilder);
            } else {
                stringBuilder.append(c.toString());
            }
        }
        stringBuilder.append(">");
        return stringBuilder.toString();
    }

    public final void b() {
        this.b = null;
        this.a = null;
    }
}
