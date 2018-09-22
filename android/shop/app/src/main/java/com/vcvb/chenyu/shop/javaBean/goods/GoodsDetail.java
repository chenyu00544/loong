package com.vcvb.chenyu.shop.javaBean.goods;

import java.util.List;

public class GoodsDetail {
    private int isType = 0;
    private List<GoodsBanner> banners;
    private GoodsPrice goodsPrice;
    private String goodsName;
    private String goodsDesc;
    private List<GoodsFaat> faats;
    private GoodsShip goodsShip;
    private GoodsShipFree shipFree;
    private List<GoodsExplain> goodsExplains;
    private GoodsEvaluate goodsEvaluate;
    private GoodsBrand goodsBrand;
    private GoodsSpecifications specifications;
    private List<GoodsDesc> descs;
    private List<GoodsAttrs> goodsAttrs;

    public int getIsType() {
        return isType;
    }

    public void setIsType(int isType) {
        this.isType = isType;
    }

    public List<GoodsBanner> getBanners() {
        return banners;
    }

    public void setBanners(List<GoodsBanner> banners) {
        this.banners = banners;
    }

    public GoodsPrice getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(GoodsPrice goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public List<GoodsFaat> getFaats() {
        return faats;
    }

    public void setFaats(List<GoodsFaat> faats) {
        this.faats = faats;
    }

    public GoodsShip getGoodsShip() {
        return goodsShip;
    }

    public void setGoodsShip(GoodsShip goodsShip) {
        this.goodsShip = goodsShip;
    }

    public GoodsShipFree getShipFree() {
        return shipFree;
    }

    public void setShipFree(GoodsShipFree shipFree) {
        this.shipFree = shipFree;
    }

    public List<GoodsExplain> getGoodsExplains() {
        return goodsExplains;
    }

    public void setGoodsExplains(List<GoodsExplain> goodsExplains) {
        this.goodsExplains = goodsExplains;
    }

    public GoodsEvaluate getGoodsEvaluate() {
        return goodsEvaluate;
    }

    public void setGoodsEvaluate(GoodsEvaluate goodsEvaluate) {
        this.goodsEvaluate = goodsEvaluate;
    }

    public GoodsBrand getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(GoodsBrand goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public GoodsSpecifications getSpecifications() {
        return specifications;
    }

    public void setSpecifications(GoodsSpecifications specifications) {
        this.specifications = specifications;
    }

    public List<GoodsDesc> getDescs() {
        return descs;
    }

    public void setDescs(List<GoodsDesc> descs) {
        this.descs = descs;
    }

    public List<GoodsAttrs> getGoodsAttrs() {
        return goodsAttrs;
    }

    public void setGoodsAttrs(List<GoodsAttrs> goodsAttrs) {
        this.goodsAttrs = goodsAttrs;
    }
}
