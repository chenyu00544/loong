package com.unionpay.mobile.android.e;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.unionpay.mobile.android.e.a.a;
import com.unionpay.mobile.android.utils.k;

final class i implements ServiceConnection {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    i(f fVar, String str, String str2) {
        this.c = fVar;
        this.a = str;
        this.b = str2;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        a a;
        String str = null;
        this.c.y.removeMessages(m_AppUI.MSG_APP_VERSION_COMMEND, this.a);
        try {
            a = a.a.a(iBinder);
        } catch (Exception e) {
            e.printStackTrace();
            a = null;
        }
        if (a != null) {
            try {
                str = a.a(this.c.d, this.c.e, new b(2003, this.a, this.c.y));
                this.c.y.sendMessageDelayed(this.c.y.obtainMessage(m_AppUI.MSG_APP_VERSION_COMMEND, this.a), (long) this.c.i);
            } catch (RemoteException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
            }
            if (str != null) {
                k.a("uppay-hce", "session Key: " + str);
                k.a("uppay-hce", "3des key: " + this.b);
                str = a.a(str, this.b);
                k.a("uppay-hce", this.a + " sessionkey after: " + str);
                l lVar = (l) this.c.v.get(this.a);
                if (lVar == null) {
                    lVar = new l(this.a);
                }
                lVar.a(str);
                lVar.a(a);
                lVar.d();
                this.c.v.put(this.a, lVar);
                this.c.y.sendMessage(this.c.y.obtainMessage(2002, this.a));
            }
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.c.y.removeMessages(m_AppUI.MSG_APP_VERSION_COMMEND, this.a);
        this.c.y.sendMessage(this.c.y.obtainMessage(m_AppUI.MSG_APP_VERSION_FORCE, this.a));
    }
}
