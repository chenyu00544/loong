package com.vcvb.chenyu.shop.javaBean.cate;

import com.vcvb.chenyu.shop.javaBean.home.Ads;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Categroy {
    private Integer id;
    private String cat_name;
    private Integer sort_order;
    private String cat_alias_name;
    private String touch_icon;
    private Ads ads;
    private List<Categroy> categroys;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public Integer getSort_order() {
        return sort_order;
    }

    public void setSort_order(Integer sort_order) {
        this.sort_order = sort_order;
    }

    public String getCat_alias_name() {
        return cat_alias_name;
    }

    public void setCat_alias_name(String cat_alias_name) {
        this.cat_alias_name = cat_alias_name;
    }

    public String getTouch_icon() {
        return touch_icon;
    }

    public void setTouch_icon(String touch_icon) {
        this.touch_icon = touch_icon;
    }

    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }

    public List<Categroy> getCategroys() {
        return categroys;
    }

    public void setCategroys(List<Categroy> categroys) {
        this.categroys = categroys;
    }

    public void setData(JSONObject json) {
        try {
            JSONArray jsonArray = json.getJSONArray("sub_cate");
            List<Categroy> _cates = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                Categroy categroy = JsonUtils.fromJsonObject(object, Categroy.class);
                categroy.setData(object);
                _cates.add(categroy);
            }
            this.setCategroys(_cates);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

        try {
            JSONObject object = json.getJSONObject("ads");
            if(object != null){
                Ads ads = JsonUtils.fromJsonObject(object, Ads.class);
                this.setAds(ads);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }

    }
}
