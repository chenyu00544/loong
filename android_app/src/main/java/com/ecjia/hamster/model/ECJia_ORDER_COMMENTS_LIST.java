package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_ORDER_COMMENTS_LIST implements Serializable {
    private String a;
    private String b;
    private String c;
    private ECJia_PHOTO d;
    private int e;
    private int f;
    private int g;

    public String getRec_id() {
        return this.a;
    }

    public void setRec_id(String str) {
        this.a = str;
    }

    public String getGoods_name() {
        return this.b;
    }

    public void setGoods_name(String str) {
        this.b = str;
    }

    public String getGoods_price() {
        return this.c;
    }

    public void setGoods_price(String str) {
        this.c = str;
    }

    public ECJia_PHOTO getImg() {
        return this.d;
    }

    public void setImg(ECJia_PHOTO eCJia_PHOTO) {
        this.d = eCJia_PHOTO;
    }

    public int getIs_commented() {
        return this.e;
    }

    public void setIs_commented(int i) {
        this.e = i;
    }

    public int getIs_showorder() {
        return this.f;
    }

    public void setIs_showorder(int i) {
        this.f = i;
    }

    public int getType() {
        return this.g;
    }

    public void setType(int i) {
        this.g = i;
    }

    public static ECJia_ORDER_COMMENTS_LIST fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_ORDER_COMMENTS_LIST eCJia_ORDER_COMMENTS_LIST = new ECJia_ORDER_COMMENTS_LIST();
        eCJia_ORDER_COMMENTS_LIST.a = jSONObject.optString("rec_id");
        eCJia_ORDER_COMMENTS_LIST.b = jSONObject.optString("goods_name");
        eCJia_ORDER_COMMENTS_LIST.c = jSONObject.optString("goods_price");
        eCJia_ORDER_COMMENTS_LIST.d = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        eCJia_ORDER_COMMENTS_LIST.e = jSONObject.optInt("is_commented");
        eCJia_ORDER_COMMENTS_LIST.f = jSONObject.optInt("is_showorder");
        return eCJia_ORDER_COMMENTS_LIST;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("rec_id", this.a);
        jSONObject.put("goods_name", this.b);
        jSONObject.put("goods_price", this.c);
        if (this.d != null) {
            jSONObject.put("img", this.d.toJson());
        }
        jSONObject.put("is_commented", this.e);
        jSONObject.put("is_showorder", this.f);
        return jSONObject;
    }
}
