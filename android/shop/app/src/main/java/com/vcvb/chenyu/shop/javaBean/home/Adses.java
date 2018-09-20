package com.vcvb.chenyu.shop.javaBean.home;

import java.util.List;

public class Adses {
    private List<Ads> ads;
    private int isType;

    public int getIsType() {
        return isType;
    }

    public void setIsType(int isType) {
        this.isType = isType;
    }

    public List<Ads> getAds() {
        return ads;
    }

    public void setAds(List<Ads> ads) {
        this.ads = ads;
    }
}
