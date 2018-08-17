package com.ecjia.hamster.model;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_PAGINATION */
public class ab {
    private int a;
    private int b;

    public void a(int i) {
        this.a = i;
    }

    public void b(int i) {
        this.b = i;
    }

    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("count", this.a);
        jSONObject.put(ParamKey.PAGE, this.b);
        return jSONObject;
    }
}
