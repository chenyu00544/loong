package com.umeng.analytics.pro;

/* compiled from: TMessage */
public final class db {
    public final String a;
    public final byte b;
    public final int c;

    public db() {
        this("", (byte) 0, 0);
    }

    public db(String str, byte b, int i) {
        this.a = str;
        this.b = b;
        this.c = i;
    }

    public String toString() {
        return "<TMessage name:'" + this.a + "' type: " + this.b + " seqid:" + this.c + ">";
    }

    public boolean equals(Object obj) {
        if (obj instanceof db) {
            return a((db) obj);
        }
        return false;
    }

    public boolean a(db dbVar) {
        return this.a.equals(dbVar.a) && this.b == dbVar.b && this.c == dbVar.c;
    }
}
