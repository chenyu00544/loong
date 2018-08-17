package com.umeng.message.entity;

import android.text.TextUtils;
import com.umeng.socialize.media.WeiXinShareContent;
import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class UMessage {
    public static final String DISPLAY_TYPE_AUTOUPDATE = "autoupdate";
    public static final String DISPLAY_TYPE_CUSTOM = "custom";
    public static final String DISPLAY_TYPE_NOTIFICATION = "notification";
    public static final String DISPLAY_TYPE_NOTIFICATIONPULLAPP = "notificationpullapp";
    public static final String DISPLAY_TYPE_PULLAPP = "pullapp";
    public static final String NOTIFICATION_GO_ACTIVITY = "go_activity";
    public static final String NOTIFICATION_GO_APP = "go_app";
    public static final String NOTIFICATION_GO_APPURL = "go_appurl";
    public static final String NOTIFICATION_GO_CUSTOM = "go_custom";
    public static final String NOTIFICATION_GO_URL = "go_url";
    private JSONObject a;
    public String activity;
    public String after_open;
    public String alias;
    public int builder_id;
    public boolean clickOrDismiss;
    public String custom;
    public String display_type;
    public Map<String, String> extra;
    public String icon;
    public String img;
    public String largeIcon;
    public String message_id;
    public String msg_id;
    public boolean play_lights;
    public boolean play_sound;
    public boolean play_vibrate;
    public String pulled_package;
    public String pulled_service;
    public long random_min;
    public boolean screen_on;
    public String sound;
    public String task_id;
    public String text;
    public String ticker;
    public String title;
    public String url;

    public UMessage(JSONObject jSONObject) throws JSONException {
        this.a = jSONObject;
        this.msg_id = jSONObject.getString("msg_id");
        this.display_type = jSONObject.getString("display_type");
        this.alias = jSONObject.optString("alias");
        this.random_min = jSONObject.optLong("random_min");
        JSONObject jSONObject2 = jSONObject.getJSONObject("body");
        this.ticker = jSONObject2.optString("ticker");
        this.title = jSONObject2.optString("title");
        this.text = jSONObject2.optString(WeiXinShareContent.TYPE_TEXT);
        this.play_vibrate = jSONObject2.optBoolean("play_vibrate", true);
        this.play_lights = jSONObject2.optBoolean("play_lights", true);
        this.play_sound = jSONObject2.optBoolean("play_sound", true);
        this.screen_on = jSONObject2.optBoolean("screen_on", false);
        this.url = jSONObject2.optString("url");
        this.img = jSONObject2.optString("img");
        this.sound = jSONObject2.optString("sound");
        this.icon = jSONObject2.optString(SocializeProtocolConstants.PROTOCOL_KEY_USER_ICON2);
        this.after_open = jSONObject2.optString("after_open");
        this.largeIcon = jSONObject2.optString("largeIcon");
        this.activity = jSONObject2.optString("activity");
        this.custom = jSONObject2.optString(DISPLAY_TYPE_CUSTOM);
        this.builder_id = jSONObject2.optInt("builder_id", 0);
        this.pulled_service = jSONObject2.optString("pulled_service");
        this.pulled_package = jSONObject2.optString("pulled_package");
        JSONObject optJSONObject = jSONObject.optJSONObject("extra");
        if (optJSONObject != null && optJSONObject.keys() != null) {
            this.extra = new HashMap();
            Iterator keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                this.extra.put(str, optJSONObject.getString(str));
            }
        }
    }

    public JSONObject getRaw() {
        return this.a;
    }

    public boolean hasResourceFromInternet() {
        return isLargeIconFromInternet() || isSoundFromInternet();
    }

    public boolean isLargeIconFromInternet() {
        return !TextUtils.isEmpty(this.img);
    }

    public boolean isSoundFromInternet() {
        return !TextUtils.isEmpty(this.sound) && (this.sound.startsWith("http://") || this.sound.startsWith("https://"));
    }
}
