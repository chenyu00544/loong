package com.ecjia.hamster.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_PROPERTY */
public class ag {
    private String a;
    private String b;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public static ag a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ag agVar = new ag();
        agVar.a = jSONObject.optString("name");
        agVar.b = jSONObject.optString("value");
        return agVar;
    }

    public JSONObject c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("name", this.a);
        jSONObject.put("value", this.b);
        return jSONObject;
    }
}
