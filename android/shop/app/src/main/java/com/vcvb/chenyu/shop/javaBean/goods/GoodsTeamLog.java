package com.vcvb.chenyu.shop.javaBean.goods;

import java.io.Serializable;

public class GoodsTeamLog implements Serializable {
    private Integer team_id;
    private Integer goods_id;
    private Integer start_time;
    private Integer status;
    private Integer t_id;
    private Integer order_num;
    private String logo;
    private String user_id;
    private String team_parent_id;
    private String user_name;
    private String nick_name;

    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getStart_time() {
        return start_time;
    }

    public void setStart_time(Integer start_time) {
        this.start_time = start_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public Integer getOrder_num() {
        return order_num;
    }

    public void setOrder_num(Integer order_num) {
        this.order_num = order_num;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTeam_parent_id() {
        return team_parent_id;
    }

    public void setTeam_parent_id(String team_parent_id) {
        this.team_parent_id = team_parent_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }
}
