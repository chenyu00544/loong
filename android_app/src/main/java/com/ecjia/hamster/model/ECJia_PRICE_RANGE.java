package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_PRICE_RANGE implements Serializable {
    private int a;
    private int b;

    public int getPrice_min() {
        return this.a;
    }

    public void setPrice_min(int i) {
        this.a = i;
    }

    public int getPrice_max() {
        return this.b;
    }

    public void setPrice_max(int i) {
        this.b = i;
    }

    public static ECJia_PRICE_RANGE fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_PRICE_RANGE eCJia_PRICE_RANGE = new ECJia_PRICE_RANGE();
        eCJia_PRICE_RANGE.a = jSONObject.optInt("price_min");
        eCJia_PRICE_RANGE.b = jSONObject.optInt("price_max");
        return eCJia_PRICE_RANGE;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("price_min", this.a);
        jSONObject.put("price_max", this.b);
        return jSONObject;
    }
}
