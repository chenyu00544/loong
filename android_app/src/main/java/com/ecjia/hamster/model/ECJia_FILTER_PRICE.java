package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_FILTER_PRICE extends ECJia_SelectedInterface {
    private String b;
    private int c;
    private int d;

    public String getPrice_range() {
        return this.b;
    }

    public void setPrice_range(String str) {
        this.b = str;
    }

    public int getPrice_min() {
        return this.c;
    }

    public void setPrice_min(int i) {
        this.c = i;
    }

    public int getPrice_max() {
        return this.d;
    }

    public void setPrice_max(int i) {
        this.d = i;
    }

    public static ECJia_FILTER_PRICE fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_FILTER_PRICE eCJia_FILTER_PRICE = new ECJia_FILTER_PRICE();
        eCJia_FILTER_PRICE.b = jSONObject.optString("price_range");
        eCJia_FILTER_PRICE.c = jSONObject.optInt("price_min");
        eCJia_FILTER_PRICE.d = jSONObject.optInt("price_max");
        eCJia_FILTER_PRICE.a = jSONObject.optBoolean("selected");
        return eCJia_FILTER_PRICE;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("price_range", this.b);
        jSONObject.put("price_min", this.c);
        jSONObject.put("price_max", this.d);
        jSONObject.put("selected", this.a);
        return jSONObject;
    }

    public boolean isSelected() {
        return this.a;
    }

    public void setSelected(boolean z) {
        this.a = z;
    }
}
