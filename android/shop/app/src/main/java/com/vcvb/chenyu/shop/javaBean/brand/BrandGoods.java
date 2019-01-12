package com.vcvb.chenyu.shop.javaBean.brand;

import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class BrandGoods {
    private Integer id;
    private String brand_name;
    private String brand_letter;
    private String brand_first_char;
    private String brand_logo;
    private String index_img;
    private String brand_bg;
    private String color;
    private String site_url;
    private String brand_desc;
    private String brand_bg_app;

    private List<Goods> goodses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand_name() {
        return brand_name;
    }

    public void setBrand_name(String brand_name) {
        this.brand_name = brand_name;
    }

    public String getBrand_letter() {
        return brand_letter;
    }

    public void setBrand_letter(String brand_letter) {
        this.brand_letter = brand_letter;
    }

    public String getBrand_first_char() {
        return brand_first_char;
    }

    public void setBrand_first_char(String brand_first_char) {
        this.brand_first_char = brand_first_char;
    }

    public String getBrand_logo() {
        return brand_logo;
    }

    public void setBrand_logo(String brand_logo) {
        this.brand_logo = brand_logo;
    }

    public String getIndex_img() {
        return index_img;
    }

    public void setIndex_img(String index_img) {
        this.index_img = index_img;
    }

    public String getBrand_bg() {
        return brand_bg;
    }

    public void setBrand_bg(String brand_bg) {
        this.brand_bg = brand_bg;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSite_url() {
        return site_url;
    }

    public void setSite_url(String site_url) {
        this.site_url = site_url;
    }

    public String getBrand_desc() {
        return brand_desc;
    }

    public void setBrand_desc(String brand_desc) {
        this.brand_desc = brand_desc;
    }

    public String getBrand_bg_app() {
        return brand_bg_app;
    }

    public void setBrand_bg_app(String brand_bg_app) {
        this.brand_bg_app = brand_bg_app;
    }

    public List<Goods> getGoodses() {
        return goodses;
    }

    public void setGoodses(List<Goods> goodses) {
        this.goodses = goodses;
    }

    public void setData(JSONObject json){
        try {
            JSONArray jsonArray = json.getJSONArray("goods");
            List<Goods> goodses = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
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
    }
}
