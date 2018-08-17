package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_GOODS_COUPON implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;

    public String getBonus_type_id() {
        return this.a;
    }

    public void setBonus_type_id(String str) {
        this.a = str;
    }

    public String getBonus_name() {
        return this.b;
    }

    public void setBonus_name(String str) {
        this.b = str;
    }

    public String getBonus_amount() {
        return this.c;
    }

    public void setBonus_amount(String str) {
        this.c = str;
    }

    public String getFormatted_bonus_amount() {
        return this.d;
    }

    public void setFormatted_bonus_amount(String str) {
        this.d = str;
    }

    public String getRequest_amount() {
        return this.e;
    }

    public void setRequest_amount(String str) {
        this.e = str;
    }

    public String getFormatted_request_amount() {
        return this.f;
    }

    public void setFormatted_request_amount(String str) {
        this.f = str;
    }

    public String getFormatted_start_date() {
        return this.g;
    }

    public void setFormatted_start_date(String str) {
        this.g = str;
    }

    public String getFormatted_end_date() {
        return this.h;
    }

    public void setFormatted_end_date(String str) {
        this.h = str;
    }

    public String getReceived_coupon() {
        return this.i;
    }

    public void setReceived_coupon(String str) {
        this.i = str;
    }

    public static ECJia_GOODS_COUPON fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_GOODS_COUPON eCJia_GOODS_COUPON = new ECJia_GOODS_COUPON();
        eCJia_GOODS_COUPON.a = jSONObject.optString("bonus_type_id");
        eCJia_GOODS_COUPON.b = jSONObject.optString("bonus_name");
        eCJia_GOODS_COUPON.c = jSONObject.optString("bonus_amount");
        eCJia_GOODS_COUPON.d = jSONObject.optString("formatted_bonus_amount");
        eCJia_GOODS_COUPON.e = jSONObject.optString("request_amount");
        eCJia_GOODS_COUPON.f = jSONObject.optString("formatted_request_amount");
        eCJia_GOODS_COUPON.g = jSONObject.optString("formatted_start_date");
        eCJia_GOODS_COUPON.h = jSONObject.optString("formatted_end_date");
        eCJia_GOODS_COUPON.i = jSONObject.optString("received_coupon");
        return eCJia_GOODS_COUPON;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("bonus_id", this.a);
        jSONObject.put("bonus_name", this.b);
        jSONObject.put("bonus_amount", this.c);
        jSONObject.put("formatted_bonus_amount", this.d);
        jSONObject.put("request_amount", this.e);
        jSONObject.put("formatted_request_amount", this.f);
        jSONObject.put("formatted_start_date", this.g);
        jSONObject.put("formatted_end_date", this.h);
        jSONObject.put("received_coupon", this.i);
        return jSONObject;
    }
}
