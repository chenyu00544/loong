package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_INVITE_REWARD implements Serializable {
    private String a;
    private String b;
    private int c;
    private int d;
    private int e;
    private String f;

    public String getInvite_data() {
        return this.a;
    }

    public void setInvite_data(String str) {
        this.a = str;
    }

    public String getLabel_invite_data() {
        return this.b;
    }

    public void setLabel_invite_data(String str) {
        this.b = str;
    }

    public int getInvite_count() {
        return this.c;
    }

    public void setInvite_count(int i) {
        this.c = i;
    }

    public int getInvite_bouns_reward() {
        return this.d;
    }

    public void setInvite_bouns_reward(int i) {
        this.d = i;
    }

    public int getInvite_integral_reward() {
        return this.e;
    }

    public void setInvite_integral_reward(int i) {
        this.e = i;
    }

    public String getInvite_balance_reward() {
        return this.f;
    }

    public void setInvite_balance_reward(String str) {
        this.f = str;
    }

    public static ECJia_INVITE_REWARD fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_INVITE_REWARD eCJia_INVITE_REWARD = new ECJia_INVITE_REWARD();
        eCJia_INVITE_REWARD.a = jSONObject.optString("invite_data");
        eCJia_INVITE_REWARD.b = jSONObject.optString("label_invite_data");
        eCJia_INVITE_REWARD.c = jSONObject.optInt("invite_count");
        eCJia_INVITE_REWARD.d = jSONObject.optInt("invite_bouns_reward");
        eCJia_INVITE_REWARD.e = jSONObject.optInt("invite_integral_reward");
        eCJia_INVITE_REWARD.f = jSONObject.optString("invite_balance_reward");
        return eCJia_INVITE_REWARD;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("invite_data", this.a);
        jSONObject.put("label_invite_data", this.b);
        jSONObject.put("invite_count", this.c);
        jSONObject.put("invite_bouns_reward", this.d);
        jSONObject.put("invite_integral_reward", this.e);
        jSONObject.put("invite_balance_reward", this.f);
        return jSONObject;
    }
}
