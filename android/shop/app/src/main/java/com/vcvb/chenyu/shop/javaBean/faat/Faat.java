package com.vcvb.chenyu.shop.javaBean.faat;

import com.vcvb.chenyu.shop.javaBean.goods.Goods;

import java.util.List;

public class Faat {
    private int isType;
    private String backGroundPic;
    private String title;
    private Goods goods;
    private List<FaatNav> faatNavs;

    public int getIsType() {
        return isType;
    }

    public void setIsType(int isType) {
        this.isType = isType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public String getBackGroundPic() {
        return backGroundPic;
    }

    public void setBackGroundPic(String backGroundPic) {
        this.backGroundPic = backGroundPic;
    }

    public List<FaatNav> getFaatNavs() {
        return faatNavs;
    }

    public void setFaatNavs(List<FaatNav> faatNavs) {
        this.faatNavs = faatNavs;
    }
}
