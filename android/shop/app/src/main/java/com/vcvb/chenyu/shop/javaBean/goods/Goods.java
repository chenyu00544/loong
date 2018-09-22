package com.vcvb.chenyu.shop.javaBean.goods;

public class Goods {
    private String goodsId;
    private String pic;
    private String goodsName;
    private String goodsPriceFormat;
    private Double goodsPrice;
    private String goodsMarketFormat;
    private Double goodsMarket;

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

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Double getGoodsMarket() {
        return goodsMarket;
    }

    public void setGoodsMarket(Double goodsMarket) {
        this.goodsMarket = goodsMarket;
    }

    public String getGoodsPriceFormat() {
        return goodsPriceFormat;
    }

    public void setGoodsPriceFormat(String goodsPriceFormat) {
        this.goodsPriceFormat = goodsPriceFormat;
    }

    public String getGoodsMarketFormat() {
        return goodsMarketFormat;
    }

    public void setGoodsMarketFormat(String goodsMarketFormat) {
        this.goodsMarketFormat = goodsMarketFormat;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
