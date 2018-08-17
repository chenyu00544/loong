package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SHOPCOMMENT */
public class aq {
    private String a;
    private String b;
    private String c;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public static aq a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        aq aqVar = new aq();
        aqVar.a = jSONObject.optString("comment_goods");
        aqVar.b = jSONObject.optString("comment_server");
        aqVar.c = jSONObject.optString("comment_delivery");
        return aqVar;
    }
}
