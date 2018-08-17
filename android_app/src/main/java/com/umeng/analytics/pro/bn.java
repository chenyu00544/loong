package com.umeng.analytics.pro;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: Response */
public class bn implements ce<bn, e>, Serializable, Cloneable {
    public static final Map<e, cq> d;
    private static final long e = -4549277923241195391L;
    private static final di f = new di("Response");
    private static final cy g = new cy("resp_code", (byte) 8, (short) 1);
    private static final cy h = new cy("msg", (byte) 11, (short) 2);
    private static final cy i = new cy(x.N, (byte) 12, (short) 3);
    private static final Map<Class<? extends dl>, dm> j = new HashMap();
    private static final int k = 0;
    public int a;
    public String b;
    public bl c;
    private byte l;
    private e[] m;

    /* compiled from: Response */
    private static class a extends dn<bn> {
        private a() {
        }

        public /* synthetic */ void a(dd ddVar, ce ceVar) throws ck {
            b(ddVar, (bn) ceVar);
        }

        public /* synthetic */ void b(dd ddVar, ce ceVar) throws ck {
            a(ddVar, (bn) ceVar);
        }

        public void a(dd ddVar, bn bnVar) throws ck {
            ddVar.j();
            while (true) {
                cy l = ddVar.l();
                if (l.b == (byte) 0) {
                    ddVar.k();
                    if (bnVar.e()) {
                        bnVar.l();
                        return;
                    }
                    throw new de("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 8) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        bnVar.a = ddVar.w();
                        bnVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        bnVar.b = ddVar.z();
                        bnVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 12) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        bnVar.c = new bl();
                        bnVar.c.a(ddVar);
                        bnVar.c(true);
                        break;
                    default:
                        dg.a(ddVar, l.b);
                        break;
                }
                ddVar.m();
            }
        }

        public void b(dd ddVar, bn bnVar) throws ck {
            bnVar.l();
            ddVar.a(bn.f);
            ddVar.a(bn.g);
            ddVar.a(bnVar.a);
            ddVar.c();
            if (bnVar.b != null && bnVar.h()) {
                ddVar.a(bn.h);
                ddVar.a(bnVar.b);
                ddVar.c();
            }
            if (bnVar.c != null && bnVar.k()) {
                ddVar.a(bn.i);
                bnVar.c.b(ddVar);
                ddVar.c();
            }
            ddVar.d();
            ddVar.b();
        }
    }

    /* compiled from: Response */
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

    /* compiled from: Response */
    private static class c extends do<bn> {
        private c() {
        }

        public void a(dd ddVar, bn bnVar) throws ck {
            ddVar = (dj) ddVar;
            ddVar.a(bnVar.a);
            BitSet bitSet = new BitSet();
            if (bnVar.h()) {
                bitSet.set(0);
            }
            if (bnVar.k()) {
                bitSet.set(1);
            }
            ddVar.a(bitSet, 2);
            if (bnVar.h()) {
                ddVar.a(bnVar.b);
            }
            if (bnVar.k()) {
                bnVar.c.b(ddVar);
            }
        }

        public void b(dd ddVar, bn bnVar) throws ck {
            ddVar = (dj) ddVar;
            bnVar.a = ddVar.w();
            bnVar.a(true);
            BitSet b = ddVar.b(2);
            if (b.get(0)) {
                bnVar.b = ddVar.z();
                bnVar.b(true);
            }
            if (b.get(1)) {
                bnVar.c = new bl();
                bnVar.c.a(ddVar);
                bnVar.c(true);
            }
        }
    }

    /* compiled from: Response */
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

    /* compiled from: Response */
    public enum e implements cl {
        RESP_CODE((short) 1, "resp_code"),
        MSG((short) 2, "msg"),
        IMPRINT((short) 3, x.N);
        
        private static final Map<String, e> d = null;
        private final short e;
        private final String f;

        static {
            d = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                d.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return RESP_CODE;
                case 2:
                    return MSG;
                case 3:
                    return IMPRINT;
                default:
                    return null;
            }
        }

        public static e b(int i) {
            e a = a(i);
            if (a != null) {
                return a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        public static e a(String str) {
            return (e) d.get(str);
        }

        private e(short s, String str) {
            this.e = s;
            this.f = str;
        }

        public short a() {
            return this.e;
        }

        public String b() {
            return this.f;
        }
    }

    public /* synthetic */ cl b(int i) {
        return c(i);
    }

    public /* synthetic */ ce p() {
        return a();
    }

    static {
        j.put(dn.class, new b());
        j.put(do.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.RESP_CODE, new cq("resp_code", (byte) 1, new cr((byte) 8)));
        enumMap.put(e.MSG, new cq("msg", (byte) 2, new cr((byte) 11)));
        enumMap.put(e.IMPRINT, new cq(x.N, (byte) 2, new cv((byte) 12, bl.class)));
        d = Collections.unmodifiableMap(enumMap);
        cq.a(bn.class, d);
    }

    public bn() {
        this.l = (byte) 0;
        this.m = new e[]{e.MSG, e.IMPRINT};
    }

    public bn(int i) {
        this();
        this.a = i;
        a(true);
    }

    public bn(bn bnVar) {
        this.l = (byte) 0;
        this.m = new e[]{e.MSG, e.IMPRINT};
        this.l = bnVar.l;
        this.a = bnVar.a;
        if (bnVar.h()) {
            this.b = bnVar.b;
        }
        if (bnVar.k()) {
            this.c = new bl(bnVar.c);
        }
    }

    public bn a() {
        return new bn(this);
    }

    public void b() {
        a(false);
        this.a = 0;
        this.b = null;
        this.c = null;
    }

    public int c() {
        return this.a;
    }

    public bn a(int i) {
        this.a = i;
        a(true);
        return this;
    }

    public void d() {
        this.l = cb.b(this.l, 0);
    }

    public boolean e() {
        return cb.a(this.l, 0);
    }

    public void a(boolean z) {
        this.l = cb.a(this.l, 0, z);
    }

    public String f() {
        return this.b;
    }

    public bn a(String str) {
        this.b = str;
        return this;
    }

    public void g() {
        this.b = null;
    }

    public boolean h() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public bl i() {
        return this.c;
    }

    public bn a(bl blVar) {
        this.c = blVar;
        return this;
    }

    public void j() {
        this.c = null;
    }

    public boolean k() {
        return this.c != null;
    }

    public void c(boolean z) {
        if (!z) {
            this.c = null;
        }
    }

    public e c(int i) {
        return e.a(i);
    }

    public void a(dd ddVar) throws ck {
        ((dm) j.get(ddVar.D())).b().b(ddVar, this);
    }

    public void b(dd ddVar) throws ck {
        ((dm) j.get(ddVar.D())).b().a(ddVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Response(");
        stringBuilder.append("resp_code:");
        stringBuilder.append(this.a);
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("msg:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("imprint:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void l() throws ck {
        if (this.c != null) {
            this.c.m();
        }
    }

    private void a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            b(new cx(new dp((OutputStream) objectOutputStream)));
        } catch (ck e) {
            throw new IOException(e.getMessage());
        }
    }

    private void a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.l = (byte) 0;
            a(new cx(new dp((InputStream) objectInputStream)));
        } catch (ck e) {
            throw new IOException(e.getMessage());
        }
    }
}
