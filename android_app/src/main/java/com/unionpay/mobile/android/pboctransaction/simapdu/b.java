package com.unionpay.mobile.android.pboctransaction.simapdu;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.text.TextUtils;
import android.util.Log;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.pboctransaction.f;
import com.unionpay.mobile.android.pboctransaction.simapdu.a.a;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.l;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import org.simalliance.openmobileapi.Channel;
import org.simalliance.openmobileapi.Reader;
import org.simalliance.openmobileapi.SEService;

public final class b implements d {
    private static b d = new b();
    private SEService a = null;
    private Channel[] b = new Channel[3];
    private c c;
    private Callback e = new c(this);
    private Handler f = new Handler(this.e);

    private b() {
    }

    private synchronized String a(String str, int i) throws a {
        String str2 = null;
        Object obj = null;
        synchronized (this) {
            if (str != null) {
                k.a("plugin-sim", "====>" + str);
                String toUpperCase = str.toUpperCase(Locale.CHINA);
                if (i > this.b.length) {
                    i = 0;
                }
                if (toUpperCase.startsWith("00A40400") || toUpperCase.startsWith("01A40400") || toUpperCase.startsWith("02A40400")) {
                    obj = 1;
                }
                if (obj != null) {
                    a(i);
                    str2 = b(f.a(toUpperCase.substring(10, (((Integer.parseInt(toUpperCase.substring(8, 9), 16) * 16) + Integer.parseInt(toUpperCase.substring(9, 10), 16)) * 2) + 10)), i);
                    if (TextUtils.isEmpty(str2)) {
                        k.c("plugin-sim", " writeApdu openchannel exception!!!");
                        throw new a();
                    }
                }
                try {
                    byte[] a = f.a(toUpperCase);
                    if (a != null) {
                        str2 = f.a(this.b[i].transmit(a));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    throw new a();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                k.a("plugin-sim", "<====" + str2);
            }
        }
        return str2;
    }

    private void a(int i) {
        k.a("plugin-sim", "closeChannel(int) +++");
        if (this.b[i] != null && i <= this.b.length) {
            try {
                this.b[i].close();
            } catch (Exception e) {
                e.printStackTrace();
                k.a("plugin-sim", " mChannel[channel].close() exception!!!");
            }
            this.b[i] = null;
        }
        k.a("plugin-sim", "closeChannel(int) ---");
    }

    private String b(byte[] bArr, int i) {
        try {
            Reader[] readers = this.a.getReaders();
            if (readers.length > 0) {
                Channel openLogicalChannel = readers[0].openSession().openLogicalChannel(bArr);
                if (openLogicalChannel != null) {
                    this.b[i] = openLogicalChannel;
                    return f.a(openLogicalChannel.getSelectResponse());
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return "";
    }

    public static synchronized b e() {
        b bVar;
        synchronized (b.class) {
            bVar = d;
        }
        return bVar;
    }

    public final String a(String str) {
        return "";
    }

    public final ArrayList<com.unionpay.mobile.android.g.c> a(e eVar) {
        ArrayList<com.unionpay.mobile.android.g.c> arrayList = null;
        k.c("plugin-sim", " SIMEngine.readList() +++");
        try {
            ArrayList arrayList2 = new ArrayList(1);
            String a = eVar.a("A0000003330101");
            k.c("plugin-sim", "full AID:" + a);
            if (a != null && a.length() >= 16) {
                arrayList2.add(new com.unionpay.mobile.android.pboctransaction.a(a, null));
                if (arrayList2.size() > 0) {
                    ArrayList<com.unionpay.mobile.android.g.c> arrayList3 = new ArrayList();
                    try {
                        Iterator it = arrayList2.iterator();
                        while (it.hasNext()) {
                            com.unionpay.mobile.android.pboctransaction.a aVar = (com.unionpay.mobile.android.pboctransaction.a) it.next();
                            if (!"06".equalsIgnoreCase(aVar.b())) {
                                String c = f.c(eVar.a(aVar));
                                k.a("nfcphone", " cardNumber=" + c);
                                if (c != null && c.length() > 0) {
                                    arrayList3.add(new com.unionpay.mobile.android.g.a(16, aVar.a(), "", c, 1));
                                    k.a("nfcphone", " valid Number= " + c);
                                }
                            }
                        }
                        arrayList = arrayList3;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        arrayList = arrayList3;
                        Log.e("plugin-sim", " SimEngine Exception = " + th2.getMessage());
                        k.c("plugin-sim", " SIMEngine.readList() ---");
                        return arrayList;
                    }
                }
                Log.e("plugin-sim", " SIMEngine --- there has no PBOC aids!!!");
                k.c("plugin-sim", " SIMEngine.readList() ---");
            }
        } catch (Throwable th3) {
            th2 = th3;
            Log.e("plugin-sim", " SimEngine Exception = " + th2.getMessage());
            k.c("plugin-sim", " SIMEngine.readList() ---");
            return arrayList;
        }
        return arrayList;
    }

    public final void a() {
        k.c("plugin-sim", "SIMEngine.destroy() +++ ");
        k.c("plugin-sim", " mSEService = " + this.a);
        d();
        if (this.a != null && this.a.isConnected()) {
            k.a("TAG", " mSEService.isConnected() = " + this.a.isConnected());
            k.c("plugin-sim", " mSEService.shutdown() ");
            this.a.shutdown();
        }
        k.c("plugin-sim", "SIMEngine.destroy() --- ");
    }

    public final void a(c cVar, Context context) {
        this.c = cVar;
        try {
            l lVar = new l();
            if (l.a() == null || !l.a().isConnected()) {
                this.f.sendEmptyMessage(2);
                return;
            }
            this.a = l.a();
            this.f.sendEmptyMessage(1);
        } catch (Throwable th) {
            th.printStackTrace();
            Log.e("plugin-sim", " service ERROR!!!");
            this.f.sendEmptyMessage(2);
        }
    }

    public final byte[] a(byte[] bArr, int i) {
        k.c("plugin-sim", " SIMEngine.sendApdu() +++");
        byte[] bArr2 = null;
        try {
            bArr2 = f.a(a(f.a(bArr), i));
        } catch (a e) {
            e.printStackTrace();
            k.c("plugin-sim", " " + e.getMessage());
        }
        k.c("plugin-sim", " SIMEngine.sendApdu() ---");
        return bArr2;
    }

    public final void b() {
    }

    public final void c() {
        d();
    }

    public final void d() {
        k.a("plugin-sim", "closeChannels() +++");
        for (int i = 0; i < this.b.length; i++) {
            a(i);
        }
        k.a("plugin-sim", "closeChannels() ---");
    }
}
