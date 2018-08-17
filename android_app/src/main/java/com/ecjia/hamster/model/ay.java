package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SUB_BUTTON */
public class ay {
    private String a;
    private String b;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public static ay a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ay ayVar = new ay();
        ayVar.a = jSONObject.optString("name");
        ayVar.b = jSONObject.optString("url");
        return ayVar;
    }
}
