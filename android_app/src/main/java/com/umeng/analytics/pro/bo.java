package com.umeng.analytics.pro;

import com.tencent.open.GameAppOperation;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* compiled from: UMEnvelope */
public class bo implements ce<bo, e>, Serializable, Cloneable {
    private static final int A = 2;
    private static final int B = 3;
    public static final Map<e, cq> k;
    private static final long l = 420342210744516016L;
    private static final di m = new di("UMEnvelope");
    private static final cy n = new cy("version", (byte) 11, (short) 1);
    private static final cy o = new cy("address", (byte) 11, (short) 2);
    private static final cy p = new cy(GameAppOperation.GAME_SIGNATURE, (byte) 11, (short) 3);
    private static final cy q = new cy("serial_num", (byte) 8, (short) 4);
    private static final cy r = new cy("ts_secs", (byte) 8, (short) 5);
    private static final cy s = new cy("length", (byte) 8, (short) 6);
    private static final cy t = new cy("entity", (byte) 11, (short) 7);
    private static final cy u = new cy("guid", (byte) 11, (short) 8);
    private static final cy v = new cy("checksum", (byte) 11, (short) 9);
    private static final cy w = new cy("codex", (byte) 8, (short) 10);
    private static final Map<Class<? extends dl>, dm> x = new HashMap();
    private static final int y = 0;
    private static final int z = 1;
    private byte C;
    private e[] D;
    public String a;
    public String b;
    public String c;
    public int d;
    public int e;
    public int f;
    public ByteBuffer g;
    public String h;
    public String i;
    public int j;

    /* compiled from: UMEnvelope */
    private static class a extends dn<bo> {
        private a() {
        }

        public /* synthetic */ void a(dd ddVar, ce ceVar) throws ck {
            b(ddVar, (bo) ceVar);
        }

        public /* synthetic */ void b(dd ddVar, ce ceVar) throws ck {
            a(ddVar, (bo) ceVar);
        }

        public void a(dd ddVar, bo boVar) throws ck {
            ddVar.j();
            while (true) {
                cy l = ddVar.l();
                if (l.b == (byte) 0) {
                    ddVar.k();
                    if (!boVar.n()) {
                        throw new de("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    } else if (!boVar.r()) {
                        throw new de("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    } else if (boVar.u()) {
                        boVar.I();
                        return;
                    } else {
                        throw new de("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                }
                switch (l.c) {
                    case (short) 1:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        boVar.a = ddVar.z();
                        boVar.a(true);
                        break;
                    case (short) 2:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        boVar.b = ddVar.z();
                        boVar.b(true);
                        break;
                    case (short) 3:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        boVar.c = ddVar.z();
                        boVar.c(true);
                        break;
                    case (short) 4:
                        if (l.b != (byte) 8) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        boVar.d = ddVar.w();
                        boVar.d(true);
                        break;
                    case (short) 5:
                        if (l.b != (byte) 8) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        boVar.e = ddVar.w();
                        boVar.e(true);
                        break;
                    case (short) 6:
                        if (l.b != (byte) 8) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        boVar.f = ddVar.w();
                        boVar.f(true);
                        break;
                    case (short) 7:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        boVar.g = ddVar.A();
                        boVar.g(true);
                        break;
                    case (short) 8:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        boVar.h = ddVar.z();
                        boVar.h(true);
                        break;
                    case (short) 9:
                        if (l.b != (byte) 11) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        boVar.i = ddVar.z();
                        boVar.i(true);
                        break;
                    case (short) 10:
                        if (l.b != (byte) 8) {
                            dg.a(ddVar, l.b);
                            break;
                        }
                        boVar.j = ddVar.w();
                        boVar.j(true);
                        break;
                    default:
                        dg.a(ddVar, l.b);
                        break;
                }
                ddVar.m();
            }
        }

        public void b(dd ddVar, bo boVar) throws ck {
            boVar.I();
            ddVar.a(bo.m);
            if (boVar.a != null) {
                ddVar.a(bo.n);
                ddVar.a(boVar.a);
                ddVar.c();
            }
            if (boVar.b != null) {
                ddVar.a(bo.o);
                ddVar.a(boVar.b);
                ddVar.c();
            }
            if (boVar.c != null) {
                ddVar.a(bo.p);
                ddVar.a(boVar.c);
                ddVar.c();
            }
            ddVar.a(bo.q);
            ddVar.a(boVar.d);
            ddVar.c();
            ddVar.a(bo.r);
            ddVar.a(boVar.e);
            ddVar.c();
            ddVar.a(bo.s);
            ddVar.a(boVar.f);
            ddVar.c();
            if (boVar.g != null) {
                ddVar.a(bo.t);
                ddVar.a(boVar.g);
                ddVar.c();
            }
            if (boVar.h != null) {
                ddVar.a(bo.u);
                ddVar.a(boVar.h);
                ddVar.c();
            }
            if (boVar.i != null) {
                ddVar.a(bo.v);
                ddVar.a(boVar.i);
                ddVar.c();
            }
            if (boVar.H()) {
                ddVar.a(bo.w);
                ddVar.a(boVar.j);
                ddVar.c();
            }
            ddVar.d();
            ddVar.b();
        }
    }

    /* compiled from: UMEnvelope */
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

    /* compiled from: UMEnvelope */
    private static class c extends do<bo> {
        private c() {
        }

        public void a(dd ddVar, bo boVar) throws ck {
            dj djVar = (dj) ddVar;
            djVar.a(boVar.a);
            djVar.a(boVar.b);
            djVar.a(boVar.c);
            djVar.a(boVar.d);
            djVar.a(boVar.e);
            djVar.a(boVar.f);
            djVar.a(boVar.g);
            djVar.a(boVar.h);
            djVar.a(boVar.i);
            BitSet bitSet = new BitSet();
            if (boVar.H()) {
                bitSet.set(0);
            }
            djVar.a(bitSet, 1);
            if (boVar.H()) {
                djVar.a(boVar.j);
            }
        }

        public void b(dd ddVar, bo boVar) throws ck {
            dj djVar = (dj) ddVar;
            boVar.a = djVar.z();
            boVar.a(true);
            boVar.b = djVar.z();
            boVar.b(true);
            boVar.c = djVar.z();
            boVar.c(true);
            boVar.d = djVar.w();
            boVar.d(true);
            boVar.e = djVar.w();
            boVar.e(true);
            boVar.f = djVar.w();
            boVar.f(true);
            boVar.g = djVar.A();
            boVar.g(true);
            boVar.h = djVar.z();
            boVar.h(true);
            boVar.i = djVar.z();
            boVar.i(true);
            if (djVar.b(1).get(0)) {
                boVar.j = djVar.w();
                boVar.j(true);
            }
        }
    }

    /* compiled from: UMEnvelope */
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

    /* compiled from: UMEnvelope */
    public enum e implements cl {
        VERSION((short) 1, "version"),
        ADDRESS((short) 2, "address"),
        SIGNATURE((short) 3, GameAppOperation.GAME_SIGNATURE),
        SERIAL_NUM((short) 4, "serial_num"),
        TS_SECS((short) 5, "ts_secs"),
        LENGTH((short) 6, "length"),
        ENTITY((short) 7, "entity"),
        GUID((short) 8, "guid"),
        CHECKSUM((short) 9, "checksum"),
        CODEX((short) 10, "codex");
        
        private static final Map<String, e> k = null;
        private final short l;
        private final String m;

        static {
            k = new HashMap();
            Iterator it = EnumSet.allOf(e.class).iterator();
            while (it.hasNext()) {
                e eVar = (e) it.next();
                k.put(eVar.b(), eVar);
            }
        }

        public static e a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
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
            return (e) k.get(str);
        }

        private e(short s, String str) {
            this.l = s;
            this.m = str;
        }

        public short a() {
            return this.l;
        }

        public String b() {
            return this.m;
        }
    }

    public /* synthetic */ cl b(int i) {
        return f(i);
    }

    public /* synthetic */ ce p() {
        return a();
    }

    static {
        x.put(dn.class, new b());
        x.put(do.class, new d());
        Map enumMap = new EnumMap(e.class);
        enumMap.put(e.VERSION, new cq("version", (byte) 1, new cr((byte) 11)));
        enumMap.put(e.ADDRESS, new cq("address", (byte) 1, new cr((byte) 11)));
        enumMap.put(e.SIGNATURE, new cq(GameAppOperation.GAME_SIGNATURE, (byte) 1, new cr((byte) 11)));
        enumMap.put(e.SERIAL_NUM, new cq("serial_num", (byte) 1, new cr((byte) 8)));
        enumMap.put(e.TS_SECS, new cq("ts_secs", (byte) 1, new cr((byte) 8)));
        enumMap.put(e.LENGTH, new cq("length", (byte) 1, new cr((byte) 8)));
        enumMap.put(e.ENTITY, new cq("entity", (byte) 1, new cr((byte) 11, true)));
        enumMap.put(e.GUID, new cq("guid", (byte) 1, new cr((byte) 11)));
        enumMap.put(e.CHECKSUM, new cq("checksum", (byte) 1, new cr((byte) 11)));
        enumMap.put(e.CODEX, new cq("codex", (byte) 2, new cr((byte) 8)));
        k = Collections.unmodifiableMap(enumMap);
        cq.a(bo.class, k);
    }

    public bo() {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
    }

    public bo(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = i;
        d(true);
        this.e = i2;
        e(true);
        this.f = i3;
        f(true);
        this.g = byteBuffer;
        this.h = str4;
        this.i = str5;
    }

    public bo(bo boVar) {
        this.C = (byte) 0;
        this.D = new e[]{e.CODEX};
        this.C = boVar.C;
        if (boVar.e()) {
            this.a = boVar.a;
        }
        if (boVar.h()) {
            this.b = boVar.b;
        }
        if (boVar.k()) {
            this.c = boVar.c;
        }
        this.d = boVar.d;
        this.e = boVar.e;
        this.f = boVar.f;
        if (boVar.y()) {
            this.g = cf.d(boVar.g);
        }
        if (boVar.B()) {
            this.h = boVar.h;
        }
        if (boVar.E()) {
            this.i = boVar.i;
        }
        this.j = boVar.j;
    }

    public bo a() {
        return new bo(this);
    }

    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
        d(false);
        this.d = 0;
        e(false);
        this.e = 0;
        f(false);
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        j(false);
        this.j = 0;
    }

    public String c() {
        return this.a;
    }

    public bo a(String str) {
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

    public bo b(String str) {
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

    public bo c(String str) {
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

    public int l() {
        return this.d;
    }

    public bo a(int i) {
        this.d = i;
        d(true);
        return this;
    }

    public void m() {
        this.C = cb.b(this.C, 0);
    }

    public boolean n() {
        return cb.a(this.C, 0);
    }

    public void d(boolean z) {
        this.C = cb.a(this.C, 0, z);
    }

    public int o() {
        return this.e;
    }

    public bo c(int i) {
        this.e = i;
        e(true);
        return this;
    }

    public void q() {
        this.C = cb.b(this.C, 1);
    }

    public boolean r() {
        return cb.a(this.C, 1);
    }

    public void e(boolean z) {
        this.C = cb.a(this.C, 1, z);
    }

    public int s() {
        return this.f;
    }

    public bo d(int i) {
        this.f = i;
        f(true);
        return this;
    }

    public void t() {
        this.C = cb.b(this.C, 2);
    }

    public boolean u() {
        return cb.a(this.C, 2);
    }

    public void f(boolean z) {
        this.C = cb.a(this.C, 2, z);
    }

    public byte[] v() {
        a(cf.c(this.g));
        return this.g == null ? null : this.g.array();
    }

    public ByteBuffer w() {
        return this.g;
    }

    public bo a(byte[] bArr) {
        a(bArr == null ? (ByteBuffer) null : ByteBuffer.wrap(bArr));
        return this;
    }

    public bo a(ByteBuffer byteBuffer) {
        this.g = byteBuffer;
        return this;
    }

    public void x() {
        this.g = null;
    }

    public boolean y() {
        return this.g != null;
    }

    public void g(boolean z) {
        if (!z) {
            this.g = null;
        }
    }

    public String z() {
        return this.h;
    }

    public bo d(String str) {
        this.h = str;
        return this;
    }

    public void A() {
        this.h = null;
    }

    public boolean B() {
        return this.h != null;
    }

    public void h(boolean z) {
        if (!z) {
            this.h = null;
        }
    }

    public String C() {
        return this.i;
    }

    public bo e(String str) {
        this.i = str;
        return this;
    }

    public void D() {
        this.i = null;
    }

    public boolean E() {
        return this.i != null;
    }

    public void i(boolean z) {
        if (!z) {
            this.i = null;
        }
    }

    public int F() {
        return this.j;
    }

    public bo e(int i) {
        this.j = i;
        j(true);
        return this;
    }

    public void G() {
        this.C = cb.b(this.C, 3);
    }

    public boolean H() {
        return cb.a(this.C, 3);
    }

    public void j(boolean z) {
        this.C = cb.a(this.C, 3, z);
    }

    public e f(int i) {
        return e.a(i);
    }

    public void a(dd ddVar) throws ck {
        ((dm) x.get(ddVar.D())).b().b(ddVar, this);
    }

    public void b(dd ddVar) throws ck {
        ((dm) x.get(ddVar.D())).b().a(ddVar, this);
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("UMEnvelope(");
        stringBuilder.append("version:");
        if (this.a == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.a);
        }
        stringBuilder.append(", ");
        stringBuilder.append("address:");
        if (this.b == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.b);
        }
        stringBuilder.append(", ");
        stringBuilder.append("signature:");
        if (this.c == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.c);
        }
        stringBuilder.append(", ");
        stringBuilder.append("serial_num:");
        stringBuilder.append(this.d);
        stringBuilder.append(", ");
        stringBuilder.append("ts_secs:");
        stringBuilder.append(this.e);
        stringBuilder.append(", ");
        stringBuilder.append("length:");
        stringBuilder.append(this.f);
        stringBuilder.append(", ");
        stringBuilder.append("entity:");
        if (this.g == null) {
            stringBuilder.append("null");
        } else {
            cf.a(this.g, stringBuilder);
        }
        stringBuilder.append(", ");
        stringBuilder.append("guid:");
        if (this.h == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.h);
        }
        stringBuilder.append(", ");
        stringBuilder.append("checksum:");
        if (this.i == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append(this.i);
        }
        if (H()) {
            stringBuilder.append(", ");
            stringBuilder.append("codex:");
            stringBuilder.append(this.j);
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public void I() throws ck {
        if (this.a == null) {
            throw new de("Required field 'version' was not present! Struct: " + toString());
        } else if (this.b == null) {
            throw new de("Required field 'address' was not present! Struct: " + toString());
        } else if (this.c == null) {
            throw new de("Required field 'signature' was not present! Struct: " + toString());
        } else if (this.g == null) {
            throw new de("Required field 'entity' was not present! Struct: " + toString());
        } else if (this.h == null) {
            throw new de("Required field 'guid' was not present! Struct: " + toString());
        } else if (this.i == null) {
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
            this.C = (byte) 0;
            a(new cx(new dp((InputStream) objectInputStream)));
        } catch (ck e) {
            throw new IOException(e.getMessage());
        }
    }
}
