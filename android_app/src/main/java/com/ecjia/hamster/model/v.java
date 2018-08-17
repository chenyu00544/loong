package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_INV_TYPE_LIST */
public class v {
    private String a;
    private String b;
    private String c;
    private String d;

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.b;
    }

    public static v a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        v vVar = new v();
        vVar.a = jSONObject.optString("id");
        vVar.b = jSONObject.optString("value");
        vVar.c = jSONObject.optString("label_value");
        vVar.d = jSONObject.optString("rate");
        return vVar;
    }
}
