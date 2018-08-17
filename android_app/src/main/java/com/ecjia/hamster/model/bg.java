package com.ecjia.hamster.model;

import com.umeng.socialize.common.SocializeConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_USER_ACCOUNT */
public class bg {
    private String a;
    private String b;
    private String c;
    private int d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public int c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public String f() {
        return this.g;
    }

    public String g() {
        return this.h;
    }

    public String h() {
        return this.i;
    }

    public String i() {
        return this.k;
    }

    public static bg a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        bg bgVar = new bg();
        bgVar.a = jSONObject.optString(SocializeConstants.TENCENT_UID);
        bgVar.b = jSONObject.optString("real_id");
        bgVar.c = jSONObject.optString("real_name");
        bgVar.d = jSONObject.optInt("verify_status");
        bgVar.e = jSONObject.optString("identity_number");
        bgVar.f = jSONObject.optString("bank_name");
        bgVar.g = jSONObject.optString("bank_card");
        bgVar.h = jSONObject.optString("identity_pic_front");
        bgVar.i = jSONObject.optString("identity_pic_back");
        bgVar.j = jSONObject.optString("mobile");
        bgVar.k = jSONObject.optString("review_content");
        return bgVar;
    }
}
