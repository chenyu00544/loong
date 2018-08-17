package com.alipay.android.phone.mrpc.core;

import com.umeng.message.util.HttpRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;

public final class s extends x {
    private String b;
    private byte[] c;
    private String d = HttpRequest.CONTENT_TYPE_FORM;
    private ArrayList<Header> e = new ArrayList();
    private Map<String, String> f = new HashMap();
    private boolean g;

    public s(String str) {
        this.b = str;
    }

    public final String a() {
        return this.b;
    }

    public final void a(String str) {
        this.d = str;
    }

    public final void a(String str, String str2) {
        if (this.f == null) {
            this.f = new HashMap();
        }
        this.f.put(str, str2);
    }

    public final void a(Header header) {
        this.e.add(header);
    }

    public final void a(boolean z) {
        this.g = z;
    }

    public final void a(byte[] bArr) {
        this.c = bArr;
    }

    public final String b(String str) {
        return this.f == null ? null : (String) this.f.get(str);
    }

    public final byte[] b() {
        return this.c;
    }

    public final String c() {
        return this.d;
    }

    public final ArrayList<Header> d() {
        return this.e;
    }

    public final boolean e() {
        return this.g;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        s sVar = (s) obj;
        if (this.c == null) {
            if (sVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(sVar.c)) {
            return false;
        }
        return this.b == null ? sVar.b == null : this.b.equals(sVar.b);
    }

    public final int hashCode() {
        int i = 1;
        if (this.f != null && this.f.containsKey("id")) {
            i = ((String) this.f.get("id")).hashCode() + 31;
        }
        return (this.b == null ? 0 : this.b.hashCode()) + (i * 31);
    }

    public final String toString() {
        return String.format("Url : %s,HttpHeader: %s", new Object[]{this.b, this.e});
    }
}
