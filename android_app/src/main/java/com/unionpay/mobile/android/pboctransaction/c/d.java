package com.unionpay.mobile.android.pboctransaction.c;

import android.os.RemoteException;
import com.unionpay.mobile.a.a.a.a;
import com.unionpay.mobile.android.utils.k;

final class d extends a {
    final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public final void a() throws RemoteException {
        k.a("plugin-tsm", "mInitCallback.initSucceed()");
        if (this.a.a != null) {
            this.a.a.a();
        }
    }

    public final void b() throws RemoteException {
        k.a("plugin-tsm", "mInitCallback.initFailed()");
        if (this.a.a != null) {
            this.a.a.b();
        }
    }
}
