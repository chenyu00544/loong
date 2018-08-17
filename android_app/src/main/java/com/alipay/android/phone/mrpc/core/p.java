package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import anet.channel.strategy.dispatch.c;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

public final class p implements c {
    private static p b = null;
    private static final ThreadFactory i = new r();
    Context a;
    private ThreadPoolExecutor c = new ThreadPoolExecutor(10, 11, 3, TimeUnit.SECONDS, new ArrayBlockingQueue(20), i, new CallerRunsPolicy());
    private f d = f.a(c.ANDROID);
    private long e;
    private long f;
    private long g;
    private int h;

    private p(Context context) {
        this.a = context;
        try {
            this.c.allowCoreThreadTimeOut(true);
        } catch (Exception e) {
        }
        CookieSyncManager.createInstance(this.a);
        CookieManager.getInstance().setAcceptCookie(true);
    }

    public static final p a(Context context) {
        return b != null ? b : b(context);
    }

    private static final synchronized p b(Context context) {
        p pVar;
        synchronized (p.class) {
            if (b != null) {
                pVar = b;
            } else {
                pVar = new p(context);
                b = pVar;
            }
        }
        return pVar;
    }

    public final f a() {
        return this.d;
    }

    public final Future<y> a(x xVar) {
        long j = 0;
        if (w.a(this.a)) {
            String str = "HttpManager" + hashCode() + ": Active Task = %d, Completed Task = %d, All Task = %d,Avarage Speed = %d KB/S, Connetct Time = %d ms, All data size = %d bytes, All enqueueConnect time = %d ms, All socket time = %d ms, All request times = %d times";
            Object[] objArr = new Object[9];
            objArr[0] = Integer.valueOf(this.c.getActiveCount());
            objArr[1] = Long.valueOf(this.c.getCompletedTaskCount());
            objArr[2] = Long.valueOf(this.c.getTaskCount());
            objArr[3] = Long.valueOf(this.g == 0 ? 0 : ((this.e * 1000) / this.g) >> 10);
            if (this.h != 0) {
                j = this.f / ((long) this.h);
            }
            objArr[4] = Long.valueOf(j);
            objArr[5] = Long.valueOf(this.e);
            objArr[6] = Long.valueOf(this.f);
            objArr[7] = Long.valueOf(this.g);
            objArr[8] = Integer.valueOf(this.h);
            String.format(str, objArr);
        }
        Object uVar = new u(this, (s) xVar);
        Object qVar = new q(this, uVar, uVar);
        this.c.execute(qVar);
        return qVar;
    }

    public final void a(long j) {
        this.e += j;
    }

    public final void b(long j) {
        this.f += j;
        this.h++;
    }

    public final void c(long j) {
        this.g += j;
    }
}
