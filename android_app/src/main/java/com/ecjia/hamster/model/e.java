package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_BONUSINFO */
public class e {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;

    public String a() {
        return this.b;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.i;
    }

    public String e() {
        return this.j;
    }

    public static e a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        e eVar = new e();
        eVar.a = jSONObject.optString("bonus_id");
        eVar.b = jSONObject.optString("bonus_name");
        eVar.c = jSONObject.optString("bonus_amount");
        eVar.d = jSONObject.optString("formatted_bonus_amount");
        eVar.e = jSONObject.optString("request_amount");
        eVar.f = jSONObject.optString("formatted_request_amount");
        eVar.g = jSONObject.optString("start_date");
        eVar.h = jSONObject.optString("end_date");
        eVar.i = jSONObject.optString("formatted_start_date");
        eVar.j = jSONObject.optString("formatted_end_date");
        return eVar;
    }
}
