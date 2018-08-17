package com.baidu.mtjstatsdk;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import com.baidu.mtjstatsdk.a.a;
import com.baidu.mtjstatsdk.b.d;
import com.taobao.accs.utl.UtilityImpl;
import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.Timer;

class j {
    private static HandlerThread a = new HandlerThread("LogSenderThread");
    private static Handler g;
    private static j h = new j();
    private boolean b = false;
    private a c = a.APP_START;
    private int d = 1;
    private Timer e;
    private WeakReference<Context> f;

    private j() {
        a.start();
        g = new Handler(a.getLooper());
    }

    public static j a() {
        return h;
    }

    private void b(Context context) {
        if (context == null) {
            d.a("statsdk", "initContext context=" + null);
        }
        if (this.f == null && context != null) {
            this.f = new WeakReference(context);
        }
    }

    public void a(Context context) {
        BasicStoreTools.getInstance().setLastSendTime(context, new Date().getTime());
    }

    public void a(Context context, long j, int i, String str) {
        if (this.e == null) {
            this.e = new Timer();
        } else {
            this.e.cancel();
        }
        if (com.baidu.mtjstatsdk.b.a.a(str)) {
            d.a("set timer log");
        }
        this.e.schedule(new m(this, str, context), j, (long) i);
    }

    public void a(Context context, String str) {
        b(context);
        if (!(context != null || this.f == null || this.f.get() == null)) {
            context = (Context) this.f.get();
        }
        g.post(new k(this, context, str));
    }

    protected void a(Context context, boolean z, String str) {
        if (z) {
            try {
                if (!((WifiManager) context.getSystemService(UtilityImpl.NET_TYPE_WIFI)).isWifiEnabled()) {
                    d.a("statsdk", "sendLogData() does not send because of only_wifi setting");
                    return;
                }
            } catch (Exception e) {
                d.a("statsdk", "sendLogData exception when get wifimanager");
                return;
            }
        }
        a.a(str).d(context, str);
    }

    public void b(Context context, String str) {
        a(context, (long) (this.d * 60000), this.d * 60000, str);
    }
}
