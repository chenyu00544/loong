package com.ecjia.hamster.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SELLERINFO */
public class ao {
    private String a;
    private String b;
    private int c;
    private String d;
    private String e;
    private String f;
    private ArrayList<an> g = new ArrayList();
    private Integer h;
    private String i;
    private boolean j;
    private boolean k;
    private String l;
    private String m;
    private String n;
    private ECJia_LOCATION o = new ECJia_LOCATION();

    public String a() {
        return this.l;
    }

    public String b() {
        return this.m;
    }

    public boolean c() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public boolean d() {
        return this.j;
    }

    public void b(boolean z) {
        this.j = z;
    }

    public String e() {
        return this.a;
    }

    public ArrayList<an> f() {
        return this.g;
    }

    public Integer g() {
        return this.h;
    }

    public void a(Integer num) {
        this.h = num;
    }

    public String h() {
        return this.d;
    }

    public String i() {
        return this.f;
    }

    public String j() {
        return this.n;
    }

    public void a(String str) {
        this.i = str;
    }

    public static ao a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ao aoVar = new ao();
        JSONArray optJSONArray = jSONObject.optJSONArray("seller_goods");
        aoVar.a = jSONObject.optString("id");
        aoVar.b = jSONObject.optString("rec_id");
        aoVar.c = jSONObject.optInt("new_goods");
        aoVar.d = jSONObject.optString("seller_name");
        aoVar.e = jSONObject.optString("seller_category");
        aoVar.f = jSONObject.optString("seller_logo");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                aoVar.g.add(an.a(optJSONArray.getJSONObject(i)));
            }
        }
        aoVar.k = false;
        aoVar.h = Integer.valueOf(jSONObject.optInt("follower"));
        aoVar.i = jSONObject.optString("is_follower");
        aoVar.l = jSONObject.optString("seller_banner");
        aoVar.m = jSONObject.optString("seller_desc");
        aoVar.n = jSONObject.optString("distance");
        aoVar.o = ECJia_LOCATION.fromJson(jSONObject.optJSONObject("location"));
        return aoVar;
    }
}
