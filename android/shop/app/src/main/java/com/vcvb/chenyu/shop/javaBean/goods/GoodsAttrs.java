package com.vcvb.chenyu.shop.javaBean.goods;

import java.util.List;

public class GoodsAttrs {
    private List<GoodsAttr> attrs;
    private String attrName;

    public List<GoodsAttr> getAttrs() {
        return attrs;
    }

    public void setAttrs(List<GoodsAttr> attrs) {
        this.attrs = attrs;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }
}
