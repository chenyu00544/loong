package com.vcvb.chenyu.shop.javaBean.cart;

import com.vcvb.chenyu.shop.javaBean.goods.Goods;
import com.vcvb.chenyu.shop.javaBean.store.Shop;

public class CartListBean {
    private Integer isType = 1;
    private boolean isCheckAll = false;
    private boolean isCheckOnce = false;
    private boolean isLong = false;
    private Shop shop;
    private Goods goods;

    public Integer getIsType() {
        return isType;
    }

    public void setIsType(Integer isType) {
        this.isType = isType;
    }

    public boolean isCheckAll() {
        return isCheckAll;
    }

    public void setCheckAll(boolean checkAll) {
        isCheckAll = checkAll;
    }

    public boolean isCheckOnce() {
        return isCheckOnce;
    }

    public void setCheckOnce(boolean checkOnce) {
        isCheckOnce = checkOnce;
    }

    public boolean isLong() {
        return isLong;
    }

    public void setLong(boolean aLong) {
        isLong = aLong;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }
}
