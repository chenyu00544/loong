package com.unionpay.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class f {
    public static Object a(JSONArray jSONArray, int i) {
        Object obj = null;
        if (jSONArray != null && i < jSONArray.length() && i >= 0) {
            try {
                obj = jSONArray.get(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }

    public static String a(JSONObject jSONObject, String str) {
        String str2 = "";
        if (d(jSONObject, str)) {
            try {
                str2 = jSONObject.getString(str);
            } catch (JSONException e) {
                g.b("uppay", f.class.toString() + " get " + str + " failed!!");
            }
        }
        return str2;
    }

    public static boolean b(JSONObject jSONObject, String str) {
        boolean z = false;
        if (d(jSONObject, str)) {
            try {
                z = jSONObject.getBoolean(str);
            } catch (JSONException e) {
                g.b("uppay", f.class.toString() + " get " + str + " failed!!");
            }
        }
        return z;
    }

    public static JSONArray c(JSONObject jSONObject, String str) {
        JSONArray jSONArray = null;
        if (d(jSONObject, str)) {
            try {
                jSONArray = jSONObject.getJSONArray(str);
            } catch (JSONException e) {
                g.b("uppay", f.class.toString() + " get " + str + " failed!!");
            }
        }
        return jSONArray;
    }

    private static boolean d(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }
}
