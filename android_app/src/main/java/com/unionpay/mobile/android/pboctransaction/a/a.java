package com.unionpay.mobile.android.pboctransaction.a;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import com.unionpay.mobile.android.pboctransaction.c;
import com.unionpay.mobile.android.pboctransaction.d;
import com.unionpay.mobile.android.pboctransaction.f;
import com.unionpay.mobile.android.utils.k;

public final class a implements d {
    private String a = null;
    private String b = "A0000003334355502D4D4F42494C45";
    private cn.a.a.a.a.a.a.a.a.a c;
    private c d;
    private Context e;
    private ServiceConnection f = new b(this);

    private byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            String a = f.a(bArr);
            if (b(a)) {
                if (a.contains(this.a)) {
                    k.c("icfcc", "pbocAID = " + this.a);
                    bArr2 = this.c.a(f.a(this.a), "00");
                } else if (a.contains(this.b)) {
                    k.c("icfcc", "upcardAID = " + this.b);
                    bArr2 = this.c.a(f.a(this.b), "01");
                }
            }
        } catch (Exception e) {
        }
        k.c("icfcc", " openSEChannel result=" + f.a(bArr2));
        return bArr2;
    }

    private static boolean b(String str) {
        return str.startsWith("00A40400") || str.startsWith("01A40400") || str.startsWith("02A40400");
    }

    public final String a(String str) {
        return "";
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.ArrayList<com.unionpay.mobile.android.g.c> a(com.unionpay.mobile.android.pboctransaction.e r9) {
        /*
        r8 = this;
        r6 = 0;
        r0 = r8.c;
        if (r0 != 0) goto L_0x0007;
    L_0x0005:
        r0 = r6;
    L_0x0006:
        return r0;
    L_0x0007:
        r7 = new java.util.ArrayList;
        r7.<init>();
        r0 = "325041592e5359532e4444463031";
        r1 = r8.c;	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r0 = com.unionpay.mobile.android.pboctransaction.f.a(r0);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r2 = "00";
        r0 = r1.a(r0, r2);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r0 = com.unionpay.mobile.android.pboctransaction.f.a(r0);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r1 = "4F";
        r0 = com.unionpay.mobile.android.pboctransaction.a.c.a(r0, r1);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r1 = "icfcc";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r3 = "aid =";
        r2.<init>(r3);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r2 = r2.append(r0);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        com.unionpay.mobile.android.utils.k.c(r1, r2);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        if (r0 == 0) goto L_0x0078;
    L_0x003a:
        r8.a = r0;	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r2 = new com.unionpay.mobile.android.pboctransaction.a;	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r1 = "";
        r2.<init>(r0, r1);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r0 = r9.a(r2);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r4 = com.unionpay.mobile.android.pboctransaction.f.c(r0);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        if (r4 == 0) goto L_0x0078;
    L_0x004d:
        r0 = r4.length();	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        if (r0 <= 0) goto L_0x0078;
    L_0x0053:
        r0 = "icfcc";
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r3 = "  ";
        r1.<init>(r3);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r1 = r1.append(r4);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        com.unionpay.mobile.android.utils.k.c(r0, r1);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r0 = new com.unionpay.mobile.android.g.a;	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r1 = 8;
        r2 = r2.a();	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r3 = "";
        r5 = 1;
        r0.<init>(r1, r2, r3, r4, r5);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
        r7.add(r0);	 Catch:{ Exception -> 0x0086, all -> 0x0096 }
    L_0x0078:
        r0 = r8.c;	 Catch:{ RemoteException -> 0x0081 }
        r1 = "00";
        r0.b(r1);	 Catch:{ RemoteException -> 0x0081 }
    L_0x007f:
        r0 = r7;
        goto L_0x0006;
    L_0x0081:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x007f;
    L_0x0086:
        r0 = move-exception;
        r0 = r8.c;	 Catch:{ RemoteException -> 0x0091 }
        r1 = "00";
        r0.b(r1);	 Catch:{ RemoteException -> 0x0091 }
    L_0x008e:
        r0 = r6;
        goto L_0x0006;
    L_0x0091:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x008e;
    L_0x0096:
        r0 = move-exception;
        r1 = r8.c;	 Catch:{ RemoteException -> 0x009f }
        r2 = "00";
        r1.b(r2);	 Catch:{ RemoteException -> 0x009f }
    L_0x009e:
        throw r0;
    L_0x009f:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x009e;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.a.a.a(com.unionpay.mobile.android.pboctransaction.e):java.util.ArrayList<com.unionpay.mobile.android.g.c>");
    }

    public final void a() {
        d();
        if (this.c != null) {
            try {
                this.c.a();
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (Exception e2) {
            }
        }
        if (this.e != null) {
            Intent intent = new Intent("com.unionpay.mobile.tsm.PBOCService");
            this.e.unbindService(this.f);
        }
    }

    public final void a(c cVar, Context context) {
        this.d = cVar;
        this.e = context;
        try {
            Intent intent = new Intent("cn.gov.pbc.tsm.client.mobile.android.bank.service");
            intent.setPackage("cn.gov.pbc.tsm.client.mobile.andorid");
            context.startService(intent);
            if (!context.bindService(intent, this.f, 1) && cVar != null) {
                k.a("icfcc", "startTSMService.initFailed()");
                cVar.b();
            }
        } catch (Exception e) {
            if (cVar != null) {
                k.a("icfcc", "starticfccService exception");
                cVar.b();
            }
        }
    }

    public final byte[] a(byte[] bArr, int i) {
        byte[] bArr2 = null;
        String a = f.a(bArr);
        k.c("icfcc", "====>" + a);
        if (this.c == null) {
            return bArr2;
        }
        if (b(a)) {
            return a(bArr);
        }
        try {
            bArr2 = this.c.b(bArr);
        } catch (RemoteException e) {
        } catch (Exception e2) {
        }
        k.c("icfcc", "<====" + f.a(bArr2));
        return bArr2;
    }

    public final void b() {
    }

    public final void c() {
    }

    public final void d() {
        if (this.c != null) {
            try {
                this.c.b("00");
                this.c.b("01");
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (Exception e2) {
            }
        }
    }
}
