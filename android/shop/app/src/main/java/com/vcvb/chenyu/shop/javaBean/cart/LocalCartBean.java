package com.vcvb.chenyu.shop.javaBean.cart;

import xiaofei.library.datastorage.annotation.ClassId;
import xiaofei.library.datastorage.annotation.ObjectId;

@ClassId("LocalCartBean")
public class LocalCartBean {
    @ObjectId
    private String goods_id;
    private String[] attr_ids;
    private String[] goods_attr_ids;
    private String num;

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String[] getAttr_ids() {
        return attr_ids;
    }

    public void setAttr_ids(String[] attr_ids) {
        this.attr_ids = attr_ids;
    }

    public String[] getGoods_attr_ids() {
        return goods_attr_ids;
    }

    public void setGoods_attr_ids(String[] goods_attr_ids) {
        this.goods_attr_ids = goods_attr_ids;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
