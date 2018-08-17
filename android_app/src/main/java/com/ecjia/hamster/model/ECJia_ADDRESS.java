package com.ecjia.hamster.model;

import com.umeng.analytics.pro.x;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_ADDRESS implements Serializable {
    private int a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private int j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;

    public int getDefault_address() {
        return this.a;
    }

    public void setDefault_address(int i) {
        this.a = i;
    }

    public String getSign_building() {
        return this.b;
    }

    public void setSign_building(String str) {
        this.b = str;
    }

    public String getCity_name() {
        return this.c;
    }

    public void setCity_name(String str) {
        this.c = str;
    }

    public String getConsignee() {
        return this.d;
    }

    public void setConsignee(String str) {
        this.d = str;
    }

    public String getTel() {
        return this.e;
    }

    public void setTel(String str) {
        this.e = str;
    }

    public String getZipcode() {
        return this.f;
    }

    public void setZipcode(String str) {
        this.f = str;
    }

    public String getCountry_name() {
        return this.g;
    }

    public void setCountry_name(String str) {
        this.g = str;
    }

    public String getCountry() {
        return this.h;
    }

    public void setCountry(String str) {
        this.h = str;
    }

    public String getCity() {
        return this.i;
    }

    public void setCity(String str) {
        this.i = str;
    }

    public int getId() {
        return this.j;
    }

    public void setId(int i) {
        this.j = i;
    }

    public String getProvince_name() {
        return this.k;
    }

    public void setProvince_name(String str) {
        this.k = str;
    }

    public String getDistrict_name() {
        return this.l;
    }

    public void setDistrict_name(String str) {
        this.l = str;
    }

    public String getEmail() {
        return this.m;
    }

    public void setEmail(String str) {
        this.m = str;
    }

    public String getAddress() {
        return this.n;
    }

    public void setAddress(String str) {
        this.n = str;
    }

    public String getProvince() {
        return this.o;
    }

    public void setProvince(String str) {
        this.o = str;
    }

    public String getDistrict() {
        return this.p;
    }

    public void setDistrict(String str) {
        this.p = str;
    }

    public String getBest_time() {
        return this.q;
    }

    public void setBest_time(String str) {
        this.q = str;
    }

    public String getMobile() {
        return this.r;
    }

    public void setMobile(String str) {
        this.r = str;
    }

    public static ECJia_ADDRESS fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_ADDRESS eCJia_ADDRESS = new ECJia_ADDRESS();
        eCJia_ADDRESS.a = jSONObject.optInt("default_address");
        eCJia_ADDRESS.b = jSONObject.optString("sign_building");
        eCJia_ADDRESS.c = jSONObject.optString("city_name");
        eCJia_ADDRESS.d = jSONObject.optString("consignee");
        eCJia_ADDRESS.e = jSONObject.optString("tel");
        eCJia_ADDRESS.f = jSONObject.optString("zipcode");
        eCJia_ADDRESS.g = jSONObject.optString("country_name");
        eCJia_ADDRESS.h = jSONObject.optString(x.G);
        eCJia_ADDRESS.i = jSONObject.optString("city");
        eCJia_ADDRESS.j = jSONObject.optInt("id");
        eCJia_ADDRESS.k = jSONObject.optString("province_name");
        eCJia_ADDRESS.l = jSONObject.optString("district_name");
        eCJia_ADDRESS.m = jSONObject.optString("email");
        eCJia_ADDRESS.n = jSONObject.optString("address");
        eCJia_ADDRESS.o = jSONObject.optString("province");
        eCJia_ADDRESS.p = jSONObject.optString("district");
        eCJia_ADDRESS.q = jSONObject.optString("best_time");
        eCJia_ADDRESS.r = jSONObject.optString("mobile");
        return eCJia_ADDRESS;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("default_address", this.a);
        jSONObject.put("sign_building", this.b);
        jSONObject.put("city_name", this.c);
        jSONObject.put("consignee", this.d);
        jSONObject.put("tel", this.e);
        jSONObject.put("zipcode", this.f);
        jSONObject.put("country_name", this.g);
        jSONObject.put(x.G, this.h);
        jSONObject.put("city", this.i);
        jSONObject.put("id", this.j);
        jSONObject.put("province_name", this.k);
        jSONObject.put("district_name", this.l);
        jSONObject.put("email", this.m);
        jSONObject.put("address", this.n);
        jSONObject.put("province", this.o);
        jSONObject.put("district", this.p);
        jSONObject.put("best_time", this.q);
        jSONObject.put("mobile", this.r);
        return jSONObject;
    }
}
