package com.vcvb.chenyu.shop.javaBean.faat;

import java.util.List;

public class Faat {
    private int scrollX = 0;
    private List<FaatNav> faatNavs;
    private List<Object> goodses;
    private Banner banner;

    public int getScrollX() {
        return scrollX;
    }

    public void setScrollX(int scrollX) {
        this.scrollX = scrollX;
    }

    public List<Object> getGoodses() {
        return goodses;
    }

    public List<FaatNav> getFaatNavs() {
        return faatNavs;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setGoodses(List<Object> goodses) {
        this.goodses = goodses;
    }

    public void setFaatNavs(List<FaatNav> faatNavs) {
        this.faatNavs = faatNavs;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

}
