package com.ecjia.hamster.activity.goodsdetail.a;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_COMMENT_LIST */
public class a {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private ArrayList<String> i = new ArrayList();

    public String a() {
        return this.h;
    }

    public ArrayList<String> b() {
        return this.i;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.b;
    }

    public String f() {
        return this.c;
    }

    public String g() {
        return this.f;
    }

    public static a a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        a aVar = new a();
        aVar.a = jSONObject.optString("comment_id");
        aVar.b = jSONObject.optString("user_name");
        aVar.d = jSONObject.optString("comment_rank");
        aVar.c = jSONObject.optString("avatar_img");
        aVar.e = jSONObject.optString("comment_content");
        aVar.f = jSONObject.optString("comment_time");
        aVar.g = jSONObject.optString("order_time");
        aVar.h = jSONObject.optString("goods_attr");
        JSONArray optJSONArray = jSONObject.optJSONArray("comment_image");
        aVar.i.clear();
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                aVar.i.add(optJSONArray.optString(i));
            }
        }
        return aVar;
    }
}
