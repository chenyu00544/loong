package com.alipay.e.a;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;

public class a {
    public static synchronized String a(Context context, Map<String, String> map) {
        String a;
        synchronized (a.class) {
            Map hashMap = new HashMap();
            hashMap.put("utdid", com.alipay.c.a.a.a.a.a(map, "utdid", ""));
            hashMap.put("tid", com.alipay.c.a.a.a.a.a(map, "tid", ""));
            hashMap.put("userId", com.alipay.c.a.a.a.a.a(map, "userId", ""));
            com.alipay.b.g.a.a(context).a(0, hashMap, null);
            a = com.alipay.b.a.a.a(context);
        }
        return a;
    }
}
