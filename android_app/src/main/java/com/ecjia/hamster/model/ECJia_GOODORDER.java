package com.ecjia.hamster.model;

import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_GOODORDER implements Serializable {
    private String a;
    private String b;
    private ArrayList<ECJia_ORDER_GOODS_LIST> c = new ArrayList();
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    public ECJia_ORDER_INFO order_info = new ECJia_ORDER_INFO();
    private boolean p;
    private String q;
    private String r;

    public String getOrder_status() {
        return this.l;
    }

    public void setOrder_status(String str) {
        this.l = str;
    }

    public String getPay_status() {
        return this.m;
    }

    public void setPay_status(String str) {
        this.m = str;
    }

    public String getLabel_order_status() {
        return this.n;
    }

    public void setLabel_order_status(String str) {
        this.n = str;
    }

    public String getOrder_status_code() {
        return this.o;
    }

    public void setOrder_status_code(String str) {
        this.o = str;
    }

    public boolean isToComment() {
        return this.p;
    }

    public void setToComment(boolean z) {
        this.p = z;
    }

    public String getGoods_num() {
        return this.j;
    }

    public void setGoods_num(String str) {
        this.j = str;
    }

    public ECJia_ORDER_INFO getOrder_info() {
        return this.order_info;
    }

    public void setOrder_info(ECJia_ORDER_INFO eCJia_ORDER_INFO) {
        this.order_info = eCJia_ORDER_INFO;
    }

    public String getOrder_time() {
        return this.a;
    }

    public void setOrder_time(String str) {
        this.a = str;
    }

    public String getFormated_shipping_fee() {
        return this.k;
    }

    public void setFormated_shipping_fee(String str) {
        this.k = str;
    }

    public String getTotal_fee() {
        return this.b;
    }

    public void setTotal_fee(String str) {
        this.b = str;
    }

    public ArrayList<ECJia_ORDER_GOODS_LIST> getGoods_list() {
        return this.c;
    }

    public void setGoods_list(ArrayList<ECJia_ORDER_GOODS_LIST> arrayList) {
        this.c = arrayList;
    }

    public String getFormated_integral_money() {
        return this.d;
    }

    public void setFormated_integral_money(String str) {
        this.d = str;
    }

    public String getFormated_bonus() {
        return this.e;
    }

    public void setFormated_bonus(String str) {
        this.e = str;
    }

    public String getFormated_discount() {
        return this.f;
    }

    public void setFormated_discount(String str) {
        this.f = str;
    }

    public String getFormated_total_fee() {
        return this.g;
    }

    public void setFormated_total_fee(String str) {
        this.g = str;
    }

    public String getOrder_sn() {
        return this.h;
    }

    public void setOrder_sn(String str) {
        this.h = str;
    }

    public String getOrder_id() {
        return this.i;
    }

    public void setOrder_id(String str) {
        this.i = str;
    }

    public String getSeller_id() {
        return this.q;
    }

    public void setSeller_id(String str) {
        this.q = str;
    }

    public String getSeller_name() {
        return this.r;
    }

    public void setSeller_name(String str) {
        this.r = str;
    }

    public static ECJia_GOODORDER fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_GOODORDER eCJia_GOODORDER = new ECJia_GOODORDER();
        eCJia_GOODORDER.a = jSONObject.optString("order_time");
        eCJia_GOODORDER.b = jSONObject.optString("total_fee");
        JSONArray optJSONArray = jSONObject.optJSONArray("goods_list");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                eCJia_GOODORDER.c.add(ECJia_ORDER_GOODS_LIST.fromJson(optJSONArray.getJSONObject(i)));
            }
        }
        eCJia_GOODORDER.d = jSONObject.optString("formated_integral_money");
        eCJia_GOODORDER.e = jSONObject.optString("formated_bonus");
        eCJia_GOODORDER.f = jSONObject.optString("formated_discount");
        eCJia_GOODORDER.g = jSONObject.optString("formated_total_fee");
        eCJia_GOODORDER.h = jSONObject.optString("order_sn");
        eCJia_GOODORDER.i = jSONObject.optString("order_id");
        eCJia_GOODORDER.j = jSONObject.optString("goods_number");
        eCJia_GOODORDER.k = jSONObject.optString("formated_shipping_fee");
        eCJia_GOODORDER.order_info = ECJia_ORDER_INFO.fromJson(jSONObject.optJSONObject("order_info"));
        eCJia_GOODORDER.l = jSONObject.optString("order_status");
        eCJia_GOODORDER.m = jSONObject.optString("pay_status");
        eCJia_GOODORDER.n = jSONObject.optString("label_order_status");
        eCJia_GOODORDER.o = jSONObject.optString("order_status_code");
        eCJia_GOODORDER.p = jSONObject.optBoolean("toComment");
        eCJia_GOODORDER.q = jSONObject.optString("seller_id");
        eCJia_GOODORDER.r = jSONObject.optString("seller_name");
        return eCJia_GOODORDER;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("order_time", this.a);
        jSONObject.put("total_fee", this.b);
        for (int i = 0; i < this.c.size(); i++) {
            jSONArray.put(((ECJia_ORDER_GOODS_LIST) this.c.get(i)).toJson());
        }
        jSONObject.put("goods_list", jSONArray);
        jSONObject.put("formated_integral_money", this.d);
        jSONObject.put("formated_bonus", this.e);
        jSONObject.put("order_sn", this.h);
        jSONObject.put("order_id", this.i);
        jSONObject.put("goods_num", this.j);
        jSONObject.put("formated_shipping_fee", this.k);
        jSONObject.put("formated_discount", this.f);
        jSONObject.put("formated_total_fee", this.g);
        jSONObject.put("order_status", this.l);
        jSONObject.put("pay_status", this.m);
        jSONObject.put("label_order_status", this.n);
        jSONObject.put("order_status_code", this.o);
        jSONObject.put("toComment", this.p);
        jSONObject.put("seller_id", this.q);
        jSONObject.put("seller_name", this.r);
        return jSONObject;
    }
}
