package com.vcvb.chenyu.shop.javaBean;

import java.util.List;

public class BaseBean {
    private int isType;
    private List<Object> header;
    private List<Object> objs;
    private List<Object> footer;

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
}
