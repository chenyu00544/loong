package com.umeng.analytics.pro;

/* compiled from: TField */
public class cy {
    public final String a;
    public final byte b;
    public final short c;

    public cy() {
        this("", (byte) 0, (short) 0);
    }

    public cy(String str, byte b, short s) {
        this.a = str;
        this.b = b;
        this.c = s;
    }

    public String toString() {
        return "<TField name:'" + this.a + "' type:" + this.b + " field-id:" + this.c + ">";
    }

    public boolean a(cy cyVar) {
        return this.b == cyVar.b && this.c == cyVar.c;
    }
}
