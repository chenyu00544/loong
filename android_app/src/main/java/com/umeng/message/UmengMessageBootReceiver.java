package com.umeng.message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.umeng.message.common.UmLog;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.l;
import com.umeng.message.proguard.l.c;
import com.umeng.message.proguard.l.d;
import java.util.Iterator;

public class UmengMessageBootReceiver extends BroadcastReceiver {
    private static final String b = UmengMessageBootReceiver.class.getName();
    private static final String c = "android.intent.action.BOOT_COMPLETED";
    Runnable a = new UmengMessageBootReceiver_1(this);
    private Context d;

    class UmengMessageBootReceiver_1 implements Runnable {
        final /* synthetic */ UmengMessageBootReceiver a;

        UmengMessageBootReceiver_1(UmengMessageBootReceiver umengMessageBootReceiver) {
            this.a = umengMessageBootReceiver;
        }

        public void run() {
            try {
                Iterator it = l.a(this.a.d).b().iterator();
                while (it.hasNext()) {
                    c cVar = (c) it.next();
                    if (l.a(this.a.d).a(cVar.a) == null && cVar.b.equals(UMessage.DISPLAY_TYPE_NOTIFICATION)) {
                        l.a(this.a.d).a(cVar.a, 2, System.currentTimeMillis());
                    }
                }
                Iterator it2 = l.a(this.a.d).d().iterator();
                while (it2.hasNext()) {
                    d dVar = (d) it2.next();
                    if (l.a(this.a.d).c(dVar.a) == null && dVar.c.equals(UMessage.DISPLAY_TYPE_NOTIFICATION)) {
                        l.a(this.a.d).a(dVar.a, dVar.b, "9", System.currentTimeMillis());
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                UmLog.d(UmengMessageBootReceiver.b, e.toString());
            }
        }
    }

    public void onReceive(Context context, Intent intent) {
        try {
            UmLog.d(b, "Boot this system , UmengMessageBootReceiver onReceive()");
            String action = intent.getAction();
            if (action != null && !action.equals("")) {
                UmLog.d(b, "action=" + intent.getAction());
                if (TextUtils.equals(intent.getAction(), c)) {
                    this.d = context;
                    new Thread(this.a).start();
                }
            }
        } catch (Exception e) {
            UmLog.d(b, e.toString());
        }
    }
}
