package com.vcvb.chenyu.shop.javaBean.evaluate;

public class Label {
    private boolean is_select = false;
    private Integer id;
    private String label_name;
    private String alias_en;
    private Integer count;

    public boolean isIs_select() {
        return is_select;
    }

    public void setIs_select(boolean is_select) {
        this.is_select = is_select;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLabel_name() {
        return label_name;
    }

    public void setLabel_name(String label_name) {
        this.label_name = label_name;
    }

    public String getAlias_en() {
        return alias_en;
    }

    public void setAlias_en(String alias_en) {
        this.alias_en = alias_en;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
