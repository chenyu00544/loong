package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import org.json.JSONObject;

public final class f extends aa {
    public f(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.b.a(new LengthFilter(32));
    }

    public final boolean b() {
        return a().length() != 0 && a().length() <= 32;
    }

    protected final String d() {
        return "_input_certId";
    }
}
