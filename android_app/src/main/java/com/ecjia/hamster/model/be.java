package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_TOTAL */
public class be {
    private String a;
    private int b;
    private String c;
    private int d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;

    public String a() {
        return this.h;
    }

    public String b() {
        return this.i;
    }

    public String c() {
        return this.a;
    }

    public static be a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        be beVar = new be();
        beVar.a = jSONObject.optString("goods_price");
        beVar.b = jSONObject.optInt("virtual_goods_count");
        beVar.c = jSONObject.optString("market_price");
        beVar.d = jSONObject.optInt("real_goods_count");
        beVar.e = jSONObject.optString("save_rate");
        beVar.f = jSONObject.optString("saving");
        beVar.g = jSONObject.optString("goods_amount");
        beVar.h = jSONObject.optString("discount");
        beVar.i = jSONObject.optString("formated_discount");
        return beVar;
    }
}
