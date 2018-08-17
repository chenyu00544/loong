package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SPECIFICATION_VALUE */
public class aw {
    private int a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    public String a() {
        return this.e;
    }

    public void a(String str) {
        this.e = str;
    }

    public String b() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public int c() {
        return this.a;
    }

    public String d() {
        return this.b;
    }

    public String e() {
        return this.c;
    }

    public static aw a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        aw awVar = new aw();
        awVar.a = jSONObject.optInt("id");
        awVar.b = jSONObject.optString("price", "0.00");
        awVar.c = jSONObject.optString("label");
        awVar.d = jSONObject.optString("format_price");
        return awVar;
    }

    public JSONObject f() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.a);
        jSONObject.put("price", this.b);
        jSONObject.put("label", this.c);
        jSONObject.put("format_price", this.d);
        jSONObject.put("parent_attr_type", this.f);
        jSONObject.put("parent_name", this.e);
        return jSONObject;
    }
}
