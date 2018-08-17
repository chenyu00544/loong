package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_REGIONS */
public class ak {
    private String a;
    private String b;
    private String c;
    private int d;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public static ak a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ak akVar = new ak();
        akVar.a = jSONObject.optString("id");
        akVar.b = jSONObject.optString("name");
        akVar.c = jSONObject.optString("parent_id");
        akVar.d = jSONObject.optInt("level");
        return akVar;
    }
}
