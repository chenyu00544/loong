package com.baidu.mapapi.utils;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.a.a.a.c.a;

class d extends a {
    final /* synthetic */ c a;

    d(c cVar) {
        this.a = cVar;
    }

    public void a(IBinder iBinder) throws RemoteException {
        Log.d(a.c, "onClientReady");
        if (a.e != null) {
            a.e = null;
        }
        a.e = com.baidu.a.a.a.a.a.a(iBinder);
        if (!a.t) {
            a.a(a.a);
        }
        a.t = true;
    }
}
