package com.vcvb.chenyu.shop.javaBean.goods;

public class GoodsComment {
    private String comment_id;
    private String user_name;
    private String content;
    private String add_time;
    private String logo;
    private String comment_img;

    public String getContent() {
        return content;
    }

    public String getLogo() {
        return logo;
    }

    public String getAdd_time() {
        return add_time;
    }

    public String getComment_id() {
        return comment_id;
    }

    public String getComment_img() {
        return comment_img;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public void setComment_img(String comment_img) {
        this.comment_img = comment_img;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}

