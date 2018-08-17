package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public final class at extends aa {
    private int c = 0;

    public at(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        String a = j.a(jSONObject, "maxLength");
        if (a == null || a.length() <= 0) {
            this.c = 23;
        } else {
            this.c = Integer.getInteger(a).intValue();
        }
        this.b.a(new LengthFilter(this.c));
    }

    public final boolean b() {
        return this.i || this.c >= a().length();
    }

    protected final String d() {
        return "_input_text";
    }
}
