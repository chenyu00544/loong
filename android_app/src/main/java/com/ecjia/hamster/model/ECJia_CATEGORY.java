package com.ecjia.hamster.model;

import java.io.Serializable;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_CATEGORY implements Serializable {
    private int a;
    private String b;
    private boolean c;
    private String d;
    private boolean e;
    private ArrayList<ECJia_CATEGORY> f = new ArrayList();

    public boolean isIschecked() {
        return this.c;
    }

    public void setIschecked(boolean z) {
        this.c = z;
    }

    public boolean isChoose() {
        return this.e;
    }

    public void setChoose(boolean z) {
        this.e = z;
    }

    public String getImage() {
        return this.d;
    }

    public void setImage(String str) {
        this.d = str;
    }

    public int getId() {
        return this.a;
    }

    public void setId(int i) {
        this.a = i;
    }

    public String getName() {
        return this.b;
    }

    public void setName(String str) {
        this.b = str;
    }

    public ArrayList<ECJia_CATEGORY> getChildren() {
        return this.f;
    }

    public void setChildren(ArrayList<ECJia_CATEGORY> arrayList) {
        this.f = arrayList;
    }

    public static ECJia_CATEGORY fromJson(JSONObject jSONObject) throws JSONException {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        ECJia_CATEGORY eCJia_CATEGORY = new ECJia_CATEGORY();
        eCJia_CATEGORY.a = jSONObject.optInt("id");
        eCJia_CATEGORY.b = jSONObject.optString("name");
        eCJia_CATEGORY.d = jSONObject.optString("image");
        eCJia_CATEGORY.c = false;
        JSONArray optJSONArray = jSONObject.optJSONArray("children");
        if (optJSONArray != null) {
            while (i < optJSONArray.length()) {
                eCJia_CATEGORY.f.add(fromJson(optJSONArray.getJSONObject(i)));
                i++;
            }
        }
        return eCJia_CATEGORY;
    }

    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONObject.put("id", this.a);
        jSONObject.put("name", this.b);
        jSONObject.put("image", this.d);
        for (int i = 0; i < this.f.size(); i++) {
            jSONArray.put(((ECJia_CATEGORY) this.f.get(i)).toJson());
        }
        jSONObject.put("children", jSONArray);
        return jSONObject;
    }
}
