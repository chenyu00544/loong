package com.ecjia.hamster.model;

import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_STOREGOODSLIST implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private int e;
    private int f;
    private ArrayList<ECJia_GOODS_LIST> g = new ArrayList();
    private ArrayList<ECJia_SHIPPING> h = new ArrayList();

    public String getRu_id() {
        return this.a;
    }

    public void setRu_id(String str) {
        this.a = str;
    }

    public String getRu_name() {
        return this.b;
    }

    public void setRu_name(String str) {
        this.b = str;
    }

    public String getGoods_amount() {
        return this.c;
    }

    public void setGoods_amount(String str) {
        this.c = str;
    }

    public String getTmp_shipping_id() {
        return this.d;
    }

    public void setTmp_shipping_id(String str) {
        this.d = str;
    }

    public int getIs_freight() {
        return this.e;
    }

    public void setIs_freight(int i) {
        this.e = i;
    }

    public int getShipping_count() {
        return this.f;
    }

    public void setShipping_count(int i) {
        this.f = i;
    }

    public ArrayList<ECJia_GOODS_LIST> getGoods_list() {
        return this.g;
    }

    public void setGoods_list(ArrayList<ECJia_GOODS_LIST> arrayList) {
        this.g = arrayList;
    }

    public ArrayList<ECJia_SHIPPING> getShipping_list() {
        return this.h;
    }

    public void setShipping_list(ArrayList<ECJia_SHIPPING> arrayList) {
        this.h = arrayList;
    }

    public static ECJia_STOREGOODSLIST fromJson(JSONObject jSONObject) throws JSONException {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        ECJia_STOREGOODSLIST eCJia_STOREGOODSLIST = new ECJia_STOREGOODSLIST();
        eCJia_STOREGOODSLIST.a = jSONObject.optString("ru_id");
        eCJia_STOREGOODSLIST.b = jSONObject.optString("ru_name");
        eCJia_STOREGOODSLIST.c = jSONObject.optString("goods_amount");
        eCJia_STOREGOODSLIST.d = jSONObject.optString("tmp_shipping_id");
        eCJia_STOREGOODSLIST.e = jSONObject.optInt("is_freight");
        eCJia_STOREGOODSLIST.f = jSONObject.optInt("shipping_count");
        JSONArray optJSONArray = jSONObject.optJSONArray("shipping");
        if (optJSONArray != null) {
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                ECJia_SHIPPING fromJson = ECJia_SHIPPING.fromJson(optJSONArray.getJSONObject(i2));
                if (i2 == 0) {
                    fromJson.setSelected(true);
                }
                eCJia_STOREGOODSLIST.h.add(fromJson);
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("goods_list");
        if (optJSONArray2 != null) {
            while (i < optJSONArray2.length()) {
                eCJia_STOREGOODSLIST.g.add(ECJia_GOODS_LIST.fromJson(optJSONArray2.getJSONObject(i)));
                i++;
            }
        }
        return eCJia_STOREGOODSLIST;
    }

    public JSONObject toJson() throws JSONException {
        int i = 0;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONArray jSONArray2 = new JSONArray();
        jSONObject.put("ru_id", this.a);
        jSONObject.put("ru_name", this.b);
        jSONObject.put("goods_amount", this.c);
        jSONObject.put("tmp_shipping_id", this.d);
        jSONObject.put("is_freight", this.e);
        jSONObject.put("shipping_count", this.f);
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            ECJia_SHIPPING eCJia_SHIPPING = (ECJia_SHIPPING) this.h.get(i2);
            if (i2 == 0) {
                eCJia_SHIPPING.setSelected(true);
            }
            jSONArray.put(eCJia_SHIPPING.toJson());
        }
        jSONObject.put("shipping", jSONArray);
        while (i < this.g.size()) {
            jSONArray2.put(((ECJia_GOODS_LIST) this.g.get(i)).toJson());
            i++;
        }
        jSONObject.put("goods_list", jSONArray2);
        return jSONObject;
    }
}
