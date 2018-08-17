package com.ecjia.hamster.model;

import com.umeng.analytics.pro.x;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_PERIOD */
public class ac {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private boolean g;
    private af h = new af();
    private ECJia_PHOTO i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;

    public boolean a() {
        return this.g;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public ECJia_PHOTO b() {
        return this.i;
    }

    public String c() {
        return this.j;
    }

    public String d() {
        return this.r;
    }

    public String e() {
        return this.p;
    }

    public String f() {
        return this.n;
    }

    public String g() {
        return this.m;
    }

    public String h() {
        return this.k;
    }

    public String i() {
        return this.l;
    }

    public String j() {
        return this.a;
    }

    public void a(String str) {
        this.c = str;
    }

    public String k() {
        return this.d;
    }

    public String l() {
        return this.f;
    }

    public String m() {
        return this.e;
    }

    public static ac a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ac acVar = new ac();
        acVar.a = jSONObject.optString("id");
        acVar.b = jSONObject.optString("begin_time");
        acVar.c = jSONObject.optString(x.X);
        acVar.d = jSONObject.optString("title");
        acVar.e = jSONObject.optString("status");
        acVar.f = jSONObject.optString("status_label");
        acVar.i = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        acVar.j = jSONObject.optString("goods_id");
        acVar.k = jSONObject.optString("spike_goods_id");
        acVar.l = jSONObject.optString("sales_count");
        acVar.m = jSONObject.optString("goods_name");
        acVar.n = jSONObject.optString("spike_num");
        acVar.o = jSONObject.optString("spike_limit");
        acVar.p = jSONObject.optString("market_price");
        acVar.q = jSONObject.optString("shop_price");
        acVar.r = jSONObject.optString("spike_price");
        return acVar;
    }
}
