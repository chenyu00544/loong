package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_PAGINATED */
public class aa {
    private int a;
    private int b;
    private int c;

    public int a() {
        return this.b;
    }

    public static aa a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        aa aaVar = new aa();
        aaVar.a = jSONObject.optInt("total");
        aaVar.b = jSONObject.optInt("more");
        aaVar.c = jSONObject.optInt("count");
        return aaVar;
    }
}
