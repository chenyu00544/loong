package com.ecjia.hamster.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_PRICE */
public class ae {
    private int a;
    private String b;
    private String c;

    public static ae a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ae aeVar = new ae();
        aeVar.a = jSONObject.optInt("id");
        aeVar.b = jSONObject.optString("price");
        aeVar.c = jSONObject.optString("rank_name");
        return aeVar;
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("id", this.a);
        jSONObject.put("price", this.b);
        jSONObject.put("rank_name", this.c);
        return jSONObject;
    }
}
