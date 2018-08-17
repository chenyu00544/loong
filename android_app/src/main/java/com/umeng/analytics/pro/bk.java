package com.umeng.analytics.pro;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: IdTracking */
public class bk implements ce<bk, e>, Serializable, Cloneable {
    public static final Map<e, cq> d;
    private static final long e = -5764118265293965743L;
    private static final di f = new di("IdTracking");
    private static final cy g = new cy("snapshots", dk.k, (short) 1);
    private static final cy h = new cy("journals", dk.m, (short) 2);
    private static final cy i = new cy("checksum", (byte) 11, (short) 3);
    private static final Map<Class<? extends dl>, dm> j = new HashMap();
    public Map<String, bj> a;
    public List<bi> b;
    public String c;
    private e[] k;

    /* compiled from: IdTracking */
    private static class a extends dn<bk> {
        private a() {
        }

        public /* synthetic */ void a(dd ddVar, ce ceVar) throws ck {
            b(ddVar, (bk) ceVar);
        }

        public /* synthetic */ void b(dd ddVar, ce ceVar) throws ck {
            a(ddVar, (bk) ceVar);
        }

        public void a(dd ddVar, bk bkVar) throws ck {
            ddVar.j();
            while (true) {
                cy l = ddVar.l();
                if (l.b == (byte) 0) {
                    ddVar.k();
                    bkVar.o();
                    return;
                }
                int i;
                switch (l.c) {
                    case (short) 1:
                        if (l.b != dk.k) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        da n = ddVar.n();
                        bkVar.a = new HashMap(n.c * 2);
                        for (i = 0; i < n.c; i++) {
                            String z = ddVar.z();
                            bj bjVar = new bj();
                            bjVar.a(ddVar);
                            bkVar.a.put(z, bjVar);
                        }
                        ddVar.o();
                        bkVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != dk.m) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        cz p = ddVar.p();
                        bkVar.b = new ArrayList(p.b);
                        for (i = 0; i < p.b; i++) {
                            bi biVar = new bi();
                            biVar.a(ddVar);
                            bkVar.b.add(biVar);
                        }
                        ddVar.q();
                        bkVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        bkVar.c = ddVar.z();
                        bkVar.c(true);
                        break;
                    default:
                        dg.a(ddVar, l.b);
                        break;
                }
                ddVar.m();
            }
        }

        public void b(dd ddVar, bk bkVar) throws ck {
            bkVar.o();
            ddVar.a(bk.f);
            if (bkVar.a != null) {
                ddVar.a(bk.g);
                ddVar.a(new da((byte) 11, (byte) 12, bkVar.a.size()));
                for (Entry entry : bkVar.a.entrySet()) {
                    ddVar.a((String) entry.getKey());
                    ((bj) entry.getValue()).b(ddVar);
                }
                ddVar.e();
                ddVar.c();
            }
            if (bkVar.b != null && bkVar.k()) {
                ddVar.a(bk.h);
                ddVar.a(new cz((byte) 12, bkVar.b.size()));
                for (bi b : bkVar.b) {
                    b.b(ddVar);
                }
                ddVar.f();
                ddVar.c();
            }
            if (bkVar.c != null && bkVar.n()) {
                ddVar.a(bk.i);
                ddVar.a(bkVar.c);
                ddVar.c();
            }
            ddVar.d();
            ddVar.b();
        }
    }

    /* compiled from: IdTracking */
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

    /* compiled from: IdTracking */
    private static class c extends do<bk> {
        private c() {
        }

        public void a(dd ddVar, bk bkVar) throws ck {
            ddVar = (dj) ddVar;
            ddVar.a(bkVar.a.size());
            for (Entry entry : bkVar.a.entrySet()) {
                ddVar.a((String) entry.getKey());
                ((bj) entry.getValue()).b(ddVar);
            }
            BitSet bitSet = new BitSet();
            if (bkVar.k()) {
                bitSet.set(0);
            }
            if (bkVar.n()) {
                bitSet.set(1);
            }
            ddVar.a(bitSet, 2);
            if (bkVar.k()) {
                ddVar.a(bkVar.b.size());
                for (bi b : bkVar.b) {
                    b.b(ddVar);
                }
            }
            if (bkVar.n()) {
                ddVar.a(bkVar.c);
            }
        }

        public void b(dd ddVar, bk bkVar) throws ck {
            int i = 0;
            ddVar = (dj) ddVar;
            da daVar = new da((byte) 11, (byte) 12, ddVar.w());
            bkVar.a = new HashMap(daVar.c * 2);
            for (int i2 = 0; i2 < daVar.c; i2++) {
                String z = ddVar.z();
                bj bjVar = new bj();
                bjVar.a(ddVar);
                bkVar.a.put(z, bjVar);
            }
            bkVar.a(true);
            BitSet b = ddVar.b(2);
            if (b.get(0)) {
                cz czVar = new cz((byte) 12, ddVar.w());
                bkVar.b = new ArrayList(czVar.b);
                while (i < czVar.b) {
                    bi biVar = new bi();
                    biVar.a(ddVar);
                    bkVar.b.add(biVar);
                    i++;
                }
                bkVar.b(true);
            }
            if (b.get(1)) {
                bkVar.c = ddVar.z();
                bkVar.c(true);
            }
        }
    }

    /* compiled from: IdTracking */
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

    /* compiled from: IdTracking */
    public enum e implements cl {
        SNAPSHOTS((short) 1, "snapshots"),
        JOURNALS((short) 2, "journals"),
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
                    return SNAPSHOTS;
                case 2:
                    return JOURNALS;
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
        return a(i);
    }

    public /* synthetic */ ce p() {
        return a();
    }

    static {
        j.put(dn.class, new b());
        j.put(do.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.SNAPSHOTS, new cq("snapshots", (byte) 1, new ct(dk.k, new cr((byte) 11), new cv((byte) 12, bj.class))));
        enumMap.put(e.JOURNALS, new cq("journals", (byte) 2, new cs(dk.m, new cv((byte) 12, bi.class))));
        enumMap.put(e.CHECKSUM, new cq("checksum", (byte) 2, new cr((byte) 11)));
        d = Collections.unmodifiableMap(enumMap);
        cq.a(bk.class, d);
    }

    public bk() {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public bk(Map<String, bj> map) {
        this();
        this.a = map;
    }

    public bk(bk bkVar) {
        this.k = new e[]{e.JOURNALS, e.CHECKSUM};
        if (bkVar.f()) {
            Map hashMap = new HashMap();
            for (Entry entry : bkVar.a.entrySet()) {
                hashMap.put((String) entry.getKey(), new bj((bj) entry.getValue()));
            }
            this.a = hashMap;
        }
        if (bkVar.k()) {
            List arrayList = new ArrayList();
            for (bi biVar : bkVar.b) {
                arrayList.add(new bi(biVar));
            }
            this.b = arrayList;
        }
        if (bkVar.n()) {
            this.c = bkVar.c;
        }
    }

    public bk a() {
        return new bk(this);
    }

    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
    }

    public int c() {
        return this.a == null ? 0 : this.a.size();
    }

    public void a(String str, bj bjVar) {
        if (this.a == null) {
            this.a = new HashMap();
        }
        this.a.put(str, bjVar);
    }

    public Map<String, bj> d() {
        return this.a;
    }

    public bk a(Map<String, bj> map) {
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
        return this.b == null ? 0 : this.b.size();
    }

    public Iterator<bi> h() {
        return this.b == null ? null : this.b.iterator();
    }

    public void a(bi biVar) {
        if (this.b == null) {
            this.b = new ArrayList();
        }
        this.b.add(biVar);
    }

    public List<bi> i() {
        return this.b;
    }

    public bk a(List<bi> list) {
        this.b = list;
        return this;
    }

    public void j() {
        this.b = null;
    }

    public boolean k() {
        return this.b != null;
    }

    public void b(boolean z) {
        if (!z) {
            this.b = null;
        }
    }

    public String l() {
        return this.c;
    }

    public bk a(String str) {
        this.c = str;
        return this;
    }

    public void m() {
        this.c = null;
    }

    public boolean n() {
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
        StringBuilder stringBuilder = new StringBuilder("IdTracking(");
        stringBuilder.append("snapshots:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        if (k()) {
            stringBuilder.append(", ");
            stringBuilder.append("journals:");
            if (this.b == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.b);
            }
        }
        if (n()) {
            stringBuilder.append(", ");
            stringBuilder.append("checksum:");
            if (this.c == null) {
                stringBuilder.append("null");
            } else {
                stringBuilder.append(this.c);
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void o() throws ck {
        if (this.a == null) {
            throw new de("Required field 'snapshots' was not present! Struct: " + toString());
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
            a(new cx(new dp((InputStream) objectInputStream)));
        } catch (ck e) {
            throw new IOException(e.getMessage());
        }
    }
}
