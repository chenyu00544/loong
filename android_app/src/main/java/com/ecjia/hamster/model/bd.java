package com.ecjia.hamster.model;

import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_TOPLINE */
public class bd {
    String a;
    String b;
    String c;
    String d;
    String e;
    private Map<String, String> f = new HashMap();

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public static bd a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        bd bdVar = new bd();
        bdVar.b = jSONObject.optString("image");
        bdVar.a = jSONObject.optString("time");
        bdVar.c = jSONObject.optString("tag");
        bdVar.d = jSONObject.optString("title");
        bdVar.e = jSONObject.optString("url");
        return bdVar;
    }
}
