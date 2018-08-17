package com.ecjia.hamster.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SHOPDATA */
public class ar {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private Integer h;
    private String i;
    private String j;
    private String k;
    private String l;
    private aq m = new aq();
    private ECJia_LOCATION n = new ECJia_LOCATION();
    private q o = new q();
    private ArrayList<w> p = new ArrayList();
    private ArrayList<ECJia_FAVOUR> q = new ArrayList();

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
        return this.e;
    }

    public String e() {
        return this.d;
    }

    public String f() {
        return this.k;
    }

    public String g() {
        return this.f;
    }

    public String h() {
        return this.g;
    }

    public String i() {
        return this.i;
    }

    public void a(String str) {
        this.i = str;
    }

    public aq j() {
        return this.m;
    }

    public q k() {
        return this.o;
    }

    public ECJia_LOCATION l() {
        return this.n;
    }

    public String m() {
        return this.l;
    }

    public Integer n() {
        return this.h;
    }

    public void a(Integer num) {
        this.h = num;
    }

    public ArrayList<w> o() {
        return this.p;
    }

    public ArrayList<ECJia_FAVOUR> p() {
        return this.q;
    }

    public static ar a(JSONObject jSONObject) throws JSONException {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        ar arVar = new ar();
        arVar.a = jSONObject.optString("id");
        arVar.b = jSONObject.optString("seller_name");
        arVar.c = jSONObject.optString("seller_logo");
        arVar.d = jSONObject.optString("seller_banner");
        arVar.e = jSONObject.optString("shop_name");
        arVar.k = jSONObject.optString("shop_address");
        arVar.f = jSONObject.optString("telephone");
        arVar.g = jSONObject.optString("seller_description");
        arVar.h = Integer.valueOf(jSONObject.optInt("follower"));
        arVar.i = jSONObject.optString("is_follower");
        arVar.j = jSONObject.optString("collect_id");
        arVar.m = aq.a(jSONObject.optJSONObject("comment"));
        arVar.n = ECJia_LOCATION.fromJson(jSONObject.optJSONObject("location"));
        arVar.o = q.a(jSONObject.optJSONObject("goods_count"));
        arVar.l = jSONObject.optString("distance");
        JSONArray optJSONArray = jSONObject.optJSONArray("menu_button");
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                arVar.p.add(w.a(optJSONArray.optJSONObject(i2)));
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("favourable_list");
        if (optJSONArray2 != null) {
            while (i < optJSONArray2.length()) {
                arVar.q.add(ECJia_FAVOUR.fromJson(optJSONArray2.getJSONObject(i)));
                i++;
            }
        }
        return arVar;
    }
}
