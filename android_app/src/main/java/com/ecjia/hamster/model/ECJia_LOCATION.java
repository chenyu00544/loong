package com.ecjia.hamster.model;

import com.sina.weibo.sdk.constant.WBPageConstants.ParamKey;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_LOCATION implements Serializable {
    public String distance;
    public String latitude;
    public String longitude;

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getDistance() {
        return this.distance;
    }

    public void setDistance(String str) {
        this.distance = str;
    }

    public static ECJia_LOCATION fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_LOCATION eCJia_LOCATION = new ECJia_LOCATION();
        eCJia_LOCATION.longitude = jSONObject.optString(ParamKey.LONGITUDE);
        eCJia_LOCATION.latitude = jSONObject.optString(ParamKey.LATITUDE);
        eCJia_LOCATION.distance = jSONObject.optString("distance");
        return eCJia_LOCATION;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ParamKey.LONGITUDE, this.longitude);
        jSONObject.put(ParamKey.LATITUDE, this.latitude);
        jSONObject.put("distance", this.distance);
        return jSONObject;
    }
}
