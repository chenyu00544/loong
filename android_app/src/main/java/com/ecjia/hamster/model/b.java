package com.ecjia.hamster.model;

import com.umeng.analytics.pro.x;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_ADPIC */
public class b {
    private String a;
    private String b;
    private String c;
    private String d;
    private Map<String, String> e = new HashMap();

    public static b a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        b bVar = new b();
        bVar.a = jSONObject.optString("ad_link");
        if (bVar.a.contains("ecjiaopen://app")) {
            bVar.b(bVar.a);
        }
        bVar.b = jSONObject.optString(x.W);
        bVar.c = jSONObject.optString(x.X);
        bVar.d = jSONObject.optString("ad_img");
        return bVar;
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    private void b(String str) {
        String replace = str.replace("ecjiaopen://app?", "");
        if (replace.contains("&")) {
            String[] split = replace.split("&");
            for (String split2 : split) {
                String[] split3 = split2.split("=");
                this.e.put(split3[0], split3[1]);
            }
            return;
        }
        String[] split4 = replace.split("=");
        this.e.put(split4[0], split4[1]);
    }
}
