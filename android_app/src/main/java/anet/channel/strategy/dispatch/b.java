package anet.channel.strategy.dispatch;

import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.c.c;
import anet.channel.entity.ENV;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.status.NetworkStatusHelper.NetworkStatus;
import anet.channel.util.ALog;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: Taobao */
class b {
    public static final int REQUEST_MERGE_PERIOD = 3000;
    public static final String TAG = "awcn.AmdcTaskExecutor";
    private static AtomicBoolean c = new AtomicBoolean(true);
    private Map<String, Object> a;
    private ENV b;

    /* compiled from: Taobao */
    private static class a implements Runnable {
        private Map<String, Object> a;

        a(Map<String, Object> map) {
            this.a = map;
        }

        public void run() {
            try {
                if (NetworkStatusHelper.f()) {
                    d.a(e.a(this.a));
                }
            } catch (Throwable e) {
                ALog.e(b.TAG, "exec amdc task failed.", null, e, new Object[0]);
            }
        }
    }

    /* compiled from: Taobao */
    private class b implements Runnable {
        ENV a;
        NetworkStatus b;
        final /* synthetic */ b c;

        private b(b bVar) {
            this.c = bVar;
            this.a = GlobalAppRuntimeInfo.getEnv();
            this.b = NetworkStatusHelper.a();
        }

        public void run() {
            synchronized (this.c) {
                Map a = this.c.a;
                this.c.a = null;
            }
            if (NetworkStatusHelper.a() != this.b) {
                ALog.w(b.TAG, "task's network status changed", null, new Object[0]);
            } else if (GlobalAppRuntimeInfo.getEnv() != this.a) {
                ALog.w(b.TAG, "task's env changed", null, new Object[0]);
            } else {
                c.b(new a(a));
            }
        }
    }

    b() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.util.Map<java.lang.String, java.lang.Object> r5) {
        /*
        r4 = this;
        monitor-enter(r4);
        r0 = r4.a;	 Catch:{ all -> 0x005b }
        if (r0 != 0) goto L_0x002a;
    L_0x0005:
        r4.a = r5;	 Catch:{ all -> 0x005b }
        r0 = anet.channel.GlobalAppRuntimeInfo.getEnv();	 Catch:{ all -> 0x005b }
        r4.b = r0;	 Catch:{ all -> 0x005b }
        r0 = c;	 Catch:{ all -> 0x005b }
        r1 = 1;
        r2 = 0;
        r0 = r0.compareAndSet(r1, r2);	 Catch:{ all -> 0x005b }
        if (r0 == 0) goto L_0x0027;
    L_0x0017:
        r0 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
    L_0x0019:
        r1 = new anet.channel.strategy.dispatch.b$b;	 Catch:{ all -> 0x005b }
        r2 = 0;
        r1.<init>();	 Catch:{ all -> 0x005b }
        r2 = (long) r0;	 Catch:{ all -> 0x005b }
        r0 = java.util.concurrent.TimeUnit.MILLISECONDS;	 Catch:{ all -> 0x005b }
        anet.channel.c.c.a(r1, r2, r0);	 Catch:{ all -> 0x005b }
    L_0x0025:
        monitor-exit(r4);	 Catch:{ all -> 0x005b }
    L_0x0026:
        return;
    L_0x0027:
        r0 = 3000; // 0xbb8 float:4.204E-42 double:1.482E-320;
        goto L_0x0019;
    L_0x002a:
        r0 = r4.a;	 Catch:{ all -> 0x005b }
        r1 = "hosts";
        r0 = r0.get(r1);	 Catch:{ all -> 0x005b }
        r0 = (java.util.Set) r0;	 Catch:{ all -> 0x005b }
        r1 = "hosts";
        r1 = r5.get(r1);	 Catch:{ all -> 0x005b }
        r1 = (java.util.Set) r1;	 Catch:{ all -> 0x005b }
        r2 = anet.channel.GlobalAppRuntimeInfo.getEnv();	 Catch:{ all -> 0x005b }
        r3 = r4.b;	 Catch:{ all -> 0x005b }
        if (r2 != r3) goto L_0x0051;
    L_0x0044:
        r2 = r0.size();	 Catch:{ all -> 0x005b }
        r3 = r1.size();	 Catch:{ all -> 0x005b }
        r2 = r2 + r3;
        r3 = 40;
        if (r2 < r3) goto L_0x005e;
    L_0x0051:
        r0 = new anet.channel.strategy.dispatch.b$a;	 Catch:{ all -> 0x005b }
        r0.<init>(r5);	 Catch:{ all -> 0x005b }
        anet.channel.c.c.b(r0);	 Catch:{ all -> 0x005b }
        monitor-exit(r4);	 Catch:{ all -> 0x005b }
        goto L_0x0026;
    L_0x005b:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x005e:
        r1.addAll(r0);	 Catch:{ all -> 0x005b }
        r4.a = r5;	 Catch:{ all -> 0x005b }
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: anet.channel.strategy.dispatch.b.a(java.util.Map):void");
    }
}
