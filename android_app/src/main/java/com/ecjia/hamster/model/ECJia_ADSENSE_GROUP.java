package com.ecjia.hamster.model;

import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_ADSENSE_GROUP implements Serializable {
    private String a;
    private ArrayList<c> b = new ArrayList();

    public String getTitle() {
        return this.a;
    }

    public void setTitle(String str) {
        this.a = str;
    }

    public ArrayList<c> getAdsense() {
        return this.b;
    }

    public void setAdsense(ArrayList<c> arrayList) {
        this.b = arrayList;
    }

    public static ECJia_ADSENSE_GROUP fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_ADSENSE_GROUP eCJia_ADSENSE_GROUP = new ECJia_ADSENSE_GROUP();
        eCJia_ADSENSE_GROUP.a = jSONObject.optString("title");
        JSONArray optJSONArray = jSONObject.optJSONArray("adsense");
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                eCJia_ADSENSE_GROUP.b.add(c.a(optJSONArray.getJSONObject(i)));
            }
        }
        return eCJia_ADSENSE_GROUP;
    }
}
