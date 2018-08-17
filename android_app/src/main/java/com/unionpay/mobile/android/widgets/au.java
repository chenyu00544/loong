package com.unionpay.mobile.android.widgets;

import android.content.Context;
import org.json.JSONObject;

public final class au extends aa {
    public au(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
    }

    public final boolean b() {
        if (this.i) {
            return true;
        }
        String a = a() != null ? a() : "";
        return (this.j == null || this.j.length() <= 0) ? a.length() > 0 && a.length() <= 64 : a.matches(this.j);
    }

    protected final String d() {
        return "login_user";
    }
}
