package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_INV_CONTENT_LIST */
public class u {
    private String a;
    private String b;

    public String a() {
        return this.b;
    }

    public static u a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        u uVar = new u();
        uVar.a = jSONObject.optString("id");
        uVar.b = jSONObject.optString("value");
        return uVar;
    }
}
