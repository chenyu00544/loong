package com.vcvb.chenyu.shop.javaBean.goods;

import java.util.List;

public class GoodsBrand {
    private String logo;
    private String name;
    private String price;
    private List<String> goodsTips;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<String> getGoodsTips() {
        return goodsTips;
    }

    public void setGoodsTips(List<String> goodsTips) {
        this.goodsTips = goodsTips;
    }
}
