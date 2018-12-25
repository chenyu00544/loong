package com.vcvb.chenyu.shop.javaBean.order;

import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrderDetail implements Serializable {
    private String order_id_str;
    private String main_order_id;
    private String order_sn;
    private Integer user_id;
    private Integer order_status;
    private Integer pay_status;
    private Integer shipping_status;
    private Integer shipping_time;
    private Integer add_time;
    private Integer current_time;
    private Integer auto_delivery_time;
    private Integer goods_cause;
    private String shipping_fee;
    private String goods_amount;
    private String insure_fee;
    private String pay_fee;
    private String pack_fee;
    private String card_fee;
    private String money_paid;
    private String order_amount;
    private String integral_money;
    private String bonus;
    private String discount;
    private String tax;
    private String add_time_date;
    private String mobile;
    private String consignee;
    private String address;
    private String country_name;
    private String province_name;
    private String city_name;
    private String district_name;
    private String invoice_no;
    private String shipping_name;
    private List<OrderGoods> orderGoodses;

    public Integer getAuto_delivery_time() {
        return auto_delivery_time;
    }

    public void setAuto_delivery_time(Integer auto_delivery_time) {
        this.auto_delivery_time = auto_delivery_time;
    }

    public Integer getShipping_time() {
        return shipping_time;
    }

    public void setShipping_time(Integer shipping_time) {
        this.shipping_time = shipping_time;
    }

    public String getOrder_id_str() {
        return order_id_str;
    }

    public void setOrder_id_str(String order_id_str) {
        this.order_id_str = order_id_str;
    }

    public String getMain_order_id() {
        return main_order_id;
    }

    public void setMain_order_id(String main_order_id) {
        this.main_order_id = main_order_id;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getOrder_status() {
        return order_status;
    }

    public void setOrder_status(Integer order_status) {
        this.order_status = order_status;
    }

    public Integer getPay_status() {
        return pay_status;
    }

    public void setPay_status(Integer pay_status) {
        this.pay_status = pay_status;
    }

    public Integer getShipping_status() {
        return shipping_status;
    }

    public void setShipping_status(Integer shipping_status) {
        this.shipping_status = shipping_status;
    }

    public Integer getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Integer add_time) {
        this.add_time = add_time;
    }

    public String getShipping_fee() {
        return shipping_fee;
    }

    public void setShipping_fee(String shipping_fee) {
        this.shipping_fee = shipping_fee;
    }

    public String getGoods_amount() {
        return goods_amount;
    }

    public void setGoods_amount(String goods_amount) {
        this.goods_amount = goods_amount;
    }

    public String getInsure_fee() {
        return insure_fee;
    }

    public void setInsure_fee(String insure_fee) {
        this.insure_fee = insure_fee;
    }

    public String getPay_fee() {
        return pay_fee;
    }

    public void setPay_fee(String pay_fee) {
        this.pay_fee = pay_fee;
    }

    public String getPack_fee() {
        return pack_fee;
    }

    public void setPack_fee(String pack_fee) {
        this.pack_fee = pack_fee;
    }

    public String getCard_fee() {
        return card_fee;
    }

    public void setCard_fee(String card_fee) {
        this.card_fee = card_fee;
    }

    public String getMoney_paid() {
        return money_paid;
    }

    public void setMoney_paid(String money_paid) {
        this.money_paid = money_paid;
    }

    public String getOrder_amount() {
        return order_amount;
    }

    public void setOrder_amount(String order_amount) {
        this.order_amount = order_amount;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public List<OrderGoods> getOrderGoodses() {
        return orderGoodses;
    }

    public String getAdd_time_date() {
        return add_time_date;
    }

    public void setAdd_time_date(String add_time_date) {
        this.add_time_date = add_time_date;
    }

    public void setOrderGoodses(List<OrderGoods> orderGoodses) {
        this.orderGoodses = orderGoodses;
    }

    public Integer getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(Integer current_time) {
        this.current_time = current_time;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getIntegral_money() {
        return integral_money;
    }

    public void setIntegral_money(String integral_money) {
        this.integral_money = integral_money;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus;
    }

    public Integer getGoods_cause() {
        return goods_cause;
    }

    public void setGoods_cause(Integer goods_cause) {
        this.goods_cause = goods_cause;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getShipping_name() {
        return shipping_name;
    }

    public void setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
    }

    public void setData(JSONObject json) {
        try {
            JSONArray orderGoodsJSONArray = json.getJSONArray("order_goods");
            List<OrderGoods> orderGoodsList = new ArrayList<>();
            for (int i = 0; i < orderGoodsJSONArray.length(); i++) {
                JSONObject object = (JSONObject) orderGoodsJSONArray.get(i);
                OrderGoods orderGoods = JsonUtils.fromJsonObject(object, OrderGoods.class);
                orderGoodsList.add(orderGoods);
            }
            this.setOrderGoodses(orderGoodsList);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
