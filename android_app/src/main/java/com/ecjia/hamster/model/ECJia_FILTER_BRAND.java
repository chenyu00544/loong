package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_FILTER_BRAND extends ECJia_SelectedInterface {
    private String b;
    private String c;

    public boolean isSelected() {
        return this.a;
    }

    public void setSelected(boolean z) {
        this.a = z;
    }

    public String getBrand_id() {
        return this.b;
    }

    public void setBrand_id(String str) {
        this.b = str;
    }

    public String getBrand_name() {
        return this.c;
    }

    public void setBrand_name(String str) {
        this.c = str;
    }

    public static ECJia_FILTER_BRAND fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_FILTER_BRAND eCJia_FILTER_BRAND = new ECJia_FILTER_BRAND();
        eCJia_FILTER_BRAND.b = jSONObject.optString("brand_id");
        eCJia_FILTER_BRAND.c = jSONObject.optString("brand_name");
        eCJia_FILTER_BRAND.a = jSONObject.optBoolean("selected");
        return eCJia_FILTER_BRAND;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("brand_id", this.b);
        jSONObject.put("brand_name", this.c);
        jSONObject.put("selected", this.a);
        return jSONObject;
    }
}
