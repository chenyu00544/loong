package com.ecjia.hamster.module.goodsReturn.model;

import com.ecjia.component.view.wheel.ECJiaWheelData;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_RETURN_REASON implements ECJiaWheelData, Serializable {
    private String a;
    private String b;

    public String getReason_id() {
        return this.a;
    }

    public void setReason_id(String str) {
        this.a = str;
    }

    public String getReason_name() {
        return this.b;
    }

    public void setReason_name(String str) {
        this.b = str;
    }

    public static ECJia_RETURN_REASON fromJson(JSONObject jSONObject) throws JSONException {
        ECJia_RETURN_REASON eCJia_RETURN_REASON = new ECJia_RETURN_REASON();
        eCJia_RETURN_REASON.a = jSONObject.optString("reason_id");
        eCJia_RETURN_REASON.b = jSONObject.optString("reason_name");
        return eCJia_RETURN_REASON;
    }

    public String getStringData() {
        return this.b;
    }
}
