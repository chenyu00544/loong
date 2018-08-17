package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_MOBILEGOODS */
public class y {
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
    private String n;
    private String o;
    private int p;

    public String a() {
        return this.n;
    }

    public String b() {
        return this.o;
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.b;
    }

    public String e() {
        return this.c;
    }

    public String f() {
        return this.d;
    }

    public String g() {
        return this.e;
    }

    public ECJia_PHOTO h() {
        return this.g;
    }

    public static y a(JSONObject jSONObject) throws JSONException {
        y yVar = new y();
        yVar.a = jSONObject.optString("id");
        yVar.b = jSONObject.optString("name");
        yVar.c = jSONObject.optString("market_price");
        yVar.d = jSONObject.optString("shop_price");
        yVar.e = jSONObject.optString("promote_price");
        yVar.f = jSONObject.optString("brief");
        yVar.g = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        yVar.h = jSONObject.optString("groupbuy_id");
        yVar.i = jSONObject.optString("object_id");
        yVar.j = jSONObject.optString("rec_type");
        yVar.k = jSONObject.optString("promote_start_date");
        yVar.l = jSONObject.optString("promote_end_date");
        yVar.m = jSONObject.optString("raminTime");
        yVar.n = jSONObject.optString("activity_type");
        yVar.p = jSONObject.optInt("saving_price");
        yVar.o = jSONObject.optString("formatted_saving_price");
        return yVar;
    }
}
