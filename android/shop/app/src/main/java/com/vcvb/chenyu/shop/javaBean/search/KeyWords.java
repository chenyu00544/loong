package com.vcvb.chenyu.shop.javaBean.search;

import xiaofei.library.datastorage.annotation.ClassId;
import xiaofei.library.datastorage.annotation.ObjectId;

@ClassId("KeyWords")
public class KeyWords {

    @ObjectId
    private String title;
    private int tag;
    private int cateId;

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCateId() {
        return cateId;
    }

    public void setCateId(int cateId) {
        this.cateId = cateId;
    }
}
