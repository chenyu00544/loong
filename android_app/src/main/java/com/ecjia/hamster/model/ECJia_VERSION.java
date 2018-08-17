package com.ecjia.hamster.model;

import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_VERSION implements Serializable {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private Binary h;

    public static class Binary implements Serializable {
        public String fsize;

        public String getFsize() {
            return this.fsize;
        }

        public void setFsize(String str) {
            this.fsize = str;
        }

        public Binary(String str) {
            this.fsize = str;
        }

        public static Binary fromJson(JSONObject jSONObject) throws JSONException {
            if (jSONObject == null) {
                return null;
            }
            Binary binary = new Binary();
            binary.fsize = jSONObject.optString("fsize");
            return binary;
        }

        public JSONObject toJson() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("fsize", this.fsize);
            return jSONObject;
        }
    }

    public Binary getBinary() {
        return this.h;
    }

    public String getName() {
        return this.a;
    }

    public void setBinary(Binary binary) {
        this.h = binary;
    }

    public void setName(String str) {
        this.a = str;
    }

    public String getBuild() {
        return this.c;
    }

    public void setBuild(String str) {
        this.c = str;
    }

    public String getUpdate_url() {
        return this.e;
    }

    public void setUpdate_url(String str) {
        this.e = str;
    }

    public static ECJia_VERSION fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_VERSION eCJia_VERSION = new ECJia_VERSION();
        eCJia_VERSION.a = jSONObject.optString("name");
        eCJia_VERSION.b = jSONObject.optString("version");
        eCJia_VERSION.c = jSONObject.optString("build");
        eCJia_VERSION.d = jSONObject.optString("install_url");
        eCJia_VERSION.e = jSONObject.optString("update_url");
        eCJia_VERSION.f = jSONObject.optString("changelog");
        eCJia_VERSION.g = jSONObject.optString("update_time");
        eCJia_VERSION.h = Binary.fromJson(jSONObject.optJSONObject("binary"));
        return eCJia_VERSION;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", this.a);
        jSONObject.put("version", this.b);
        jSONObject.put("build", this.c);
        jSONObject.put("install_url", this.d);
        jSONObject.put("update_url", this.e);
        jSONObject.put("changelog", this.f);
        jSONObject.put("update_time", this.g);
        jSONObject.put("binary", this.h.toJson());
        return jSONObject;
    }

    public String getVersion() {
        return this.b;
    }

    public void setVersion(String str) {
        this.b = str;
    }

    public String getInstall_url() {
        return this.d;
    }

    public void setInstall_url(String str) {
        this.d = str;
    }

    public String getUpdate_time() {
        return this.g;
    }

    public void setUpdate_time(String str) {
        this.g = str;
    }

    public String getChangelog() {
        return this.f;
    }

    public void setChangelog(String str) {
        this.f = str;
    }
}
