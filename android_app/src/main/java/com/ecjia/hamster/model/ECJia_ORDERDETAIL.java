package com.ecjia.hamster.model;

import com.umeng.analytics.pro.x;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_ORDERDETAIL implements Serializable {
    private String A;
    private int B;
    private int C;
    private String D;
    private String E;
    private String F;
    private String G;
    private ECJia_ADDRESS a;
    private String b;
    private String c;
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
    private String p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public String getInv_tax_no() {
        return this.G;
    }

    public void setInv_tax_no(String str) {
        this.G = str;
    }

    public String getInv_content() {
        return this.F;
    }

    public void setInv_content(String str) {
        this.F = str;
    }

    public String getInv_payee() {
        return this.E;
    }

    public void setInv_payee(String str) {
        this.E = str;
    }

    public String getFormated_surplus() {
        return this.c;
    }

    public void setFormated_surplus(String str) {
        this.c = str;
    }

    public String getFormated_money_paid() {
        return this.b;
    }

    public void setFormated_money_paid(String str) {
        this.b = str;
    }

    public String getService_phone() {
        return this.D;
    }

    public void setService_phone(String str) {
        this.D = str;
    }

    public String getFormated_order_amount() {
        return this.q;
    }

    public void setFormated_order_amount(String str) {
        this.q = str;
    }

    public String getPostscript() {
        return this.p;
    }

    public void setPostscript(String str) {
        this.p = str;
    }

    public String getShipping_time() {
        return this.o;
    }

    public void setShipping_time(String str) {
        this.o = str;
    }

    public String getFormated_integral_money() {
        return this.l;
    }

    public void setFormated_integral_money(String str) {
        this.l = str;
    }

    public String getFormated_discount() {
        return this.m;
    }

    public void setFormated_discount(String str) {
        this.m = str;
    }

    public String getFormated_bonus() {
        return this.n;
    }

    public void setFormated_bonus(String str) {
        this.n = str;
    }

    public String getOrder_id() {
        return this.j;
    }

    public void setOrder_id(String str) {
        this.j = str;
    }

    public String getOrder_sn() {
        return this.d;
    }

    public void setOrder_sn(String str) {
        this.d = str;
    }

    public String getOrder_time() {
        return this.e;
    }

    public void setOrder_time(String str) {
        this.e = str;
    }

    public String getFormated_total_fee() {
        return this.f;
    }

    public void setFormated_total_fee(String str) {
        this.f = str;
    }

    public String getGoods_total_fee() {
        return this.g;
    }

    public void setGoods_total_fee(String str) {
        this.g = str;
    }

    public String getFormated_shipping_fee() {
        return this.h;
    }

    public void setFormated_shipping_fee(String str) {
        this.h = str;
    }

    public ECJia_ADDRESS getAddress() {
        return this.a;
    }

    public void setAddress(ECJia_ADDRESS eCJia_ADDRESS) {
        this.a = eCJia_ADDRESS;
    }

    public String getPay_id() {
        return this.A;
    }

    public void setPay_id(String str) {
        this.A = str;
    }

    public String getPay_name() {
        return this.i;
    }

    public void setPay_name(String str) {
        this.i = str;
    }

    public String getFormated_tax() {
        return this.k;
    }

    public void setFormated_tax(String str) {
        this.k = str;
    }

    public String getBonus() {
        return this.r;
    }

    public void setBonus(String str) {
        this.r = str;
    }

    public String getIntegral() {
        return this.s;
    }

    public void setIntegral(String str) {
        this.s = str;
    }

    public String getIntegral_money() {
        return this.t;
    }

    public void setIntegral_money(String str) {
        this.t = str;
    }

    public String getTotal_fee() {
        return this.u;
    }

    public void setTotal_fee(String str) {
        this.u = str;
    }

    public String getOrder_amount() {
        return this.v;
    }

    public void setOrder_amount(String str) {
        this.v = str;
    }

    public String getSurplus() {
        return this.w;
    }

    public void setSurplus(String str) {
        this.w = str;
    }

    public String getMoney_paid() {
        return this.x;
    }

    public void setMoney_paid(String str) {
        this.x = str;
    }

    public String getLabel_order_status() {
        return this.y;
    }

    public void setLabel_order_status(String str) {
        this.y = str;
    }

    public String getStatus_code() {
        return this.z;
    }

    public void setStatus_code(String str) {
        this.z = str;
    }

    public int getIs_commented() {
        return this.B;
    }

    public void setIs_commented(int i) {
        this.B = i;
    }

    public int getIs_showorder() {
        return this.C;
    }

    public void setIs_showorder(int i) {
        this.C = i;
    }

    public static ECJia_ORDERDETAIL fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_ORDERDETAIL eCJia_ORDERDETAIL = new ECJia_ORDERDETAIL();
        eCJia_ORDERDETAIL.d = jSONObject.optString("order_sn");
        eCJia_ORDERDETAIL.e = jSONObject.optString("formated_add_time");
        eCJia_ORDERDETAIL.f = jSONObject.optString("formated_total_fee");
        eCJia_ORDERDETAIL.g = jSONObject.optString("formated_goods_amount");
        eCJia_ORDERDETAIL.h = jSONObject.optString("formated_shipping_fee");
        eCJia_ORDERDETAIL.E = jSONObject.optString("inv_payee");
        eCJia_ORDERDETAIL.F = jSONObject.optString("inv_content");
        eCJia_ORDERDETAIL.G = jSONObject.optString("inv_tax_no");
        eCJia_ORDERDETAIL.a = new ECJia_ADDRESS();
        eCJia_ORDERDETAIL.a.setConsignee(jSONObject.optString("consignee"));
        eCJia_ORDERDETAIL.a.setMobile(jSONObject.optString("mobile"));
        eCJia_ORDERDETAIL.a.setCountry(jSONObject.optString("country_id"));
        eCJia_ORDERDETAIL.a.setCountry_name(jSONObject.optString(x.G));
        eCJia_ORDERDETAIL.a.setProvince(jSONObject.optString("country_id"));
        eCJia_ORDERDETAIL.a.setProvince_name(jSONObject.optString(x.G));
        eCJia_ORDERDETAIL.a.setCity(jSONObject.optString("country_id"));
        eCJia_ORDERDETAIL.a.setCity_name(jSONObject.optString(x.G));
        eCJia_ORDERDETAIL.a.setDistrict(jSONObject.optString("district_id"));
        eCJia_ORDERDETAIL.a.setDistrict_name(jSONObject.optString("district"));
        eCJia_ORDERDETAIL.a.setAddress(jSONObject.optString("address"));
        eCJia_ORDERDETAIL.i = jSONObject.optString("pay_name");
        eCJia_ORDERDETAIL.j = jSONObject.optString("order_id");
        eCJia_ORDERDETAIL.m = jSONObject.optString("formated_discount");
        eCJia_ORDERDETAIL.n = jSONObject.optString("formated_bonus");
        eCJia_ORDERDETAIL.l = jSONObject.optString("formated_integral_money");
        eCJia_ORDERDETAIL.o = jSONObject.optString("shipping_time");
        eCJia_ORDERDETAIL.p = jSONObject.optString("postscript");
        eCJia_ORDERDETAIL.q = jSONObject.optString("formated_order_amount");
        eCJia_ORDERDETAIL.k = jSONObject.optString("formated_tax");
        eCJia_ORDERDETAIL.r = jSONObject.optString("bonus");
        eCJia_ORDERDETAIL.s = jSONObject.optString("integral");
        eCJia_ORDERDETAIL.t = jSONObject.optString("integral_money");
        eCJia_ORDERDETAIL.u = jSONObject.optString("total_fee");
        eCJia_ORDERDETAIL.c = jSONObject.optString("formated_surplus");
        eCJia_ORDERDETAIL.b = jSONObject.optString("formated_money_paid");
        eCJia_ORDERDETAIL.A = jSONObject.optString("pay_id");
        eCJia_ORDERDETAIL.v = jSONObject.optString("order_amount");
        eCJia_ORDERDETAIL.w = jSONObject.optString("surplus");
        eCJia_ORDERDETAIL.x = jSONObject.optString("money_paid");
        eCJia_ORDERDETAIL.z = jSONObject.optString("status_code");
        eCJia_ORDERDETAIL.y = jSONObject.optString("label_order_status");
        eCJia_ORDERDETAIL.B = jSONObject.optInt("is_commented");
        eCJia_ORDERDETAIL.C = jSONObject.optInt("is_showorder");
        eCJia_ORDERDETAIL.D = jSONObject.optString("service_phone");
        return eCJia_ORDERDETAIL;
    }
}
