package com.umeng.message.entity;

import com.umeng.analytics.pro.x;
import com.umeng.message.MsgConstant;
import org.json.JSONException;
import org.json.JSONObject;

public class UInAppMessage {
    public static final int CARD_A = 2;
    public static final int CARD_B = 3;
    public static final String CLOSE = "go_app";
    public static final String GO_ACTIVITY = "go_activity";
    public static final String GO_URL = "go_url";
    public static final String NONE = "none";
    public static final int SPLASH_A = 0;
    public static final int SPLASH_B = 1;
    private JSONObject a;
    public String action_activity;
    public String action_type;
    public String action_url;
    public String bottom_action_activity;
    public String bottom_action_type;
    public String bottom_action_url;
    public int bottom_height;
    public String bottom_image_url;
    public int bottom_width;
    public String check_num;
    public boolean display_button;
    public String display_name;
    public int display_time;
    public String expire_time;
    public int height;
    public String image_url;
    public String msg_id;
    public int msg_type;
    public int pduration;
    public int sduration;
    public int show_times;
    public int show_type;
    public String start_time;
    public int width;

    public UInAppMessage(JSONObject jSONObject) throws JSONException {
        this.a = jSONObject;
        this.msg_id = jSONObject.getString("msg_id");
        this.msg_type = jSONObject.getInt(MsgConstant.INAPP_MSG_TYPE);
        JSONObject jSONObject2 = jSONObject.getJSONObject("msg_info");
        this.display_button = jSONObject2.getBoolean("display_button");
        this.display_name = jSONObject2.optString(x.g, "");
        this.display_time = jSONObject2.optInt("display_time", 5);
        JSONObject jSONObject3 = jSONObject2.getJSONObject("image");
        this.image_url = jSONObject3.getString("imageurl");
        this.width = jSONObject3.getInt("width");
        this.height = jSONObject3.getInt("height");
        this.action_type = jSONObject3.getString(MsgConstant.KEY_ACTION_TYPE);
        this.action_activity = jSONObject3.optString("activity", "");
        this.action_url = jSONObject3.optString("url", "");
        if (jSONObject2.has("bottom_image")) {
            jSONObject2 = jSONObject2.getJSONObject("bottom_image");
            this.bottom_image_url = jSONObject2.getString("imageurl");
            this.bottom_width = jSONObject2.getInt("width");
            this.bottom_height = jSONObject2.getInt("height");
            this.bottom_action_type = jSONObject2.getString(MsgConstant.KEY_ACTION_TYPE);
            this.bottom_action_activity = jSONObject2.optString("activity", "");
            this.bottom_action_url = jSONObject2.optString("url", "");
        }
        jSONObject2 = jSONObject.getJSONObject("policy");
        this.show_type = jSONObject2.getInt("show_type");
        this.show_times = jSONObject2.getInt("show_times");
        this.start_time = jSONObject2.getString(x.W);
        this.expire_time = jSONObject2.getString("expire_time");
    }

    public JSONObject getRaw() {
        return this.a;
    }
}
