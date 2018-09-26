package com.vcvb.chenyu.shop.javaBean;

import com.vcvb.chenyu.shop.adapter.b.Item;

import java.util.List;

public class BaseBean {
    private int isType;
    private Object header;
    private List<Object> objs;
    private Object footer;

    private Item mheader;
    private List<Item> itemList;
    private Item mfooter;

    public int getIsType() {
        return isType;
    }

    public void setIsType(int isType) {
        this.isType = isType;
    }

    public List<Object> getObjs() {
        return objs;
    }

    public void setObjs(List<Object> objs) {
        this.objs = objs;
    }

    public Object getFooter() {
        return footer;
    }

    public Object getHeader() {
        return header;
    }

    public void setFooter(Object footer) {
        this.footer = footer;
    }

    public void setHeader(Object header) {
        this.header = header;
    }

    public Item getMheader() {
        return mheader;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public void setMheader(Item mheader) {
        this.mheader = mheader;
    }

    public Item getMfooter() {
        return mfooter;
    }

    public void setMfooter(Item mfooter) {
        this.mfooter = mfooter;
    }
}
