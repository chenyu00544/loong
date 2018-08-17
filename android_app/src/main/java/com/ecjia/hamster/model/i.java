package com.ecjia.hamster.model;

import com.umeng.socialize.net.utils.SocializeProtocolConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_CITY */
public class i {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;

    public String a() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public void b(String str) {
        this.b = str;
    }

    public void c(String str) {
        this.e = str;
    }

    public String b() {
        return this.c;
    }

    public void d(String str) {
        this.c = str;
    }

    public String c() {
        return this.d;
    }

    public void e(String str) {
        this.d = str;
    }

    public static i a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        i iVar = new i();
        iVar.a = jSONObject.optString("id");
        iVar.b = jSONObject.optString("ishot");
        iVar.c = jSONObject.optString("name");
        iVar.d = jSONObject.optString(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_PINYIN);
        iVar.e = jSONObject.optString("parent_id");
        return iVar;
    }

    public JSONObject d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.a);
        jSONObject.put("ishot", this.b);
        jSONObject.put("name", this.c);
        jSONObject.put(SocializeProtocolConstants.PROTOCOL_KEY_FRIENDS_PINYIN, this.d);
        jSONObject.put("parent_id", this.e);
        return jSONObject;
    }
}
