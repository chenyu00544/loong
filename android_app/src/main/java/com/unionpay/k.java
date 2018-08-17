package com.unionpay;

import java.util.Comparator;
import org.json.JSONObject;

final class k implements Comparator {
    String a = "";

    k(String str) {
        this.a = str;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        JSONObject jSONObject = (JSONObject) obj2;
        long optLong = ((JSONObject) obj).optLong(this.a);
        long optLong2 = jSONObject.optLong(this.a);
        return optLong < optLong2 ? -1 : optLong > optLong2 ? 1 : 0;
    }
}
