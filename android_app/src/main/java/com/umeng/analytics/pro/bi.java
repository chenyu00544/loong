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

/* compiled from: IdJournal */
public class bi implements ce<bi, e>, Serializable, Cloneable {
    public static final Map<e, cq> e;
    private static final long f = 9132678615281394583L;
    private static final di g = new di("IdJournal");
    private static final cy h = new cy(anet.channel.strategy.dispatch.c.DOMAIN, (byte) 11, (short) 1);
    private static final cy i = new cy("old_id", (byte) 11, (short) 2);
    private static final cy j = new cy("new_id", (byte) 11, (short) 3);
    private static final cy k = new cy("ts", (byte) 10, (short) 4);
    private static final Map<Class<? extends dl>, dm> l = new HashMap();
    private static final int m = 0;
    public String a;
    public String b;
    public String c;
    public long d;
    private byte n;
    private e[] o;

    /* compiled from: IdJournal */
    private static class a extends dn<bi> {
        private a() {
        }

        public /* synthetic */ void a(dd ddVar, ce ceVar) throws ck {
            b(ddVar, (bi) ceVar);
        }

        public /* synthetic */ void b(dd ddVar, ce ceVar) throws ck {
            a(ddVar, (bi) ceVar);
        }

        public void a(dd ddVar, bi biVar) throws ck {
            ddVar.j();
            while (true) {
                cy l = ddVar.l();
                if (l.b == (byte) 0) {
                    ddVar.k();
                    if (biVar.n()) {
                        biVar.o();
                        return;
                    }
                    throw new de("Required field 'ts' was not found in serialized data! Struct: " + toString());
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        biVar.a = ddVar.z();
                        biVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        biVar.b = ddVar.z();
                        biVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        biVar.c = ddVar.z();
                        biVar.c(true);
                        break;
                    case (short) 4:
                        if (l.b != (byte) 10) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        biVar.d = ddVar.x();
                        biVar.d(true);
                        break;
                    default:
                        dg.a(ddVar, l.b);
                        break;
                }
                ddVar.m();
            }
        }

        public void b(dd ddVar, bi biVar) throws ck {
            biVar.o();
            ddVar.a(bi.g);
            if (biVar.a != null) {
                ddVar.a(bi.h);
                ddVar.a(biVar.a);
                ddVar.c();
            }
            if (biVar.b != null && biVar.h()) {
                ddVar.a(bi.i);
                ddVar.a(biVar.b);
                ddVar.c();
            }
            if (biVar.c != null) {
                ddVar.a(bi.j);
                ddVar.a(biVar.c);
                ddVar.c();
            }
            ddVar.a(bi.k);
            ddVar.a(biVar.d);
            ddVar.c();
            ddVar.d();
            ddVar.b();
        }
    }

    /* compiled from: IdJournal */
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

    /* compiled from: IdJournal */
    private static class c extends do<bi> {
        private c() {
        }

        public void a(dd ddVar, bi biVar) throws ck {
            dj djVar = (dj) ddVar;
            djVar.a(biVar.a);
            djVar.a(biVar.c);
            djVar.a(biVar.d);
            BitSet bitSet = new BitSet();
            if (biVar.h()) {
                bitSet.set(0);
            }
            djVar.a(bitSet, 1);
            if (biVar.h()) {
                djVar.a(biVar.b);
            }
        }

        public void b(dd ddVar, bi biVar) throws ck {
            dj djVar = (dj) ddVar;
            biVar.a = djVar.z();
            biVar.a(true);
            biVar.c = djVar.z();
            biVar.c(true);
            biVar.d = djVar.x();
            biVar.d(true);
            if (djVar.b(1).get(0)) {
                biVar.b = djVar.z();
                biVar.b(true);
            }
        }
    }

    /* compiled from: IdJournal */
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

    /* compiled from: IdJournal */
    public enum e implements cl {
        DOMAIN((short) 1, anet.channel.strategy.dispatch.c.DOMAIN),
        OLD_ID((short) 2, "old_id"),
        NEW_ID((short) 3, "new_id"),
        TS((short) 4, "ts");
        
        private static final Map<String, e> e = null;
        private final short f;
        private final String g;

        static {
            e = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                e.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return DOMAIN;
                case 2:
                    return OLD_ID;
                case 3:
                    return NEW_ID;
                case 4:
                    return TS;
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
            return (e) e.get(str);
        }

        private e(short s, String str) {
            this.f = s;
            this.g = str;
        }

        public short a() {
            return this.f;
        }

        public String b() {
            return this.g;
        }
    }

    public /* synthetic */ cl b(int i) {
        return a(i);
    }

    public /* synthetic */ ce p() {
        return a();
    }

    static {
        l.put(dn.class, new b());
        l.put(do.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.DOMAIN, new cq(anet.channel.strategy.dispatch.c.DOMAIN, (byte) 1, new cr((byte) 11)));
        enumMap.put(e.OLD_ID, new cq("old_id", (byte) 2, new cr((byte) 11)));
        enumMap.put(e.NEW_ID, new cq("new_id", (byte) 1, new cr((byte) 11)));
        enumMap.put(e.TS, new cq("ts", (byte) 1, new cr((byte) 10)));
        e = Collections.unmodifiableMap(enumMap);
        cq.a(bi.class, e);
    }

    public bi() {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
    }

    public bi(String str, String str2, long j) {
        this();
        this.a = str;
        this.c = str2;
        this.d = j;
        d(true);
    }

    public bi(bi biVar) {
        this.n = (byte) 0;
        this.o = new e[]{e.OLD_ID};
        this.n = biVar.n;
        if (biVar.e()) {
            this.a = biVar.a;
        }
        if (biVar.h()) {
            this.b = biVar.b;
        }
        if (biVar.k()) {
            this.c = biVar.c;
        }
        this.d = biVar.d;
    }

    public bi a() {
        return new bi(this);
    }

    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
    }

    public String c() {
        return this.a;
    }

    public bi a(String str) {
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

    public String f() {
        return this.b;
    }

    public bi b(String str) {
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

    public String i() {
        return this.c;
    }

    public bi c(String str) {
        this.c = str;
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

    public long l() {
        return this.d;
    }

    public bi a(long j) {
        this.d = j;
        d(true);
        return this;
    }

    public void m() {
        this.n = cb.b(this.n, 0);
    }

    public boolean n() {
        return cb.a(this.n, 0);
    }

    public void d(boolean z) {
        this.n = cb.a(this.n, 0, z);
    }

    public e a(int i) {
        return e.a(i);
    }

    public void a(dd ddVar) throws ck {
        ((dm) l.get(ddVar.D())).b().b(ddVar, this);
    }

    public void b(dd ddVar) throws ck {
        ((dm) l.get(ddVar.D())).b().a(ddVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("IdJournal(");
        stringBuilder.append("domain:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (h()) {
            stringBuilder.append(", ");
            stringBuilder.append("old_id:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        stringBuilder.append(", ");
        stringBuilder.append("new_id:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("ts:");
        stringBuilder.append(this.d);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void o() throws ck {
        if (this.a == null) {
            throw new de("Required field 'domain' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new de("Required field 'new_id' was not present! Struct: " + toString());
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
            this.n = (byte) 0;
            a(new cx(new dp((InputStream) objectInputStream)));
        } catch (ck e) {
            throw new IOException(e.getMessage());
        }
    }
}
