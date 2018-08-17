package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_PHOTO implements Serializable {
    private String a;
    private String b;
    private String c;

    public String getSmall() {
        return this.a;
    }

    public void setSmall(String str) {
        this.a = str;
    }

    public String getThumb() {
        return this.b;
    }

    public void setThumb(String str) {
        this.b = str;
    }

    public String getUrl() {
        return this.c;
    }

    public void setUrl(String str) {
        this.c = str;
    }

    public static ECJia_PHOTO fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_PHOTO eCJia_PHOTO = new ECJia_PHOTO();
        eCJia_PHOTO.a = jSONObject.optString("small");
        eCJia_PHOTO.b = jSONObject.optString("thumb");
        eCJia_PHOTO.c = jSONObject.optString("url");
        return eCJia_PHOTO;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("small", this.a);
        jSONObject.put("thumb", this.b);
        jSONObject.put("url", this.c);
        return jSONObject;
    }
}
