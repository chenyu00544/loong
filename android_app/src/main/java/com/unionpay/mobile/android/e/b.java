package com.unionpay.mobile.android.e;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.unionpay.mobile.android.e.a.b.a;

public final class b extends a {
    private int a;
    private String b;
    private Handler c;

    public b(int i, String str, Handler handler) {
        this.a = i;
        this.b = str;
        this.c = handler;
    }

    public final void a(String str) throws RemoteException {
        Bundle bundle;
        switch (this.a) {
            case 2003:
                bundle = new Bundle();
                bundle.putString("pkgName", this.b);
                bundle.putBoolean("success", false);
                bundle.putString("errCode", str);
                this.c.sendMessage(Message.obtain(this.c, 2003, bundle));
                return;
            case m_AppUI.MSG_APP_VERSION /*2004*/:
                bundle = new Bundle();
                bundle.putBoolean("success", false);
                bundle.putString("errCode", str);
                this.c.sendMessage(Message.obtain(this.c, m_AppUI.MSG_APP_VERSION, bundle));
                return;
            default:
                return;
        }
    }

    public final void a(String str, String str2) throws RemoteException {
        Bundle bundle;
        switch (this.a) {
            case 2003:
                bundle = new Bundle();
                bundle.putString("pkgName", this.b);
                bundle.putBoolean("success", true);
                bundle.putString("result", str);
                bundle.putString("reserved", str2);
                this.c.sendMessage(Message.obtain(this.c, 2003, bundle));
                return;
            case m_AppUI.MSG_APP_VERSION /*2004*/:
                bundle = new Bundle();
                bundle.putBoolean("success", true);
                bundle.putString("result", str);
                bundle.putString("reserved", str2);
                this.c.sendMessage(Message.obtain(this.c, m_AppUI.MSG_APP_VERSION, bundle));
                return;
            default:
                return;
        }
    }
}
