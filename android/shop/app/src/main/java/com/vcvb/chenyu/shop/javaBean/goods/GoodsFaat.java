package com.vcvb.chenyu.shop.javaBean.goods;

import com.vcvb.chenyu.shop.tools.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GoodsFaat {
    private Integer start_time;
    private Integer end_time;
    private Integer current_time;
    private String act_name;
    private String min_amount;
    private Integer act_type;
    private String act_type_ext;
    private String activity_thumb;
    private List<Gift> gifts;

    public Integer getCurrent_time() {
        return current_time;
    }

    public void setCurrent_time(Integer current_time) {
        this.current_time = current_time;
    }

    public Integer getStart_time() {
        return start_time;
    }

    public void setStart_time(Integer start_time) {
        this.start_time = start_time;
    }

    public Integer getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Integer end_time) {
        this.end_time = end_time;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getMin_amount() {
        return min_amount;
    }

    public void setMin_amount(String min_amount) {
        this.min_amount = min_amount;
    }

    public Integer getAct_type() {
        return act_type;
    }

    public void setAct_type(Integer act_type) {
        this.act_type = act_type;
    }

    public String getAct_type_ext() {
        return act_type_ext;
    }

    public void setAct_type_ext(String act_type_ext) {
        this.act_type_ext = act_type_ext;
    }

    public String getActivity_thumb() {
        return activity_thumb;
    }

    public void setActivity_thumb(String activity_thumb) {
        this.activity_thumb = activity_thumb;
    }

    public List<Gift> getGifts() {
        return gifts;
    }

    public void setGifts(List<Gift> gifts) {
        this.gifts = gifts;
    }

    public void setData(JSONObject Json){
        try {
            JSONArray giftJsonArray = Json.getJSONArray("gift");
            List<Gift> _gift = new ArrayList<>();
            for (int i = 0; i < giftJsonArray.length(); i++) {
                JSONObject object = (JSONObject) giftJsonArray.get(i);
                Gift gift = JsonUtils.fromJsonObject(object, Gift.class);
                _gift.add(gift);
            }
            this.setGifts(_gift);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        }
    }
}