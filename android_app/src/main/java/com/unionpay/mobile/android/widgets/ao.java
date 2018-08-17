package com.unionpay.mobile.android.widgets;

import android.content.Context;
import org.json.JSONObject;

public final class ao extends aa {
    public ao(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.b.a(129);
    }

    public final boolean b() {
        String a = a();
        return a != null && a.length() > 0;
    }

    protected final String d() {
        return "login_pwd";
    }
}
