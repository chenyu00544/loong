package com.ecjia.hamster.model;

import com.tencent.tauth.AuthActivity;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_PLAYER */
public class ad {
    private String a;
    private ECJia_PHOTO b = new ECJia_PHOTO();
    private String c;
    private String d;
    private int e;
    private Map<String, String> f = new HashMap();

    public ECJia_PHOTO a() {
        return this.b;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.d;
    }

    public int d() {
        return this.e;
    }

    public static ad a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ad adVar = new ad();
        adVar.a = jSONObject.optString("description");
        adVar.b = ECJia_PHOTO.fromJson(jSONObject.optJSONObject("photo"));
        adVar.c = jSONObject.optString("url");
        if (adVar.c.contains("ecjiaopen://app")) {
            adVar.a(adVar.c);
        }
        adVar.d = jSONObject.optString(AuthActivity.ACTION_KEY);
        adVar.e = jSONObject.optInt("action_id");
        return adVar;
    }

    private void a(String str) {
        String replace = str.replace("ecjiaopen://app?", "");
        if (replace.contains("&")) {
            String[] split = replace.split("&");
            for (String split2 : split) {
                String[] split3 = split2.split("=");
                this.f.put(split3[0], split3[1]);
            }
            return;
        }
        String[] split4 = replace.split("=");
        this.f.put(split4[0], split4[1]);
    }
}
