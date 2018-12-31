package com.vcvb.chenyu.shop.javaBean.goods;

import java.io.Serializable;

public class GoodsCommentImg implements Serializable {
    private Integer id;
    private String comment_img;
    private String img_thumb;

    public String getComment_img() {
        return comment_img;
    }

    public Integer getId() {
        return id;
    }

    public String getImg_thumb() {
        return img_thumb;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setComment_img(String comment_img) {
        this.comment_img = comment_img;
    }

    public void setImg_thumb(String img_thumb) {
        this.img_thumb = img_thumb;
    }
}

