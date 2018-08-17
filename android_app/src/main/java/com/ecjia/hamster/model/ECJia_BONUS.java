package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_BONUS implements Serializable {
    private String A;
    private String a;
    private int b;
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
    private int m;
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
    private boolean x = false;
    private String y;
    private String z;

    public int getBonus_status() {
        return this.m;
    }

    public void setBonus_status(int i) {
        this.m = i;
    }

    public String getFormatted_bonus_status() {
        return this.n;
    }

    public void setFormatted_bonus_status(String str) {
        this.n = str;
    }

    public String getFormatted_use_start_date() {
        return this.o;
    }

    public void setFormatted_use_start_date(String str) {
        this.o = str;
    }

    public String getFormatted_use_end_date() {
        return this.p;
    }

    public void setFormatted_use_end_date(String str) {
        this.p = str;
    }

    public String getBonus_name() {
        return this.q;
    }

    public void setBonus_name(String str) {
        this.q = str;
    }

    public String getBonus_amount() {
        return this.r;
    }

    public void setBonus_amount(String str) {
        this.r = str;
    }

    public String getFormatted_bonus_amount() {
        return this.s;
    }

    public void setFormatted_bonus_amount(String str) {
        this.s = str;
    }

    public String getStart_date() {
        return this.t;
    }

    public void setStart_date(String str) {
        this.t = str;
    }

    public String getEnd_date() {
        return this.u;
    }

    public void setEnd_date(String str) {
        this.u = str;
    }

    public String getFormatted_start_date() {
        return this.v;
    }

    public void setFormatted_start_date(String str) {
        this.v = str;
    }

    public String getFormatted_end_date() {
        return this.w;
    }

    public void setFormatted_end_date(String str) {
        this.w = str;
    }

    public String getFormated_min_goods_amount() {
        return this.l;
    }

    public void setFormated_min_goods_amount(String str) {
        this.l = str;
    }

    public String getMin_goods_amount() {
        return this.f;
    }

    public void setMin_goods_amount(String str) {
        this.f = str;
    }

    public String getUse_start_date() {
        return this.g;
    }

    public void setUse_start_date(String str) {
        this.g = str;
    }

    public String getUse_end_date() {
        return this.h;
    }

    public void setUse_end_date(String str) {
        this.h = str;
    }

    public String getStatus() {
        return this.i;
    }

    public void setStatus(String str) {
        this.i = str;
    }

    public String getFormated_use_start_date() {
        return this.j;
    }

    public void setFormated_use_start_date(String str) {
        this.j = str;
    }

    public String getFormated_use_end_date() {
        return this.k;
    }

    public void setFormated_use_end_date(String str) {
        this.k = str;
    }

    public int getType_id() {
        return this.b;
    }

    public void setType_id(int i) {
        this.b = i;
    }

    public String getType_name() {
        return this.c;
    }

    public void setType_name(String str) {
        this.c = str;
    }

    public String getType_money() {
        return this.d;
    }

    public void setType_money(String str) {
        this.d = str;
    }

    public String getBonus_id() {
        return this.a;
    }

    public void setBonus_id(String str) {
        this.a = str;
    }

    public String getBonus_money_formated() {
        return this.e;
    }

    public void setBonus_money_formated(String str) {
        this.e = str;
    }

    public String getRequest_amount() {
        return this.y;
    }

    public void setRequest_amount(String str) {
        this.y = str;
    }

    public String getFormatted_request_amount() {
        return this.z;
    }

    public void setFormatted_request_amount(String str) {
        this.z = str;
    }

    public String getLabel_status() {
        return this.A;
    }

    public void setLabel_status(String str) {
        this.A = str;
    }

    public boolean isIschecked() {
        return this.x;
    }

    public void setIschecked(boolean z) {
        this.x = z;
    }

    public static ECJia_BONUS fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_BONUS eCJia_BONUS = new ECJia_BONUS();
        eCJia_BONUS.b = jSONObject.optInt("type_id");
        eCJia_BONUS.c = jSONObject.optString("type_name");
        eCJia_BONUS.d = jSONObject.optString("type_money");
        eCJia_BONUS.a = jSONObject.optString("bonus_id");
        eCJia_BONUS.g = jSONObject.optString("use_start_date");
        eCJia_BONUS.h = jSONObject.optString("use_end_date");
        eCJia_BONUS.f = jSONObject.optString("min_goods_amount");
        eCJia_BONUS.e = jSONObject.optString("bonus_money_formated");
        eCJia_BONUS.q = jSONObject.optString("bonus_name");
        eCJia_BONUS.r = jSONObject.optString("bonus_amount");
        eCJia_BONUS.s = jSONObject.optString("formatted_bonus_amount");
        eCJia_BONUS.y = jSONObject.optString("request_amount");
        eCJia_BONUS.z = jSONObject.optString("formatted_request_amount");
        eCJia_BONUS.m = jSONObject.optInt("bonus_status");
        eCJia_BONUS.n = jSONObject.optString("formatted_bonus_status");
        eCJia_BONUS.t = jSONObject.optString("start_date");
        eCJia_BONUS.u = jSONObject.optString("end_date");
        eCJia_BONUS.j = jSONObject.optString("formated_use_start_date");
        eCJia_BONUS.k = jSONObject.optString("formated_use_end_date");
        eCJia_BONUS.i = jSONObject.optString("status");
        eCJia_BONUS.l = jSONObject.optString("formated_min_goods_amount");
        eCJia_BONUS.o = jSONObject.optString("formatted_use_start_date");
        eCJia_BONUS.p = jSONObject.optString("formatted_use_end_date");
        eCJia_BONUS.v = jSONObject.optString("formatted_start_date");
        eCJia_BONUS.w = jSONObject.optString("formatted_end_date");
        eCJia_BONUS.A = jSONObject.optString("label_status");
        eCJia_BONUS.x = false;
        return eCJia_BONUS;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type_id", this.b);
        jSONObject.put("type_name", this.c);
        jSONObject.put("type_money", this.d);
        jSONObject.put("bonus_id", this.a);
        jSONObject.put("bonus_money_formated", this.e);
        jSONObject.put("min_goods_amount", this.f);
        jSONObject.put("use_start_date", this.g);
        jSONObject.put("use_end_date", this.h);
        jSONObject.put("status", this.i);
        jSONObject.put("formated_use_start_date", this.j);
        jSONObject.put("formated_use_end_date", this.k);
        jSONObject.put("formated_min_goods_amount", this.l);
        jSONObject.put("bonus_status", this.m);
        jSONObject.put("formatted_bonus_status", this.n);
        jSONObject.put("formatted_use_start_date", this.o);
        jSONObject.put("formatted_use_end_date", this.p);
        jSONObject.put("bonus_name", this.q);
        jSONObject.put("bonus_amount", this.r);
        jSONObject.put("formatted_bonus_amount", this.s);
        jSONObject.put("start_date", this.t);
        jSONObject.put("end_date", this.u);
        jSONObject.put("formatted_start_date", this.v);
        jSONObject.put("formatted_end_date", this.w);
        jSONObject.put("ischecked", this.x);
        jSONObject.put("formatted_request_amount", this.z);
        jSONObject.put("request_amount", this.y);
        jSONObject.put("label_status", this.A);
        return jSONObject;
    }
}
