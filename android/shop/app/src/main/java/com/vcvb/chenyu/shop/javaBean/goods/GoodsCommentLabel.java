package com.vcvb.chenyu.shop.javaBean.goods;

public class GoodsCommentLabel {
    private Integer id;
    private String label_name;
    private String alias_en;
    private Integer count;

    public Integer getId() {
        return id;
    }

    public Integer getCount() {
        return count;
    }

    public String getAlias_en() {
        return alias_en;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setAlias_en(String alias_en) {
        this.alias_en = alias_en;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }
}
