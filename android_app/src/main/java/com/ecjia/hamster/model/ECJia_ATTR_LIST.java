package com.ecjia.hamster.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_ATTR_LIST extends ECJia_SelectedInterface {
    private String b;
    private String c;

    public String getAttr_id() {
        return this.b;
    }

    public void setAttr_id(String str) {
        this.b = str;
    }

    public String getAttr_value() {
        return this.c;
    }

    public void setAttr_value(String str) {
        this.c = str;
    }

    public static ECJia_ATTR_LIST fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_ATTR_LIST eCJia_ATTR_LIST = new ECJia_ATTR_LIST();
        eCJia_ATTR_LIST.b = jSONObject.optString("attr_id");
        eCJia_ATTR_LIST.c = jSONObject.optString("attr_value");
        eCJia_ATTR_LIST.a = jSONObject.optBoolean("selected");
        return eCJia_ATTR_LIST;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("attr_id", this.b);
        jSONObject.put("attr_value", this.c);
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
