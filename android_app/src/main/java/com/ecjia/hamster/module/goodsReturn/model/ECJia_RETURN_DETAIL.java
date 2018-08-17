package com.ecjia.hamster.module.goodsReturn.model;

import com.ecjia.hamster.model.ECJia_PHOTO;
import com.sina.weibo.sdk.constant.WBConstants;
import com.umeng.analytics.pro.x;
import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_RETURN_DETAIL implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private int i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private ECJia_PHOTO q = new ECJia_PHOTO();
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private String w;
    private String x;
    private ArrayList<String> y = new ArrayList();
    private ArrayList<RETURN_LOG> z = new ArrayList();

    public static class RETURN_LOG implements Serializable {
        private String a;
        private String b;
        private String c;

        public String getLog_description() {
            return this.a;
        }

        public void setLog_description(String str) {
            this.a = str;
        }

        public String getFormatted_action_time() {
            return this.b;
        }

        public void setFormatted_action_time(String str) {
            this.b = str;
        }

        public String getAction_user() {
            return this.c;
        }

        public void setAction_user(String str) {
            this.c = str;
        }

        public static RETURN_LOG fromJson(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            RETURN_LOG return_log = new RETURN_LOG();
            return_log.a = jSONObject.optString("log_description");
            return_log.b = jSONObject.optString("formatted_action_time");
            return_log.c = jSONObject.optString("action_user");
            return return_log;
        }
    }

    public String getOrder_sn() {
        return this.d;
    }

    public void setOrder_sn(String str) {
        this.d = str;
    }

    public String getLabel_return_reason() {
        return this.l;
    }

    public void setLabel_return_reason(String str) {
        this.l = str;
    }

    public String getService_phone() {
        return this.c;
    }

    public void setService_phone(String str) {
        this.c = str;
    }

    public String getSeller_id() {
        return this.a;
    }

    public void setSeller_id(String str) {
        this.a = str;
    }

    public String getSeller_name() {
        return this.b;
    }

    public void setSeller_name(String str) {
        this.b = str;
    }

    public String getReturn_id() {
        return this.e;
    }

    public void setReturn_id(String str) {
        this.e = str;
    }

    public String getReturn_sn() {
        return this.f;
    }

    public void setReturn_sn(String str) {
        this.f = str;
    }

    public String getReturn_type() {
        return this.g;
    }

    public void setReturn_type(String str) {
        this.g = str;
    }

    public String getGoods_name() {
        return this.h;
    }

    public void setGoods_name(String str) {
        this.h = str;
    }

    public int getNumber() {
        return this.i;
    }

    public void setNumber(int i) {
        this.i = i;
    }

    public String getCreate_time() {
        return this.j;
    }

    public void setCreate_time(String str) {
        this.j = str;
    }

    public String getReturn_status() {
        return this.k;
    }

    public void setReturn_status(String str) {
        this.k = str;
    }

    public String getLabel_return_status() {
        return this.m;
    }

    public void setLabel_return_status(String str) {
        this.m = str;
    }

    public String getReturn_description() {
        return this.o;
    }

    public void setReturn_description(String str) {
        this.o = str;
    }

    public String getFormatted_refund_price() {
        return this.p;
    }

    public void setFormatted_refund_price(String str) {
        this.p = str;
    }

    public ECJia_PHOTO getImg() {
        return this.q;
    }

    public void setImg(ECJia_PHOTO eCJia_PHOTO) {
        this.q = eCJia_PHOTO;
    }

    public String getConsignee() {
        return this.r;
    }

    public void setConsignee(String str) {
        this.r = str;
    }

    public String getMobile() {
        return this.s;
    }

    public void setMobile(String str) {
        this.s = str;
    }

    public String getCountry() {
        return this.t;
    }

    public void setCountry(String str) {
        this.t = str;
    }

    public String getProvince() {
        return this.u;
    }

    public void setProvince(String str) {
        this.u = str;
    }

    public String getCity() {
        return this.v;
    }

    public void setCity(String str) {
        this.v = str;
    }

    public String getDistrict() {
        return this.w;
    }

    public void setDistrict(String str) {
        this.w = str;
    }

    public String getAddress() {
        return this.x;
    }

    public void setAddress(String str) {
        this.x = str;
    }

    public ArrayList<String> getReturn_images() {
        return this.y;
    }

    public void setReturn_images(ArrayList<String> arrayList) {
        this.y = arrayList;
    }

    public String getRefused_reason() {
        return this.n;
    }

    public void setRefused_reason(String str) {
        this.n = str;
    }

    public ArrayList<RETURN_LOG> getReturn_log() {
        return this.z;
    }

    public void setReturn_log(ArrayList<RETURN_LOG> arrayList) {
        this.z = arrayList;
    }

    public static ECJia_RETURN_DETAIL fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        int i;
        ECJia_RETURN_DETAIL eCJia_RETURN_DETAIL = new ECJia_RETURN_DETAIL();
        eCJia_RETURN_DETAIL.a = jSONObject.optString("seller_id");
        eCJia_RETURN_DETAIL.b = jSONObject.optString("seller_name");
        eCJia_RETURN_DETAIL.d = jSONObject.optString("order_sn");
        eCJia_RETURN_DETAIL.e = jSONObject.optString("return_id");
        eCJia_RETURN_DETAIL.f = jSONObject.optString("return_sn");
        eCJia_RETURN_DETAIL.g = jSONObject.optString("return_type");
        eCJia_RETURN_DETAIL.h = jSONObject.optString("goods_name");
        eCJia_RETURN_DETAIL.i = jSONObject.optInt("number");
        eCJia_RETURN_DETAIL.j = jSONObject.optString(WBConstants.GAME_PARAMS_GAME_CREATE_TIME);
        eCJia_RETURN_DETAIL.k = jSONObject.optString("return_status");
        eCJia_RETURN_DETAIL.l = jSONObject.optString("label_return_reason");
        eCJia_RETURN_DETAIL.m = jSONObject.optString("label_return_status");
        eCJia_RETURN_DETAIL.n = jSONObject.optString("refused_reason");
        eCJia_RETURN_DETAIL.o = jSONObject.optString("return_description");
        eCJia_RETURN_DETAIL.p = jSONObject.optString("formatted_refund_price");
        eCJia_RETURN_DETAIL.q = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("img"));
        eCJia_RETURN_DETAIL.r = jSONObject.optString("consignee");
        eCJia_RETURN_DETAIL.s = jSONObject.optString("mobile");
        eCJia_RETURN_DETAIL.t = jSONObject.optString(x.G);
        eCJia_RETURN_DETAIL.u = jSONObject.optString("province");
        eCJia_RETURN_DETAIL.v = jSONObject.optString("city");
        eCJia_RETURN_DETAIL.w = jSONObject.optString("district");
        eCJia_RETURN_DETAIL.x = jSONObject.optString("address");
        eCJia_RETURN_DETAIL.c = jSONObject.optString("service_phone");
        JSONArray optJSONArray = jSONObject.optJSONArray("return_images");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (i = 0; i < optJSONArray.length(); i++) {
                eCJia_RETURN_DETAIL.y.add(optJSONArray.optString(i));
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("return_log");
        if (optJSONArray != null && optJSONArray2.length() > 0) {
            for (i = 0; i < optJSONArray2.length(); i++) {
                eCJia_RETURN_DETAIL.z.add(0, RETURN_LOG.fromJson(optJSONArray2.optJSONObject(i)));
            }
        }
        return eCJia_RETURN_DETAIL;
    }
}
