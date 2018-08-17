package com.ecjia.hamster.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ECJia_NEWGOODITEM implements Serializable {
    private String a;
    private String b;
    private ArrayList<ECJia_GOODS_LIST> c = new ArrayList();
    public ArrayList<a> children = new ArrayList();
    private ArrayList<ECJia_GOODS_LIST> d = new ArrayList();
    private Map<String, ECJia_GOODS_LIST> e = new HashMap();
    private ArrayList<n> f = new ArrayList();
    private Boolean g;
    private Boolean h;

    public static class a {
        public ArrayList<ECJia_GOODS_LIST> a = new ArrayList();
        private n b;

        public n a() {
            return this.b;
        }

        public void a(ECJia_GOODS_LIST eCJia_GOODS_LIST) {
            this.a.add(eCJia_GOODS_LIST);
        }

        public a(n nVar) {
            this.b = nVar;
        }

        public ArrayList<ECJia_GOODS_LIST> b() {
            return this.a;
        }
    }

    public Boolean getIsCheckedbuy() {
        return this.g;
    }

    public void setIsCheckedbuy(Boolean bool) {
        this.g = bool;
    }

    public Boolean getIscheckDelete() {
        return this.h;
    }

    public void setIscheckDelete(Boolean bool) {
        this.h = bool;
    }

    public String getId() {
        return this.a;
    }

    public void setId(String str) {
        this.a = str;
    }

    public String getName() {
        return this.b;
    }

    public void setName(String str) {
        this.b = str;
    }

    public ArrayList<ECJia_GOODS_LIST> getGoodslist() {
        return this.c;
    }

    public void setGoodslist(ArrayList<ECJia_GOODS_LIST> arrayList) {
        this.c = arrayList;
    }

    public static ECJia_NEWGOODITEM fromJson(JSONObject jSONObject) throws JSONException {
        int i = 0;
        if (jSONObject == null) {
            return null;
        }
        int i2;
        ECJia_NEWGOODITEM eCJia_NEWGOODITEM = new ECJia_NEWGOODITEM();
        eCJia_NEWGOODITEM.a = jSONObject.optString("id");
        eCJia_NEWGOODITEM.b = jSONObject.optString("name");
        eCJia_NEWGOODITEM.h = Boolean.valueOf(false);
        JSONArray optJSONArray = jSONObject.optJSONArray("goods_list");
        int length = optJSONArray.length();
        eCJia_NEWGOODITEM.g = Boolean.valueOf(true);
        if (optJSONArray != null && length > 0) {
            for (i2 = 0; i2 < length; i2++) {
                ECJia_GOODS_LIST fromJson = ECJia_GOODS_LIST.fromJson(optJSONArray.optJSONObject(i2));
                eCJia_NEWGOODITEM.c.add(fromJson);
                eCJia_NEWGOODITEM.e.put(fromJson.getRec_id(), fromJson);
                eCJia_NEWGOODITEM.d.add(fromJson);
                if (fromJson.getIs_checked() == 0) {
                    eCJia_NEWGOODITEM.g = Boolean.valueOf(false);
                }
            }
        }
        optJSONArray = jSONObject.optJSONArray("favourable_group");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            for (i2 = 0; i2 < optJSONArray.length(); i2++) {
                eCJia_NEWGOODITEM.f.add(n.a(optJSONArray.optJSONObject(i2)));
            }
        }
        if (eCJia_NEWGOODITEM.f.size() > 0) {
            for (int i3 = 0; i3 < eCJia_NEWGOODITEM.f.size(); i3++) {
                a aVar = new a((n) eCJia_NEWGOODITEM.f.get(i3));
                for (length = 0; length < ((n) eCJia_NEWGOODITEM.f.get(i3)).e().size(); length++) {
                    aVar.a((ECJia_GOODS_LIST) eCJia_NEWGOODITEM.e.get(((n) eCJia_NEWGOODITEM.f.get(i3)).e().get(length)));
                    eCJia_NEWGOODITEM.d.remove(eCJia_NEWGOODITEM.e.get(((n) eCJia_NEWGOODITEM.f.get(i3)).e().get(length)));
                }
                eCJia_NEWGOODITEM.children.add(aVar);
            }
        }
        if (eCJia_NEWGOODITEM.d.size() > 0) {
            a aVar2 = new a();
            while (i < eCJia_NEWGOODITEM.d.size()) {
                aVar2.a((ECJia_GOODS_LIST) eCJia_NEWGOODITEM.d.get(i));
                i++;
            }
            eCJia_NEWGOODITEM.children.add(aVar2);
        }
        return eCJia_NEWGOODITEM;
    }

    public JSONObject toJson() throws JSONException {
        return new JSONObject();
    }
}
