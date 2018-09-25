package com.vcvb.chenyu.shop.javaBean;

import com.vcvb.chenyu.shop.adapter.b.Item;

import java.util.List;

public class BaseBean {
    private int isType;
    private List<Object> header;
    private Item mHeader;
    private List<Object> objs;
    private List<Item> mObjs;
    private List<Object> footer;
    private Item mFooter;

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

    public List<Object> getHeader() {
        return header;
    }

    public void setHeader(List<Object> header) {
        this.header = header;
    }

    public List<Object> getFooter() {
        return footer;
    }

    public void setFooter(List<Object> footer) {
        this.footer = footer;
    }

    public Item getmHeader() {
        return mHeader;
    }

    public void setmHeader(Item mHeader) {
        this.mHeader = mHeader;
    }

    public Item getmFooter() {
        return mFooter;
    }

    public void setmFooter(Item mFooter) {
        this.mFooter = mFooter;
    }

    public List<Item> getmObjs() {
        return mObjs;
    }

    public void setmObjs(List<Item> mObjs) {
        this.mObjs = mObjs;
    }
}
