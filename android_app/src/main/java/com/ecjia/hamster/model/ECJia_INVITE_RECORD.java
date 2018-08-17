package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_INVITE_RECORD implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    public String getInvitee_name() {
        return this.a;
    }

    public void setInvitee_name(String str) {
        this.a = str;
    }

    public String getReg_time() {
        return this.b;
    }

    public void setReg_time(String str) {
        this.b = str;
    }

    public String getLabel_award_type() {
        return this.c;
    }

    public void setLabel_award_type(String str) {
        this.c = str;
    }

    public String getAward_type() {
        return this.d;
    }

    public void setAward_type(String str) {
        this.d = str;
    }

    public String getGive_award() {
        return this.e;
    }

    public void setGive_award(String str) {
        this.e = str;
    }

    public String getAward_time() {
        return this.f;
    }

    public void setAward_time(String str) {
        this.f = str;
    }

    public static ECJia_INVITE_RECORD fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_INVITE_RECORD eCJia_INVITE_RECORD = new ECJia_INVITE_RECORD();
        eCJia_INVITE_RECORD.a = jSONObject.optString("invitee_name");
        eCJia_INVITE_RECORD.b = jSONObject.optString("reg_time");
        eCJia_INVITE_RECORD.c = jSONObject.optString("label_reward_type");
        eCJia_INVITE_RECORD.d = jSONObject.optString("reward_type");
        eCJia_INVITE_RECORD.e = jSONObject.optString("give_reward");
        eCJia_INVITE_RECORD.f = jSONObject.optString("reward_time");
        return eCJia_INVITE_RECORD;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("invitee_name", this.a);
        jSONObject.put("reg_time", this.b);
        jSONObject.put("label_reward_type", this.c);
        jSONObject.put("reward_type", this.d);
        jSONObject.put("give_reward", this.e);
        jSONObject.put("reward_time", this.f);
        return jSONObject;
    }
}
