package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_FILTER_CATEGORY extends ECJia_SelectedInterface {
    private String b;
    private String c;
    private String d;
    private boolean e;

    public String getCat_id() {
        return this.b;
    }

    public void setCat_id(String str) {
        this.b = str;
    }

    public String getCat_name() {
        return this.c;
    }

    public void setCat_name(String str) {
        this.c = str;
    }

    public String getParent_id() {
        return this.d;
    }

    public void setParent_id(String str) {
        this.d = str;
    }

    public boolean isSelected() {
        return this.e;
    }

    public void setSelected(boolean z) {
        this.e = z;
    }

    public static ECJia_FILTER_CATEGORY fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_FILTER_CATEGORY eCJia_FILTER_CATEGORY = new ECJia_FILTER_CATEGORY();
        eCJia_FILTER_CATEGORY.b = jSONObject.optString("cat_id");
        eCJia_FILTER_CATEGORY.c = jSONObject.optString("cat_name");
        eCJia_FILTER_CATEGORY.d = jSONObject.optString("parent_id");
        eCJia_FILTER_CATEGORY.e = jSONObject.optBoolean("selected");
        return eCJia_FILTER_CATEGORY;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cat_id", this.b);
        jSONObject.put("cat_name", this.c);
        jSONObject.put("parent_id", this.d);
        jSONObject.put("selected", this.e);
        return jSONObject;
    }
}
