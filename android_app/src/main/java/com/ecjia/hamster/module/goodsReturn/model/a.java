package com.ecjia.hamster.module.goodsReturn.model;

import com.ecjia.hamster.model.ECJia_PHOTO;
import com.sina.weibo.sdk.constant.WBConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_RETURN_LIST */
public class a {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private int g;
    private String h;
    private String i;
    private String j;
    private ECJia_PHOTO k = new ECJia_PHOTO();
    private int l;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.f;
    }

    public String f() {
        return this.h;
    }

    public String g() {
        return this.i;
    }

    public String h() {
        return this.j;
    }

    public ECJia_PHOTO i() {
        return this.k;
    }

    public static a a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        a aVar = new a();
        aVar.a = jSONObject.optString("seller_id");
        aVar.b = jSONObject.optString("seller_name");
        aVar.c = jSONObject.optString("return_id");
        aVar.d = jSONObject.optString("return_sn");
        aVar.e = jSONObject.optString("return_type");
        aVar.f = jSONObject.optString("goods_name");
        aVar.g = jSONObject.optInt("number");
        aVar.h = jSONObject.optString(WBConstants.GAME_PARAMS_GAME_CREATE_TIME);
        aVar.i = jSONObject.optString("return_status");
        aVar.j = jSONObject.optString("label_return_status");
        aVar.k = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        aVar.l = jSONObject.optInt("is_support_activation");
        return aVar;
    }
}
