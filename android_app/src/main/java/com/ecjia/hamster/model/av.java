package com.ecjia.hamster.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SPECIFICATION */
public class av {
    public static String a = "1";
    public static String b = "2";
    public ArrayList<aw> c = new ArrayList();
    private String d;
    private String e;

    public String a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    public static av a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        av avVar = new av();
        avVar.d = jSONObject.optString("name");
        avVar.e = jSONObject.optString("attr_type");
        JSONArray optJSONArray = jSONObject.optJSONArray("value");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                aw a = aw.a(optJSONArray.getJSONObject(i));
                a.a(jSONObject.optString("name"));
                a.b(jSONObject.optString("attr_type"));
                avVar.c.add(a);
            }
        }
        return avVar;
    }

    public JSONObject c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("name", this.d);
        jSONObject.put("attr_type", this.e);
        for (int i = 0; i < this.c.size(); i++) {
            jSONArray.put(((aw) this.c.get(i)).f());
        }
        jSONObject.put("value", jSONArray);
        return jSONObject;
    }
}
