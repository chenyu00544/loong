package com.baidu.location.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Handler;
import com.baidu.location.b.f;
import com.baidu.location.b.k;
import com.baidu.location.b.o;
import com.baidu.location.c.d;

public class i implements f, g {
    private static i hw = null;
    private boolean hA = false;
    private boolean hB = false;
    private boolean hC = false;
    private a hD = null;
    private boolean hx = false;
    private boolean hy = true;
    final Handler hz = new Handler();

    private class a extends BroadcastReceiver {
        final /* synthetic */ i a;

        private a(i iVar) {
            this.a = iVar;
        }

        public void onReceive(Context context, Intent intent) {
            if (context != null && this.a.hz != null) {
                this.a.b0();
            }
        }
    }

    private class b implements com.baidu.location.b.b, Runnable {
        final /* synthetic */ i bf;

        class i_b_1 extends Thread {
            final /* synthetic */ b a;

            i_b_1(b bVar) {
                this.a = bVar;
            }

            public void run() {
                super.run();
                d.try().n();
                d.try().k();
            }
        }

        private b(i iVar) {
            this.bf = iVar;
        }

        public void run() {
            if (this.bf.hy) {
                if (this.bf.hB && e.bw().bu() && d.try().long()) {
                    new i_b_1(this).start();
                }
                if (this.bf.hB && e.bw().bu()) {
                    o.aX().aV();
                }
                if (this.bf.hB && this.bf.hy) {
                    this.bf.hz.postDelayed(this, (long) k.cy);
                    this.bf.hC = true;
                    return;
                }
                this.bf.hC = false;
            }
        }
    }

    private i() {
    }

    private void b0() {
        State state;
        State state2 = State.UNKNOWN;
        try {
            state = ((ConnectivityManager) com.baidu.location.f.getServiceContext().getSystemService("connectivity")).getNetworkInfo(1).getState();
        } catch (Exception e) {
            state = state2;
        }
        if (State.CONNECTED != state) {
            this.hB = false;
        } else if (!this.hB) {
            this.hB = true;
            this.hz.postDelayed(new b(), (long) k.cy);
            this.hC = true;
        }
    }

    public static i bX() {
        if (hw == null) {
            hw = new i();
        }
        return hw;
    }

    public void bU() {
        this.hy = false;
    }

    public void bV() {
        if (this.hx) {
            this.hy = true;
            if (!this.hC && this.hy) {
                this.hz.postDelayed(new b(), (long) k.cy);
                this.hC = true;
            }
        }
    }

    public synchronized void bW() {
        if (com.baidu.location.f.isServing) {
            if (!this.hx) {
                try {
                    this.hD = new a();
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                    com.baidu.location.f.getServiceContext().registerReceiver(this.hD, intentFilter);
                    this.hA = true;
                    b0();
                } catch (Exception e) {
                }
                this.hy = true;
                this.hx = true;
            }
        }
    }

    public void bY() {
        if (this.hD == null) {
            this.hD = new a();
        }
        try {
            if (!this.hA) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                com.baidu.location.f.getServiceContext().registerReceiver(this.hD, intentFilter);
                b0();
                this.hA = true;
            }
        } catch (Exception e) {
        }
    }

    public synchronized void bZ() {
        if (this.hx) {
            try {
                com.baidu.location.f.getServiceContext().unregisterReceiver(this.hD);
            } catch (Exception e) {
            }
            this.hy = false;
            this.hx = false;
            this.hD = null;
        }
    }
}
