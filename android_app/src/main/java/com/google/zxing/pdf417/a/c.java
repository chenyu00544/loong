package com.google.zxing.pdf417.a;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.b;
import com.google.zxing.h;

/* compiled from: BoundingBox */
final class c {
    private b a;
    private h b;
    private h c;
    private h d;
    private h e;
    private int f;
    private int g;
    private int h;
    private int i;

    c(b bVar, h hVar, h hVar2, h hVar3, h hVar4) throws NotFoundException {
        if (!(hVar == null && hVar3 == null) && (!(hVar2 == null && hVar4 == null) && ((hVar == null || hVar2 != null) && (hVar3 == null || hVar4 != null)))) {
            a(bVar, hVar, hVar2, hVar3, hVar4);
            return;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    c(c cVar) {
        a(cVar.a, cVar.b, cVar.c, cVar.d, cVar.e);
    }

    private void a(b bVar, h hVar, h hVar2, h hVar3, h hVar4) {
        this.a = bVar;
        this.b = hVar;
        this.c = hVar2;
        this.d = hVar3;
        this.e = hVar4;
        i();
    }

    static c a(c cVar, c cVar2) throws NotFoundException {
        if (cVar == null) {
            return cVar2;
        }
        if (cVar2 == null) {
            return cVar;
        }
        return new c(cVar.a, cVar.b, cVar.c, cVar2.d, cVar2.e);
    }

    c a(int i, int i2, boolean z) throws NotFoundException {
        int b;
        h hVar;
        h hVar2 = this.b;
        h hVar3 = this.c;
        h hVar4 = this.d;
        h hVar5 = this.e;
        if (i > 0) {
            h hVar6 = z ? this.b : this.d;
            b = ((int) hVar6.b()) - i;
            if (b < 0) {
                b = 0;
            }
            hVar = new h(hVar6.a(), (float) b);
            if (!z) {
                hVar4 = hVar;
                hVar = hVar2;
            }
        } else {
            hVar = hVar2;
        }
        if (i2 > 0) {
            hVar6 = z ? this.c : this.e;
            b = ((int) hVar6.b()) + i2;
            if (b >= this.a.e()) {
                b = this.a.e() - 1;
            }
            hVar2 = new h(hVar6.a(), (float) b);
            if (!z) {
                hVar5 = hVar2;
                hVar2 = hVar3;
            }
        } else {
            hVar2 = hVar3;
        }
        i();
        return new c(this.a, hVar, hVar2, hVar4, hVar5);
    }

    private void i() {
        if (this.b == null) {
            this.b = new h(0.0f, this.d.b());
            this.c = new h(0.0f, this.e.b());
        } else if (this.d == null) {
            this.d = new h((float) (this.a.d() - 1), this.b.b());
            this.e = new h((float) (this.a.d() - 1), this.c.b());
        }
        this.f = (int) Math.min(this.b.a(), this.c.a());
        this.g = (int) Math.max(this.d.a(), this.e.a());
        this.h = (int) Math.min(this.b.b(), this.d.b());
        this.i = (int) Math.max(this.c.b(), this.e.b());
    }

    int a() {
        return this.f;
    }

    int b() {
        return this.g;
    }

    int c() {
        return this.h;
    }

    int d() {
        return this.i;
    }

    h e() {
        return this.b;
    }

    h f() {
        return this.d;
    }

    h g() {
        return this.c;
    }

    h h() {
        return this.e;
    }
}
