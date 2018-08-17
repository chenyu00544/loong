package com.ecjia.hamster.model;

import com.sina.weibo.sdk.constant.WBConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_HOTNEWS */
public class t {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private Map<String, String> g = new HashMap();

    public String a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public static t a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        t tVar = new t();
        tVar.a = jSONObject.optString("id");
        tVar.b = jSONObject.optString("title");
        tVar.c = jSONObject.optString("description");
        tVar.d = jSONObject.optString("image");
        tVar.e = jSONObject.optString("content_url");
        tVar.f = jSONObject.optString(WBConstants.GAME_PARAMS_GAME_CREATE_TIME);
        if (!tVar.e.contains("ecjiaopen://app")) {
            return tVar;
        }
        tVar.a(tVar.e);
        return tVar;
    }

    private void a(String str) {
        String replace = str.replace("ecjiaopen://app?", "");
        if (replace.contains("&")) {
            String[] split = replace.split("&");
            for (String split2 : split) {
                String[] split3 = split2.split("=");
                this.g.put(split3[0], split3[1]);
            }
            return;
        }
        String[] split4 = replace.split("=");
        this.g.put(split4[0], split4[1]);
    }
}
