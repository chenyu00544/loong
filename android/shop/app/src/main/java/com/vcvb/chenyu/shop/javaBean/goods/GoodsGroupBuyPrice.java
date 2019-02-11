package com.vcvb.chenyu.shop.javaBean.goods;

import java.io.Serializable;

public class GoodsGroupBuyPrice implements Serializable {
    private Integer amount;
    private String price;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
