package com.unionpay.c;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

final class ao {

    static class a implements ap {
        String a = "";
        long b = 0;
        int c = 0;
        String d = "";

        a() {
        }

        public final void a(aq aqVar) {
            aqVar.b(4);
            aqVar.a(this.a);
            aqVar.a(this.b);
            aqVar.a(this.c);
            aqVar.a(this.d);
        }

        public final String toString() {
            return "Activity{name:" + this.a + ",start:" + this.b + ",duration:" + this.c + ",refer:" + this.d;
        }
    }

    static class b implements ap {
        String a = "";
        String b = "";
        int c = 0;
        long d;
        Map e;

        b() {
        }

        public final void a(aq aqVar) {
            aqVar.b(5);
            aqVar.a(this.a);
            aqVar.a(this.b);
            aqVar.a(this.c);
            aqVar.a(this.d);
            Map map = this.e;
            if (map == null) {
                aqVar.a();
                return;
            }
            Map hashMap = new HashMap();
            for (String str : map.keySet()) {
                Object obj = map.get(str);
                if (obj instanceof String) {
                    hashMap.put(str, obj.toString());
                } else if (obj instanceof Number) {
                    hashMap.put(str, Double.valueOf(((Number) obj).doubleValue()));
                }
            }
            aqVar.d(hashMap.size());
            for (Entry entry : hashMap.entrySet()) {
                aqVar.a((String) entry.getKey());
                Object value = entry.getValue();
                if (value instanceof Number) {
                    aqVar.a(((Number) value).doubleValue());
                } else if (value instanceof String) {
                    aqVar.a(value.toString());
                }
            }
        }

        public final String toString() {
            return "AppEvent{id:" + this.a + ",label:" + this.b + ",count:" + this.c + ",ts:" + this.d + ",kv:" + this.e + '}';
        }
    }

    static class c implements ap {
        long a = 0;
        int b = 1;
        String c = "";
        byte[] d = new byte[0];
        String e = "";

        c() {
        }

        public final void a(aq aqVar) {
            aqVar.b(5);
            aqVar.a(this.a);
            aqVar.a(this.b);
            aqVar.a(this.c);
            byte[] bArr = this.d;
            if (bArr == null) {
                aqVar.a();
            } else {
                aqVar.e(bArr.length);
                aqVar.a(bArr);
            }
            aqVar.a(this.e);
        }
    }

    static class d implements ap {
        String a = "";
        String b = "";
        String c = "";
        long d = 0;
        String e = "";
        String f = "";
        boolean g = false;
        long h = 0;
        long i = 0;

        d() {
        }

        public final void a(aq aqVar) {
            aqVar.b(9);
            aqVar.a(this.a);
            aqVar.a(this.b);
            aqVar.a(this.c);
            aqVar.a(this.d);
            aqVar.a(this.e);
            aqVar.a(this.f);
            aqVar.a(this.g);
            aqVar.a(this.h);
            aqVar.a(this.i);
        }
    }

    static class e implements ap {
        String a = "";
        String b = "";
        h c = new h();
        String d = "";
        String e = "";
        String f = "";
        String g = "";
        String h = "";
        int i = 8;
        String j = "";
        int k = -1;
        String l = "";
        boolean m = false;
        String n = "";
        String o = "";
        String p = "";
        String q = "";
        long r = 0;
        String s = "";
        String t = "";
        String u = "";
        int v;
        int w;
        String x = "";

        e() {
        }

        public final void a(aq aqVar) {
            aqVar.b(24);
            aqVar.a(this.a);
            aqVar.a(this.b);
            aqVar.a(this.c);
            aqVar.a(this.d);
            aqVar.a(this.e);
            aqVar.a(this.f);
            aqVar.a(this.g);
            aqVar.a(this.h);
            aqVar.a(this.i);
            aqVar.a(this.j);
            aqVar.a(this.k);
            aqVar.a(this.l);
            aqVar.a(this.m);
            aqVar.a(this.n);
            aqVar.a(this.o);
            aqVar.a(this.p);
            aqVar.a(this.q);
            aqVar.a(this.r).a(this.s).a(this.t).a(this.u).a(this.v).a(this.w).a(this.x);
        }
    }

    static class f implements ap {
        String a = "";
        String b = "";
        d c = new d();
        e d = new e();
        List e = new ArrayList();
        long f = 0;
        long g = 0;
        long h = 0;
        Long[][] i;

        f() {
        }

        public final void a(aq aqVar) {
            aqVar.b(6);
            aqVar.a(this.a);
            aqVar.a(this.b);
            aqVar.a(this.c);
            aqVar.a(this.d);
            aqVar.b(this.e.size());
            for (ap a : this.e) {
                aqVar.a(a);
            }
            if (this.i == null) {
                aqVar.a();
                return;
            }
            aqVar.b(this.i.length);
            for (Long[] lArr : this.i) {
                if (lArr == null || lArr.length == 0) {
                    aqVar.a();
                } else {
                    aqVar.b(lArr.length);
                    for (Long longValue : lArr) {
                        aqVar.a(longValue.longValue());
                    }
                }
            }
        }
    }

    static class g implements ap {
        String A = "";
        String B = "";
        String C = "";
        String a = "";
        int b = 0;
        float c = 0.0f;
        String d = "";
        String e = "";
        String f = "";
        int g = 0;
        int h = 0;
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        int m = 0;
        float n = 0.0f;
        float o = 0.0f;
        int p = 0;
        String q = "";
        String r = "";
        String s = "";
        String t = "";
        String u = "";
        String v = "";
        String w = "";
        boolean x = false;
        String y = "";
        String z = "";

        g() {
        }

        public final void a(aq aqVar) {
            aqVar.b(29);
            aqVar.a(this.a);
            aqVar.a(this.b);
            aqVar.a(this.c);
            aqVar.a(this.d);
            aqVar.a(this.e);
            aqVar.a(this.f);
            aqVar.a(this.g);
            aqVar.a(this.h);
            aqVar.a(this.i);
            aqVar.a(this.j);
            aqVar.a(this.k);
            aqVar.a(this.l);
            aqVar.a(this.m);
            aqVar.a(this.n);
            aqVar.a(this.o);
            aqVar.a(this.p);
            aqVar.a(this.q);
            aqVar.a(this.r);
            aqVar.a(this.s);
            aqVar.a(this.t);
            aqVar.a(this.u);
            aqVar.a(this.v);
            aqVar.a(this.w);
            aqVar.a(this.x);
            aqVar.a(this.y);
            aqVar.a(this.z);
            aqVar.a(this.A);
            aqVar.a(this.B);
            aqVar.a(this.C);
        }
    }

    static class h implements ap {
        double a = 0.0d;
        double b = 0.0d;

        h() {
        }

        public final void a(aq aqVar) {
            aqVar.b(2);
            aqVar.a(this.a);
            aqVar.a(this.b);
        }
    }

    static class i implements ap {
        int a = -1;
        j b;
        g c;
        c d;

        i() {
        }

        public final void a(aq aqVar) {
            aqVar.b(2);
            aqVar.a(this.a);
            switch (this.a) {
                case 1:
                    aqVar.a(this.c);
                    return;
                case 2:
                    aqVar.a(this.b);
                    return;
                case 3:
                    aqVar.a(this.d);
                    return;
                default:
                    throw new IOException("unknown TMessageType");
            }
        }
    }

    static class j implements ap {
        String a = "";
        long b = 0;
        int c = 0;
        int d = 0;
        List e = new ArrayList();
        List f = new ArrayList();
        int g = 0;
        int h = 0;
        long i = 0;

        j() {
        }

        public final int a() {
            int c = (((((aq.c(8) + aq.b(this.a)) + aq.b(this.b)) + aq.c(this.c)) + aq.c(this.d)) + aq.c(this.h)) + aq.c(this.e.size());
            int i = c;
            for (a aVar : this.e) {
                i = (aq.b(aVar.d) + (((aq.c(4) + aq.b(aVar.a)) + aq.b(aVar.b)) + aq.c(aVar.c))) + i;
            }
            c = aq.c(this.f.size()) + i;
            i = c;
            for (b bVar : this.f) {
                i = (aq.c(bVar.c) + ((aq.c(3) + aq.b(bVar.a)) + aq.b(bVar.b))) + i;
            }
            return aq.b(this.i) + i;
        }

        public final void a(aq aqVar) {
            aqVar.b(8);
            aqVar.a(this.a);
            aqVar.a(this.b);
            aqVar.a(this.c);
            aqVar.a(this.d);
            aqVar.b(this.e.size());
            for (ap a : this.e) {
                aqVar.a(a);
            }
            aqVar.b(this.f.size());
            for (ap a2 : this.f) {
                aqVar.a(a2);
            }
            aqVar.a(this.h);
            aqVar.a(this.i);
        }

        public final String toString() {
            return "Session{id:" + this.a + ",start:" + this.b + ",status:" + this.c + ",duration:" + this.d + ",connected:" + this.h + ",time_gap:" + this.i + '}';
        }
    }
}
