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
import java.util.Map.Entry;

/* compiled from: Imprint */
public class bl implements ce<bl, e>, Serializable, Cloneable {
    public static final Map<e, cq> d;
    private static final long e = 2846460275012375038L;
    private static final di f = new di("Imprint");
    private static final cy g = new cy("property", dk.k, (short) 1);
    private static final cy h = new cy("version", (byte) 8, (short) 2);
    private static final cy i = new cy("checksum", (byte) 11, (short) 3);
    private static final Map<Class<? extends dl>, dm> j = new HashMap();
    private static final int k = 0;
    public Map<String, bm> a;
    public int b;
    public String c;
    private byte l;

    /* compiled from: Imprint */
    private static class a extends dn<bl> {
        private a() {
        }

        public /* synthetic */ void a(dd ddVar, ce ceVar) throws ck {
            b(ddVar, (bl) ceVar);
        }

        public /* synthetic */ void b(dd ddVar, ce ceVar) throws ck {
            a(ddVar, (bl) ceVar);
        }

        public void a(dd ddVar, bl blVar) throws ck {
            ddVar.j();
            while (true) {
                cy l = ddVar.l();
                if (l.b == (byte) 0) {
                    ddVar.k();
                    if (blVar.i()) {
                        blVar.m();
                        return;
                    }
                    throw new de("Required field 'version' was not found in serialized data! Struct: " + toString());
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != dk.k) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        da n = ddVar.n();
                        blVar.a = new HashMap(n.c * 2);
                        for (int i = 0; i < n.c; i++) {
                            String z = ddVar.z();
                            bm bmVar = new bm();
                            bmVar.a(ddVar);
                            blVar.a.put(z, bmVar);
                        }
                        ddVar.o();
                        blVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 8) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        blVar.b = ddVar.w();
                        blVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        blVar.c = ddVar.z();
                        blVar.c(true);
                        break;
                    default:
                        dg.a(ddVar, l.b);
                        break;
                }
                ddVar.m();
            }
        }

        public void b(dd ddVar, bl blVar) throws ck {
            blVar.m();
            ddVar.a(bl.f);
            if (blVar.a != null) {
                ddVar.a(bl.g);
                ddVar.a(new da((byte) 11, (byte) 12, blVar.a.size()));
                for (Entry entry : blVar.a.entrySet()) {
                    ddVar.a((String) entry.getKey());
                    ((bm) entry.getValue()).b(ddVar);
                }
                ddVar.e();
                ddVar.c();
            }
            ddVar.a(bl.h);
            ddVar.a(blVar.b);
            ddVar.c();
            if (blVar.c != null) {
                ddVar.a(bl.i);
                ddVar.a(blVar.c);
                ddVar.c();
            }
            ddVar.d();
            ddVar.b();
        }
    }

    /* compiled from: Imprint */
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

    /* compiled from: Imprint */
    private static class c extends do<bl> {
        private c() {
        }

        public void a(dd ddVar, bl blVar) throws ck {
            ddVar = (dj) ddVar;
            ddVar.a(blVar.a.size());
            for (Entry entry : blVar.a.entrySet()) {
                ddVar.a((String) entry.getKey());
                ((bm) entry.getValue()).b(ddVar);
            }
            ddVar.a(blVar.b);
            ddVar.a(blVar.c);
        }

        public void b(dd ddVar, bl blVar) throws ck {
            ddVar = (dj) ddVar;
            da daVar = new da((byte) 11, (byte) 12, ddVar.w());
            blVar.a = new HashMap(daVar.c * 2);
            for (int i = 0; i < daVar.c; i++) {
                String z = ddVar.z();
                bm bmVar = new bm();
                bmVar.a(ddVar);
                blVar.a.put(z, bmVar);
            }
            blVar.a(true);
            blVar.b = ddVar.w();
            blVar.b(true);
            blVar.c = ddVar.z();
            blVar.c(true);
        }
    }

    /* compiled from: Imprint */
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

    /* compiled from: Imprint */
    public enum e implements cl {
        PROPERTY((short) 1, "property"),
        VERSION((short) 2, "version"),
        CHECKSUM((short) 3, "checksum");
        
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
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
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
        enumMap.put(e.PROPERTY, new cq("property", (byte) 1, new ct(dk.k, new cr((byte) 11), new cv((byte) 12, bm.class))));
        enumMap.put(e.VERSION, new cq("version", (byte) 1, new cr((byte) 8)));
        enumMap.put(e.CHECKSUM, new cq("checksum", (byte) 1, new cr((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        cq.a(bl.class, d);
    }

    public bl() {
        this.l = (byte) 0;
    }

    public bl(Map<String, bm> map, int i, String str) {
        this();
        this.a = map;
        this.b = i;
        b(true);
        this.c = str;
    }

    public bl(bl blVar) {
        this.l = (byte) 0;
        this.l = blVar.l;
        if (blVar.f()) {
            Map hashMap = new HashMap();
            for (Entry entry : blVar.a.entrySet()) {
                hashMap.put((String) entry.getKey(), new bm((bm) entry.getValue()));
            }
            this.a = hashMap;
        }
        this.b = blVar.b;
        if (blVar.l()) {
            this.c = blVar.c;
        }
    }

    public bl a() {
        return new bl(this);
    }

    public void b() {
        this.a = null;
        b(false);
        this.b = 0;
        this.c = null;
    }

    public int c() {
        return this.a == null ? 0 : this.a.size();
    }

    public void a(String str, bm bmVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, bmVar);
    }

    public Map<String, bm> d() {
        return this.a;
    }

    public bl a(Map<String, bm> map) {
        this.a = map;
        return this;
    }

    public void e() {
        this.a = null;
    }

    public boolean f() {
        return this.a != null;
    }

    public void a(boolean z) {
        if (!z) {
            this.a = null;
        }
    }

    public int g() {
        return this.b;
    }

    public bl a(int i) {
        this.b = i;
        b(true);
        return this;
    }

    public void h() {
        this.l = cb.b(this.l, 0);
    }

    public boolean i() {
        return cb.a(this.l, 0);
    }

    public void b(boolean z) {
        this.l = cb.a(this.l, 0, z);
    }

    public String j() {
        return this.c;
    }

    public bl a(String str) {
        this.c = str;
        return this;
    }

    public void k() {
        this.c = null;
    }

    public boolean l() {
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
        StringBuilder stringBuilder = new StringBuilder("Imprint(");
        stringBuilder.append("property:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("version:");
        stringBuilder.append(this.b);
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void m() throws ck {
        if (this.a == null) {
            throw new de("Required field 'property' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new de("Required field 'checksum' was not present! Struct: " + toString());
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
