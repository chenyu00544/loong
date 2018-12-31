package com.vcvb.chenyu.shop.javaBean.goods;

import java.io.Serializable;

public class GoodsGVP implements Serializable {
    private Integer id;
    private String price_type;
    private String goods_id;
    private String volume_number;
    private String volume_price;
    private String volume_price_format;

    public String getGoods_id() {
        return goods_id;
    }

    public Integer getId() {
        return id;
    }

    public String getVolume_price() {
        return volume_price;
    }

    public String getVolume_number() {
        return volume_number;
    }

    public String getPrice_type() {
        return price_type;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setVolume_price(String volume_price) {
        this.volume_price = volume_price;
    }

    public void setVolume_number(String volume_number) {
        this.volume_number = volume_number;
    }

    public void setPrice_type(String price_type) {
        this.price_type = price_type;
    }

    public String getVolume_price_format() {
        return volume_price_format;
    }

    public void setVolume_price_format(String volume_price_format) {
        this.volume_price_format = volume_price_format;
    }
}
