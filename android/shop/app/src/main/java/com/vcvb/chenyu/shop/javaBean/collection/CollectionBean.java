package com.vcvb.chenyu.shop.javaBean.collection;

public class CollectionBean {
    private int isType = 1;
    private String goodsName;
    private boolean isLong = false;
    private boolean isSelect = false;
    private boolean isSelectAll = false;
    private String pic = "";
    private String date = "2000-01-01";

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

    public boolean getIsLong() {
        return isLong;
    }

    public void setIsLong(boolean isLong) {
        this.isLong = isLong;
    }

    public boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public boolean getIsSelectAll() {
        return isSelectAll;
    }

    public void setIsSelectAll(boolean isSelectAll) {
        this.isSelectAll = isSelectAll;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
