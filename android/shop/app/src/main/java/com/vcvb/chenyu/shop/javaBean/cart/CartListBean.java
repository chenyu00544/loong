package com.vcvb.chenyu.shop.javaBean.cart;

public class CartListBean {
    private int isType = 1;
    private String goodsName;
    private String tag = null;
    private String attr;
    private int goodsNum = 1;
    private String storeId;
    private String storeName;
    private String cartId;
    private String goodsPic;
    private double goodsPrice;
    private double goodsMarket;
    private String goodsTag;
    private String goodsFaat;
    private String priceTotal;
    private boolean isCheckAll = false;
    private boolean isCheckOnce = false;
    private boolean isLong = false;

    public int getIsType() {
        return isType;
    }

    public void setIsType(int isType) {
        this.isType = isType;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public double getGoodsMarket() {
        return goodsMarket;
    }

    public void setGoodsMarket(double goodsMarket) {
        this.goodsMarket = goodsMarket;
    }

    public String getGoodsTag() {
        return goodsTag;
    }

    public void setGoodsTag(String goodsTag) {
        this.goodsTag = goodsTag;
    }

    public String getGoodsFaat() {
        return goodsFaat;
    }

    public void setGoodsFaat(String goodsFaat) {
        this.goodsFaat = goodsFaat;
    }

    public String getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(String priceTotal) {
        this.priceTotal = priceTotal;
    }

    public boolean getIsCheckAll() {
        return isCheckAll;
    }

    public void setIsCheckAll(boolean isCheckAll) {
        this.isCheckAll = isCheckAll;
    }

    public boolean getIsChecOnce() {
        return isCheckOnce;
    }

    public void setIsChecOnce(boolean isCheckOnce) {
        this.isCheckOnce = isCheckOnce;
    }

    public boolean getIsLong() {
        return isLong;
    }

    public void setIsLong(boolean isLong) {
        this.isLong = isLong;
    }
}
