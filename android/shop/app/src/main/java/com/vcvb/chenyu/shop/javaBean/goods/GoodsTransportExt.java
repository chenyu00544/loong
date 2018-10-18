package com.vcvb.chenyu.shop.javaBean.goods;

public class GoodsTransportExt {
    private Integer id;
    private String tid;
    private String ru_id;
    private String admin_id;
    private String area_id;
    private String top_area_id;
    private String sprice;

    public Integer getId() {
        return id;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public String getArea_id() {
        return area_id;
    }

    public String getRu_id() {
        return ru_id;
    }

    public String getSprice() {
        return sprice;
    }

    public String getTid() {
        return tid;
    }

    public String getTop_area_id() {
        return top_area_id;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRu_id(String ru_id) {
        this.ru_id = ru_id;
    }

    public void setSprice(String sprice) {
        this.sprice = sprice;
    }

    public void setTop_area_id(String top_area_id) {
        this.top_area_id = top_area_id;
    }
}
