package com.ecjia.hamster.model;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_DEVICE implements Serializable {
    private static ECJia_DEVICE d;
    private String a;
    private String b;
    private String c;

    public ECJia_DEVICE() {
        d = this;
    }

    public static ECJia_DEVICE getInstance() {
        if (d == null) {
            synchronized (ECJia_DEVICE.class) {
                if (d == null) {
                    d = new ECJia_DEVICE();
                }
            }
        }
        return d;
    }

    public String getUdid() {
        return this.a;
    }

    public void setUdid(String str) {
        this.a = str;
    }

    public String getClient() {
        return this.b;
    }

    public void setClient(String str) {
        this.b = str;
    }

    public String getCode() {
        return this.c;
    }

    public void setCode(String str) {
        this.c = str;
    }

    public static ECJia_DEVICE fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_DEVICE instance = getInstance();
        instance.a = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_UDID);
        instance.b = jSONObject.optString("client");
        instance.c = jSONObject.optString("code");
        return instance;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("client", this.b);
        jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_UDID, this.a);
        jSONObject.put("code", this.c);
        return jSONObject;
    }
}
