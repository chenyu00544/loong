package com.vcvb.chenyu.shop.javaBean.goods;

import java.io.Serializable;

public class GoodsFC implements Serializable {
    private Integer id;
    private String goods_id;
    private String cfull;
    private String cfull_format;
    private String creduce;
    private String creduce_format;

    public String getGoods_id() {
        return goods_id;
    }

    public Integer getId() {
        return id;
    }

    public String getCfull() {
        return cfull;
    }

    public String getCfull_format() {
        return cfull_format;
    }

    public String getCreduce() {
        return creduce;
    }

    public String getCreduce_format() {
        return creduce_format;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCfull(String cfull) {
        this.cfull = cfull;
    }

    public void setCfull_format(String cfull_format) {
        this.cfull_format = cfull_format;
    }

    public void setCreduce(String creduce) {
        this.creduce = creduce;
    }

    public void setCreduce_format(String creduce_format) {
        this.creduce_format = creduce_format;
    }
}
