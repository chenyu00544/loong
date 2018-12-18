package com.vcvb.chenyu.shop.javaBean.faat;

public class FaatNav {
    private boolean IsSelect = false;
    private String act_name;
    private String icon_pic;
    private String group_id;
    private String activity_thumb;
    private String app_icon;
    private String color;

    public boolean getIsSelect() {
        return IsSelect;
    }

    public void setIsSelect(boolean IsSelect) {
        this.IsSelect = IsSelect;
    }

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

    public String getIcon_pic() {
        return icon_pic;
    }

    public void setIcon_pic(String icon_pic) {
        this.icon_pic = icon_pic;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getActivity_thumb() {
        return activity_thumb;
    }

    public void setActivity_thumb(String activity_thumb) {
        this.activity_thumb = activity_thumb;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getApp_icon() {
        return app_icon;
    }

    public void setApp_icon(String app_icon) {
        this.app_icon = app_icon;
    }
}
