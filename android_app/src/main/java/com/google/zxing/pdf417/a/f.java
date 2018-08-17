package com.google.zxing.pdf417.a;

import java.util.Formatter;

/* compiled from: DetectionResult */
final class f {
    private final a a;
    private final g[] b = new g[(this.d + 2)];
    private c c;
    private final int d;

    f(a aVar, c cVar) {
        this.a = aVar;
        this.d = aVar.a();
        this.c = cVar;
    }

    g[] a() {
        a(this.b[0]);
        a(this.b[this.d + 1]);
        int i = 928;
        while (true) {
            int f = f();
            if (f > 0 && f < r0) {
                i = f;
            }
        }
        return this.b;
    }

    private void a(g gVar) {
        if (gVar != null) {
            ((h) gVar).a(this.a);
        }
    }

    private int f() {
        int g = g();
        if (g == 0) {
            return 0;
        }
        for (int i = 1; i < this.d + 1; i++) {
            d[] b = this.b[i].b();
            int i2 = 0;
            while (i2 < b.length) {
                if (!(b[i2] == null || b[i2].a())) {
                    a(i, i2, b);
                }
                i2++;
            }
        }
        return g;
    }

    private int g() {
        h();
        return j() + i();
    }

    private int h() {
        if (!(this.b[0] == null || this.b[this.d + 1] == null)) {
            d[] b = this.b[0].b();
            d[] b2 = this.b[this.d + 1].b();
            int i = 0;
            while (i < b.length) {
                if (!(b[i] == null || b2[i] == null || b[i].h() != b2[i].h())) {
                    for (int i2 = 1; i2 <= this.d; i2++) {
                        d dVar = this.b[i2].b()[i];
                        if (dVar != null) {
                            dVar.b(b[i].h());
                            if (!dVar.a()) {
                                this.b[i2].b()[i] = null;
                            }
                        }
                    }
                }
                i++;
            }
        }
        return 0;
    }

    private int i() {
        if (this.b[this.d + 1] == null) {
            return 0;
        }
        d[] b = this.b[this.d + 1].b();
        int i = 0;
        for (int i2 = 0; i2 < b.length; i2++) {
            if (b[i2] != null) {
                int h = b[i2].h();
                int i3 = i;
                i = 0;
                for (int i4 = this.d + 1; i4 > 0 && i < 2; i4--) {
                    d dVar = this.b[i4].b()[i2];
                    if (dVar != null) {
                        i = a(h, i, dVar);
                        if (!dVar.a()) {
                            i3++;
                        }
                    }
                }
                i = i3;
            }
        }
        return i;
    }

    private int j() {
        if (this.b[0] == null) {
            return 0;
        }
        d[] b = this.b[0].b();
        int i = 0;
        for (int i2 = 0; i2 < b.length; i2++) {
            if (b[i2] != null) {
                int h = b[i2].h();
                int i3 = i;
                int i4 = 0;
                for (i = 1; i < this.d + 1 && i4 < 2; i++) {
                    d dVar = this.b[i].b()[i2];
                    if (dVar != null) {
                        i4 = a(h, i4, dVar);
                        if (!dVar.a()) {
                            i3++;
                        }
                    }
                }
                i = i3;
            }
        }
        return i;
    }

    private static int a(int i, int i2, d dVar) {
        if (dVar == null || dVar.a()) {
            return i2;
        }
        if (!dVar.a(i)) {
            return i2 + 1;
        }
        dVar.b(i);
        return 0;
    }

    private void a(int i, int i2, d[] dVarArr) {
        d[] b;
        d dVar = dVarArr[i2];
        d[] b2 = this.b[i - 1].b();
        if (this.b[i + 1] != null) {
            b = this.b[i + 1].b();
        } else {
            b = b2;
        }
        d[] dVarArr2 = new d[14];
        dVarArr2[2] = b2[i2];
        dVarArr2[3] = b[i2];
        if (i2 > 0) {
            dVarArr2[0] = dVarArr[i2 - 1];
            dVarArr2[4] = b2[i2 - 1];
            dVarArr2[5] = b[i2 - 1];
        }
        if (i2 > 1) {
            dVarArr2[8] = dVarArr[i2 - 2];
            dVarArr2[10] = b2[i2 - 2];
            dVarArr2[11] = b[i2 - 2];
        }
        if (i2 < dVarArr.length - 1) {
            dVarArr2[1] = dVarArr[i2 + 1];
            dVarArr2[6] = b2[i2 + 1];
            dVarArr2[7] = b[i2 + 1];
        }
        if (i2 < dVarArr.length - 2) {
            dVarArr2[9] = dVarArr[i2 + 2];
            dVarArr2[12] = b2[i2 + 2];
            dVarArr2[13] = b[i2 + 2];
        }
        int length = dVarArr2.length;
        int i3 = 0;
        while (i3 < length && !a(dVar, dVarArr2[i3])) {
            i3++;
        }
    }

    private static boolean a(d dVar, d dVar2) {
        if (dVar2 == null || !dVar2.a() || dVar2.f() != dVar.f()) {
            return false;
        }
        dVar.b(dVar2.h());
        return true;
    }

    int b() {
        return this.d;
    }

    int c() {
        return this.a.c();
    }

    int d() {
        return this.a.b();
    }

    public void a(c cVar) {
        this.c = cVar;
    }

    c e() {
        return this.c;
    }

    void a(int i, g gVar) {
        this.b[i] = gVar;
    }

    g a(int i) {
        return this.b[i];
    }

    public String toString() {
        g gVar = this.b[0];
        if (gVar == null) {
            gVar = this.b[this.d + 1];
        }
        Formatter formatter = new Formatter();
        for (int i = 0; i < gVar.b().length; i++) {
            formatter.format("CW %3d:", new Object[]{Integer.valueOf(i)});
            for (int i2 = 0; i2 < this.d + 2; i2++) {
                if (this.b[i2] == null) {
                    formatter.format("    |   ", new Object[0]);
                } else {
                    if (this.b[i2].b()[i] == null) {
                        formatter.format("    |   ", new Object[0]);
                    } else {
                        formatter.format(" %3d|%3d", new Object[]{Integer.valueOf(this.b[i2].b()[i].h()), Integer.valueOf(this.b[i2].b()[i].g())});
                    }
                }
            }
            formatter.format("\n", new Object[0]);
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
    }
}
