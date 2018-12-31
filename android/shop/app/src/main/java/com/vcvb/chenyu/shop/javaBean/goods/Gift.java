package com.vcvb.chenyu.shop.javaBean.goods;

import java.io.Serializable;

public class Gift implements Serializable {
    private Integer id;
    private String price;
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
