package com.ecjia.hamster.model;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_FAVOURABLE_GROUP */
public class n {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private List<String> i = new ArrayList();

    public String a() {
        return this.h;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.g;
    }

    public List<String> e() {
        return this.i;
    }

    public static n a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        n nVar = new n();
        nVar.a = jSONObject.optString("activity_id");
        nVar.b = jSONObject.optString("activity_name");
        nVar.c = jSONObject.optString("min_amount");
        nVar.d = jSONObject.optString("max_amount");
        nVar.e = jSONObject.optString("discount");
        nVar.f = jSONObject.optString("type");
        nVar.g = jSONObject.optString("type_label");
        nVar.h = jSONObject.optString("label_discount");
        JSONArray optJSONArray = jSONObject.optJSONArray("rec_id");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                nVar.i.add(optJSONArray.optString(i));
            }
        }
        return nVar;
    }
}
