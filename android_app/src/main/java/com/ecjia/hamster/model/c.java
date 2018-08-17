package com.ecjia.hamster.model;

import com.umeng.analytics.pro.x;
import com.umeng.socialize.media.WeiXinShareContent;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_ADSENSE */
public class c {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private Map<String, String> g = new HashMap();

    public String a() {
        return this.c;
    }

    public String b() {
        return this.d;
    }

    public static c a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        c cVar = new c();
        cVar.a = jSONObject.optString("id");
        cVar.b = jSONObject.optString(WeiXinShareContent.TYPE_TEXT);
        cVar.c = jSONObject.optString("url");
        if (cVar.c.contains("ecjiaopen://app")) {
            cVar.a(cVar.c);
        }
        cVar.d = jSONObject.optString("image");
        cVar.e = jSONObject.optString(x.W);
        cVar.f = jSONObject.optString(x.X);
        return cVar;
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
