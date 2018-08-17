package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_RELATED_GOOD */
public class al {
    private ECJia_PHOTO a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private int h;
    private String i;

    public ECJia_PHOTO a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public int d() {
        return this.h;
    }

    public String e() {
        return this.i;
    }

    public static al a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        al alVar = new al();
        try {
            alVar.a = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        alVar.i = jSONObject.optString("name");
        alVar.c = jSONObject.optString("shop_price");
        alVar.h = jSONObject.optInt("goods_id");
        alVar.b = jSONObject.optString("promote_price");
        alVar.d = jSONObject.optString("market_price");
        alVar.f = jSONObject.optString("formated_promote_price");
        alVar.g = jSONObject.optString("formated_shop_price");
        alVar.e = jSONObject.optString("formated_market_price");
        return alVar;
    }
}
