package com.vcvb.chenyu.shop.javaBean.goods;

import java.util.List;

public class GoodsSpecifications {
    private List<GoodsSpecification> goodsSpecifications;
    private String headerLogo;

    public String getHeaderLogo() {
        return headerLogo;
    }

    public void setHeaderLogo(String headerLogo) {
        this.headerLogo = headerLogo;
    }

    public List<GoodsSpecification> getGoodsSpecifications() {
        return goodsSpecifications;
    }

    public void setGoodsSpecifications(List<GoodsSpecification> goodsSpecifications) {
        this.goodsSpecifications = goodsSpecifications;
    }

}
