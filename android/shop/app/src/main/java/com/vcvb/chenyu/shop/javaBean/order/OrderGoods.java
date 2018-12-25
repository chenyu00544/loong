package com.vcvb.chenyu.shop.javaBean.order;

import java.io.Serializable;

public class OrderGoods  implements Serializable {

    private Integer rec_id;
    private Integer goods_id;
    private Integer order_id;

    private Integer ru_id;
    private Integer product_id;
    private String product_sn;
    private Integer o_goods_number;

    private String goods_name;
    private String goods_sn;

    private String goods_price;
    private String shop_price_format;
    private String market_price;
    private String market_price_format;

    private String goods_thumb;
    private String goods_img;
    private String original_img;

    private Integer is_fast;
    private Integer is_return;
    private Integer is_reality;
    private Integer is_real;
    private Integer is_on_sale;
    private Integer is_delete;

    private Integer is_promote;
    private Integer promote_start_date;
    private Integer promote_end_date;
    private String promote_price;
    private String promote_price_format;

    private String goods_attr;
    private String goods_attr_id;
    private String shipping_fee;

    private Integer current_time;

    private Integer bonus_type_id;

    private String goods_cause;

    public Integer getRec_id() {
        return rec_id;
    }

    public void setRec_id(Integer rec_id) {
        this.rec_id = rec_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getRu_id() {
        return ru_id;
    }

    public void setRu_id(Integer ru_id) {
        this.ru_id = ru_id;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_sn() {
        return product_sn;
    }

    public void setProduct_sn(String product_sn) {
        this.product_sn = product_sn;
    }

    public Integer getO_goods_number() {
        return o_goods_number;
    }

    public void setO_goods_number(Integer o_goods_number) {
        this.o_goods_number = o_goods_number;
    }

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_sn() {
        return goods_sn;
    }

    public void setGoods_sn(String goods_sn) {
        this.goods_sn = goods_sn;
    }

    public String getGoods_price() {
        return goods_price;
    }

    public void setGoods_price(String goods_price) {
        this.goods_price = goods_price;
    }

    public String getShop_price_format() {
        return shop_price_format;
    }

    public void setShop_price_format(String shop_price_format) {
        this.shop_price_format = shop_price_format;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getMarket_price_format() {
        return market_price_format;
    }

    public void setMarket_price_format(String market_price_format) {
        this.market_price_format = market_price_format;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }

    public String getGoods_img() {
        return goods_img;
    }

    public void setGoods_img(String goods_img) {
        this.goods_img = goods_img;
    }

    public String getOriginal_img() {
        return original_img;
    }

    public void setOriginal_img(String original_img) {
        this.original_img = original_img;
    }

    public Integer getIs_fast() {
        return is_fast;
    }

    public void setIs_fast(Integer is_fast) {
        this.is_fast = is_fast;
    }

    public Integer getIs_return() {
        return is_return;
    }

    public void setIs_return(Integer is_return) {
        this.is_return = is_return;
    }

    public Integer getIs_reality() {
        return is_reality;
    }

    public void setIs_reality(Integer is_reality) {
        this.is_reality = is_reality;
    }

    public Integer getIs_real() {
        return is_real;
    }

    public void setIs_real(Integer is_real) {
        this.is_real = is_real;
    }

    public Integer getIs_on_sale() {
        return is_on_sale;
    }

    public void setIs_on_sale(Integer is_on_sale) {
        this.is_on_sale = is_on_sale;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public Integer getIs_promote() {
        return is_promote;
    }

    public void setIs_promote(Integer is_promote) {
        this.is_promote = is_promote;
    }

    public Integer getPromote_start_date() {
        return promote_start_date;
    }

    public void setPromote_start_date(Integer promote_start_date) {
        this.promote_start_date = promote_start_date;
    }

    public Integer getPromote_end_date() {
        return promote_end_date;
    }

    public void setPromote_end_date(Integer promote_end_date) {
        this.promote_end_date = promote_end_date;
    }

    public String getPromote_price() {
        return promote_price;
    }

    public void setPromote_price(String promote_price) {
        this.promote_price = promote_price;
    }

    public String getPromote_price_format() {
        return promote_price_format;
    }

    public void setPromote_price_format(String promote_price_format) {
        this.promote_price_format = promote_price_format;
    }

    public String getGoods_attr() {
        return goods_attr;
    }

    public void setGoods_attr(String goods_attr) {
        this.goods_attr = goods_attr;
    }

    public String getGoods_attr_id() {
        return goods_attr_id;
    }

    public void setGoods_attr_id(String goods_attr_id) {
        this.goods_attr_id = goods_attr_id;
    }

    public String getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public Integer getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(Integer current_time) {
        this.current_time = current_time;
    }

    public Integer getBonus_type_id() {
        return bonus_type_id;
    }

    public void setBonus_type_id(Integer bonus_type_id) {
        this.bonus_type_id = bonus_type_id;
    }

    public String getGoods_cause() {
        return goods_cause;
    }

    public void setGoods_cause(String goods_cause) {
        this.goods_cause = goods_cause;
    }

}
