package com.unionpay.tsmservice;

import android.content.Context;
import android.os.RemoteException;
import com.unionpay.tsmservice.b.a;
import com.unionpay.tsmservice.b.aa;
import com.unionpay.tsmservice.b.ac;
import com.unionpay.tsmservice.b.ae;
import com.unionpay.tsmservice.b.ag;
import com.unionpay.tsmservice.b.ai;
import com.unionpay.tsmservice.b.ak;
import com.unionpay.tsmservice.b.am;
import com.unionpay.tsmservice.b.ao;
import com.unionpay.tsmservice.b.aq;
import com.unionpay.tsmservice.b.as;
import com.unionpay.tsmservice.b.aw;
import com.unionpay.tsmservice.b.ay;
import com.unionpay.tsmservice.b.ba;
import com.unionpay.tsmservice.b.bc;
import com.unionpay.tsmservice.b.be;
import com.unionpay.tsmservice.b.bg;
import com.unionpay.tsmservice.b.bi;
import com.unionpay.tsmservice.b.bk;
import com.unionpay.tsmservice.b.bm;
import com.unionpay.tsmservice.b.bo;
import com.unionpay.tsmservice.b.bq;
import com.unionpay.tsmservice.b.bs;
import com.unionpay.tsmservice.b.bu;
import com.unionpay.tsmservice.b.bw;
import com.unionpay.tsmservice.b.by;
import com.unionpay.tsmservice.b.c;
import com.unionpay.tsmservice.b.ca;
import com.unionpay.tsmservice.b.cc;
import com.unionpay.tsmservice.b.e;
import com.unionpay.tsmservice.b.g;
import com.unionpay.tsmservice.b.i;
import com.unionpay.tsmservice.b.k;
import com.unionpay.tsmservice.b.m;
import com.unionpay.tsmservice.b.o;
import com.unionpay.tsmservice.b.q;
import com.unionpay.tsmservice.b.s;
import com.unionpay.tsmservice.b.w;
import com.unionpay.tsmservice.b.y;
import com.unionpay.tsmservice.utils.IUPJniInterface;

public class h {
    private i a;
    private int b;
    private bu c;
    private d d;
    private e e;
    private int f;
    private g g;
    private Context h;
    private int i;

    public h(i iVar, int i, bu buVar, d dVar) {
        this(iVar, i, buVar, dVar, null);
    }

    public h(i iVar, int i, bu buVar, d dVar, e eVar) {
        this(iVar, i, buVar, dVar, null, 1000);
    }

    public h(i iVar, int i, bu buVar, d dVar, e eVar, int i2) {
        this.b = -1;
        this.f = 1000;
        this.a = iVar;
        this.b = i;
        this.c = buVar;
        this.d = dVar;
        this.e = eVar;
        this.f = i2;
    }

    public h(i iVar, int i, bw bwVar, int i2, g gVar, Context context) {
        this.b = -1;
        this.f = 1000;
        this.a = iVar;
        this.b = i;
        this.i = i2;
        this.c = bwVar;
        this.g = gVar;
        this.h = context;
    }

    public int a() throws RemoteException {
        String[] strArr = new String[1];
        int a = this.a.a(1000, strArr);
        if (a != 0) {
            return a;
        }
        a = this.a.a(IUPJniInterface.rER(strArr[0], IUPJniInterface.mSK()), strArr);
        if (a != 0) {
            return a;
        }
        String dMG = IUPJniInterface.dMG(strArr[0], this.a.c());
        IUPJniInterface.sSK(dMG);
        Context a2 = this.a.a();
        if (a2 != null) {
            IUPJniInterface.uSKT(a2.getPackageName(), dMG);
        }
        switch (this.b) {
            case 0:
                return this.a.a((bo) this.c, this.d);
            case 1:
                return this.a.a((ao) this.c, this.d);
            case 2:
                return this.a.a((ak) this.c, this.d);
            case 3:
                return this.a.a((bc) this.c, this.d);
            case 4:
                return this.a.a((ai) this.c, this.d);
            case 5:
                return this.a.a((am) this.c, this.d);
            case 6:
                return this.a.a((as) this.c, this.d);
            case 7:
                return this.a.a((ag) this.c, this.d);
            case 8:
                return this.a.a((ae) this.c, this.d);
            case 9:
                return this.a.a((bg) this.c, this.d);
            case 10:
                return this.a.a((bi) this.c, this.d);
            case 11:
                return this.a.a((ba) this.c, this.d);
            case 12:
                return this.a.a((be) this.c, this.d);
            case 13:
                return this.a.a((aw) this.c, this.d);
            case 14:
                return this.a.a((ca) this.c, this.d);
            case 15:
                return this.a.a((i) this.c, this.d);
            case 16:
                return this.a.a((k) this.c, this.d, this.e);
            case 17:
                return this.a.a((g) this.c, this.d, this.e);
            case 18:
                return this.a.a((e) this.c, this.d, this.e);
            case 19:
                return this.a.a((y) this.c, this.d);
            case 20:
                return this.a.a((bs) this.c, this.d);
            case 21:
                return this.a.a((w) this.c, this.d);
            case 22:
                return this.a.a((by) this.c, this.d);
            case 23:
                return this.a.a((aa) this.c, this.d);
            case 24:
                return this.a.a((bm) this.c, this.d);
            case 25:
                return this.a.a((ac) this.c, this.d, this.e);
            case 26:
                return this.a.a((m) this.c, this.d);
            case 27:
                return this.a.a((o) this.c, this.d);
            case 28:
                return this.a.a((aq) this.c, this.d);
            case 29:
                return this.a.a((s) this.c, this.d);
            case 30:
                return this.a.a((cc) this.c, this.d);
            case 31:
                return this.a.a((ay) this.c, this.d);
            case 32:
                return this.a.a((bw) this.c);
            case 33:
                return this.a.a(this.i);
            case 34:
                return this.a.f();
            case 35:
                return this.a.a((q) this.c, this.d);
            case 36:
                return this.a.a((bk) this.c, this.d);
            case 37:
                return this.a.a((a) this.c, this.d);
            case 38:
                return this.a.a((c) this.c, this.d, this.e);
            case 39:
                return this.a.a((bq) this.c, this.d);
            case 1000:
                return this.a.a((bw) this.c, this.i, this.g, this.h);
            default:
                return 0;
        }
    }
}
