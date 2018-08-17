package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_GOODS_ATTR implements Serializable {
    private String a;
    private String b;

    public String getValue() {
        return this.b;
    }

    public void setValue(String str) {
        this.b = str;
    }

    public String getName() {
        return this.a;
    }

    public void setName(String str) {
        this.a = str;
    }

    public static ECJia_GOODS_ATTR fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_GOODS_ATTR eCJia_GOODS_ATTR = new ECJia_GOODS_ATTR();
        eCJia_GOODS_ATTR.a = jSONObject.optString("name");
        eCJia_GOODS_ATTR.b = jSONObject.optString("value");
        return eCJia_GOODS_ATTR;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", this.a);
        jSONObject.put("value", this.b);
        return jSONObject;
    }
}
