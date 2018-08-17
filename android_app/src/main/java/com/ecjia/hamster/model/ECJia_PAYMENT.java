package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_PAYMENT implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private boolean h;

    public String getIs_online() {
        return this.g;
    }

    public void setIs_online(String str) {
        this.g = str;
    }

    public String getIs_cod() {
        return this.a;
    }

    public void setIs_cod(String str) {
        this.a = str;
    }

    public String getPay_name() {
        return this.f;
    }

    public void setPay_name(String str) {
        this.f = str;
    }

    public String getPay_code() {
        return this.b;
    }

    public void setPay_code(String str) {
        this.b = str;
    }

    public String getPay_fee() {
        return this.c;
    }

    public void setPay_fee(String str) {
        this.c = str;
    }

    public String getPay_id() {
        return this.d;
    }

    public void setPay_id(String str) {
        this.d = str;
    }

    public String getFormated_pay_fee() {
        return this.e;
    }

    public void setFormated_pay_fee(String str) {
        this.e = str;
    }

    public static ECJia_PAYMENT fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_PAYMENT eCJia_PAYMENT = new ECJia_PAYMENT();
        eCJia_PAYMENT.a = jSONObject.optString("is_cod");
        eCJia_PAYMENT.b = jSONObject.optString("pay_code");
        eCJia_PAYMENT.c = jSONObject.optString("pay_fee");
        eCJia_PAYMENT.d = jSONObject.optString("pay_id");
        eCJia_PAYMENT.e = jSONObject.optString("formated_pay_fee");
        eCJia_PAYMENT.f = jSONObject.optString("pay_name");
        eCJia_PAYMENT.g = jSONObject.optString("is_online");
        eCJia_PAYMENT.h = jSONObject.optBoolean("isSelected");
        return eCJia_PAYMENT;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_cod", this.a);
        jSONObject.put("pay_code", this.b);
        jSONObject.put("pay_fee", this.c);
        jSONObject.put("pay_id", this.d);
        jSONObject.put("formated_pay_fee", this.e);
        jSONObject.put("pay_name", this.f);
        jSONObject.put("is_online", this.g);
        jSONObject.put("isSelected", this.h);
        return jSONObject;
    }

    public boolean isSelected() {
        return this.h;
    }

    public void setSelected(boolean z) {
        this.h = z;
    }
}
