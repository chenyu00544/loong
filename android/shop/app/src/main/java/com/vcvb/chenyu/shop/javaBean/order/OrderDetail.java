package com.vcvb.chenyu.shop.javaBean.order;

import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderDetail {
    private String order_id;
    private String main_order_id;
    private String order_sn;
    private Integer user_id;
    private Integer order_status;
    private Integer pay_status;
    private Integer shipping_status;
    private String shipping_fee;
    private String goods_amount;
    private String insure_fee;
    private String pay_fee;
    private String pack_fee;
    private String card_fee;
    private String money_paid;
    private String order_amount;
    private String discount;
    private String tax;
    private List<OrderGoods> orderGoodses;


    public List<OrderGoods> getOrderGoodses() {
        return orderGoodses;
    }

    public void setOrderGoodses(List<OrderGoods> orderGoodses) {
        this.orderGoodses = orderGoodses;
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
