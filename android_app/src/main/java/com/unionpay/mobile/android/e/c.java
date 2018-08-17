package com.unionpay.mobile.android.e;

import android.content.ServiceConnection;
import com.unionpay.mobile.android.e.a.a;
import com.unionpay.mobile.android.g.d;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public final class c implements d {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private a g;
    private ServiceConnection h;

    public c(JSONObject jSONObject, String str, a aVar, ServiceConnection serviceConnection) {
        this.a = j.a(jSONObject, "num");
        this.b = j.a(jSONObject, "name");
        this.d = j.a(jSONObject, "type");
        String a = j.a(jSONObject, "type");
        a = e.a.equals(a) ? e.e : e.b.equals(a) ? e.f : e.c.equals(a) ? e.g : e.d.equals(a) ? e.h : "";
        this.c = a;
        this.e = j.a(jSONObject, "instNum");
        this.f = str;
        this.g = aVar;
        this.h = serviceConnection;
    }

    public final String a() {
        return this.a;
    }

    public final String b() {
        return this.b;
    }

    public final String c() {
        return this.c;
    }

    public final String d() {
        return this.d;
    }

    public final String e() {
        return this.e;
    }

    public final String f() {
        return this.f;
    }

    public final a g() {
        return this.g;
    }

    public final ServiceConnection h() {
        return this.h;
    }
}
