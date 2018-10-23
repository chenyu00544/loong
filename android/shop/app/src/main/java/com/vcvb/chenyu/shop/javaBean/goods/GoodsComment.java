package com.vcvb.chenyu.shop.javaBean.goods;

import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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

    public void setData(JSONObject Json){
        try {
            JSONArray commentImgs = Json.getJSONArray("comment_img");
            List<GoodsCommentImg> _comment_imgs = new ArrayList<>();
            for (int i = 0; i < commentImgs.length(); i++) {
                JSONObject object = (JSONObject) commentImgs.get(i);
                GoodsCommentImg goodsCommentImg = JsonUtils.fromJsonObject(object, GoodsCommentImg.class);
                _comment_imgs.add(goodsCommentImg);
            }
            this.setComment_imgs(_comment_imgs);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }

    }
}

