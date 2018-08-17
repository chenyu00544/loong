package com.umeng.analytics.pro;

import com.umeng.analytics.pro.cx.a;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* compiled from: TSerializer */
public class cn {
    private final ByteArrayOutputStream a;
    private final dp b;
    private dd c;

    public cn() {
        this(new a());
    }

    public cn(df dfVar) {
        this.a = new ByteArrayOutputStream();
        this.b = new dp(this.a);
        this.c = dfVar.a(this.b);
    }

    public byte[] a(ce ceVar) throws ck {
        this.a.reset();
        ceVar.b(this.c);
        return this.a.toByteArray();
    }

    public String a(ce ceVar, String str) throws ck {
        try {
            return new String(a(ceVar), str);
        } catch (UnsupportedEncodingException e) {
            throw new ck("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    public String b(ce ceVar) throws ck {
        return new String(a(ceVar));
    }
}
