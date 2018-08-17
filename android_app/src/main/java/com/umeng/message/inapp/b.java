package com.umeng.message.inapp;

import android.os.Handler;
import android.os.SystemClock;

/* compiled from: UmengCountDownTimer */
abstract class b {
    private static final String a = b.class.getName();
    private static final int g = 1;
    private static final int h = 2;
    private final long b;
    private final long c;
    private long d;
    private long e;
    private boolean f = false;
    private Handler i = new b_1(this);

    /* compiled from: UmengCountDownTimer */
    class b_1 extends Handler {
        final /* synthetic */ b a;

        b_1(b bVar) {
            this.a = bVar;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void handleMessage(android.os.Message r9) {
            /*
            r8 = this;
            r6 = 0;
            r1 = 1;
            r2 = r8.a;
            monitor-enter(r2);
            r0 = r9.what;	 Catch:{ all -> 0x002a }
            if (r0 != r1) goto L_0x0028;
        L_0x000a:
            r0 = r8.a;	 Catch:{ all -> 0x002a }
            r0 = r0.f;	 Catch:{ all -> 0x002a }
            if (r0 == 0) goto L_0x0014;
        L_0x0012:
            monitor-exit(r2);	 Catch:{ all -> 0x002a }
        L_0x0013:
            return;
        L_0x0014:
            r0 = r8.a;	 Catch:{ all -> 0x002a }
            r0 = r0.d;	 Catch:{ all -> 0x002a }
            r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x002a }
            r0 = r0 - r4;
            r3 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
            if (r3 > 0) goto L_0x002d;
        L_0x0023:
            r0 = r8.a;	 Catch:{ all -> 0x002a }
            r0.e();	 Catch:{ all -> 0x002a }
        L_0x0028:
            monitor-exit(r2);	 Catch:{ all -> 0x002a }
            goto L_0x0013;
        L_0x002a:
            r0 = move-exception;
            monitor-exit(r2);	 Catch:{ all -> 0x002a }
            throw r0;
        L_0x002d:
            r3 = r8.a;	 Catch:{ all -> 0x002a }
            r4 = r3.c;	 Catch:{ all -> 0x002a }
            r3 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
            if (r3 >= 0) goto L_0x0045;
        L_0x0037:
            r3 = r8.a;	 Catch:{ all -> 0x002a }
            r3.a(r0);	 Catch:{ all -> 0x002a }
            r3 = 1;
            r3 = r8.obtainMessage(r3);	 Catch:{ all -> 0x002a }
            r8.sendMessageDelayed(r3, r0);	 Catch:{ all -> 0x002a }
            goto L_0x0028;
        L_0x0045:
            r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x002a }
            r3 = r8.a;	 Catch:{ all -> 0x002a }
            r3.a(r0);	 Catch:{ all -> 0x002a }
            r0 = r8.a;	 Catch:{ all -> 0x002a }
            r0 = r0.c;	 Catch:{ all -> 0x002a }
            r0 = r0 + r4;
            r4 = android.os.SystemClock.elapsedRealtime();	 Catch:{ all -> 0x002a }
            r0 = r0 - r4;
        L_0x005a:
            r3 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1));
            if (r3 >= 0) goto L_0x0066;
        L_0x005e:
            r3 = r8.a;	 Catch:{ all -> 0x002a }
            r4 = r3.c;	 Catch:{ all -> 0x002a }
            r0 = r0 + r4;
            goto L_0x005a;
        L_0x0066:
            r3 = 1;
            r3 = r8.obtainMessage(r3);	 Catch:{ all -> 0x002a }
            r8.sendMessageDelayed(r3, r0);	 Catch:{ all -> 0x002a }
            goto L_0x0028;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.inapp.b.1.handleMessage(android.os.Message):void");
        }
    }

    public abstract void a(long j);

    public abstract void e();

    public b(long j, long j2) {
        this.b = j;
        this.c = j2;
    }

    public final synchronized void a() {
        this.f = true;
        this.i.removeMessages(1);
    }

    public final synchronized b b() {
        b bVar;
        this.f = false;
        if (this.b <= 0) {
            e();
            bVar = this;
        } else {
            this.d = SystemClock.elapsedRealtime() + this.b;
            this.i.sendMessage(this.i.obtainMessage(1));
            bVar = this;
        }
        return bVar;
    }

    public final synchronized b c() {
        b bVar;
        this.f = false;
        this.e = this.d - SystemClock.elapsedRealtime();
        if (this.e <= 0) {
            bVar = this;
        } else {
            this.i.removeMessages(1);
            this.i.sendMessageAtFrontOfQueue(this.i.obtainMessage(2));
            bVar = this;
        }
        return bVar;
    }

    public final synchronized b d() {
        b bVar;
        this.f = false;
        if (this.e <= 0) {
            bVar = this;
        } else {
            this.i.removeMessages(2);
            this.d = this.e + SystemClock.elapsedRealtime();
            this.i.sendMessageAtFrontOfQueue(this.i.obtainMessage(1));
            bVar = this;
        }
        return bVar;
    }
}
