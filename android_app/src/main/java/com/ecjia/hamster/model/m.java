package com.ecjia.hamster.model;

import com.umeng.analytics.pro.x;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_EXPRESS */
public class m {
    private String a;
    private String b;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public static m a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        m mVar = new m();
        mVar.a = jSONObject.optString("time");
        mVar.b = jSONObject.optString(x.aI);
        return mVar;
    }
}
