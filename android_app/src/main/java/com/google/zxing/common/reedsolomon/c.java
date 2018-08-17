package com.google.zxing.common.reedsolomon;

/* compiled from: ReedSolomonDecoder */
public final class c {
    private final a a;

    public c(a aVar) {
        this.a = aVar;
    }

    public void a(int[] iArr, int i) throws ReedSolomonException {
        int i2 = 0;
        b bVar = new b(this.a, iArr);
        int[] iArr2 = new int[i];
        int i3 = 1;
        for (int i4 = 0; i4 < i; i4++) {
            int b = bVar.b(this.a.a(this.a.d() + i4));
            iArr2[(iArr2.length - 1) - i4] = b;
            if (b != 0) {
                i3 = 0;
            }
        }
        if (i3 == 0) {
            b[] a = a(this.a.a(i, 1), new b(this.a, iArr2), i);
            b bVar2 = a[0];
            b bVar3 = a[1];
            int[] a2 = a(bVar2);
            int[] a3 = a(bVar3, a2);
            while (i2 < a2.length) {
                int length = (iArr.length - 1) - this.a.b(a2[i2]);
                if (length < 0) {
                    throw new ReedSolomonException("Bad error location");
                }
                iArr[length] = a.b(iArr[length], a3[i2]);
                i2++;
            }
        }
    }

    public b[] a(b bVar, b bVar2, int i) throws ReedSolomonException {
        if (bVar.a() >= bVar2.a()) {
            b bVar3 = bVar2;
            bVar2 = bVar;
            bVar = bVar3;
        }
        b a = this.a.a();
        b b = this.a.b();
        while (bVar.a() >= i / 2) {
            if (bVar.b()) {
                throw new ReedSolomonException("r_{i-1} was zero");
            }
            b a2 = this.a.a();
            int c = this.a.c(bVar.a(bVar.a()));
            b bVar4 = a2;
            a2 = bVar2;
            while (a2.a() >= bVar.a() && !a2.b()) {
                int a3 = a2.a() - bVar.a();
                int c2 = this.a.c(a2.a(a2.a()), c);
                bVar4 = bVar4.a(this.a.a(a3, c2));
                a2 = a2.a(bVar.a(a3, c2));
            }
            a = bVar4.b(b).a(a);
            if (a2.a() >= bVar.a()) {
                throw new IllegalStateException("Division algorithm failed to reduce polynomial?");
            }
            bVar2 = bVar;
            bVar = a2;
            bVar3 = b;
            b = a;
            a = bVar3;
        }
        int a4 = b.a(0);
        if (a4 == 0) {
            throw new ReedSolomonException("sigmaTilde(0) was zero");
        }
        a4 = this.a.c(a4);
        b = b.c(a4);
        a = bVar.c(a4);
        return new b[]{b, a};
    }

    private int[] a(b bVar) throws ReedSolomonException {
        int i = 0;
        int i2 = 1;
        int a = bVar.a();
        if (a == 1) {
            return new int[]{bVar.a(1)};
        }
        int[] iArr = new int[a];
        while (i2 < this.a.c() && i < a) {
            if (bVar.b(i2) == 0) {
                iArr[i] = this.a.c(i2);
                i++;
            }
            i2++;
        }
        if (i == a) {
            return iArr;
        }
        throw new ReedSolomonException("Error locator degree does not match number of roots");
    }

    private int[] a(b bVar, int[] iArr) {
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            int c = this.a.c(iArr[i]);
            int i2 = 1;
            int i3 = 0;
            while (i3 < length) {
                int c2;
                if (i != i3) {
                    c2 = this.a.c(iArr[i3], c);
                    c2 = this.a.c(i2, (c2 & 1) == 0 ? c2 | 1 : c2 & -2);
                } else {
                    c2 = i2;
                }
                i3++;
                i2 = c2;
            }
            iArr2[i] = this.a.c(bVar.b(c), this.a.c(i2));
            if (this.a.d() != 0) {
                iArr2[i] = this.a.c(iArr2[i], c);
            }
        }
        return iArr2;
    }
}
