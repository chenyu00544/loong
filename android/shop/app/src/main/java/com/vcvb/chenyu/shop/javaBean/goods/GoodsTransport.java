package com.vcvb.chenyu.shop.javaBean.goods;

import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoodsTransport {
    private Integer tid;
    private String remarks;
    private String title;
    private String shipping_title;
    private List<GoodsTransportExt> exts;

    public Integer getTid() {
        return tid;
    }

    public List<GoodsTransportExt> getExts() {
        return exts;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getShipping_title() {
        return shipping_title;
    }

    public String getTitle() {
        return title;
    }

    public void setExts(List<GoodsTransportExt> exts) {
        this.exts = exts;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setShipping_title(String shipping_title) {
        this.shipping_title = shipping_title;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setData(JSONObject Json){
        try {
            JSONArray tExtJSONArray = Json.getJSONArray("t_ext");
            List<GoodsTransportExt> goodsTransportExts = new ArrayList<>();
            for (int i = 0; i < tExtJSONArray.length(); i++) {
                JSONObject object = (JSONObject) tExtJSONArray.get(i);
                GoodsTransportExt goodsTransportExt = JsonUtils.fromJsonObject(object, GoodsTransportExt.class);
                goodsTransportExts.add(goodsTransportExt);
            }
            this.setExts(goodsTransportExts);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }
}
