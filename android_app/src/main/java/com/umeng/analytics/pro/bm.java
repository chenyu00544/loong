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

/* compiled from: ImprintValue */
public class bm implements ce<bm, e>, Serializable, Cloneable {
    public static final Map<e, cq> d;
    private static final long e = 7501688097813630241L;
    private static final di f = new di("ImprintValue");
    private static final cy g = new cy("value", (byte) 11, (short) 1);
    private static final cy h = new cy("ts", (byte) 10, (short) 2);
    private static final cy i = new cy("guid", (byte) 11, (short) 3);
    private static final Map<Class<? extends dl>, dm> j = new HashMap();
    private static final int k = 0;
    public String a;
    public long b;
    public String c;
    private byte l;
    private e[] m;

    /* compiled from: ImprintValue */
    private static class a extends dn<bm> {
        private a() {
        }

        public /* synthetic */ void a(dd ddVar, ce ceVar) throws ck {
            b(ddVar, (bm) ceVar);
        }

        public /* synthetic */ void b(dd ddVar, ce ceVar) throws ck {
            a(ddVar, (bm) ceVar);
        }

        public void a(dd ddVar, bm bmVar) throws ck {
            ddVar.j();
            while (true) {
                cy l = ddVar.l();
                if (l.b == (byte) 0) {
                    ddVar.k();
                    if (bmVar.h()) {
                        bmVar.l();
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
                        bmVar.a = ddVar.z();
                        bmVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 10) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        bmVar.b = ddVar.x();
                        bmVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        bmVar.c = ddVar.z();
                        bmVar.c(true);
                        break;
                    default:
                        dg.a(ddVar, l.b);
                        break;
                }
                ddVar.m();
            }
        }

        public void b(dd ddVar, bm bmVar) throws ck {
            bmVar.l();
            ddVar.a(bm.f);
            if (bmVar.a != null && bmVar.e()) {
                ddVar.a(bm.g);
                ddVar.a(bmVar.a);
                ddVar.c();
            }
            ddVar.a(bm.h);
            ddVar.a(bmVar.b);
            ddVar.c();
            if (bmVar.c != null) {
                ddVar.a(bm.i);
                ddVar.a(bmVar.c);
                ddVar.c();
            }
            ddVar.d();
            ddVar.b();
        }
    }

    /* compiled from: ImprintValue */
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

    /* compiled from: ImprintValue */
    private static class c extends do<bm> {
        private c() {
        }

        public void a(dd ddVar, bm bmVar) throws ck {
            dj djVar = (dj) ddVar;
            djVar.a(bmVar.b);
            djVar.a(bmVar.c);
            BitSet bitSet = new BitSet();
            if (bmVar.e()) {
                bitSet.set(0);
            }
            djVar.a(bitSet, 1);
            if (bmVar.e()) {
                djVar.a(bmVar.a);
            }
        }

        public void b(dd ddVar, bm bmVar) throws ck {
            dj djVar = (dj) ddVar;
            bmVar.b = djVar.x();
            bmVar.b(true);
            bmVar.c = djVar.z();
            bmVar.c(true);
            if (djVar.b(1).get(0)) {
                bmVar.a = djVar.z();
                bmVar.a(true);
            }
        }
    }

    /* compiled from: ImprintValue */
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

    /* compiled from: ImprintValue */
    public enum e implements cl {
        VALUE((short) 1, "value"),
        TS((short) 2, "ts"),
        GUID((short) 3, "guid");
        
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
                    return VALUE;
                case 2:
                    return TS;
                case 3:
                    return GUID;
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
        return a(i);
    }

    public /* synthetic */ ce p() {
        return a();
    }

    static {
        j.put(dn.class, new b());
        j.put(do.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.VALUE, new cq("value", (byte) 2, new cr((byte) 11)));
        enumMap.put(e.TS, new cq("ts", (byte) 1, new cr((byte) 10)));
        enumMap.put(e.GUID, new cq("guid", (byte) 1, new cr((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        cq.a(bm.class, d);
    }

    public bm() {
        this.l = (byte) 0;
        this.m = new e[]{e.VALUE};
    }

    public bm(long j, String str) {
        this();
        this.b = j;
        b(true);
        this.c = str;
    }

    public bm(bm bmVar) {
        this.l = (byte) 0;
        this.m = new e[]{e.VALUE};
        this.l = bmVar.l;
        if (bmVar.e()) {
            this.a = bmVar.a;
        }
        this.b = bmVar.b;
        if (bmVar.k()) {
            this.c = bmVar.c;
        }
    }

    public bm a() {
        return new bm(this);
    }

    public void b() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public String c() {
        return this.a;
    }

    public bm a(String str) {
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

    public bm a(long j) {
        this.b = j;
        b(true);
        return this;
    }

    public void g() {
        this.l = cb.b(this.l, 0);
    }

    public boolean h() {
        return cb.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = cb.a(this.l, 0, z);
    }

    public String i() {
        return this.c;
    }

    public bm b(String str) {
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

    public e a(int i) {
        return e.a(i);
    }

    public void a(dd ddVar) throws ck {
        ((dm) j.get(ddVar.D())).b().b(ddVar, this);
    }

    public void b(dd ddVar) throws ck {
        ((dm) j.get(ddVar.D())).b().a(ddVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ImprintValue(");
        Object obj = 1;
        if (e()) {
            stringBuilder.append("value:");
            if (this.a == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.a);
            }
            obj = null;
        }
        if (obj == null) {
            stringBuilder.append(", ");
        }
        stringBuilder.append("ts:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void l() throws ck {
        if (this.c == null) {
            throw new de("Required field 'guid' was not present! Struct: " + toString());
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
