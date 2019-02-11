package com.vcvb.chenyu.shop.javaBean.goods;

import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GoodsTeam implements Serializable {
    private Integer id;
    private Integer goods_id;
    private Integer team_num;
    private Integer validity_time;
    private Integer limit_num;
    private Integer astrict_num;
    private Integer tc_id;
    private String team_name;
    private String team_price;
    private String isnot_aduit_reason;
    private String team_desc;
    private List<GoodsTeamLog> goodsTeamLogs;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(Integer goods_id) {
        this.goods_id = goods_id;
    }

    public Integer getTeam_num() {
        return team_num;
    }

    public void setTeam_num(Integer team_num) {
        this.team_num = team_num;
    }

    public Integer getValidity_time() {
        return validity_time;
    }

    public void setValidity_time(Integer validity_time) {
        this.validity_time = validity_time;
    }

    public Integer getLimit_num() {
        return limit_num;
    }

    public void setLimit_num(Integer limit_num) {
        this.limit_num = limit_num;
    }

    public Integer getAstrict_num() {
        return astrict_num;
    }

    public void setAstrict_num(Integer astrict_num) {
        this.astrict_num = astrict_num;
    }

    public Integer getTc_id() {
        return tc_id;
    }

    public void setTc_id(Integer tc_id) {
        this.tc_id = tc_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_price() {
        return team_price;
    }

    public void setTeam_price(String team_price) {
        this.team_price = team_price;
    }

    public String getIsnot_aduit_reason() {
        return isnot_aduit_reason;
    }

    public void setIsnot_aduit_reason(String isnot_aduit_reason) {
        this.isnot_aduit_reason = isnot_aduit_reason;
    }

    public String getTeam_desc() {
        return team_desc;
    }

    public void setTeam_desc(String team_desc) {
        this.team_desc = team_desc;
    }

    public List<GoodsTeamLog> getGoodsTeamLogs() {
        return goodsTeamLogs;
    }

    public void setGoodsTeamLogs(List<GoodsTeamLog> goodsTeamLogs) {
        this.goodsTeamLogs = goodsTeamLogs;
    }

    public void setData(JSONObject object) {
        try {
            JSONArray array = object.getJSONArray("team_log");
            List<GoodsTeamLog> _goodsTeamLogs = new ArrayList<>();
            for (int i = 0; i < array.length(); i++) {
                JSONObject o = (JSONObject) array.get(i);
                GoodsTeamLog goodsTeamLog = JsonUtils.fromJsonObject(o,
                        GoodsTeamLog.class);
                _goodsTeamLogs.add(goodsTeamLog);
            }
            this.setGoodsTeamLogs(_goodsTeamLogs);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
