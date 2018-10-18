package com.vcvb.chenyu.shop.javaBean.goods;

import java.util.List;

public class GoodsComment {
    private Integer comment_id;
    private String user_name;
    private String content;
    private String add_time;
    private String logo;
    private List<GoodsCommentImg> comment_imgs;

    public String getContent() {
        return content;
    }

    public String getLogo() {
        return logo;
    }

    public String getAdd_time() {
        return add_time;
    }

    public Integer getComment_id() {
        return comment_id;
    }

    public List<GoodsCommentImg> getComment_imgs() {
        return comment_imgs;
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

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public void setComment_imgs(List<GoodsCommentImg> comment_imgs) {
        this.comment_imgs = comment_imgs;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}

