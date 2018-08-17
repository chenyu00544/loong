package de.greenrobot.event;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

/* compiled from: HandlerPoster */
final class d extends Handler {
    private final g a = new g();
    private final int b;
    private final c c;
    private boolean d;

    d(c cVar, Looper looper, int i) {
        super(looper);
        this.c = cVar;
        this.b = i;
    }

    void a(k kVar, Object obj) {
        f a = f.a(kVar, obj);
        synchronized (this) {
            this.a.a(a);
            if (!this.d) {
                this.d = true;
                if (!sendMessage(obtainMessage())) {
                    throw new EventBusException("Could not send handler message");
                }
            }
        }
    }

    public void handleMessage(Message message) {
        try {
            long uptimeMillis = SystemClock.uptimeMillis();
            do {
                f a = this.a.a();
                if (a == null) {
                    synchronized (this) {
                        a = this.a.a();
                        if (a == null) {
                            this.d = false;
                            this.d = false;
                            return;
                        }
                    }
                }
                this.c.a(a);
            } while (SystemClock.uptimeMillis() - uptimeMillis < ((long) this.b));
            if (sendMessage(obtainMessage())) {
                this.d = true;
                return;
            }
            throw new EventBusException("Could not send handler message");
        } catch (Throwable th) {
            this.d = false;
        }
    }
}
