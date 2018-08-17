package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import org.json.JSONObject;

public final class e extends aa {
    public e(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.b.a(new LengthFilter(3));
        this.b.a(18);
    }

    public final boolean b() {
        return 3 == a().length();
    }

    protected final String d() {
        return "_input_cvn2";
    }
}
