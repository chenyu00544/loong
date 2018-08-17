package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_ARTICLE */
public class d {
    private String a;
    private int b;
    private String c;
    private String d;

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public int d() {
        return this.b;
    }

    public static d a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        d dVar = new d();
        dVar.c = jSONObject.optString("short_title");
        dVar.b = jSONObject.optInt("id");
        dVar.d = jSONObject.optString("title");
        return dVar;
    }
}
