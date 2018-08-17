package com.unionpay.c;

import android.content.Context;
import android.os.Message;
import com.umeng.analytics.pro.x;
import java.util.Map;

final class ba implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ Map c;
    final /* synthetic */ Context d;

    ba(String str, String str2, Map map, Context context) {
        this.a = str;
        this.b = str2;
        this.c = map;
        this.d = context;
    }

    public final void run() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onEvent being called! eventId: ");
            stringBuilder.append(this.a);
            stringBuilder.append(", eventLabel: ");
            stringBuilder.append(this.b);
            stringBuilder.append(", eventMap: ");
            stringBuilder.append(this.c == null ? "null" : "mapSize: " + String.valueOf(this.c.size()));
            y.a(stringBuilder.toString());
            a aVar = new a();
            aVar.a.put(x.aI, this.d);
            aVar.a.put("apiType", Integer.valueOf(4));
            aVar.a.put("eventId", am.a(this.a));
            aVar.a.put("eventLabel", this.b == null ? null : am.a(this.b));
            aVar.a.put("map", this.c);
            aVar.a.put("occurTime", String.valueOf(System.currentTimeMillis()));
            Message.obtain(w.a(), 102, aVar).sendToTarget();
        } catch (Throwable th) {
            if (a.a) {
                th.printStackTrace();
            }
        }
    }
}
