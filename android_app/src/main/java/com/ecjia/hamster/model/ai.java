package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_RANK_PRICES */
public class ai {
    private String a;
    private String b;
    private String c;
    private String d;

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public static ai a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ai aiVar = new ai();
        aiVar.a = jSONObject.optString("id");
        aiVar.b = jSONObject.optString("rank_name");
        aiVar.c = jSONObject.optString("unformatted_price");
        aiVar.d = jSONObject.optString("price");
        return aiVar;
    }
}
