package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SELLERGOODS */
public class an {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private ECJia_PHOTO i = new ECJia_PHOTO();
    private String j;
    private String k;
    private int l;

    public String a() {
        return this.j;
    }

    public String b() {
        return this.k;
    }

    public String c() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
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

    public ECJia_PHOTO g() {
        return this.i;
    }

    public String h() {
        return this.e;
    }

    public static an a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        an anVar = new an();
        anVar.a = jSONObject.optString("id");
        anVar.b = jSONObject.optString("name");
        anVar.c = jSONObject.optString("market_price");
        anVar.d = jSONObject.optString("shop_price");
        anVar.e = jSONObject.optString("promote_price");
        anVar.f = jSONObject.optString("promote_start_date");
        anVar.g = jSONObject.optString("promote_end_date");
        anVar.h = jSONObject.optString("brief");
        anVar.i = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        anVar.j = jSONObject.optString("activity_type");
        anVar.l = jSONObject.optInt("saving_price");
        anVar.k = jSONObject.optString("formatted_saving_price");
        return anVar;
    }
}
