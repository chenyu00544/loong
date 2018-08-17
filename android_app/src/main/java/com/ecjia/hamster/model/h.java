package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_CHECKIN_RECORD */
public class h {
    private String a;
    private int b;
    private String c;
    private String d;
    private long e;
    private String f;

    public long a() {
        return this.e;
    }

    public String b() {
        return this.f;
    }

    public String c() {
        return this.a;
    }

    public String d() {
        return this.c;
    }

    public int e() {
        return this.b;
    }

    public static h a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        h hVar = new h();
        hVar.a = jSONObject.optString("user_name");
        hVar.b = jSONObject.optInt("integral");
        hVar.c = jSONObject.optString("avatar_img");
        hVar.d = jSONObject.optString("label_integral");
        hVar.e = jSONObject.optLong("time");
        hVar.f = jSONObject.optString("formatted_time");
        return hVar;
    }
}
