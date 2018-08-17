package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import com.unionpay.mobile.android.g.b;
import com.unionpay.mobile.android.nocard.views.l;
import com.unionpay.mobile.android.pro.a.a.a;
import com.unionpay.mobile.android.utils.k;
import org.simalliance.openmobileapi.Reader;

public class h extends l {
    public h(Context context) {
        super(context);
    }

    public final void C() {
        StringBuffer stringBuffer = new StringBuffer("000");
        com.unionpay.mobile.android.utils.l lVar = null;
        b bVar = this.a;
        if (b.ba) {
            lVar = new com.unionpay.mobile.android.utils.l();
        }
        if (!(lVar == null || com.unionpay.mobile.android.utils.l.a() == null || !com.unionpay.mobile.android.utils.l.a().isConnected())) {
            for (Reader reader : com.unionpay.mobile.android.utils.l.a().getReaders()) {
                if (reader != null) {
                    if (reader.getName().toLowerCase().startsWith("sim")) {
                        stringBuffer.setCharAt(0, '1');
                    }
                    if (reader.getName().toLowerCase().startsWith("ese")) {
                        stringBuffer.setCharAt(1, '1');
                    }
                    if (reader.getName().toLowerCase().startsWith("sd")) {
                        stringBuffer.setCharAt(2, '1');
                    }
                }
            }
        }
        d(stringBuffer.toString());
    }

    public com.unionpay.mobile.android.pro.a.a.b D() {
        return null;
    }

    protected final void u() {
        b bVar;
        try {
            Class.forName("org.simalliance.openmobileapi.SEService");
            bVar = this.a;
            b.ba = true;
            com.unionpay.mobile.android.utils.l lVar = new com.unionpay.mobile.android.utils.l(this.d, this);
        } catch (Exception e) {
            e.printStackTrace();
            bVar = this.a;
            b.ba = false;
            v();
        }
    }

    public final void z() {
        synchronized (this) {
            k.c("uppay-pro", "checkAndGetSDCardsList +++");
            boolean z = "00".equalsIgnoreCase(this.a.I.c) || "95".equalsIgnoreCase(this.a.I.c);
            com.unionpay.mobile.android.pro.a.a.b D = D();
            a rVar = new r(this);
            if (D != null) {
                D().a(rVar, z);
            } else {
                rVar.a(null);
            }
            k.c("uppay-pro", "checkAndGetSDCardsList ---");
        }
    }
}
