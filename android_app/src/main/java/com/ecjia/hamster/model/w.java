package com.ecjia.hamster.model;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: ECJia_MENU_BUTTON */
public class w {
    private String a;
    private String b;
    private ArrayList<ay> c = new ArrayList();

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public ArrayList<ay> c() {
        return this.c;
    }

    public static w a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        w wVar = new w();
        wVar.a = jSONObject.optString("name");
        wVar.b = jSONObject.optString("url");
        JSONArray optJSONArray = jSONObject.optJSONArray("sub_button");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                wVar.c.add(ay.a(optJSONArray.getJSONObject(i)));
            }
        }
        return wVar;
    }
}
