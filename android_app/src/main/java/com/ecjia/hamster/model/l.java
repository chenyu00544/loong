package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_CONSULTION */
public class l {
    private String a;
    private String b;
    private String c;

    public String a() {
        return this.c;
    }

    public void a(String str) {
        this.c = str;
    }

    public String b() {
        return this.a;
    }

    public void b(String str) {
        this.a = str;
    }

    public static l a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        l lVar = new l();
        lVar.a = jSONObject.optString("content");
        lVar.b = jSONObject.optString("time");
        lVar.c = jSONObject.optString("is_myself");
        return lVar;
    }
}
