package com.vcvb.chenyu.shop.javaBean.cart;

import java.util.List;

import xiaofei.library.datastorage.annotation.ClassId;
import xiaofei.library.datastorage.annotation.ObjectId;

@ClassId("LocalCartBean")
public class LocalCartBean {
    @ObjectId
    private String hash_code = "0";

    private Integer goods_id;
    private List<Integer> attr_ids;
    private List<Integer> goods_attr_ids;
    private Integer num;

    public String getHash_code() {
        return hash_code;
    }

    public void setHash_code(String hash_code) {
        this.hash_code = hash_code;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public List<Integer> getAttr_ids() {
        return attr_ids;
    }

    public void setAttr_ids(List<Integer> attr_ids) {
        this.attr_ids = attr_ids;
    }

    public List<Integer> getGoods_attr_ids() {
        return goods_attr_ids;
    }

    public void setGoods_attr_ids(List<Integer> goods_attr_ids) {
        this.goods_attr_ids = goods_attr_ids;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
