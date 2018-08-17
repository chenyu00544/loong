package com.ecjia.hamster.model;

import com.umeng.socialize.common.SocializeConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_RECHARGE_INFO */
public class aj {
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
    private String k;
    private String l;
    private String m;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.d;
    }

    public String c() {
        return this.f;
    }

    public String d() {
        return this.h;
    }

    public String e() {
        return this.i;
    }

    public String f() {
        return this.j;
    }

    public String g() {
        return this.k;
    }

    public String h() {
        return this.l;
    }

    public String i() {
        return this.m;
    }

    public static aj a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        aj ajVar = new aj();
        ajVar.a = jSONObject.optString("account_id");
        ajVar.b = jSONObject.optString(SocializeConstants.TENCENT_UID);
        ajVar.c = jSONObject.optString("admin_user");
        ajVar.d = jSONObject.optString("amount");
        ajVar.e = jSONObject.optString("format_amount");
        ajVar.g = jSONObject.optString("user_note");
        ajVar.h = jSONObject.optString("type");
        ajVar.i = jSONObject.optString("type_lable");
        ajVar.j = jSONObject.optString("payment_name");
        ajVar.k = jSONObject.optString("payment_id");
        ajVar.l = jSONObject.optString("is_paid");
        ajVar.m = jSONObject.optString("pay_status");
        ajVar.f = jSONObject.optString("add_time");
        return ajVar;
    }

    public JSONObject j() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("account_id", this.a);
        jSONObject.put(SocializeConstants.TENCENT_UID, this.b);
        jSONObject.put("admin_user", this.c);
        jSONObject.put("amount", this.d);
        jSONObject.put("format_amount", this.e);
        jSONObject.put("user_note", this.g);
        jSONObject.put("type", this.h);
        jSONObject.put("type_lable", this.i);
        jSONObject.put("payment_name", this.j);
        jSONObject.put("payment_id", this.k);
        jSONObject.put("is_paid", this.l);
        jSONObject.put("pay_status", this.m);
        jSONObject.put("add_time", this.f);
        return jSONObject;
    }
}
