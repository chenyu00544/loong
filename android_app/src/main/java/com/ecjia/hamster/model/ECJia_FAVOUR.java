package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_FAVOUR implements Serializable {
    private String a;
    private String b;
    private String c;

    public String getName() {
        return this.a;
    }

    public void setName(String str) {
        this.a = str;
    }

    public String getType() {
        return this.b;
    }

    public void setType(String str) {
        this.b = str;
    }

    public String getType_label() {
        return this.c;
    }

    public void setType_label(String str) {
        this.c = str;
    }

    public static ECJia_FAVOUR fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_FAVOUR eCJia_FAVOUR = new ECJia_FAVOUR();
        eCJia_FAVOUR.a = jSONObject.optString("name");
        eCJia_FAVOUR.b = jSONObject.optString("type");
        eCJia_FAVOUR.c = jSONObject.optString("type_label");
        return eCJia_FAVOUR;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", this.a);
        jSONObject.put("type", this.b);
        jSONObject.put("type_label", this.c);
        return jSONObject;
    }
}
