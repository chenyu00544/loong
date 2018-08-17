package com.unionpay.mobile.android.g;

import java.util.HashMap;
import org.json.JSONObject;

public final class f implements e {
    private HashMap<String, Object> a = new HashMap();

    public final JSONObject a(String str) {
        Object obj = this.a.get(str);
        return (obj == null || !(obj instanceof JSONObject)) ? null : (JSONObject) obj;
    }

    public final void a(String str, Object obj) {
        this.a.put(str, obj);
    }
}
