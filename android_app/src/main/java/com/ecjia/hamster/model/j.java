package com.ecjia.hamster.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_COLLECT_LIST */
public class j {
    private String a;
    private String b;
    private String c;
    private int d;
    private ECJia_PHOTO e = new ECJia_PHOTO();
    private String f;
    private int g;
    private String h;
    private String i;
    private int j;

    public String a() {
        return this.h;
    }

    public String b() {
        return this.i;
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

    public int f() {
        return this.d;
    }

    public ECJia_PHOTO g() {
        return this.e;
    }

    public String h() {
        return this.f;
    }

    public int i() {
        return this.g;
    }

    public static j a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        j jVar = new j();
        jVar.a = jSONObject.optString("shop_price");
        jVar.b = jSONObject.optString("market_price");
        jVar.c = jSONObject.optString("name");
        jVar.d = jSONObject.optInt("goods_id");
        jVar.e = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        jVar.f = jSONObject.optString("promote_price");
        jVar.g = jSONObject.optInt("rec_id");
        jVar.h = jSONObject.optString("activity_type");
        jVar.j = jSONObject.optInt("saving_price");
        jVar.i = jSONObject.optString("formatted_saving_price");
        return jVar;
    }

    public JSONObject j() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("shop_price", this.a);
        jSONObject.put("market_price", this.b);
        jSONObject.put("name", this.c);
        jSONObject.put("goods_id", this.d);
        if (this.e != null) {
            jSONObject.put("img", this.e.toJson());
        }
        jSONObject.put("promote_price", this.f);
        jSONObject.put("rec_id", this.g);
        jSONObject.put("activity_type", this.h);
        jSONObject.put("saving_price", this.j);
        jSONObject.put("formatted_saving_price", this.i);
        return jSONObject;
    }
}
