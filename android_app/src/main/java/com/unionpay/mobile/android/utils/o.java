package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.unionpay.mobile.android.c.a;
import com.unionpay.mobile.android.h.c;
import com.unionpay.mobile.android.h.d;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public final class o extends UPPayEngine implements a {
    private Context b;

    public o(Context context) {
        super(context);
        this.b = context;
    }

    public final String a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            jSONObject.put("reqtm", UPPayEngine.i());
            str = jSONObject.toString();
        } catch (JSONException e) {
        }
        k.c("uppay", "post message = " + str);
        String f = f(str);
        d d = d();
        if (d == null) {
            return null;
        }
        try {
            d.a(f);
            HashMap hashMap = new HashMap(1);
            hashMap.put("sid", f());
            d.a(hashMap);
            g();
            if (this.a == null) {
                this.a = new c(d, this.b);
            }
            int a = this.a.a();
            String c = this.a.c();
            if (a == 0) {
                f = g(c);
                k.a("uppay", "[ response msg ] " + f);
                return f;
            }
            Handler e2 = e();
            if (e2 != null) {
                Message obtainMessage = e2.obtainMessage(2);
                obtainMessage.arg1 = a;
                e2.sendMessage(obtainMessage);
            }
            return null;
        } catch (Exception e3) {
            return null;
        }
    }
}
