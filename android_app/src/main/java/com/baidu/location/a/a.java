package com.baidu.location.a;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.util.Log;
import com.baidu.location.LLSInterface;
import com.baidu.location.b.f;
import com.baidu.location.c.b;
import com.baidu.location.c.d;
import com.baidu.location.e.e;
import com.baidu.location.e.h;
import com.baidu.location.e.i;
import com.baidu.location.e.m;
import com.baidu.location.e.n;
import com.baidu.location.e.o;
import com.baidu.location.h.c;
import com.baidu.location.h.l;

public class a extends Service implements LLSInterface, f {
    static a l7 = null;
    private static long mb = 0;
    private HandlerThread l6;
    private boolean l8 = false;
    Messenger l9 = null;
    private Looper ma;

    public class a extends Handler {
        final /* synthetic */ a a;

        public a(a aVar, Looper looper) {
            this.a = aVar;
            super(looper);
        }

        public void handleMessage(Message message) {
            if (com.baidu.location.f.isServing) {
                switch (message.what) {
                    case 11:
                        this.a.m(message);
                        break;
                    case 12:
                        this.a.l(message);
                        break;
                    case 15:
                        this.a.n(message);
                        break;
                    case 22:
                        m.ba().do(message);
                        break;
                    case 28:
                        m.ba().bc();
                        break;
                    case 41:
                        m.ba().a9();
                        break;
                    case 401:
                        try {
                            message.getData();
                            break;
                        } catch (Exception e) {
                            break;
                        }
                }
            }
            if (message.what == 1) {
                this.a.dQ();
            }
            if (message.what == 0) {
                this.a.dR();
            }
            super.handleMessage(message);
        }
    }

    public static long dO() {
        return mb;
    }

    public static Handler dP() {
        return l7;
    }

    private void dQ() {
        c.a().cS();
        m.ba().bb();
        o.ci();
        com.baidu.location.e.c.bq().bs();
        h.bJ().bK();
        if (!this.l8) {
            Process.killProcess(Process.myPid());
        }
    }

    private void dR() {
        c.a().cO();
        l.a().c5();
        com.baidu.location.b.c.N();
        m.ba().bi();
        b.aZ().a0();
        e.bw().by();
    }

    private void l(Message message) {
        com.baidu.location.e.c.bq().char(message);
    }

    private void m(Message message) {
        Log.d("baidu_location_service", "baidu location service register ...");
        com.baidu.location.e.c.bq().long(message);
        d.try();
        com.baidu.location.e.f.bB().bE();
        com.baidu.location.e.d.aR().aQ();
    }

    private void n(Message message) {
        com.baidu.location.e.c.bq().goto(message);
    }

    public double getVersion() {
        return 6.130000114440918d;
    }

    public IBinder onBind(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            com.baidu.location.b.c.bl = extras.getString("key");
            com.baidu.location.b.c.bk = extras.getString("sign");
            this.l8 = extras.getBoolean("kill_process");
        }
        return this.l9.getBinder();
    }

    public void onCreate(Context context) {
        mb = System.currentTimeMillis();
        this.l6 = n.a();
        this.ma = this.l6.getLooper();
        l7 = new a(this, this.ma);
        this.l9 = new Messenger(l7);
        l7.sendEmptyMessage(0);
        Log.d("baidu_location_service", "baidu location service start1 ..." + Process.myPid());
    }

    public void onDestroy() {
        l.a().dc();
        i.bX().bZ();
        d.try().h();
        com.baidu.location.e.f.bB().bH();
        com.baidu.location.h.d.a().cF();
        e.bw().bz();
        l7.sendEmptyMessage(1);
        Log.d("baidu_location_service", "baidu location service stop ...");
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    public boolean onUnBind(Intent intent) {
        return false;
    }
}
