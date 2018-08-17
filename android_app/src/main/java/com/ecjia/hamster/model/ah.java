package com.ecjia.hamster.model;

import com.umeng.socialize.media.WeiXinShareContent;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_QUICK */
public class ah {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private Map<String, String> f = new HashMap();

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public static ah a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ah ahVar = new ah();
        ahVar.a = jSONObject.optString("image");
        ahVar.c = jSONObject.optString(WeiXinShareContent.TYPE_TEXT);
        ahVar.b = jSONObject.optString("url");
        if (ahVar.b.contains("ecjiaopen://app")) {
            ahVar.a(ahVar.b);
        }
        String[] split = ahVar.c.split("\\|");
        if (split == null || split.length <= 1) {
            return ahVar;
        }
        ahVar.d = split[0];
        ahVar.e = split[1];
        return ahVar;
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
