package com.vcvb.chenyu.shop.javaBean.evaluate;

import java.io.Serializable;

public class CommentImg  implements Serializable {
    private Integer id;
    private String rec_id;
    private String order_id;
    private String user_id;
    private String comment_img;
    private String goods_id;
    private String comment_id;
    private String img_thumb;
    private String cont_desc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRec_id() {
        return rec_id;
    }

    public void setRec_id(String rec_id) {
        this.rec_id = rec_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComment_img() {
        return comment_img;
    }

    public void setComment_img(String comment_img) {
        this.comment_img = comment_img;
    }

    public String getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(String goods_id) {
        this.goods_id = goods_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getImg_thumb() {
        return img_thumb;
    }

    public void setImg_thumb(String img_thumb) {
        this.img_thumb = img_thumb;
    }

    public String getCont_desc() {
        return cont_desc;
    }

    public void setCont_desc(String cont_desc) {
        this.cont_desc = cont_desc;
    }
}
