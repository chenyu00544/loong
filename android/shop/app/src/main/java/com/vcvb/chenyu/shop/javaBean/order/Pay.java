package com.vcvb.chenyu.shop.javaBean.order;

public class Pay {
    private Integer def = 0;
    private Integer pay_id;
    private Integer pay_fee;
    private String pay_code;
    private String pay_name;

    public Integer getDef() {
        return def;
    }

    public void setDef(Integer def) {
        this.def = def;
    }

    public Integer getPay_id() {
        return pay_id;
    }

    public void setPay_id(Integer pay_id) {
        this.pay_id = pay_id;
    }

    public Integer getPay_fee() {
        return pay_fee;
    }

    public void setPay_fee(Integer pay_fee) {
        this.pay_fee = pay_fee;
    }

    public String getPay_code() {
        return pay_code;
    }

    public void setPay_code(String pay_code) {
        this.pay_code = pay_code;
    }

    public String getPay_name() {
        return pay_name;
    }

    public void setPay_name(String pay_name) {
        this.pay_name = pay_name;
    }
}
