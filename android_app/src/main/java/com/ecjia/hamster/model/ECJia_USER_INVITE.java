package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_USER_INVITE implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;

    public String getInvite_code() {
        return this.a;
    }

    public void setInvite_code(String str) {
        this.a = str;
    }

    public String getInvite_qrcode_image() {
        return this.b;
    }

    public void setInvite_qrcode_image(String str) {
        this.b = str;
    }

    public String getInvite_template() {
        return this.c;
    }

    public void setInvite_template(String str) {
        this.c = str;
    }

    public String getInvite_explain() {
        return this.d;
    }

    public void setInvite_explain(String str) {
        this.d = str;
    }

    public String getInvite_url() {
        return this.e;
    }

    public void setInvite_url(String str) {
        this.e = str;
    }

    public static ECJia_USER_INVITE fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_USER_INVITE eCJia_USER_INVITE = new ECJia_USER_INVITE();
        eCJia_USER_INVITE.a = jSONObject.optString("invite_code");
        eCJia_USER_INVITE.b = jSONObject.optString("invite_qrcode_image");
        eCJia_USER_INVITE.c = jSONObject.optString("invite_template");
        eCJia_USER_INVITE.d = jSONObject.optString("invite_explain");
        eCJia_USER_INVITE.e = jSONObject.optString("invite_url");
        return eCJia_USER_INVITE;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("invite_code", this.a);
        jSONObject.put("invite_qrcode_image", this.b);
        jSONObject.put("invite_template", this.c);
        jSONObject.put("invite_explain", this.d);
        jSONObject.put("invite_url", this.e);
        return jSONObject;
    }
}
