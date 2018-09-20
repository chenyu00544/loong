package com.vcvb.chenyu.shop.javaBean.home;

import java.util.List;

public class HomeBean {
    private List<Slide> slides;
    private List<Adses> adses;
    private List<Goods> goodsList;

    public List<Adses> getAdses() {
        return adses;
    }

    public void setAdses(List<Adses> adses) {
        this.adses = adses;
    }

    public List<Slide> getSlides() {
        return slides;
    }

    public void setSlides(List<Slide> slides) {
        this.slides = slides;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
