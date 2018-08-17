package com.ecjia.hamster.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_GOODS */
public class p {
    private int A;
    private String B;
    private String C;
    private String D;
    private String E;
    private String F;
    private String G;
    private ArrayList<ECJia_FAVOUR> H = new ArrayList();
    private ArrayList<ECJia_GOODS_COUPON> I = new ArrayList();
    public ArrayList<ECJia_PRODUCT_SPECIFICATION> a = new ArrayList();
    public ArrayList<av> b = new ArrayList();
    private String c;
    private int d;
    private String e;
    private String f;
    private String g;
    private ArrayList<ae> h = new ArrayList();
    private ECJia_PHOTO i = new ECJia_PHOTO();
    private String j;
    private ArrayList<ECJia_PHOTO> k = new ArrayList();
    private String l;
    private ArrayList<ag> m = new ArrayList();
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private int s;
    private int t;
    private int u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    public String a() {
        return this.F;
    }

    public String b() {
        return this.E;
    }

    public String c() {
        return this.G;
    }

    public ArrayList<ECJia_GOODS_COUPON> d() {
        return this.I;
    }

    public ArrayList<ECJia_FAVOUR> e() {
        return this.H;
    }

    public String f() {
        return this.C;
    }

    public String g() {
        return this.y;
    }

    public String h() {
        return this.B;
    }

    public String i() {
        return this.q;
    }

    public String j() {
        return this.c;
    }

    public String k() {
        return this.x;
    }

    public int l() {
        return this.A;
    }

    public String m() {
        return this.g;
    }

    public ECJia_PHOTO n() {
        return this.i;
    }

    public ArrayList<ECJia_PHOTO> o() {
        return this.k;
    }

    public String p() {
        return this.l;
    }

    public ArrayList<ag> q() {
        return this.m;
    }

    public String r() {
        return this.o;
    }

    public String s() {
        return this.p;
    }

    public int t() {
        return this.s;
    }

    public String u() {
        return this.w;
    }

    public String v() {
        return this.z;
    }

    public static p a(JSONObject jSONObject) throws JSONException {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        int i2;
        p pVar = new p();
        pVar.c = jSONObject.optString("promote_end_date");
        pVar.d = jSONObject.optInt("click_count");
        pVar.e = jSONObject.optString("goods_sn");
        pVar.f = jSONObject.optString("promote_start_date");
        pVar.g = jSONObject.optString("goods_number");
        JSONArray optJSONArray = jSONObject.optJSONArray("rank_prices");
        if (optJSONArray != null) {
            for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                pVar.h.add(ae.a(optJSONArray.getJSONObject(i2)));
            }
        }
        pVar.i = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        pVar.j = jSONObject.optString("brand_id");
        optJSONArray = jSONObject.optJSONArray("pictures");
        if (optJSONArray != null) {
            for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                pVar.k.add(ECJia_PHOTO.fromJson(optJSONArray.getJSONObject(i2)));
            }
        }
        pVar.l = jSONObject.optString("goods_name");
        optJSONArray = jSONObject.optJSONArray("properties");
        if (optJSONArray != null) {
            for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                pVar.m.add(ag.a(optJSONArray.getJSONObject(i2)));
            }
        }
        pVar.n = jSONObject.optString("goods_weight");
        pVar.o = jSONObject.optString("promote_price");
        pVar.p = jSONObject.optString("formated_promote_price");
        pVar.q = jSONObject.optString("formatted_saving_price");
        pVar.r = jSONObject.optString("integral");
        pVar.s = jSONObject.optInt("id");
        pVar.u = jSONObject.optInt("rec_id");
        pVar.t = jSONObject.optInt("goods_id");
        pVar.v = jSONObject.optString("cat_id");
        pVar.w = jSONObject.optString("is_shipping");
        pVar.x = jSONObject.optString("shop_price");
        pVar.y = jSONObject.optString("unformatted_shop_price");
        pVar.z = jSONObject.optString("market_price");
        pVar.A = jSONObject.optInt("collected");
        pVar.B = jSONObject.optString("activity_type");
        pVar.D = jSONObject.optString("goods_attr_price_mode");
        optJSONArray = jSONObject.optJSONArray("specification");
        if (optJSONArray != null) {
            for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                pVar.b.add(av.a(optJSONArray.getJSONObject(i2)));
            }
        }
        pVar.C = jSONObject.optString("give_integral");
        pVar.E = jSONObject.optString("promote_limit");
        pVar.F = jSONObject.optString("sales_volume");
        optJSONArray = jSONObject.optJSONArray("favourable_list");
        if (optJSONArray != null) {
            for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                pVar.H.add(ECJia_FAVOUR.fromJson(optJSONArray.getJSONObject(i2)));
            }
        }
        optJSONArray = jSONObject.optJSONArray("goods_coupon");
        if (optJSONArray != null) {
            for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                pVar.I.add(ECJia_GOODS_COUPON.fromJson(optJSONArray.getJSONObject(i2)));
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("product_specification");
        if (optJSONArray2 != null) {
            while (i < optJSONArray2.length()) {
                pVar.a.add(ECJia_PRODUCT_SPECIFICATION.fromJson(optJSONArray2.getJSONObject(i)));
                i++;
            }
        }
        pVar.G = jSONObject.optString("share_link");
        return pVar;
    }

    public JSONObject w() throws JSONException {
        int i;
        int i2 = 0;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("promote_end_date", this.c);
        jSONObject.put("click_count", this.d);
        jSONObject.put("goods_sn", this.e);
        jSONObject.put("promote_start_date", this.f);
        jSONObject.put("goods_number", this.g);
        for (i = 0; i < this.h.size(); i++) {
            jSONArray.put(((ae) this.h.get(i)).a());
        }
        jSONObject.put("rank_prices", jSONArray);
        if (this.i != null) {
            jSONObject.put("img", this.i.toJson());
        }
        jSONObject.put("brand_id", this.j);
        for (i = 0; i < this.k.size(); i++) {
            jSONArray.put(((ECJia_PHOTO) this.k.get(i)).toJson());
        }
        jSONObject.put("pictures", jSONArray);
        jSONObject.put("goods_name", this.l);
        for (i = 0; i < this.m.size(); i++) {
            jSONArray.put(((ag) this.m.get(i)).c());
        }
        jSONObject.put("properties", jSONArray);
        jSONObject.put("goods_weight", this.n);
        jSONObject.put("promote_price", this.o);
        jSONObject.put("formated_promote_price", this.p);
        jSONObject.put("formatted_saving_price", this.q);
        jSONObject.put("integral", this.r);
        jSONObject.put("id", this.s);
        jSONObject.put("rec_id", this.u);
        jSONObject.put("goods_id", this.t);
        jSONObject.put("cat_id", this.v);
        jSONObject.put("is_shipping", this.w);
        jSONObject.put("shop_price", this.x);
        jSONObject.put("unformatted_shop_price", this.y);
        jSONObject.put("market_price", this.z);
        jSONObject.put("collected", this.A);
        jSONObject.put("activity_type", this.B);
        jSONObject.put("goods_attr_price_mode", this.D);
        for (i = 0; i < this.b.size(); i++) {
            jSONArray.put(((av) this.b.get(i)).c());
        }
        jSONObject.put("specification", jSONArray);
        jSONObject.put("give_integral", this.C);
        jSONObject.put("promote_limit", this.E);
        jSONObject.put("sales_volume", this.F);
        jSONArray = new JSONArray();
        for (i = 0; i < this.H.size(); i++) {
            jSONArray.put(((ECJia_FAVOUR) this.H.get(i)).toJson());
        }
        jSONObject.put("favourable_list", jSONArray);
        JSONArray jSONArray2 = new JSONArray();
        while (i2 < this.I.size()) {
            jSONArray2.put(((ECJia_GOODS_COUPON) this.I.get(i2)).toJson());
            i2++;
        }
        jSONObject.put("goods_coupon", jSONArray2);
        jSONObject.put("share_link", this.G);
        return jSONObject;
    }
}
