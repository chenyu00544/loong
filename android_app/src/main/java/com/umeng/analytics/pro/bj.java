package com.umeng.analytics.pro;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: IdSnapshot */
public class bj implements ce<bj, e>, Serializable, Cloneable {
    public static final Map<e, cq> d;
    private static final long e = -6496538196005191531L;
    private static final di f = new di("IdSnapshot");
    private static final cy g = new cy("identity", (byte) 11, (short) 1);
    private static final cy h = new cy("ts", (byte) 10, (short) 2);
    private static final cy i = new cy("version", (byte) 8, (short) 3);
    private static final Map<Class<? extends dl>, dm> j = new HashMap();
    private static final int k = 0;
    private static final int l = 1;
    public String a;
    public long b;
    public int c;
    private byte m;

    /* compiled from: IdSnapshot */
    private static class a extends dn<bj> {
        private a() {
        }

        public /* synthetic */ void a(dd ddVar, ce ceVar) throws ck {
            b(ddVar, (bj) ceVar);
        }

        public /* synthetic */ void b(dd ddVar, ce ceVar) throws ck {
            a(ddVar, (bj) ceVar);
        }

        public void a(dd ddVar, bj bjVar) throws ck {
            ddVar.j();
            while (true) {
                cy l = ddVar.l();
                if (l.b == (byte) 0) {
                    ddVar.k();
                    if (!bjVar.h()) {
                        throw new de("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    } else if (bjVar.k()) {
                        bjVar.l();
                        return;
                    } else {
                        throw new de("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        bjVar.a = ddVar.z();
                        bjVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 10) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        bjVar.b = ddVar.x();
                        bjVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 8) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        bjVar.c = ddVar.w();
                        bjVar.c(true);
                        break;
                    default:
                        dg.a(ddVar, l.b);
                        break;
                }
                ddVar.m();
            }
        }

        public void b(dd ddVar, bj bjVar) throws ck {
            bjVar.l();
            ddVar.a(bj.f);
            if (bjVar.a != null) {
                ddVar.a(bj.g);
                ddVar.a(bjVar.a);
                ddVar.c();
            }
            ddVar.a(bj.h);
            ddVar.a(bjVar.b);
            ddVar.c();
            ddVar.a(bj.i);
            ddVar.a(bjVar.c);
            ddVar.c();
            ddVar.d();
            ddVar.b();
        }
    }

    /* compiled from: IdSnapshot */
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

    /* compiled from: IdSnapshot */
    private static class c extends do<bj> {
        private c() {
        }

        public void a(dd ddVar, bj bjVar) throws ck {
            dj djVar = (dj) ddVar;
            djVar.a(bjVar.a);
            djVar.a(bjVar.b);
            djVar.a(bjVar.c);
        }

        public void b(dd ddVar, bj bjVar) throws ck {
            dj djVar = (dj) ddVar;
            bjVar.a = djVar.z();
            bjVar.a(true);
            bjVar.b = djVar.x();
            bjVar.b(true);
            bjVar.c = djVar.w();
            bjVar.c(true);
        }
    }

    /* compiled from: IdSnapshot */
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

    /* compiled from: IdSnapshot */
    public enum e implements cl {
        IDENTITY((short) 1, "identity"),
        TS((short) 2, "ts"),
        VERSION((short) 3, "version");
        
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
                    return IDENTITY;
                case 2:
                    return TS;
                case 3:
                    return VERSION;
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
        enumMap.put(e.IDENTITY, new cq("identity", (byte) 1, new cr((byte) 11)));
        enumMap.put(e.TS, new cq("ts", (byte) 1, new cr((byte) 10)));
        enumMap.put(e.VERSION, new cq("version", (byte) 1, new cr((byte) 8)));
        d = Collections.unmodifiableMap(enumMap);
        cq.a(bj.class, d);
    }

    public bj() {
        this.m = (byte) 0;
    }

    public bj(String str, long j, int i) {
        this();
        this.a = str;
        this.b = j;
        b(true);
        this.c = i;
        c(true);
    }

    public bj(bj bjVar) {
        this.m = (byte) 0;
        this.m = bjVar.m;
        if (bjVar.e()) {
            this.a = bjVar.a;
        }
        this.b = bjVar.b;
        this.c = bjVar.c;
    }

    public bj a() {
        return new bj(this);
    }

    public void b() {
        this.a = null;
        b(false);
        this.b = 0;
        c(false);
        this.c = 0;
    }

    public String c() {
        return this.a;
    }

    public bj a(String str) {
        this.a = str;
        return this;
    }

    public void d() {
        this.a = null;
    }

    public boolean e() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public long f() {
        return this.b;
    }

    public bj a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public void g() {
        this.m = cb.b(this.m, 0);
    }

    public boolean h() {
        return cb.a(this.m, 0);
    }

    public void b(boolean z) {
        this.m = cb.a(this.m, 0, z);
    }

    public int i() {
        return this.c;
    }

    public bj a(int i) {
        this.c = i;
        c(true);
        return this;
    }

    public void j() {
        this.m = cb.b(this.m, 1);
    }

    public boolean k() {
        return cb.a(this.m, 1);
    }

    public void c(boolean z) {
        this.m = cb.a(this.m, 1, z);
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
        StringBuilder stringBuilder = new StringBuilder("IdSnapshot(");
        stringBuilder.append("identity:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.c);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void l() throws ck {
        if (this.a == null) {
            throw new de("Required field 'identity' was not present! Struct: " + toString());
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
            this.m = (byte) 0;
            a(new cx(new dp((InputStream) objectInputStream)));
        } catch (ck e) {
            throw new IOException(e.getMessage());
        }
    }
}
