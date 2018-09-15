package com.vcvb.chenyu.shop.javaBean.goods;

import java.util.List;

public class GoodsPrice {
    private String price;
    private String market;
    private List<String> goodsTips;

    public List<String> getGoodsTips() {
        return goodsTips;
    }

    public void setGoodsTips(List<String> goodsTips) {
        this.goodsTips = goodsTips;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
