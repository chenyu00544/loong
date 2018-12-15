package com.vcvb.chenyu.shop.javaBean.faat;

import com.vcvb.chenyu.shop.javaBean.home.Ads;
import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Banner {
    private List<Ads> ads;
    private String type;
    private String width;
    private String height;
    private Integer navpos;

    public List<Ads> getAds() {
        return ads;
    }

    public void setAds(List<Ads> ads) {
        this.ads = ads;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getWidth() {
        return width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getNavpos() {
        return navpos;
    }

    public void setNavpos(Integer navpos) {
        this.navpos = navpos;
    }

    public void setData(JSONObject Json){
        try {
            JSONArray adsJsonArray = Json.getJSONArray("ads");
            List<Ads> ads = new ArrayList<>();
            for (int i = 0; i < adsJsonArray.length(); i++) {
                JSONObject object = (JSONObject) adsJsonArray.get(i);
                Ads ad = JsonUtils.fromJsonObject(object, Ads.class);
                ads.add(ad);
            }
            this.setAds(ads);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }
}
