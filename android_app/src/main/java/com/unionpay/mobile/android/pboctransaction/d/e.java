package com.unionpay.mobile.android.pboctransaction.d;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import com.unionpay.tsmservice.c.c;
import com.unionpay.tsmservice.c.g;
import com.unionpay.tsmservice.c.k;
import com.unionpay.tsmservice.c.o;
import com.unionpay.tsmservice.c.s;
import com.unionpay.tsmservice.c.u;
import com.unionpay.tsmservice.c.w;
import com.unionpay.tsmservice.d.a;

public final class e extends a {
    private int a;
    private Handler b;

    public e(int i, Handler handler) {
        this.a = i;
        this.b = handler;
    }

    public final void a(Bundle bundle) throws RemoteException {
        switch (this.a) {
            case 1000:
                bundle.setClassLoader(o.class.getClassLoader());
                this.b.sendMessage(Message.obtain(this.b, 1000, bundle));
                return;
            case 1011:
                bundle.setClassLoader(s.class.getClassLoader());
                s sVar = (s) bundle.get("result");
                String b = sVar.b();
                String a = sVar.a();
                Bundle bundle2 = new Bundle();
                bundle2.putString("channel", b);
                bundle2.putString("apdu", a);
                this.b.sendMessage(Message.obtain(this.b, 1011, bundle2));
                return;
            case 1012:
                bundle.setClassLoader(u.class.getClassLoader());
                this.b.sendMessage(Message.obtain(this.b, 1012, ((u) bundle.get("result")).a()));
                return;
            case 1013:
                this.b.sendMessage(Message.obtain(this.b, 1013, ""));
                return;
            case 1014:
                bundle.setClassLoader(k.class.getClassLoader());
                this.b.sendMessage(Message.obtain(this.b, 1014, (k) bundle.get("result")));
                return;
            case 1015:
                bundle.setClassLoader(g.class.getClassLoader());
                this.b.sendMessage(Message.obtain(this.b, 1015, ((g) bundle.get("result")).a()));
                return;
            case 1016:
                com.unionpay.mobile.android.utils.k.c("uppay-spay", "check spay support callback");
                bundle.setClassLoader(c.class.getClassLoader());
                bundle.get("result");
                this.b.sendMessage(Message.obtain(this.b, 1016, ""));
                return;
            case 1018:
                com.unionpay.mobile.android.utils.k.c("uppay-spay", "get vendor pay status callback");
                bundle.setClassLoader(w.class.getClassLoader());
                Bundle a2 = ((w) bundle.get("result")).a();
                a2.putBoolean("KEY_SUCCESS_VENDOR", true);
                this.b.sendMessage(Message.obtain(this.b, 1018, a2));
                com.unionpay.mobile.android.utils.k.c("unpay", "result vendorPayStatusResult max card num reached:" + a2.getBoolean("maxCardNumReached"));
                return;
            default:
                return;
        }
    }

    public final void a(String str, String str2) throws RemoteException {
        Log.e("uppay", "errorCode:" + str + ", errorDesc:" + str2);
        if ("1003700023".equals(str) && this.a == 1018) {
            com.unionpay.mobile.android.utils.k.c("uppay", "error 100370023 from get vendor pay status");
            Bundle bundle = new Bundle();
            bundle.putBoolean("KEY_SUCCESS_VENDOR", false);
            this.b.sendMessage(Message.obtain(this.b, 1018, bundle));
            return;
        }
        this.b.sendMessage(Message.obtain(this.b, 1, this.a, 0, str));
    }
}
