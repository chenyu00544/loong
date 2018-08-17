package com.unionpay.mobile.android.widgets;

import android.content.Context;
import com.unionpay.mobile.android.widgets.w.a;
import org.json.JSONObject;

public abstract class z extends w implements a {
    public z(Context context, JSONObject jSONObject, String str) {
        super(context, jSONObject, str);
    }

    public final /* bridge */ /* synthetic */ void a(Context context, String str) {
        super.a(context, str);
    }

    public final /* bridge */ /* synthetic */ void a(Context context, String str, String[] strArr, Object[] objArr) {
        super.a(context, str, strArr, objArr);
    }

    public final /* bridge */ /* synthetic */ boolean a(String str) {
        return super.a(str);
    }

    public /* bridge */ /* synthetic */ boolean f() {
        return super.f();
    }

    public String h() {
        StringBuffer stringBuffer = new StringBuffer();
        if (!(n() == null || a() == null)) {
            stringBuffer.append("\"");
            stringBuffer.append(n());
            stringBuffer.append("\":");
            stringBuffer.append("\"");
            stringBuffer.append(a());
            stringBuffer.append("\"");
        }
        return stringBuffer.toString();
    }

    public final /* bridge */ /* synthetic */ String i() {
        return super.i();
    }
}
