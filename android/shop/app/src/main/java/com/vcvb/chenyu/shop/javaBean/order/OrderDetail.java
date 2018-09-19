package com.vcvb.chenyu.shop.javaBean.order;

import com.vcvb.chenyu.shop.javaBean.home.Goods;

import java.util.List;

public class OrderDetail {
    private int isType;
    private String orderId;
    private String orderDate;
    private int addressId;
    private String orderConsignee;
    private String orderPhone;
    private String orderAddress;

    private int goodsId;
    private String goodsPic;
    private String goodsName;
    private String goodsAttr;
    private int goodsAttrId;
    private String goodsPriceFormat;
    private Double goodsPrice;
    private String goodsMarketFormat;
    private Double goodsMarket;
    private int goodsNum;

    private Double shipFree;
    private String shipFreeFormat;

    private Double discount;
    private String discountFormat;

    private Double totalPay;
    private String totalPayFormat;
    private Double totalPayAble;
    private String totalPayAbleFormat;

    private Double totalOncePay;
    private String totalOncePayFormat;

    private int payType;

    private List<Goods> list;

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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getGoodsMarket() {
        return goodsMarket;
    }

    public void setGoodsMarket(Double goodsMarket) {
        this.goodsMarket = goodsMarket;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(Double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Double getShipFree() {
        return shipFree;
    }

    public void setShipFree(Double shipFree) {
        this.shipFree = shipFree;
    }

    public Double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(Double totalPay) {
        this.totalPay = totalPay;
    }

    public Double getTotalPayAble() {
        return totalPayAble;
    }

    public void setTotalPayAble(Double totalPayAble) {
        this.totalPayAble = totalPayAble;
    }

    public String getTotalPayAbleFormat() {
        return totalPayAbleFormat;
    }

    public void setTotalPayAbleFormat(String totalPayAbleFormat) {
        this.totalPayAbleFormat = totalPayAbleFormat;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getGoodsAttrId() {
        return goodsAttrId;
    }

    public void setGoodsAttrId(int goodsAttrId) {
        this.goodsAttrId = goodsAttrId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getDiscountFormat() {
        return discountFormat;
    }

    public void setDiscountFormat(String discountFormat) {
        this.discountFormat = discountFormat;
    }

    public String getGoodsAttr() {
        return goodsAttr;
    }

    public void setGoodsAttr(String goodsAttr) {
        this.goodsAttr = goodsAttr;
    }

    public String getGoodsMarketFormat() {
        return goodsMarketFormat;
    }

    public void setGoodsMarketFormat(String goodsMarketFormat) {
        this.goodsMarketFormat = goodsMarketFormat;
    }

    public String getGoodsPic() {
        return goodsPic;
    }

    public void setGoodsPic(String goodsPic) {
        this.goodsPic = goodsPic;
    }

    public String getGoodsPriceFormat() {
        return goodsPriceFormat;
    }

    public void setGoodsPriceFormat(String goodsPriceFormat) {
        this.goodsPriceFormat = goodsPriceFormat;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderConsignee() {
        return orderConsignee;
    }

    public void setOrderConsignee(String orderConsignee) {
        this.orderConsignee = orderConsignee;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public String getShipFreeFormat() {
        return shipFreeFormat;
    }

    public void setShipFreeFormat(String shipFreeFormat) {
        this.shipFreeFormat = shipFreeFormat;
    }

    public String getTotalPayFormat() {
        return totalPayFormat;
    }

    public void setTotalPayFormat(String totalPayFormat) {
        this.totalPayFormat = totalPayFormat;
    }

    public Double getTotalOncePay() {
        return totalOncePay;
    }

    public void setTotalOncePay(Double totalOncePay) {
        this.totalOncePay = totalOncePay;
    }

    public String getTotalOncePayFormat() {
        return totalOncePayFormat;
    }

    public void setTotalOncePayFormat(String totalOncePayFormat) {
        this.totalOncePayFormat = totalOncePayFormat;
    }

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public List<Goods> getList() {
        return list;
    }

    public void setList(List<Goods> list) {
        this.list = list;
    }
}
