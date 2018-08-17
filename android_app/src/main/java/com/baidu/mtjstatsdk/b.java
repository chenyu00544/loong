package com.baidu.mtjstatsdk;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import java.util.HashMap;

class b {
    private static HandlerThread c = new HandlerThread("EventHandleThread");
    private static Handler d;
    private static b e = new b();
    HashMap<String, g> a = new HashMap();
    public final String b = "$|$";

    private b() {
        c.start();
        c.setPriority(10);
        d = new Handler(c.getLooper());
    }

    public static b a() {
        return e;
    }

    public String a(String str, String str2) {
        return "__sdk_" + str + "$|$" + str2;
    }

    public void a(Context context, String str, String str2, int i, long j, String str3) {
        d.post(new c(this, context, str3, str, str2, i, j));
    }

    public void a(Context context, String str, String str2, long j, String str3) {
        d.post(new d(this, context, str3, j, str, str2));
    }

    public void b(Context context, String str, String str2, long j, String str3) {
        d.post(new e(this, context, str3, str, str2, j));
    }

    public void c(Context context, String str, String str2, long j, String str3) {
        d.post(new f(this, context, str3, j, str, str2));
    }
}
