package com.unionpay.mobile.android.pboctransaction.c;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.unionpay.mobile.a.a.b;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.pboctransaction.f;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;
import java.util.Iterator;

public final class a implements d {
    c a;
    private b b = null;
    private boolean c = false;
    private Context d = null;
    private Handler e = null;
    private final Callback f = new b(this);
    private final ServiceConnection g = new c(this);
    private final com.unionpay.mobile.a.a.a.a h = new d(this);

    public final String a(String str) {
        return "";
    }

    public final ArrayList<com.unionpay.mobile.android.g.c> a(e eVar) {
        ArrayList b;
        ArrayList<com.unionpay.mobile.android.g.c> arrayList = null;
        k.a("plugin-tsm", "RemoteApduEngine.readList() +++");
        try {
            String str = this.c ? "D15600010100016111000000B0004101" : "D15600010100016111000000B0004001";
            k.a("plugin-tsm", "sid=" + str);
            str = this.b.a("00a4040010" + str, 0);
            if (str != null && str.equalsIgnoreCase("9000")) {
                str = this.b.a("80CA2F0000", 0);
            }
            b = f.b(str);
        } catch (Exception e) {
            e.printStackTrace();
            k.c("plugin-tsm", e.getMessage());
            b = null;
        }
        if (b != null && b.size() > 0) {
            ArrayList<com.unionpay.mobile.android.g.c> arrayList2 = new ArrayList();
            Iterator it = b.iterator();
            while (it.hasNext()) {
                com.unionpay.mobile.android.pboctransaction.a aVar = (com.unionpay.mobile.android.pboctransaction.a) it.next();
                if (aVar.c() && !"06".equalsIgnoreCase(aVar.b())) {
                    String c = f.c(eVar.a(aVar));
                    if (c != null && c.length() > 0) {
                        arrayList2.add(new com.unionpay.mobile.android.g.a(4, aVar.a(), "", c, 1));
                    }
                }
            }
            arrayList = arrayList2;
        }
        k.a("plugin-tsm", "RemoteApduEngine.readList() ---");
        return arrayList;
    }

    public final void a() {
        d();
        if (this.d != null) {
            Context context = this.d;
            k.a("plugin-tsm", "unbindTSMService() ++");
            new Intent("com.unionpay.mobile.tsm.PBOCService").setPackage("com.unionpay.mobile.tsm");
            context.unbindService(this.g);
        }
    }

    public final void a(c cVar, Context context) {
        this.a = cVar;
        this.d = context;
        this.e = new Handler(this.f);
        try {
            Intent intent = new Intent("com.unionpay.mobile.tsm.PBOCService");
            intent.setPackage("com.unionpay.mobile.tsm");
            context.startService(intent);
            if (this.e != null) {
                this.e.sendMessageDelayed(Message.obtain(this.e, b.REQUEST_MERGE_PERIOD), 8000);
            }
            if (!context.bindService(intent, this.g, 1) && this.a != null) {
                k.a("plugin-tsm", "startTSMService.initFailed()");
                this.a.b();
            }
        } catch (Exception e) {
            if (this.a != null) {
                k.a("plugin-tsm", "startTSMService exception");
                this.a.b();
            }
        }
    }

    public final void a(boolean z) {
        this.c = z;
    }

    public final byte[] a(byte[] bArr, int i) {
        String str = null;
        if (bArr == null) {
            return str;
        }
        String a = f.a(bArr);
        k.a("plugin-tsm", "[---->]" + a);
        try {
            str = this.b.a(a, i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        k.a("plugin-tsm", "[<----]" + str);
        return f.a(str);
    }

    public final void b() {
    }

    public final void c() {
        d();
    }

    public final void d() {
        if (this.b != null) {
            try {
                this.b.a(0);
                this.b.a(1);
                this.b.a(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
