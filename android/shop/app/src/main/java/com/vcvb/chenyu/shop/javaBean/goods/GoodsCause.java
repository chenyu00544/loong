package com.vcvb.chenyu.shop.javaBean.goods;

import java.io.Serializable;

public class GoodsCause implements Serializable {
    private Integer cause_type;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCause_type() {
        return cause_type;
    }

    public void setCause_type(Integer cause_type) {
        this.cause_type = cause_type;
    }
}
