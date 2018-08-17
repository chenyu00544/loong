package com.taobao.agoo;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.taobao.accs.utl.ALog;
import org.android.agoo.control.AgooFactory;
import org.android.agoo.control.NotifManager;

/* compiled from: Taobao */
class a implements Runnable {
    final /* synthetic */ Intent a;
    final /* synthetic */ BaseNotifyClickActivity b;

    a(BaseNotifyClickActivity baseNotifyClickActivity, Intent intent) {
        this.b = baseNotifyClickActivity;
        this.a = intent;
    }

    public void run() {
        Intent intent;
        Throwable th;
        Throwable th2;
        Intent intent2 = null;
        try {
            if (this.a != null) {
                String access$000 = this.b.parseMsgByThirdPush(this.a);
                if (TextUtils.isEmpty(access$000) || TextUtils.isEmpty(this.b.msgSource)) {
                    ALog.e("accs.BaseNotifyClickActivity", "parseMsgFromNotifyListener null!!", "source", this.b.msgSource);
                } else {
                    if (this.b.notifyManager == null) {
                        this.b.notifyManager = new NotifManager();
                    }
                    if (this.b.agooFactory == null) {
                        this.b.agooFactory = new AgooFactory();
                        this.b.agooFactory.init(this.b.getApplicationContext(), this.b.notifyManager, null);
                    }
                    Bundle msgReceiverPreHandler = this.b.agooFactory.msgReceiverPreHandler(access$000.getBytes("UTF-8"), this.b.msgSource, null, false);
                    intent = new Intent();
                    try {
                        intent.putExtras(msgReceiverPreHandler);
                        this.b.agooFactory.saveMsg(access$000.getBytes("UTF-8"), "2");
                        this.b.reportClickNotifyMsg(intent);
                        this.b.onMessage(intent);
                    } catch (Throwable th3) {
                        th = th3;
                        intent2 = intent;
                        th2 = th;
                        this.b.onMessage(intent2);
                        throw th2;
                    }
                }
            }
            intent = null;
            this.b.onMessage(intent);
        } catch (Throwable th4) {
            th2 = th4;
            ALog.e("accs.BaseNotifyClickActivity", "buildMessage", th2, new Object[0]);
            this.b.onMessage(intent2);
        }
    }
}
