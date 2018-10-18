package com.vcvb.chenyu.shop.javaBean.goods;

public class GoodsAttr {
    private int attrId;
    private String attrName;
    private boolean isSelect = false;
    private int buttonId = 0;

    private Integer goods_attr_id;
    private String goods_id;
    private String attr_id;
    private String color_value;
    private String attr_price;
    private String attr_value;
    private String attr_img_flie;
    private String attr_gallery_flie;
    private String attr_name;

    public int getAttrId() {
        return attrId;
    }

    public void setAttrId(int attrId) {
        this.attrId = attrId;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public boolean getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public int getButtonId() {
        return buttonId;
    }

    public void setButtonId(int buttonId) {
        this.buttonId = buttonId;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public String getAttr_gallery_flie() {
        return attr_gallery_flie;
    }

    public String getAttr_id() {
        return attr_id;
    }

    public String getAttr_img_flie() {
        return attr_img_flie;
    }

    public String getAttr_name() {
        return attr_name;
    }

    public String getAttr_price() {
        return attr_price;
    }

    public String getAttr_value() {
        return attr_value;
    }

    public String getColor_value() {
        return color_value;
    }

    public Integer getGoods_attr_id() {
        return goods_attr_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public void setAttr_gallery_flie(String attr_gallery_flie) {
        this.attr_gallery_flie = attr_gallery_flie;
    }

    public void setAttr_id(String attr_id) {
        this.attr_id = attr_id;
    }

    public void setAttr_img_flie(String attr_img_flie) {
        this.attr_img_flie = attr_img_flie;
    }

    public void setAttr_name(String attr_name) {
        this.attr_name = attr_name;
    }

    public void setAttr_price(String attr_price) {
        this.attr_price = attr_price;
    }

    public void setAttr_value(String attr_value) {
        this.attr_value = attr_value;
    }

    public void setColor_value(String color_value) {
        this.color_value = color_value;
    }

    public void setGoods_attr_id(Integer goods_attr_id) {
        this.goods_attr_id = goods_attr_id;
    }
}
