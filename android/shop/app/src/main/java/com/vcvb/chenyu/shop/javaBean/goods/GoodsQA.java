package com.vcvb.chenyu.shop.javaBean.goods;

import java.io.Serializable;

public class GoodsQA implements Serializable {
    private Integer qa_id;
    private String id_value;
    private String email;
    private String user_name;
    private String content;
    private String useful;

    public String getUser_name() {
        return user_name;
    }

    public String getContent() {
        return content;
    }

    public Integer getQa_id() {
        return qa_id;
    }

    public String getEmail() {
        return email;
    }

    public String getId_value() {
        return id_value;
    }

    public String getUseful() {
        return useful;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId_value(String id_value) {
        this.id_value = id_value;
    }

    public void setQa_id(Integer qa_id) {
        this.qa_id = qa_id;
    }

    public void setUseful(String useful) {
        this.useful = useful;
    }
}
