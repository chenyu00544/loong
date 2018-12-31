package com.vcvb.chenyu.shop.javaBean.goods;

import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsBrand implements Serializable {
    private Integer id;
    private String brand_name;
    private String brand_letter;
    private String brand_first_char;
    private String brand_logo;
    private List<Goods> goodses;
    private int isScroll = 0;

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

    public List<Goods> getGoodses() {
        return goodses;
    }

    public void setGoodses(List<Goods> goodses) {
        this.goodses = goodses;
    }

    public int getIsScroll() {
        return isScroll;
    }

    public void setIsScroll(int isScroll) {
        this.isScroll = isScroll;
    }

    public void setData(JSONObject Json) throws JSONException {
        try {
            JSONArray goodsJsonArray = Json.getJSONArray("brand_goodses");
            List<Goods> _goods = new ArrayList<>();
            for (int i = 0; i < goodsJsonArray.length(); i++) {
                JSONObject object = (JSONObject) goodsJsonArray.get(i);
                Goods goods = JsonUtils.fromJsonObject(object, Goods.class);
                _goods.add(goods);
            }
            this.setGoodses(_goods);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }
}
