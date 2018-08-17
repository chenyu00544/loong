package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SHOPINFO */
public class at {
    private int a;
    private String b;
    private String c;

    public String a() {
        return this.c;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.a;
    }

    public static at a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        at atVar = new at();
        atVar.a = jSONObject.optInt("id");
        atVar.b = jSONObject.optString("image");
        atVar.c = jSONObject.optString("title");
        return atVar;
    }
}
