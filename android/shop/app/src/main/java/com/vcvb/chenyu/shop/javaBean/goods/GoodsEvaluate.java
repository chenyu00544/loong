package com.vcvb.chenyu.shop.javaBean.goods;

import java.util.List;

public class GoodsEvaluate {
    private List<Evaluates> evaluates;
    private List<Probs> probs;
    private String pj;
    private String hp;
    private String zp;
    private String jg;
    private String wl;
    private String wdj;
    private int isScroll = 0;

    public List<Evaluates> getEvaluates() {
        return evaluates;
    }

    public void setEvaluates(List<Evaluates> evaluates) {
        this.evaluates = evaluates;
    }

    public List<Probs> getProbs() {
        return probs;
    }

    public void setProbs(List<Probs> probs) {
        this.probs = probs;
    }

    public String getPj() {
        return pj;
    }

    public void setPj(String pj) {
        this.pj = pj;
    }

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getJg() {
        return jg;
    }

    public void setJg(String jg) {
        this.jg = jg;
    }

    public String getZp() {
        return zp;
    }

    public void setZp(String zp) {
        this.zp = zp;
    }

    public String getWl() {
        return wl;
    }

    public void setWl(String wl) {
        this.wl = wl;
    }

    public String getWdj() {
        return wdj;
    }

    public void setWdj(String wdj) {
        this.wdj = wdj;
    }

    public int getIsScroll() {
        return isScroll;
    }

    public void setIsScroll(int isScroll) {
        this.isScroll = isScroll;
    }
}
