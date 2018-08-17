package com.google.zxing.c.a.a;

import java.util.ArrayList;
import java.util.List;

/* compiled from: ExpandedRow */
final class c {
    private final List<b> a;
    private final int b;
    private final boolean c;

    c(List<b> list, int i, boolean z) {
        this.a = new ArrayList(list);
        this.b = i;
        this.c = z;
    }

    List<b> a() {
        return this.a;
    }

    int b() {
        return this.b;
    }

    boolean a(List<b> list) {
        return this.a.equals(list);
    }

    public String toString() {
        return "{ " + this.a + " }";
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.a.equals(cVar.a()) && this.c == cVar.c) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return this.a.hashCode() ^ Boolean.valueOf(this.c).hashCode();
    }
}
