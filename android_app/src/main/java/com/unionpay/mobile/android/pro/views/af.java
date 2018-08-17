package com.unionpay.mobile.android.pro.views;

import android.os.Handler.Callback;
import android.os.Message;
import com.baidu.mapapi.UIMsg.f_FUN;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.unionpay.mobile.android.g.a;
import com.unionpay.mobile.android.g.b;

final class af implements Callback {
    final /* synthetic */ v a;

    af(v vVar) {
        this.a = vVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case m_AppUI.MSG_APP_DATA_OK /*2000*/:
                if (message.obj == null) {
                    this.a.a(this.a.a.ap, false);
                    break;
                }
                a aVar = (a) message.obj;
                if (aVar != null) {
                    this.a.a(aVar);
                    break;
                }
                break;
            case f_FUN.FUN_ID_VOICE_SCH /*2001*/:
                if (!"1003100020".equalsIgnoreCase((String) message.obj)) {
                    if (!b.bm) {
                        this.a.a(this.a.a.ap, false);
                        break;
                    }
                    this.a.e(this.a.a.ap, "fail");
                    break;
                } else if (b.bm) {
                    this.a.v();
                    break;
                }
                break;
        }
        return true;
    }
}
