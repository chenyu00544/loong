package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_CONFIG */
public class k {
    private String a;
    private String b;
    private String c;
    private int d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;

    public String a() {
        return this.m;
    }

    public String b() {
        return this.n;
    }

    public String c() {
        return this.j;
    }

    public String d() {
        return this.a;
    }

    public String e() {
        return this.b;
    }

    public int f() {
        return this.d;
    }

    public String g() {
        return this.h;
    }

    public static k a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        k kVar = new k();
        kVar.a = jSONObject.optString("service_phone");
        kVar.b = jSONObject.optString("site_url");
        kVar.c = jSONObject.optString("shop_desc");
        kVar.d = jSONObject.optInt("shop_closed");
        kVar.e = jSONObject.optString("close_comment");
        kVar.h = jSONObject.optString("shop_address");
        kVar.f = jSONObject.optString("shop_reg_closed");
        kVar.g = jSONObject.optString("goods_url");
        kVar.i = jSONObject.optString("alipay_notify_url");
        kVar.j = jSONObject.optString("get_password_url");
        kVar.p = jSONObject.optString("mobile_qr_code");
        kVar.k = jSONObject.optString("shop_name");
        kVar.l = jSONObject.optString("bonus_readme_url");
        kVar.m = jSONObject.optString("mobile_phone_login_bgimage");
        kVar.n = jSONObject.optString("mobile_phone_login_bgcolor");
        kVar.o = jSONObject.optString("mobile_phone_login_fgcolor");
        return kVar;
    }
}
