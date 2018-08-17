package com.alipay.b.g;

import android.content.Context;
import com.alipay.b.f.d;
import com.alipay.b.f.g;
import com.alipay.b.f.h;
import com.alipay.b.f.i;
import java.util.HashMap;
import java.util.Map;

public class a {
    private static a a;
    private static Object c = new Object();
    private Context b;

    public interface a {
        void a(b bVar);
    }

    public class b {
        public String a;
        public String b;
        public String c;
        public String d;
        final /* synthetic */ a e;

        public b(a aVar) {
            this.e = aVar;
        }
    }

    private a(Context context) {
        this.b = context;
    }

    public static a a(Context context) {
        if (a == null) {
            synchronized (c) {
                if (a == null) {
                    a = new a(context);
                }
            }
        }
        return a;
    }

    public synchronized b a() {
        b bVar;
        bVar = new b(this);
        try {
            bVar.a = com.alipay.b.a.a.a(this.b, "");
            bVar.b = h.c(this.b);
            bVar.c = com.alipay.b.a.a.a(this.b);
            Context context = this.b;
            bVar.d = com.alipay.b.e.a.a();
        } catch (Throwable th) {
        }
        return bVar;
    }

    public void a(int i, Map<String, String> map, a aVar) {
        com.alipay.b.b.a.a().a(i);
        String a = h.a(this.b);
        String c = com.alipay.b.b.a.a().c();
        if (com.alipay.c.a.a.a.a.b(a) && !com.alipay.c.a.a.a.a.a(a, c)) {
            com.alipay.b.f.a.a(this.b);
            d.a(this.b);
            g.a(this.b);
            i.h();
        }
        if (!com.alipay.c.a.a.a.a.a(a, c)) {
            h.a(this.b, c);
        }
        Object a2 = com.alipay.c.a.a.a.a.a(map, "utdid", "");
        c = com.alipay.c.a.a.a.a.a(map, "tid", "");
        String a3 = com.alipay.c.a.a.a.a.a(map, "userId", "");
        if (com.alipay.c.a.a.a.a.a((String) a2)) {
            Context context = this.b;
            a2 = "";
        }
        Map hashMap = new HashMap();
        hashMap.put("utdid", a2);
        hashMap.put("tid", c);
        hashMap.put("userId", a3);
        hashMap.put("appName", "");
        hashMap.put("appKeyClient", "");
        hashMap.put("appchannel", "");
        hashMap.put("rpcVersion", "3");
        com.alipay.b.h.b.a().a(new b(this, hashMap, aVar));
    }
}
