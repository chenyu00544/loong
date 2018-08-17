package com.unionpay.mobile.android.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.unionpay.mobile.android.h.c;
import com.unionpay.mobile.android.h.d;
import java.lang.ref.WeakReference;

public final class r implements Callback, Runnable {
    private d a = null;
    private Handler b = null;
    private WeakReference<a> c = null;
    private Context d;

    public interface a {
        void a(int i, byte[] bArr);
    }

    public r(Context context, String str, a aVar) {
        this.a = new d(0, str, null);
        this.b = new Handler(this);
        this.c = new WeakReference(aVar);
        this.d = context;
    }

    public final void a() {
        new Thread(this).start();
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                if (!(this.c == null || this.c.get() == null)) {
                    ((a) this.c.get()).a(message.arg1, message.obj != null ? (byte[]) message.obj : null);
                    break;
                }
        }
        return true;
    }

    public final void run() {
        c cVar = new c(this.a, this.d);
        int a = cVar.a();
        if (this.b != null) {
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.what = 0;
            obtainMessage.arg1 = a;
            obtainMessage.obj = cVar.b();
            this.b.sendMessage(obtainMessage);
        }
    }
}
