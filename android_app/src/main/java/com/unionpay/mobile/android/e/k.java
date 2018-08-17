package com.unionpay.mobile.android.e;

import com.umeng.message.common.a;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public final class k {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private JSONObject g;

    public k(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.g = jSONObject;
            this.a = j.a(jSONObject, a.c);
            this.b = j.a(jSONObject, "issuer");
            this.c = j.a(jSONObject, "syn_key");
            this.d = j.a(jSONObject, "pub_key");
            this.e = j.a(jSONObject, "status");
            this.f = j.a(jSONObject, "priority");
        }
    }

    public final boolean a() {
        return this.e.equals("D");
    }

    public final String b() {
        return this.a;
    }

    public final String c() {
        return this.b;
    }

    public final String d() {
        return this.c;
    }

    public final String e() {
        return this.d;
    }

    public final JSONObject f() {
        return this.g;
    }
}
