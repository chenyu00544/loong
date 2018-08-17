package com.ecjia.hamster.model;

import com.baidu.mapapi.SDKInitializer;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_STATUS */
public class ax {
    private int a;
    private int b;
    private String c;
    private String d;
    private int e;

    public int a() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public int b() {
        return this.a;
    }

    public void b(int i) {
        this.a = i;
    }

    public int c() {
        return this.b;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.c;
    }

    public static ax a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ax axVar = new ax();
        axVar.a = jSONObject.optInt("succeed");
        axVar.b = jSONObject.optInt(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE);
        axVar.d = jSONObject.optString("error_desc");
        return axVar;
    }

    public static ax b(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ax axVar = new ax();
        axVar.a = jSONObject.optInt("succeed");
        axVar.c = jSONObject.optString(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE);
        axVar.d = jSONObject.optString("error_desc");
        return axVar;
    }
}
