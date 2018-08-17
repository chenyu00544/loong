package com.alipay.sdk.b;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.alipay.sdk.a.a;
import com.alipay.sdk.f.b;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public final class c {
    private static c d;
    public String a;
    public String b = "sdk-and-lite";
    public String c;

    private c() {
    }

    public static synchronized c a() {
        c cVar;
        synchronized (c.class) {
            if (d == null) {
                d = new c();
            }
            cVar = d;
        }
        return cVar;
    }

    public final synchronized void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            PreferenceManager.getDefaultSharedPreferences(b.a().a).edit().putString("trideskey", str).commit();
            a.b = str;
        }
    }

    public static String b() {
        return Long.toHexString(System.currentTimeMillis()) + (new Random().nextInt(9000) + 1000);
    }

    static String a(Context context, HashMap<String, String> hashMap) {
        Object obj = "";
        try {
            obj = com.alipay.e.a.a.a(context, hashMap);
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a("third", "GetApdidEx", th);
        }
        if (TextUtils.isEmpty(obj)) {
            com.alipay.sdk.app.a.a.a("third", "GetApdidNull", "apdid == null");
        }
        return obj;
    }

    public final String b(Context context, HashMap<String, String> hashMap) {
        Future submit = Executors.newFixedThreadPool(2).submit(new d(this, context, hashMap));
        String str = "";
        try {
            return (String) submit.get(3000, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a("third", "GetApdidTimeout", th);
            return str;
        }
    }
}
