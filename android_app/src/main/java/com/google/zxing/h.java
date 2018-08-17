package com.google.zxing;

import com.google.zxing.common.a.a;

/* compiled from: ResultPoint */
public class h {
    private final float a;
    private final float b;

    public h(float f, float f2) {
        this.a = f;
        this.b = f2;
    }

    public final float a() {
        return this.a;
    }

    public final float b() {
        return this.b;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof h)) {
            return false;
        }
        h hVar = (h) obj;
        if (this.a == hVar.a && this.b == hVar.b) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.a) * 31) + Float.floatToIntBits(this.b);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(25);
        stringBuilder.append('(');
        stringBuilder.append(this.a);
        stringBuilder.append(',');
        stringBuilder.append(this.b);
        stringBuilder.append(')');
        return stringBuilder.toString();
    }

    public static void a(h[] hVarArr) {
        h hVar;
        h hVar2;
        h hVar3;
        float a = a(hVarArr[0], hVarArr[1]);
        float a2 = a(hVarArr[1], hVarArr[2]);
        float a3 = a(hVarArr[0], hVarArr[2]);
        if (a2 >= a && a2 >= a3) {
            hVar = hVarArr[0];
            hVar2 = hVarArr[1];
            hVar3 = hVarArr[2];
        } else if (a3 < a2 || a3 < a) {
            hVar = hVarArr[2];
            hVar2 = hVarArr[0];
            hVar3 = hVarArr[1];
        } else {
            hVar = hVarArr[1];
            hVar2 = hVarArr[0];
            hVar3 = hVarArr[2];
        }
        if (a(hVar2, hVar, hVar3) >= 0.0f) {
            h hVar4 = hVar3;
            hVar3 = hVar2;
            hVar2 = hVar4;
        }
        hVarArr[0] = hVar3;
        hVarArr[1] = hVar;
        hVarArr[2] = hVar2;
    }

    public static float a(h hVar, h hVar2) {
        return a.a(hVar.a, hVar.b, hVar2.a, hVar2.b);
    }

    private static float a(h hVar, h hVar2, h hVar3) {
        float f = hVar2.a;
        float f2 = hVar2.b;
        return ((hVar3.a - f) * (hVar.b - f2)) - ((hVar.a - f) * (hVar3.b - f2));
    }
}
