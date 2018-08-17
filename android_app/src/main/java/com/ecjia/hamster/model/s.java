package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_GROUPGOODS */
public class s {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private ECJia_PHOTO g = new ECJia_PHOTO();
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private af n = new af();

    public af a() {
        return this.n;
    }

    public void a(af afVar) {
        this.n = afVar;
    }

    public String b() {
        return this.m;
    }

    public void a(String str) {
        this.m = str;
    }

    public String c() {
        return this.k;
    }

    public String d() {
        return this.l;
    }

    public void b(String str) {
        this.l = str;
    }

    public String e() {
        return this.i;
    }

    public String f() {
        return this.j;
    }

    public String g() {
        return this.a;
    }

    public String h() {
        return this.b;
    }

    public String i() {
        return this.c;
    }

    public String j() {
        return this.d;
    }

    public String k() {
        return this.e;
    }

    public ECJia_PHOTO l() {
        return this.g;
    }

    public static s a(JSONObject jSONObject) throws JSONException {
        s sVar = new s();
        sVar.a = jSONObject.optString("id");
        sVar.b = jSONObject.optString("name");
        sVar.c = jSONObject.optString("market_price");
        sVar.d = jSONObject.optString("shop_price");
        sVar.e = jSONObject.optString("promote_price");
        sVar.f = jSONObject.optString("brief");
        sVar.g = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        sVar.h = jSONObject.optString("groupbuy_id");
        sVar.i = jSONObject.optString("object_id");
        sVar.j = jSONObject.optString("rec_type");
        sVar.k = jSONObject.optString("promote_start_date");
        sVar.l = jSONObject.optString("promote_end_date");
        sVar.m = jSONObject.optString("raminTime");
        return sVar;
    }
}
