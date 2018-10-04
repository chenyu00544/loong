package com.vcvb.chenyu.shop.javaBean.goods;

public class Goods {
    private String goodsId;
    private String pic;
    private String goodsName;

    private String goods_name;
    private String shop_price;
    private String goods_thumb;
    private String is_best;
    private String market_price;

    public String getGoods_name() {
        return goods_name;
    }

    public String getGoods_thumb() {
        return goods_thumb;
    }

    public String getIs_best() {
        return is_best;
    }

    public String getMarket_price() {
        return market_price;
    }

    public String getShop_price() {
        return shop_price;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public void setGoods_thumb(String goods_thumb) {
        this.goods_thumb = goods_thumb;
    }

    public void setIs_best(String is_best) {
        this.is_best = is_best;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public void setShop_price(String shop_price) {
        this.shop_price = shop_price;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
