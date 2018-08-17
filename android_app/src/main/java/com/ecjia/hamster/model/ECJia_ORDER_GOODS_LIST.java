package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_ORDER_GOODS_LIST implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private ECJia_PHOTO e = new ECJia_PHOTO();
    private String f;
    private String g;
    private String h;
    private int i;
    private String j;
    private int k;
    private int l;
    private int m;

    public int getAllow_return() {
        return this.l;
    }

    public void setAllow_return(int i) {
        this.l = i;
    }

    public int getReturn_number() {
        return this.m;
    }

    public void setReturn_number(int i) {
        this.m = i;
    }

    public String getRec_id() {
        return this.d;
    }

    public void setRec_id(String str) {
        this.d = str;
    }

    public int getIs_commented() {
        return this.k;
    }

    public void setIs_commented(int i) {
        this.k = i;
    }

    public String getActivity_type() {
        return this.j;
    }

    public void setActivity_type(String str) {
        this.j = str;
    }

    public String getFormatted_saving_price() {
        return this.h;
    }

    public void setFormatted_saving_price(String str) {
        this.h = str;
    }

    public int getSaving_price() {
        return this.i;
    }

    public void setSaving_price(int i) {
        this.i = i;
    }

    public String getGoods_number() {
        return this.a;
    }

    public void setGoods_number(String str) {
        this.a = str;
    }

    public String getSubtotal() {
        return this.g;
    }

    public void setSubtotal(String str) {
        this.g = str;
    }

    public String getGoods_id() {
        return this.b;
    }

    public void setGoods_id(String str) {
        this.b = str;
    }

    public String getName() {
        return this.c;
    }

    public void setName(String str) {
        this.c = str;
    }

    public ECJia_PHOTO getImg() {
        return this.e;
    }

    public void setImg(ECJia_PHOTO eCJia_PHOTO) {
        this.e = eCJia_PHOTO;
    }

    public String getFormated_shop_price() {
        return this.f;
    }

    public void setFormated_shop_price(String str) {
        this.f = str;
    }

    public static ECJia_ORDER_GOODS_LIST fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_ORDER_GOODS_LIST eCJia_ORDER_GOODS_LIST = new ECJia_ORDER_GOODS_LIST();
        eCJia_ORDER_GOODS_LIST.a = jSONObject.optString("goods_number");
        eCJia_ORDER_GOODS_LIST.b = jSONObject.optString("goods_id");
        eCJia_ORDER_GOODS_LIST.c = jSONObject.optString("name");
        eCJia_ORDER_GOODS_LIST.e = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        eCJia_ORDER_GOODS_LIST.f = jSONObject.optString("formated_shop_price");
        eCJia_ORDER_GOODS_LIST.g = jSONObject.optString("subtotal");
        eCJia_ORDER_GOODS_LIST.j = jSONObject.optString("activity_type");
        eCJia_ORDER_GOODS_LIST.i = jSONObject.optInt("saving_price");
        eCJia_ORDER_GOODS_LIST.h = jSONObject.optString("formatted_saving_price");
        eCJia_ORDER_GOODS_LIST.k = jSONObject.optInt("is_commented");
        eCJia_ORDER_GOODS_LIST.d = jSONObject.optString("rec_id");
        eCJia_ORDER_GOODS_LIST.m = jSONObject.optInt("return_number");
        eCJia_ORDER_GOODS_LIST.l = jSONObject.optInt("allow_return");
        return eCJia_ORDER_GOODS_LIST;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("goods_number", this.a);
        jSONObject.put("goods_id", this.b);
        jSONObject.put("name", this.c);
        if (this.e != null) {
            jSONObject.put("img", this.e.toJson());
        }
        jSONObject.put("formated_shop_price", this.f);
        jSONObject.put("subtotal", this.g);
        jSONObject.put("activity_type", this.j);
        jSONObject.put("saving_price", this.i);
        jSONObject.put("formatted_saving_price", this.h);
        jSONObject.put("is_commented", this.k);
        jSONObject.put("rec_id", this.d);
        jSONObject.put("return_number", this.m);
        jSONObject.put("allow_return", this.l);
        return jSONObject;
    }
}
