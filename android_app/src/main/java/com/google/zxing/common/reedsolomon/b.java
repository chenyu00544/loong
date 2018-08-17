package com.google.zxing.common.reedsolomon;

/* compiled from: GenericGFPoly */
final class b {
    private final a a;
    private final int[] b;

    b(a aVar, int[] iArr) {
        int i = 1;
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.a = aVar;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.b = iArr;
            return;
        }
        while (i < length && iArr[i] == 0) {
            i++;
        }
        if (i == length) {
            this.b = aVar.a().b;
            return;
        }
        this.b = new int[(length - i)];
        System.arraycopy(iArr, i, this.b, 0, this.b.length);
    }

    int a() {
        return this.b.length - 1;
    }

    boolean b() {
        return this.b[0] == 0;
    }

    int a(int i) {
        return this.b[(this.b.length - 1) - i];
    }

    int b(int i) {
        int i2 = 0;
        if (i == 0) {
            return a(0);
        }
        int length = this.b.length;
        int i3;
        if (i == 1) {
            int[] iArr = this.b;
            int length2 = iArr.length;
            i3 = 0;
            while (i2 < length2) {
                int b = a.b(i3, iArr[i2]);
                i2++;
                i3 = b;
            }
            return i3;
        }
        i3 = this.b[0];
        i2 = 1;
        while (i2 < length) {
            b = a.b(this.a.c(i, i3), this.b[i2]);
            i2++;
            i3 = b;
        }
        return i3;
    }

    b a(b bVar) {
        if (!this.a.equals(bVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (b()) {
            return bVar;
        } else {
            if (bVar.b()) {
                return this;
            }
            Object obj = this.b;
            Object obj2 = bVar.b;
            if (obj.length <= obj2.length) {
                Object obj3 = obj2;
                obj2 = obj;
                obj = obj3;
            }
            Object obj4 = new int[obj.length];
            int length = obj.length - r1.length;
            System.arraycopy(obj, 0, obj4, 0, length);
            for (int i = length; i < obj.length; i++) {
                obj4[i] = a.b(r1[i - length], obj[i]);
            }
            return new b(this.a, obj4);
        }
    }

    b b(b bVar) {
        if (!this.a.equals(bVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (b() || bVar.b()) {
            return this.a.a();
        } else {
            int[] iArr = this.b;
            int length = iArr.length;
            int[] iArr2 = bVar.b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    iArr3[i + i3] = a.b(iArr3[i + i3], this.a.c(i2, iArr2[i3]));
                }
            }
            return new b(this.a, iArr3);
        }
    }

    b c(int i) {
        if (i == 0) {
            return this.a.a();
        }
        if (i == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.a.c(this.b[i2], i);
        }
        return new b(this.a, iArr);
    }

    b a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.a.a();
        } else {
            int length = this.b.length;
            int[] iArr = new int[(length + i)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.a.c(this.b[i3], i2);
            }
            return new b(this.a, iArr);
        }
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(a() * 8);
        for (int a = a(); a >= 0; a--) {
            int a2 = a(a);
            if (a2 != 0) {
                if (a2 < 0) {
                    stringBuilder.append(" - ");
                    a2 = -a2;
                } else if (stringBuilder.length() > 0) {
                    stringBuilder.append(" + ");
                }
                if (a == 0 || a2 != 1) {
                    a2 = this.a.b(a2);
                    if (a2 == 0) {
                        stringBuilder.append('1');
                    } else if (a2 == 1) {
                        stringBuilder.append('a');
                    } else {
                        stringBuilder.append("a^");
                        stringBuilder.append(a2);
                    }
                }
                if (a != 0) {
                    if (a == 1) {
                        stringBuilder.append('x');
                    } else {
                        stringBuilder.append("x^");
                        stringBuilder.append(a);
                    }
                }
            }
        }
        return stringBuilder.toString();
    }
}
