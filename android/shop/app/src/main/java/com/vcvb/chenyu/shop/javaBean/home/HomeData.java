package com.vcvb.chenyu.shop.javaBean.home;

import com.vcvb.chenyu.shop.javaBean.goods.Goods;

import java.util.List;

public class HomeData {

    private List<Banner> banner;
    private List<AdsList> adslist;
    private List<Goods> goods;

    /**
     * @return the array
     */
    public List<Banner> getBanner() {
        return banner;
    }
    /**
     * @param array the array to set
     */
    public void setBanner(List<Banner> array) {
        this.banner = array;
    }

}
