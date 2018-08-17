package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.w.a;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

/* compiled from: ViewPageTracker */
public class bd {
    private static JSONObject b = new JSONObject();
    private final Map<String, Long> a = new HashMap();

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            synchronized (this.a) {
                this.a.put(str, Long.valueOf(System.currentTimeMillis()));
            }
        }
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            Long l;
            synchronized (this.a) {
                l = (Long) this.a.remove(str);
            }
            if (l != null) {
                long currentTimeMillis = System.currentTimeMillis() - l.longValue();
                synchronized (b) {
                    try {
                        b = new JSONObject();
                        b.put(x.ab, str);
                        b.put("duration", currentTimeMillis);
                    } catch (Throwable th) {
                    }
                }
            }
        }
    }

    public void a() {
        String str = null;
        long j = 0;
        synchronized (this.a) {
            for (Entry entry : this.a.entrySet()) {
                String str2;
                long j2;
                if (((Long) entry.getValue()).longValue() > j) {
                    long longValue = ((Long) entry.getValue()).longValue();
                    str2 = (String) entry.getKey();
                    j2 = longValue;
                } else {
                    j2 = j;
                    str2 = str;
                }
                str = str2;
                j = j2;
            }
        }
        if (str != null) {
            b(str);
        }
    }

    public static void a(Context context) {
        JSONObject jSONObject = null;
        try {
            synchronized (b) {
                if (b.length() > 0) {
                    jSONObject = new JSONObject(b.toString());
                    b = new JSONObject();
                }
            }
            if (jSONObject == null) {
                return;
            }
            if (jSONObject.length() > 0) {
                w.a(context).a(bb.a(), jSONObject, a.PAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
