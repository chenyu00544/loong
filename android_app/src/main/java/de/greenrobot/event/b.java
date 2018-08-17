package de.greenrobot.event;

import android.util.Log;

/* compiled from: BackgroundPoster */
final class b implements Runnable {
    private final g a = new g();
    private volatile boolean b;
    private final c c;

    b(c cVar) {
        this.c = cVar;
    }

    public void a(k kVar, Object obj) {
        f a = f.a(kVar, obj);
        synchronized (this) {
            this.a.a(a);
            if (!this.b) {
                this.b = true;
                c.a.execute(this);
            }
        }
    }

    public void run() {
        while (true) {
            try {
                f a = this.a.a(1000);
                if (a == null) {
                    synchronized (this) {
                        a = this.a.a();
                        if (a == null) {
                            this.b = false;
                            this.b = false;
                            return;
                        }
                    }
                }
                this.c.a(a);
            } catch (Throwable e) {
                Log.w("Event", new StringBuilder(String.valueOf(Thread.currentThread().getName())).append(" was interruppted").toString(), e);
                this.b = false;
                return;
            } catch (Throwable th) {
                this.b = false;
            }
        }
    }
}
