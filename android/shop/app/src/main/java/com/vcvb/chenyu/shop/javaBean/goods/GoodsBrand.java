package com.vcvb.chenyu.shop.javaBean.goods;

import java.util.List;

public class GoodsBrand {
    private String logo;
    private String name;
    private String price;
    private List<String> goodsTips;
    private List<Goods> goodses;
    private int isScroll = 0;

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
}
