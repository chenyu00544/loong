package com.ecjia.hamster.model;

import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_FILTER_ATTR implements Serializable {
    private String a;
    private ArrayList<ECJia_ATTR_LIST> b = new ArrayList();

    public String getFilter_attr_name() {
        return this.a;
    }

    public void setFilter_attr_name(String str) {
        this.a = str;
    }

    public ArrayList<ECJia_ATTR_LIST> getAttrs() {
        return this.b;
    }

    public void setAttrs(ArrayList<ECJia_ATTR_LIST> arrayList) {
        this.b = arrayList;
    }

    public static ECJia_FILTER_ATTR fromJson(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return null;
        }
        ECJia_FILTER_ATTR eCJia_FILTER_ATTR = new ECJia_FILTER_ATTR();
        eCJia_FILTER_ATTR.a = jSONObject.optString("filter_attr_name");
        JSONArray optJSONArray = jSONObject.optJSONArray("attr_list");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                eCJia_FILTER_ATTR.b.add(ECJia_ATTR_LIST.fromJson(optJSONArray.optJSONObject(i)));
            }
        }
        return eCJia_FILTER_ATTR;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("filter_attr_name", this.a);
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < this.b.size(); i++) {
            jSONArray.put(((ECJia_ATTR_LIST) this.b.get(i)).toJson());
        }
        jSONObject.put("attr_list", jSONArray);
        return jSONObject;
    }
}
