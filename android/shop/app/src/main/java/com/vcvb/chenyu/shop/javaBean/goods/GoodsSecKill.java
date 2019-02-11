package com.vcvb.chenyu.shop.javaBean.goods;

import java.io.Serializable;

public class GoodsSecKill implements Serializable {

    private Integer id;
    private Integer tb_id;
    private Integer goods_id;
    private Integer sec_id;
    private Integer ru_id;
    private Integer start_time;
    private Integer end_time;
    private String acti_title;
    private String sec_price;
    private Integer sec_num;
    private Integer sec_limit;
    private Integer b_time;
    private Integer e_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTb_id() {
        return tb_id;
    }

    public void setTb_id(Integer tb_id) {
        this.tb_id = tb_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getSec_id() {
        return sec_id;
    }

    public void setSec_id(Integer sec_id) {
        this.sec_id = sec_id;
    }

    public Integer getRu_id() {
        return ru_id;
    }

    public void setRu_id(Integer ru_id) {
        this.ru_id = ru_id;
    }

    public Integer getStart_time() {
        return start_time;
    }

    public void setStart_time(Integer start_time) {
        this.start_time = start_time;
    }

    public Integer getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Integer end_time) {
        this.end_time = end_time;
    }

    public String getActi_title() {
        return acti_title;
    }

    public void setActi_title(String acti_title) {
        this.acti_title = acti_title;
    }

    public String getSec_price() {
        return sec_price;
    }

    public void setSec_price(String sec_price) {
        this.sec_price = sec_price;
    }

    public Integer getSec_num() {
        return sec_num;
    }

    public void setSec_num(Integer sec_num) {
        this.sec_num = sec_num;
    }

    public Integer getSec_limit() {
        return sec_limit;
    }

    public void setSec_limit(Integer sec_limit) {
        this.sec_limit = sec_limit;
    }

    public Integer getB_time() {
        return b_time;
    }

    public void setB_time(Integer b_time) {
        this.b_time = b_time;
    }

    public Integer getE_time() {
        return e_time;
    }

    public void setE_time(Integer e_time) {
        this.e_time = e_time;
    }
}
