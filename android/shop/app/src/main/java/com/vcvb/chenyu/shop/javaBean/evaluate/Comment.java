package com.vcvb.chenyu.shop.javaBean.evaluate;

import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Comment  implements Serializable {
    private Integer comment_id;
    private Integer comment_type;
    private Integer id_value;
    private Integer comment_rank;
    private Integer comment_server;
    private Integer comment_delivery;
    private String add_time_format;
    private String user_name;
    private String content;
    private String order_id;
    private String user_id;
    private String logo;
    private Integer parent_id;
    private Integer status;
    private List<CommentImg> commentImgs;

    public Integer getComment_id() {
        return comment_id;
    }

    public void setComment_id(Integer comment_id) {
        this.comment_id = comment_id;
    }

    public Integer getComment_type() {
        return comment_type;
    }

    public void setComment_type(Integer comment_type) {
        this.comment_type = comment_type;
    }

    public Integer getId_value() {
        return id_value;
    }

    public void setId_value(Integer id_value) {
        this.id_value = id_value;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<CommentImg> getCommentImgs() {
        return commentImgs;
    }

    public void setCommentImgs(List<CommentImg> commentImgs) {
        this.commentImgs = commentImgs;
    }

    public Integer getComment_rank() {
        return comment_rank;
    }

    public void setComment_rank(Integer comment_rank) {
        this.comment_rank = comment_rank;
    }

    public Integer getComment_server() {
        return comment_server;
    }

    public void setComment_server(Integer comment_server) {
        this.comment_server = comment_server;
    }

    public Integer getComment_delivery() {
        return comment_delivery;
    }

    public void setComment_delivery(Integer comment_delivery) {
        this.comment_delivery = comment_delivery;
    }

    public String getAdd_time_format() {
        return add_time_format;
    }

    public void setAdd_time_format(String add_time_format) {
        this.add_time_format = add_time_format;
    }

    public void setData(JSONObject json) {
        try {
            JSONArray array = json.getJSONArray("comment_img");
            List<CommentImg> commentImgs = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = (JSONObject) array.get(i);
                    CommentImg commentImg = JsonUtils.fromJsonObject(o, CommentImg.class);
                    commentImgs.add(commentImg);
            }
            this.setCommentImgs(commentImgs);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
