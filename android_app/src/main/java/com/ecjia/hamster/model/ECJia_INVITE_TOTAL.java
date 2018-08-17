package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_INVITE_TOTAL implements Serializable {
    private int a;
    private int b;
    private int c;
    private String d;

    public int getInvite_count() {
        return this.a;
    }

    public void setInvite_count(int i) {
        this.a = i;
    }

    public int getInvite_bouns_reward() {
        return this.b;
    }

    public void setInvite_bouns_reward(int i) {
        this.b = i;
    }

    public int getInvite_integral_reward() {
        return this.c;
    }

    public void setInvite_integral_reward(int i) {
        this.c = i;
    }

    public String getInvite_balance_reward() {
        return this.d;
    }

    public void setInvite_balance_reward(String str) {
        this.d = str;
    }

    public static ECJia_INVITE_TOTAL fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_INVITE_TOTAL eCJia_INVITE_TOTAL = new ECJia_INVITE_TOTAL();
        eCJia_INVITE_TOTAL.a = jSONObject.optInt("invite_count");
        eCJia_INVITE_TOTAL.b = jSONObject.optInt("invite_bouns_reward");
        eCJia_INVITE_TOTAL.c = jSONObject.optInt("invite_integral_reward");
        eCJia_INVITE_TOTAL.d = jSONObject.optString("invite_balance_reward");
        return eCJia_INVITE_TOTAL;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("invite_count", this.a);
        jSONObject.put("invite_bouns_reward", this.b);
        jSONObject.put("invite_integral_reward", this.c);
        jSONObject.put("invite_balance_reward", this.d);
        return jSONObject;
    }
}
