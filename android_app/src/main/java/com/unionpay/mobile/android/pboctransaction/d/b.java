package com.unionpay.mobile.android.pboctransaction.d;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.RemoteException;
import com.unionpay.b.a.a;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.pboctransaction.f;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;

public final class b implements d {
    private Context a;
    private c b;
    private a c;
    private int d = 0;
    private Callback e = new c(this);
    private Handler f = new Handler(this.e);

    private void a(boolean z) {
        if (this.b == null) {
            return;
        }
        if (z) {
            this.b.a();
        } else {
            this.b.b();
        }
    }

    public final String a(String str) {
        return "";
    }

    public final ArrayList<com.unionpay.mobile.android.g.c> a(e eVar) {
        RemoteException remoteException;
        Exception exception;
        ArrayList<com.unionpay.mobile.android.g.c> arrayList = null;
        if (this.c == null) {
            return null;
        }
        try {
            com.unionpay.b.a.b[] a = this.c.a(this.d);
            if (a == null || a.length <= 0) {
                return null;
            }
            ArrayList<com.unionpay.mobile.android.g.c> arrayList2 = new ArrayList();
            int i = 0;
            while (i < a.length) {
                try {
                    String a2 = a[i].a();
                    if (a2 != null && a2.startsWith("A000000333")) {
                        Object obj;
                        a2 = a[i].a();
                        if (a2 != null && a2.length() > 16) {
                            if (!"06".equalsIgnoreCase(a2.substring(14, 16))) {
                                obj = null;
                                if (obj == null) {
                                    arrayList2.add(new com.unionpay.mobile.android.g.a(1, a[i].a(), "", a[i].b(), 1));
                                }
                            }
                        }
                        int i2 = 1;
                        if (obj == null) {
                            arrayList2.add(new com.unionpay.mobile.android.g.a(1, a[i].a(), "", a[i].b(), 1));
                        }
                    }
                    i++;
                } catch (RemoteException e) {
                    remoteException = e;
                    arrayList = arrayList2;
                } catch (Exception e2) {
                    exception = e2;
                    arrayList = arrayList2;
                }
            }
            return arrayList2;
        } catch (RemoteException e3) {
            remoteException = e3;
            remoteException.printStackTrace();
            return arrayList;
        } catch (Exception e4) {
            exception = e4;
            exception.printStackTrace();
            return arrayList;
        }
    }

    public final void a() {
    }

    public final void a(c cVar, Context context) {
        this.b = cVar;
        this.a = context;
        try {
            Intent intent = new Intent();
            intent.setAction("com.unionpay.client3.action.TSM_MODEL");
            intent.setPackage("com.unionpay");
            context.startService(intent);
            if (context.bindService(intent, new d(this), 1)) {
                this.f.sendMessageDelayed(this.f.obtainMessage(1), 3000);
                return;
            }
            k.a("plugin-clientV3", "startSamsungService() failed!!!");
            a(false);
        } catch (Exception e) {
            a(false);
        }
    }

    public final byte[] a(byte[] bArr, int i) {
        byte[] bArr2 = null;
        if (this.c != null) {
            try {
                k.a("plugin-clientV3", "--->" + f.a(bArr));
                String a = this.c.a(this.d, f.a(bArr), i);
                k.a("plugin-clientV3", "<---" + a);
                bArr2 = f.a(a);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bArr2;
    }

    public final void b() {
    }

    public final void c() {
    }

    public final void d() {
    }
}
