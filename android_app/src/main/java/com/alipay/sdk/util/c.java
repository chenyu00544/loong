package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import com.alipay.android.app.IRemoteServiceCallback;

public class c {
    public Activity a;
    private com.alipay.android.app.a b;
    private final Object c = com.alipay.android.app.a.class;
    private boolean d;
    private a e;
    private ServiceConnection f = new e(this);
    private IRemoteServiceCallback g = new f(this);

    public interface a {
        void a();
    }

    public c(Activity activity, a aVar) {
        this.a = activity;
        this.e = aVar;
    }

    public final String a(String str) {
        try {
            com.alipay.sdk.util.i.a a = i.a(this.a, "com.eg.android.AlipayGphone");
            if (a.a()) {
                return "failed";
            }
            if (a != null && a.b > 78) {
                Intent intent = new Intent();
                intent.setClassName("com.eg.android.AlipayGphone", "com.alipay.android.app.TransProcessPayActivity");
                this.a.startActivity(intent);
                Thread.sleep(200);
            }
            return b(str);
        } catch (Throwable th) {
            com.alipay.sdk.app.a.a.a("biz", "CheckClientSignEx", th);
        }
    }

    private String b(String str) {
        Intent intent = new Intent();
        intent.setPackage("com.eg.android.AlipayGphone");
        intent.setAction("com.eg.android.AlipayGphone.IAlixPay");
        String g = i.g(this.a);
        try {
            String g2;
            this.a.getApplicationContext().bindService(intent, this.f, 1);
            synchronized (this.c) {
                if (this.b == null) {
                    try {
                        this.c.wait((long) com.alipay.sdk.b.a.b().a());
                    } catch (Throwable e) {
                        com.alipay.sdk.app.a.a.a("biz", "BindWaitTimeoutEx", e);
                    }
                }
            }
            try {
                if (this.b == null) {
                    g2 = i.g(this.a);
                    com.alipay.sdk.app.a.a.a("biz", "ClientBindFailed", g + "|" + g2 + "|" + i.h(this.a));
                    g2 = "failed";
                    try {
                        this.b.b(this.g);
                    } catch (Throwable th) {
                    }
                    try {
                        this.a.unbindService(this.f);
                    } catch (Throwable th2) {
                    }
                    this.g = null;
                    this.f = null;
                    this.b = null;
                    if (!this.d) {
                        return g2;
                    }
                    this.a.setRequestedOrientation(0);
                    this.d = false;
                    return g2;
                }
                if (this.e != null) {
                    this.e.a();
                }
                if (this.a.getRequestedOrientation() == 0) {
                    this.a.setRequestedOrientation(1);
                    this.d = true;
                }
                this.b.a(this.g);
                g2 = this.b.a(str);
                try {
                    this.b.b(this.g);
                } catch (Throwable th3) {
                }
                try {
                    this.a.unbindService(this.f);
                } catch (Throwable th4) {
                }
                this.g = null;
                this.f = null;
                this.b = null;
                if (!this.d) {
                    return g2;
                }
                this.a.setRequestedOrientation(0);
                this.d = false;
                return g2;
            } catch (Throwable th5) {
            }
            this.a.unbindService(this.f);
            this.g = null;
            this.f = null;
            this.b = null;
            if (this.d) {
                return g2;
            }
            this.a.setRequestedOrientation(0);
            this.d = false;
            return g2;
            this.g = null;
            this.f = null;
            this.b = null;
            if (this.d) {
                return g2;
            }
            this.a.setRequestedOrientation(0);
            this.d = false;
            return g2;
        } catch (Throwable e2) {
            com.alipay.sdk.app.a.a.a("biz", "ClientBindServiceFailed", e2);
            return "failed";
        }
    }
}
