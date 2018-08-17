package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_GOODSCOUNT */
public class q {
    public String a;
    public String b;
    public String c;
    public String d;

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

    public static q a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        q qVar = new q();
        qVar.a = jSONObject.optString("count");
        qVar.b = jSONObject.optString("new_goods");
        qVar.d = jSONObject.optString("hot_goods");
        qVar.c = jSONObject.optString("best_goods");
        return qVar;
    }
}
