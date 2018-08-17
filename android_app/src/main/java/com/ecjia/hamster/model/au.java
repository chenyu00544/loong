package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SIMPLEGOODS */
public class au {
    private int a;
    private String b;
    private String c;
    private String d;
    private int e;
    private ECJia_PHOTO f = new ECJia_PHOTO();
    private String g;
    private String h;
    private String i;
    private int j;
    private String k;

    public String a() {
        return this.k;
    }

    public String b() {
        return this.i;
    }

    public int c() {
        return this.a;
    }

    public String d() {
        return this.h;
    }

    public String e() {
        return this.b;
    }

    public String f() {
        return this.c;
    }

    public String g() {
        return this.d;
    }

    public int h() {
        return this.e;
    }

    public ECJia_PHOTO i() {
        return this.f;
    }

    public static au a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        au auVar = new au();
        auVar.a = jSONObject.optInt("id");
        auVar.b = jSONObject.optString("shop_price");
        auVar.c = jSONObject.optString("market_price");
        auVar.d = jSONObject.optString("name");
        auVar.e = jSONObject.optInt("goods_id");
        auVar.f = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        auVar.g = jSONObject.optString("brief");
        auVar.h = jSONObject.optString("promote_price");
        auVar.k = jSONObject.optString("activity_type");
        auVar.j = jSONObject.optInt("saving_price");
        auVar.i = jSONObject.optString("formatted_saving_price");
        return auVar;
    }
}
