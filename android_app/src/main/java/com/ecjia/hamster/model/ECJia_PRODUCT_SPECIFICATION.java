package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_PRODUCT_SPECIFICATION implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    public String getGoods_attr_ids() {
        return this.a;
    }

    public void setGoods_attr_ids(String str) {
        this.a = str;
    }

    public String getProduct_number() {
        return this.b;
    }

    public void setProduct_number(String str) {
        this.b = str;
    }

    public String getProduct_price() {
        return this.c;
    }

    public void setProduct_price(String str) {
        this.c = str;
    }

    public String getFormat_product_price() {
        return this.d;
    }

    public void setFormat_product_price(String str) {
        this.d = str;
    }

    public String getProduct_promote_price() {
        return this.e;
    }

    public void setProduct_promote_price(String str) {
        this.e = str;
    }

    public String getFormat_product_promote_price() {
        return this.f;
    }

    public void setFormat_product_promote_price(String str) {
        this.f = str;
    }

    public String getProduct_market_price() {
        return this.g;
    }

    public void setProduct_market_price(String str) {
        this.g = str;
    }

    public String getFormat_product_market_price() {
        return this.h;
    }

    public void setFormat_product_market_price(String str) {
        this.h = str;
    }

    public static ECJia_PRODUCT_SPECIFICATION fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_PRODUCT_SPECIFICATION eCJia_PRODUCT_SPECIFICATION = new ECJia_PRODUCT_SPECIFICATION();
        eCJia_PRODUCT_SPECIFICATION.a = jSONObject.optString("goods_attr_ids");
        eCJia_PRODUCT_SPECIFICATION.b = jSONObject.optString("product_number");
        eCJia_PRODUCT_SPECIFICATION.c = jSONObject.optString("product_price");
        eCJia_PRODUCT_SPECIFICATION.d = jSONObject.optString("format_product_price");
        eCJia_PRODUCT_SPECIFICATION.e = jSONObject.optString("product_promote_price");
        eCJia_PRODUCT_SPECIFICATION.f = jSONObject.optString("format_product_promote_price");
        eCJia_PRODUCT_SPECIFICATION.g = jSONObject.optString("product_market_price");
        eCJia_PRODUCT_SPECIFICATION.h = jSONObject.optString("format_product_market_price");
        return eCJia_PRODUCT_SPECIFICATION;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("goods_attr_ids", this.a);
        jSONObject.put("product_number", this.b);
        jSONObject.put("product_price", this.c);
        jSONObject.put("format_product_price", this.d);
        jSONObject.put("product_promote_price", this.e);
        jSONObject.put("format_product_promote_price", this.f);
        jSONObject.put("product_market_price", this.g);
        jSONObject.put("format_product_market_price", this.h);
        return jSONObject;
    }
}
