package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SUGGEST */
public class az {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private ECJia_PHOTO l = new ECJia_PHOTO();
    private af m = new af();

    public af a() {
        return this.m;
    }

    public void a(af afVar) {
        this.m = afVar;
    }

    public String b() {
        return this.a;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.f;
    }

    public String f() {
        return this.j;
    }

    public String g() {
        return this.k;
    }

    public String h() {
        return this.h;
    }

    public ECJia_PHOTO i() {
        return this.l;
    }

    public static az a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        az azVar = new az();
        azVar.a = jSONObject.optString("goods_id");
        azVar.b = jSONObject.optString("name");
        azVar.c = jSONObject.optString("goods_name");
        azVar.d = jSONObject.optString("market_price");
        azVar.e = jSONObject.optString("format_market_price");
        azVar.f = jSONObject.optString("shop_price");
        azVar.g = jSONObject.optString("format_shop_price");
        azVar.h = jSONObject.optString("promote_price");
        azVar.i = jSONObject.optString("brief");
        azVar.j = jSONObject.optString("promote_start_date");
        azVar.k = jSONObject.optString("promote_end_date");
        azVar.l = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        return azVar;
    }
}
