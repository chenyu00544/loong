package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import java.util.Calendar;
import org.json.JSONObject;

public final class av extends aa {
    public av(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.b.a(new LengthFilter(4));
        this.b.a(2);
    }

    public final String a() {
        return this.b.b().trim();
    }

    public final boolean b() {
        String a = a();
        if (4 == a.length()) {
            int parseInt = Integer.parseInt(a.substring(0, 2));
            int parseInt2 = Integer.parseInt(a.substring(2));
            int i = Calendar.getInstance().get(1) % 100;
            boolean z = parseInt > 0 && parseInt <= 12 && (parseInt2 > i || (parseInt2 == i && parseInt >= Calendar.getInstance().get(2) + 1));
            if (z) {
                return true;
            }
        }
        return false;
    }

    protected final String d() {
        return "_select_availdata";
    }
}
