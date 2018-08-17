package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_VERIFY_INFO */
public class bh {
    private String a;
    private String b;
    private String c;
    private String d;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public static bh a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        bh bhVar = new bh();
        bhVar.a = jSONObject.optString("real_id");
        bhVar.b = jSONObject.optString("real_name");
        bhVar.c = jSONObject.optString("bank_card");
        bhVar.d = jSONObject.optString("bank_name");
        return bhVar;
    }
}
