package com.ecjia.hamster.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_SEARCHRESULT */
public class am {
    private String a;
    private ArrayList<JSONObject> b = new ArrayList();

    public String a() {
        return this.a;
    }

    public static am a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        am amVar = new am();
        amVar.a = jSONObject.optString("type");
        JSONArray optJSONArray = jSONObject.optJSONArray("result");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                amVar.b.add(optJSONArray.getJSONObject(i));
            }
        }
        return amVar;
    }
}
