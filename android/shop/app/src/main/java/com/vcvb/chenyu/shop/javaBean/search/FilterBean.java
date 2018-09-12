package com.vcvb.chenyu.shop.javaBean.search;

import java.util.ArrayList;
import java.util.List;

public class FilterBean {
    private int isType;
    private String title;
    private String subTitle;
    private List<FilterList> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public int getIsType() {
        return isType;
    }

    public void setIsType(int isType) {
        this.isType = isType;
    }

    public List<FilterList> getList() {
        return list;
    }

    public void setList(List<FilterList> list) {
        this.list = list;
    }


}
