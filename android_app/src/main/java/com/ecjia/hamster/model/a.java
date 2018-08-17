package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_ACCESS_TOKEN */
public class a {
    private String a;
    private String b;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public static a a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        a aVar = new a();
        aVar.a = jSONObject.optString("access_token");
        aVar.b = jSONObject.optString("expires_in");
        return aVar;
    }
}
