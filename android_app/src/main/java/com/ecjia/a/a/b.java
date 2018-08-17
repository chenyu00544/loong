package com.ecjia.a.a;

import com.ecjia.hamster.model.z;

/* compiled from: ECJiaMyEvent */
public class b extends a {
    private z e;

    public b(boolean z, int i) {
        super(z, i);
        this.a = z;
        this.b = i;
    }

    public b(String str) {
        super(str);
        this.c = str;
    }

    public b(String str, String str2) {
        super(str, str2);
        this.c = str;
        this.d = str2;
    }

    public b(String str, String str2, int i) {
        super(str, str2);
        this.c = str;
        this.d = str2;
        this.b = i;
    }

    public z e() {
        return this.e;
    }

    public void a(z zVar) {
        this.e = zVar;
    }
}
