package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SESSION */
public class ap {
    private static ap c;
    public String a;
    public String b;

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public static ap c() {
        if (c == null) {
            c = new ap();
        }
        return c;
    }

    public static ap a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ap c = c();
        c.a = jSONObject.optString("uid");
        c.b = jSONObject.optString("sid");
        return c;
    }

    public JSONObject d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("uid", this.a);
        jSONObject.put("sid", this.b);
        return jSONObject;
    }
}
