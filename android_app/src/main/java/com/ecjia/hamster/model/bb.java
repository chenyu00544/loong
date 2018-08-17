package com.ecjia.hamster.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_TOPIC */
public class bb {
    private String a;
    private String b;
    private String c;
    private String d;
    private ArrayList<bc> e = new ArrayList();
    private ArrayList<an> f = new ArrayList();

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.d;
    }

    public ArrayList<bc> d() {
        return this.e;
    }

    public ArrayList<an> e() {
        return this.f;
    }

    public static bb a(JSONObject jSONObject) throws JSONException {
        int i = 0;
        bb bbVar = new bb();
        if (jSONObject == null) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("sub_goods");
        JSONArray optJSONArray2 = jSONObject.optJSONArray("topic_type");
        bbVar.a = jSONObject.optString("topic_id");
        bbVar.b = jSONObject.optString("topic_title");
        bbVar.c = jSONObject.optString("topic_description");
        bbVar.d = jSONObject.optString("topic_image");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                bc bcVar = new bc();
                bcVar.a(optJSONArray2.optString(i2));
                bcVar.a(false);
                bbVar.e.add(bcVar);
            }
        }
        if (optJSONArray != null && optJSONArray.length() > 0) {
            while (i < optJSONArray.length()) {
                bbVar.f.add(an.a(optJSONArray.optJSONObject(i)));
                i++;
            }
        }
        return bbVar;
    }
}
