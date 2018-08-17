package com.ecjia.hamster.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SHOPHELP */
public class as {
    public ArrayList<d> a = new ArrayList();
    private String b;

    public String a() {
        return this.b;
    }

    public static as a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        as asVar = new as();
        asVar.b = jSONObject.optString("name");
        JSONArray optJSONArray = jSONObject.optJSONArray("article");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                d a = d.a(optJSONArray.getJSONObject(i));
                a.a(asVar.b);
                asVar.a.add(a);
            }
        }
        return asVar;
    }
}
