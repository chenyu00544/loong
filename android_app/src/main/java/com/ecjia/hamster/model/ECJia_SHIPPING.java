package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_SHIPPING implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private boolean k;

    public boolean isSelected() {
        return this.k;
    }

    public void setSelected(boolean z) {
        this.k = z;
    }

    public String getSupport_cod() {
        return this.a;
    }

    public void setSupport_cod(String str) {
        this.a = str;
    }

    public String getShipping_fee() {
        return this.j;
    }

    public void setShipping_fee(String str) {
        this.j = str;
    }

    public String getShipping_desc() {
        return this.b;
    }

    public void setShipping_desc(String str) {
        this.b = str;
    }

    public String getShipping_id() {
        return this.c;
    }

    public void setShipping_id(String str) {
        this.c = str;
    }

    public String getFormat_shipping_fee() {
        return this.d;
    }

    public void setFormat_shipping_fee(String str) {
        this.d = str;
    }

    public String getInsure() {
        return this.e;
    }

    public void setInsure(String str) {
        this.e = str;
    }

    public String getInsure_formated() {
        return this.f;
    }

    public void setInsure_formated(String str) {
        this.f = str;
    }

    public String getShipping_code() {
        return this.g;
    }

    public void setShipping_code(String str) {
        this.g = str;
    }

    public String getShipping_name() {
        return this.h;
    }

    public void setShipping_name(String str) {
        this.h = str;
    }

    public String getFree_money() {
        return this.i;
    }

    public void setFree_money(String str) {
        this.i = str;
    }

    public static ECJia_SHIPPING fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_SHIPPING eCJia_SHIPPING = new ECJia_SHIPPING();
        eCJia_SHIPPING.a = jSONObject.optString("support_cod");
        eCJia_SHIPPING.b = jSONObject.optString("shipping_desc");
        eCJia_SHIPPING.c = jSONObject.optString("shipping_id");
        eCJia_SHIPPING.d = jSONObject.optString("format_shipping_fee");
        eCJia_SHIPPING.e = jSONObject.optString("insure");
        eCJia_SHIPPING.f = jSONObject.optString("insure_formated");
        eCJia_SHIPPING.g = jSONObject.optString("shipping_code");
        eCJia_SHIPPING.h = jSONObject.optString("shipping_name");
        eCJia_SHIPPING.i = jSONObject.optString("free_money");
        eCJia_SHIPPING.j = jSONObject.optString("shipping_fee");
        eCJia_SHIPPING.k = jSONObject.optBoolean("isSelected");
        return eCJia_SHIPPING;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("support_cod", this.a);
        jSONObject.put("shipping_desc", this.b);
        jSONObject.put("shipping_id", this.c);
        jSONObject.put("format_shipping_fee", this.d);
        jSONObject.put("insure", this.e);
        jSONObject.put("insure_formated", this.f);
        jSONObject.put("shipping_code", this.g);
        jSONObject.put("shipping_name", this.h);
        jSONObject.put("free_money", this.i);
        jSONObject.put("shipping_fee", this.j);
        jSONObject.put("isSelected", this.k);
        return jSONObject;
    }
}
