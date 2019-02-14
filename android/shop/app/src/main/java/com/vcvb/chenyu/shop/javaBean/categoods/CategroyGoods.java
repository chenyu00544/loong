package com.vcvb.chenyu.shop.javaBean.categoods;

import com.vcvb.chenyu.shop.javaBean.cate.Categroy;
import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.javaBean.home.Adses;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategroyGoods {

    private List<Adses> adses;
    private List<Categroy> cates;
    private List<Goods> goodses;

    public List<Adses> getAdses() {
        return adses;
    }

    public void setAdses(List<Adses> adses) {
        this.adses = adses;
    }

    public List<Goods> getGoodses() {
        return goodses;
    }

    public void setGoodses(List<Goods> goodses) {
        this.goodses = goodses;
    }

    public List<Categroy> getCates() {
        return cates;
    }

    public void setCates(List<Categroy> cates) {
        this.cates = cates;
    }

    public void setData(JSONObject Json) {
        try {
            JSONArray goodsesJsonArray = Json.getJSONArray("goods");
            List<Goods> goodses = new ArrayList<>();
            for (int i = 0; i < goodsesJsonArray.length(); i++) {
                JSONObject object = (JSONObject) goodsesJsonArray.get(i);
                Goods goods = JsonUtils.fromJsonObject(object, Goods.class);
                goodses.add(goods);
            }
            this.setGoodses(goodses);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

        try {
            JSONArray adsesJsonArray = Json.getJSONArray("ads");
            List<Adses> adses = new ArrayList<>();
            for (int i = 0; i < adsesJsonArray.length(); i++) {
                JSONObject object = (JSONObject) adsesJsonArray.get(i);
                Adses ads = new Adses();
                ads.setData(object);
                if(this.isHave(ads.getType())){
                    adses.add(ads);
                }
            }
            this.setAdses(adses);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray catesJsonArray = Json.getJSONArray("cates");
            List<Categroy> categroys = new ArrayList<>();
            for (int i = 0; i < catesJsonArray.length(); i++) {
                JSONObject object = (JSONObject) catesJsonArray.get(i);
                Categroy categroy = JsonUtils.fromJsonObject(object, Categroy.class);
                categroys.add(categroy);
            }
            this.setCates(categroys);
            this.setAdses(adses);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public boolean isHave(String str) {
        String[] strs = new String[]{
                "slide", "navigation", "ads_1", "ads_2", "ads_3", "ads_10", "ads_4", "ads_5",
                "ads_7", "ads_8", "ads_9"
        };
        for (int i = 0; i < strs.length; i++) {
            if(str.equals(strs[i])){
                return true;
            }
        }
        return false;
    }
}
