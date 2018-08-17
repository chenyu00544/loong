package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_GOODS_ACTIVE */
public class r {
    private String a;
    private String b;
    private String c;

    public static r a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        r rVar = new r();
        rVar.a = jSONObject.optString("name");
        rVar.b = jSONObject.optString("type");
        rVar.c = jSONObject.optString("type_label");
        return rVar;
    }
}
