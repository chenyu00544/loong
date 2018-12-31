package com.vcvb.chenyu.shop.javaBean.goods;

import java.io.Serializable;

public class GoodsBanner implements Serializable {
    private Integer img_id;
    private String goods_id;
    private String img_original;
    private String img_url;

    public String getGoods_id() {
        return goods_id;
    }

    public Integer getImg_id() {
        return img_id;
    }

    public String getImg_original() {
        return img_original;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public void setImg_id(Integer img_id) {
        this.img_id = img_id;
    }

    public void setImg_original(String img_original) {
        this.img_original = img_original;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
