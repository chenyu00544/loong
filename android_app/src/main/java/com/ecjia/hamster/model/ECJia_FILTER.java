package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_FILTER implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private ECJia_PRICE_RANGE f = new ECJia_PRICE_RANGE();
    private String g;

    public String getFilter_attr() {
        return this.g;
    }

    public void setFilter_attr(String str) {
        this.g = str;
    }

    public String getKeywords() {
        return this.a;
    }

    public void setKeywords(String str) {
        this.a = str;
    }

    public ECJia_PRICE_RANGE getPrice_range() {
        return this.f;
    }

    public void setPrice_range(ECJia_PRICE_RANGE eCJia_PRICE_RANGE) {
        this.f = eCJia_PRICE_RANGE;
    }

    public String getSort_by() {
        return this.b;
    }

    public void setSort_by(String str) {
        this.b = str;
    }

    public String getBrand_id() {
        return this.c;
    }

    public void setBrand_id(String str) {
        this.c = str;
    }

    public String getCategory_id() {
        return this.e;
    }

    public void setCategory_id(String str) {
        this.e = str;
    }

    public String getShop_id() {
        return this.d;
    }

    public void setShop_id(String str) {
        this.d = str;
    }

    public static ECJia_FILTER fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_FILTER eCJia_FILTER = new ECJia_FILTER();
        eCJia_FILTER.a = jSONObject.optString("keywords");
        eCJia_FILTER.b = jSONObject.optString("sort_by");
        eCJia_FILTER.c = jSONObject.optString("brand_id");
        eCJia_FILTER.d = jSONObject.optString("shop_id");
        eCJia_FILTER.e = jSONObject.optString("category_id");
        eCJia_FILTER.g = jSONObject.optString("filter_attr");
        eCJia_FILTER.f = ECJia_PRICE_RANGE.fromJson(jSONObject.optJSONObject("price_range"));
        return eCJia_FILTER;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("keywords", this.a);
        jSONObject.put("sort_by", this.b);
        jSONObject.put("brand_id", this.c);
        jSONObject.put("category_id", this.e);
        jSONObject.put("shop_id", this.d);
        if (this.f != null) {
            jSONObject.put("price_range", this.f.toJson());
        }
        jSONObject.put("filter_attr", this.g);
        return jSONObject;
    }
}
