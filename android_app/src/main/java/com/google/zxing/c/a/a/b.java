package com.google.zxing.c.a.a;

import com.google.zxing.c.a.c;
import com.umeng.message.proguard.k;

/* compiled from: ExpandedPair */
final class b {
    private final boolean a;
    private final com.google.zxing.c.a.b b;
    private final com.google.zxing.c.a.b c;
    private final c d;

    b(com.google.zxing.c.a.b bVar, com.google.zxing.c.a.b bVar2, c cVar, boolean z) {
        this.b = bVar;
        this.c = bVar2;
        this.d = cVar;
        this.a = z;
    }

    com.google.zxing.c.a.b a() {
        return this.b;
    }

    com.google.zxing.c.a.b b() {
        return this.c;
    }

    c c() {
        return this.d;
    }

    public boolean d() {
        return this.c == null;
    }

    public String toString() {
        return "[ " + this.b + k.u + this.c + " : " + (this.d == null ? "null" : Integer.valueOf(this.d.a())) + " ]";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (a(this.b, bVar.b) && a(this.c, bVar.c) && a(this.d, bVar.d)) {
            return true;
        }
        return false;
    }

    private static boolean a(Object obj, Object obj2) {
        if (obj == null) {
            return obj2 == null;
        } else {
            return obj.equals(obj2);
        }
    }

    public int hashCode() {
        return (a(this.b) ^ a(this.c)) ^ a(this.d);
    }

    private static int a(Object obj) {
        return obj == null ? 0 : obj.hashCode();
    }
}
