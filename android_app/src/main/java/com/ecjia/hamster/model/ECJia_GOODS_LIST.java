package com.ecjia.hamster.model;

import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_GOODS_LIST implements Serializable {
    private int A;
    private String a;
    private String b;
    private String c;
    private String d;
    private int e;
    private String f;
    private ECJia_PHOTO g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private ArrayList<ECJia_GOODS_ATTR> m = new ArrayList();
    private String n;
    private String o;
    private String p;
    private String q;
    private int r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private int x;
    private Boolean y;
    private Boolean z;

    public int getIs_favourble() {
        return this.x;
    }

    public void setIs_favourble(int i) {
        this.x = i;
    }

    public Boolean getIsCheckedbuy() {
        if (this.A == 1) {
            this.y = Boolean.valueOf(true);
        } else if (this.A == 0) {
            this.y = Boolean.valueOf(false);
        }
        return this.y;
    }

    public void setIsCheckedbuy(Boolean bool) {
        this.y = bool;
    }

    public Boolean getIscheckDelete() {
        return this.z;
    }

    public void setIscheckDelete(Boolean bool) {
        this.z = bool;
    }

    public String getSeller_id() {
        return this.v;
    }

    public void setSeller_id(String str) {
        this.v = str;
    }

    public String getCan_handsel() {
        return this.a;
    }

    public void setCan_handsel(String str) {
        this.a = str;
    }

    public String getParent_id() {
        return this.w;
    }

    public void setParent_id(String str) {
        this.w = str;
    }

    public String getGoods_sn() {
        return this.b;
    }

    public void setGoods_sn(String str) {
        this.b = str;
    }

    public String getFormated_subtotal() {
        return this.c;
    }

    public void setFormated_subtotal(String str) {
        this.c = str;
    }

    public String getIs_gift() {
        return this.d;
    }

    public void setIs_gift(String str) {
        this.d = str;
    }

    public int getGoods_number() {
        return this.e;
    }

    public void setGoods_number(int i) {
        this.e = i;
    }

    public String getIs_real() {
        return this.f;
    }

    public void setIs_real(String str) {
        this.f = str;
    }

    public ECJia_PHOTO getImg() {
        return this.g;
    }

    public void setImg(ECJia_PHOTO eCJia_PHOTO) {
        this.g = eCJia_PHOTO;
    }

    public String getGoods_name() {
        return this.h;
    }

    public void setGoods_name(String str) {
        this.h = str;
    }

    public String getPid() {
        return this.i;
    }

    public void setPid(String str) {
        this.i = str;
    }

    public String getSubtotal() {
        return this.j;
    }

    public void setSubtotal(String str) {
        this.j = str;
    }

    public String getIs_shipping() {
        return this.k;
    }

    public void setIs_shipping(String str) {
        this.k = str;
    }

    public String getGoods_price() {
        return this.l;
    }

    public void setGoods_price(String str) {
        this.l = str;
    }

    public ArrayList<ECJia_GOODS_ATTR> getGoods_attr() {
        return this.m;
    }

    public void setGoods_attr(ArrayList<ECJia_GOODS_ATTR> arrayList) {
        this.m = arrayList;
    }

    public String getFormated_goods_price() {
        return this.n;
    }

    public void setFormated_goods_price(String str) {
        this.n = str;
    }

    public String getGoods_attr_id() {
        return this.o;
    }

    public void setGoods_attr_id(String str) {
        this.o = str;
    }

    public String getMarket_price() {
        return this.p;
    }

    public void setMarket_price(String str) {
        this.p = str;
    }

    public String getRec_type() {
        return this.q;
    }

    public void setRec_type(String str) {
        this.q = str;
    }

    public int getGoods_id() {
        return this.r;
    }

    public void setGoods_id(int i) {
        this.r = i;
    }

    public String getExtension_code() {
        return this.s;
    }

    public void setExtension_code(String str) {
        this.s = str;
    }

    public String getFormated_market_price() {
        return this.t;
    }

    public void setFormated_market_price(String str) {
        this.t = str;
    }

    public String getRec_id() {
        return this.u;
    }

    public void setRec_id(String str) {
        this.u = str;
    }

    public int getIs_checked() {
        return this.A;
    }

    public void setIs_checked(int i) {
        if (i == 1) {
            this.y = Boolean.valueOf(true);
        } else if (i == 0) {
            this.y = Boolean.valueOf(false);
        }
        this.A = i;
    }

    public static ECJia_GOODS_LIST fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_GOODS_LIST eCJia_GOODS_LIST = new ECJia_GOODS_LIST();
        eCJia_GOODS_LIST.a = jSONObject.optString("can_handsel");
        eCJia_GOODS_LIST.b = jSONObject.optString("goods_sn");
        eCJia_GOODS_LIST.c = jSONObject.optString("formated_subtotal");
        eCJia_GOODS_LIST.d = jSONObject.optString("is_gift");
        eCJia_GOODS_LIST.e = jSONObject.optInt("goods_number");
        eCJia_GOODS_LIST.f = jSONObject.optString("is_real");
        eCJia_GOODS_LIST.g = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        eCJia_GOODS_LIST.h = jSONObject.optString("goods_name");
        eCJia_GOODS_LIST.i = jSONObject.optString("pid");
        eCJia_GOODS_LIST.j = jSONObject.optString("subtotal");
        eCJia_GOODS_LIST.k = jSONObject.optString("is_shipping");
        eCJia_GOODS_LIST.l = jSONObject.optString("goods_price");
        JSONArray optJSONArray = jSONObject.optJSONArray("goods_attr");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                eCJia_GOODS_LIST.m.add(ECJia_GOODS_ATTR.fromJson(optJSONArray.getJSONObject(i)));
            }
        }
        eCJia_GOODS_LIST.n = jSONObject.optString("formated_goods_price");
        eCJia_GOODS_LIST.o = jSONObject.optString("goods_attr_id");
        eCJia_GOODS_LIST.p = jSONObject.optString("market_price");
        eCJia_GOODS_LIST.q = jSONObject.optString("rec_type");
        eCJia_GOODS_LIST.r = jSONObject.optInt("goods_id");
        eCJia_GOODS_LIST.s = jSONObject.optString("extension_code");
        eCJia_GOODS_LIST.t = jSONObject.optString("formated_market_price");
        eCJia_GOODS_LIST.u = jSONObject.optString("rec_id");
        eCJia_GOODS_LIST.v = jSONObject.optString("seller_id");
        eCJia_GOODS_LIST.w = jSONObject.optString("parent_id");
        eCJia_GOODS_LIST.x = jSONObject.optInt("is_favourble");
        eCJia_GOODS_LIST.z = Boolean.valueOf(false);
        eCJia_GOODS_LIST.A = jSONObject.optInt("is_checked");
        return eCJia_GOODS_LIST;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("can_handsel", this.a);
        jSONObject.put("goods_sn", this.b);
        jSONObject.put("formated_subtotal", this.c);
        jSONObject.put("is_gift", this.d);
        jSONObject.put("goods_number", this.e);
        jSONObject.put("is_real", this.f);
        if (this.g != null) {
            jSONObject.put("img", this.g.toJson());
        }
        jSONObject.put("goods_name", this.h);
        jSONObject.put("pid", this.i);
        jSONObject.put("subtotal", this.j);
        jSONObject.put("is_shipping", this.k);
        jSONObject.put("goods_price", this.l);
        for (int i = 0; i < this.m.size(); i++) {
            jSONArray.put(((ECJia_GOODS_ATTR) this.m.get(i)).toJson());
        }
        jSONObject.put("goods_attr", jSONArray);
        jSONObject.put("formated_goods_price", this.n);
        jSONObject.put("goods_attr_id", this.o);
        jSONObject.put("market_price", this.p);
        jSONObject.put("rec_type", this.q);
        jSONObject.put("goods_id", this.r);
        jSONObject.put("extension_code", this.s);
        jSONObject.put("formated_market_price", this.t);
        jSONObject.put("rec_id", this.u);
        jSONObject.put("parent_id", this.w);
        jSONObject.put("seller_id", this.v);
        jSONObject.put("is_favourble", this.x);
        jSONObject.put("is_checked", this.A);
        return jSONObject;
    }
}
