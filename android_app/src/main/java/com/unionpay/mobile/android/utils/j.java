package com.unionpay.mobile.android.utils;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class j {
    public static String a(JSONArray jSONArray, int i) {
        String str = "";
        if (jSONArray != null && i >= 0 && i < jSONArray.length()) {
            try {
                str = jSONArray.getString(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String a(JSONObject jSONObject, String str) {
        String str2 = "";
        if (f(jSONObject, str)) {
            try {
                str2 = jSONObject.getString(str);
            } catch (JSONException e) {
                k.c("uppay", j.class.toString() + " get " + str + " failed!!");
            }
        }
        return str2;
    }

    public static int b(JSONObject jSONObject, String str) {
        int i = 0;
        if (f(jSONObject, str)) {
            try {
                i = jSONObject.getInt(str);
            } catch (JSONException e) {
                k.c("uppay", j.class.toString() + " get " + str + " failed!!");
            }
        }
        return i;
    }

    public static Object b(JSONArray jSONArray, int i) {
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

    public static JSONObject c(JSONObject jSONObject, String str) {
        JSONObject jSONObject2 = null;
        if (f(jSONObject, str)) {
            try {
                jSONObject2 = jSONObject.getJSONObject(str);
            } catch (JSONException e) {
                k.c("uppay", j.class.toString() + " get " + str + " failed!!");
            }
        }
        return jSONObject2;
    }

    public static JSONArray d(JSONObject jSONObject, String str) {
        JSONArray jSONArray = null;
        if (f(jSONObject, str)) {
            try {
                jSONArray = jSONObject.getJSONArray(str);
            } catch (JSONException e) {
                k.c("uppay", j.class.toString() + " get " + str + " failed!!");
            }
        }
        return jSONArray;
    }

    public static List<JSONArray> e(JSONObject jSONObject, String str) {
        List<JSONArray> arrayList = new ArrayList(1);
        JSONArray d = d(jSONObject, str);
        int i = 0;
        while (d != null && i < d.length()) {
            arrayList.add((JSONArray) b(d, i));
            i++;
        }
        return arrayList;
    }

    private static boolean f(JSONObject jSONObject, String str) {
        return jSONObject != null && jSONObject.has(str);
    }
}
