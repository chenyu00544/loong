package com.ecjia.hamster.model;

import com.tencent.open.GameAppOperation;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_USER */
public class bf {
    private String a;
    private String b;
    private String c;
    private int d;
    private String e;
    private String f;
    private String g;
    private String h;
    private a i = new a();
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private int s;
    private int t;
    private bh u = new bh();
    private ArrayList<ECJia_BONUS> v = new ArrayList();

    /* compiled from: ECJia_USER */
    public static class a {
        private int a;
        private int b;
        private int c;
        private int d;
        private int e;
        private int f;

        public int a() {
            return this.f;
        }

        public int b() {
            return this.e;
        }

        public int c() {
            return this.a;
        }

        public int d() {
            return this.b;
        }

        public int e() {
            return this.c;
        }

        public static a a(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            a aVar = new a();
            aVar.a = jSONObject.optInt("shipped");
            aVar.b = jSONObject.optInt("await_ship");
            aVar.c = jSONObject.optInt("await_pay");
            aVar.d = jSONObject.optInt("finished");
            aVar.e = jSONObject.optInt("allow_comment");
            aVar.f = jSONObject.optInt("returns");
            return aVar;
        }
    }

    public String a() {
        return this.r;
    }

    public String b() {
        return this.q;
    }

    public String c() {
        return this.p;
    }

    public String d() {
        return this.n;
    }

    public String e() {
        return this.m;
    }

    public ArrayList<ECJia_BONUS> f() {
        return this.v;
    }

    public String g() {
        return this.g;
    }

    public String h() {
        return this.f;
    }

    public String i() {
        return this.j;
    }

    public String j() {
        return this.k;
    }

    public void a(String str) {
        this.k = str;
    }

    public String k() {
        return this.l;
    }

    public String l() {
        return this.e;
    }

    public String m() {
        return this.a;
    }

    public a n() {
        return this.i;
    }

    public String o() {
        return this.c;
    }

    public String p() {
        return this.b;
    }

    public String q() {
        return this.h;
    }

    public void b(String str) {
        this.h = str;
    }

    public String r() {
        return this.o;
    }

    public int s() {
        return this.s;
    }

    public int t() {
        return this.t;
    }

    public bh u() {
        return this.u;
    }

    public static bf a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        bf bfVar = new bf();
        bfVar.a = jSONObject.optString("id");
        bfVar.b = jSONObject.optString("name");
        bfVar.c = jSONObject.optString("rank_name");
        bfVar.d = jSONObject.optInt("rank_level");
        bfVar.e = jSONObject.optString("collection_num");
        bfVar.m = jSONObject.optString("collect_merchant_num");
        bfVar.f = jSONObject.optString("email");
        bfVar.g = jSONObject.optString("mobile_phone");
        bfVar.h = jSONObject.optString("avatar_img");
        bfVar.i = a.a(jSONObject.optJSONObject("order_num"));
        bfVar.j = jSONObject.optString("formated_user_money");
        bfVar.k = jSONObject.optString("user_points");
        bfVar.l = jSONObject.optString("user_bonus_count");
        bfVar.n = jSONObject.optString("signup_reward_url");
        bfVar.o = jSONObject.optString(GameAppOperation.QQFAV_DATALINE_OPENID);
        bfVar.q = jSONObject.optString("access_token");
        bfVar.r = jSONObject.optString("nick_name");
        bfVar.p = jSONObject.optString("update_username_time");
        JSONArray optJSONArray = jSONObject.optJSONArray("bonus_list");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                bfVar.v.add(ECJia_BONUS.fromJson(optJSONArray.getJSONObject(i)));
            }
        }
        bfVar.s = jSONObject.optInt("real_name_verified");
        bfVar.t = jSONObject.optInt("is_bind_mobile");
        bfVar.u = bh.a(jSONObject.optJSONObject("real_name_verifyInfo"));
        return bfVar;
    }
}
