package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_MERCHANTINFO */
public class x {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.f;
    }

    public String e() {
        return this.g;
    }

    public String f() {
        return this.h;
    }

    public static x a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        x xVar = new x();
        JSONObject optJSONObject = jSONObject.optJSONObject("comment");
        xVar.a = jSONObject.optString("id");
        xVar.b = jSONObject.optString("seller_name");
        xVar.c = jSONObject.optString("seller_logo");
        xVar.d = jSONObject.optString("goods_count");
        xVar.e = jSONObject.optString("follower");
        xVar.f = optJSONObject.optString("comment_goods");
        xVar.g = optJSONObject.optString("comment_server");
        xVar.h = optJSONObject.optString("comment_delivery");
        return xVar;
    }
}
