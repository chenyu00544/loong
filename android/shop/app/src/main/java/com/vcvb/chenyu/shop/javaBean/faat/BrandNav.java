package com.vcvb.chenyu.shop.javaBean.faat;

public class BrandNav {
    private boolean IsSelect = false;
    private String act_name;
    private String cat_alias_name;
    private String touch_icon;
    private String color;

    public boolean isSelect() {
        return IsSelect;
    }

    public void setSelect(boolean select) {
        IsSelect = select;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getCat_alias_name() {
        return cat_alias_name;
    }

    public void setCat_alias_name(String cat_alias_name) {
        this.cat_alias_name = cat_alias_name;
    }

    public String getTouch_icon() {
        return touch_icon;
    }

    public void setTouch_icon(String touch_icon) {
        this.touch_icon = touch_icon;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
