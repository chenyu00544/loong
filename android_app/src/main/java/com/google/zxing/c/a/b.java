package com.google.zxing.c.a;

/* compiled from: DataCharacter */
public class b {
    private final int a;
    private final int b;

    public b(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public final int a() {
        return this.a;
    }

    public final int b() {
        return this.b;
    }

    public final String toString() {
        return this.a + "(" + this.b + ')';
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        if (this.a == bVar.a && this.b == bVar.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return this.a ^ this.b;
    }
}
