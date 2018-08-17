package com.ecjia.hamster.model;

import com.tencent.open.SocialConstants;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_ORDER_INFO implements Serializable {
    private String a;
    private String b;
    private int c;
    private String d;
    private String e;
    private String f;

    public String getPay_code() {
        return this.a;
    }

    public void setPay_code(String str) {
        this.a = str;
    }

    public String getDesc() {
        return this.f;
    }

    public void setDesc(String str) {
        this.f = str;
    }

    public String getOrder_amount() {
        return this.b;
    }

    public void setOrder_amount(String str) {
        this.b = str;
    }

    public int getOrder_id() {
        return this.c;
    }

    public void setOrder_id(int i) {
        this.c = i;
    }

    public String getOrder_sn() {
        return this.d;
    }

    public void setOrder_sn(String str) {
        this.d = str;
    }

    public String getSubject() {
        return this.e;
    }

    public void setSubject(String str) {
        this.e = str;
    }

    public static ECJia_ORDER_INFO fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_ORDER_INFO eCJia_ORDER_INFO = new ECJia_ORDER_INFO();
        eCJia_ORDER_INFO.a = jSONObject.optString("pay_code");
        eCJia_ORDER_INFO.b = jSONObject.optString("order_amount");
        eCJia_ORDER_INFO.c = jSONObject.optInt("order_id");
        eCJia_ORDER_INFO.e = jSONObject.optString("subject");
        eCJia_ORDER_INFO.f = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        eCJia_ORDER_INFO.d = jSONObject.optString("order_sn");
        return eCJia_ORDER_INFO;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("pay_code", this.a);
        jSONObject.put("order_amount", this.b);
        jSONObject.put("order_id", this.c);
        jSONObject.put("subject", this.e);
        jSONObject.put(SocialConstants.PARAM_APP_DESC, this.f);
        jSONObject.put("order_sn", this.d);
        return jSONObject;
    }
}
