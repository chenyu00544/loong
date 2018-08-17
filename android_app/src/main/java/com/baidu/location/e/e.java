package com.baidu.location.e;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.baidu.location.b.f;
import com.tencent.connect.common.Constants;

public class e implements f {
    private static e gr = null;
    private int gq = -1;
    private String gs = null;
    private a gt = null;
    private boolean gu = false;

    public class a extends BroadcastReceiver {
        final /* synthetic */ e a;

        public a(e eVar) {
            this.a = eVar;
        }

        public void onReceive(Context context, Intent intent) {
            try {
                if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                    this.a.gu = false;
                    int intExtra = intent.getIntExtra("status", 0);
                    int intExtra2 = intent.getIntExtra("plugged", 0);
                    int intExtra3 = intent.getIntExtra("level", -1);
                    int intExtra4 = intent.getIntExtra("scale", -1);
                    if (intExtra3 <= 0 || intExtra4 <= 0) {
                        this.a.gq = -1;
                    } else {
                        this.a.gq = (intExtra3 * 100) / intExtra4;
                    }
                    switch (intExtra) {
                        case 2:
                            this.a.gs = "4";
                            break;
                        case 3:
                        case 4:
                            this.a.gs = "3";
                            break;
                        default:
                            this.a.gs = null;
                            break;
                    }
                    switch (intExtra2) {
                        case 1:
                            this.a.gs = Constants.VIA_SHARE_TYPE_INFO;
                            this.a.gu = true;
                            return;
                        case 2:
                            this.a.gs = "5";
                            this.a.gu = true;
                            return;
                        default:
                            return;
                    }
                }
            } catch (Exception e) {
                this.a.gs = null;
            }
        }
    }

    private e() {
    }

    public static synchronized e bw() {
        e eVar;
        synchronized (e.class) {
            if (gr == null) {
                gr = new e();
            }
            eVar = gr;
        }
        return eVar;
    }

    public boolean bu() {
        return this.gu;
    }

    public String bv() {
        return this.gs;
    }

    public int bx() {
        return this.gq;
    }

    public void by() {
        this.gt = new a(this);
        com.baidu.location.f.getServiceContext().registerReceiver(this.gt, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
    }

    public void bz() {
        if (this.gt != null) {
            try {
                com.baidu.location.f.getServiceContext().unregisterReceiver(this.gt);
            } catch (Exception e) {
            }
        }
        this.gt = null;
    }
}
