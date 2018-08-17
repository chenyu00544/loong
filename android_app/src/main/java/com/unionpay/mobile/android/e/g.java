package com.unionpay.mobile.android.e;

import android.os.Bundle;
import android.os.Handler.Callback;
import android.os.Message;
import com.baidu.mapapi.UIMsg.f_FUN;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.unionpay.mobile.android.utils.k;

final class g implements Callback {
    final /* synthetic */ f a;

    g(f fVar) {
        this.a = fVar;
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case f_FUN.FUN_ID_VOICE_SCH /*2001*/:
                f.a(this.a);
                this.a.b();
                break;
            case 2002:
                f.a(this.a, (String) message.obj);
                break;
            case 2003:
                Bundle bundle = (Bundle) message.obj;
                if (bundle != null) {
                    String string = bundle.getString("pkgName");
                    boolean z = bundle.getBoolean("success");
                    String string2 = bundle.getString("result");
                    String string3 = bundle.getString("reserved");
                    k.c("yitong", "result: " + string2);
                    d dVar = (d) this.a.u.get(string);
                    if (dVar == null) {
                        dVar = new d(string);
                    }
                    if (z) {
                        dVar.a(string2);
                        dVar.b(string3);
                    }
                    dVar.e();
                    this.a.u.put(string, dVar);
                    f.a(this.a, string);
                    break;
                }
                break;
            case m_AppUI.MSG_APP_VERSION_FORCE /*2005*/:
                break;
            case m_AppUI.MSG_APP_VERSION_COMMEND /*2006*/:
                Object obj = message.obj;
                break;
        }
        String str = (String) message.obj;
        d dVar2 = (d) this.a.u.get(str);
        l lVar = (l) this.a.v.get(str);
        dVar2.f();
        this.a.u.put(str, dVar2);
        lVar.e();
        this.a.v.put(str, lVar);
        f.a(this.a, str);
        return false;
    }
}
