package com.vcvb.chenyu.shop.javaBean.goods;

import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsGroupBuy implements Serializable {
    private Integer goods_id;
    private Integer act_type;
    private Integer start_time;
    private Integer end_time;
    private Integer is_finished;
    private Integer deposit;
    private Integer restrict_amount;
    private List<GoodsGroupBuyPrice> goodsGroupBuyPrices;

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getAct_type() {
        return act_type;
    }

    public void setAct_type(Integer act_type) {
        this.act_type = act_type;
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

    public Integer getIs_finished() {
        return is_finished;
    }

    public void setIs_finished(Integer is_finished) {
        this.is_finished = is_finished;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getRestrict_amount() {
        return restrict_amount;
    }

    public void setRestrict_amount(Integer restrict_amount) {
        this.restrict_amount = restrict_amount;
    }

    public List<GoodsGroupBuyPrice> getGoodsGroupBuyPrices() {
        return goodsGroupBuyPrices;
    }

    public void setGoodsGroupBuyPrices(List<GoodsGroupBuyPrice> goodsGroupBuyPrices) {
        this.goodsGroupBuyPrices = goodsGroupBuyPrices;
    }

    public void setData(JSONObject object) {
        try {
            JSONArray array = object.getJSONArray("price_ladder");
            List<GoodsGroupBuyPrice> _goodsGroupBuyPrices = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = (JSONObject) array.get(i);
                GoodsGroupBuyPrice goodsGroupBuyPrice = JsonUtils.fromJsonObject(o,
                        GoodsGroupBuyPrice.class);
                _goodsGroupBuyPrices.add(goodsGroupBuyPrice);
            }
            this.setGoodsGroupBuyPrices(_goodsGroupBuyPrices);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
