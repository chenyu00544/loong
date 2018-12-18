package com.vcvb.chenyu.shop.javaBean.order;

public class OrderReturnCause {
    private boolean is_select = false;
    private Integer cause_id;
    private Integer is_show;
    private String cause_name;

    public boolean isIs_select() {
        return is_select;
    }

    public void setIs_select(boolean is_select) {
        this.is_select = is_select;
    }

    public Integer getCause_id() {
        return cause_id;
    }

    public void setCause_id(Integer cause_id) {
        this.cause_id = cause_id;
    }

    public Integer getIs_show() {
        return is_show;
    }

    public void setIs_show(Integer is_show) {
        this.is_show = is_show;
    }

    public String getCause_name() {
        return cause_name;
    }

    public void setCause_name(String cause_name) {
        this.cause_name = cause_name;
    }
}
