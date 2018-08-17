package com.unionpay.uppay;

import android.content.Intent;
import android.nfc.NfcManager;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import com.unionpay.mobile.android.e.f;
import com.unionpay.mobile.android.g.e;
import com.unionpay.mobile.android.i.a;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.views.l;
import com.unionpay.mobile.android.pro.a.a.b;
import com.unionpay.mobile.android.pro.views.j;
import com.unionpay.mobile.android.pro.views.k;
import com.unionpay.mobile.android.pro.views.v;
import com.unionpay.mobile.android.pro.views.x;
import com.unionpay.mobile.android.utils.o;

public final class PayActivity extends a {
    public static String e = "";
    private b f = null;
    private f g = null;
    private o h;
    private k i = null;

    static {
        System.loadLibrary("entryexpro");
    }

    public final com.unionpay.mobile.android.nocard.views.b a(int i, e eVar) {
        com.unionpay.mobile.android.nocard.views.b jVar;
        switch (i) {
            case 1:
                jVar = new j(this);
                ((l) jVar).a(a());
                return jVar;
            case 2:
                return new v(this, eVar);
            case 6:
                return new x(this, eVar);
            case 17:
                jVar = new k(this, eVar, (UPPayEngine) a(UPPayEngine.class.toString()));
                this.i = jVar;
                this.i.r = c();
                this.i.a(this.b);
                return jVar;
            case 18:
                return new com.unionpay.mobile.android.pro.views.a(this, eVar, (UPPayEngine) a(UPPayEngine.class.toString()));
            default:
                return null;
        }
    }

    public final Object a(String str) {
        if (b.class.toString().equalsIgnoreCase(str)) {
            if (this.f == null) {
                this.f = new b(this, c());
            }
            return this.f;
        } else if (!f.class.toString().equalsIgnoreCase(str)) {
            return super.a(str);
        } else {
            if (this.g == null) {
                this.g = new f(this);
            }
            return this.g;
        }
    }

    public final boolean a() {
        if (VERSION.SDK_INT < 10) {
            return false;
        }
        return (getPackageManager().checkPermission("android.permission.NFC", com.unionpay.mobile.android.utils.f.b(this)) == 0 ? 1 : null) == null ? false : ((NfcManager) getSystemService("nfc")).getDefaultAdapter() != null;
    }

    public final UPPayEngine d() {
        this.h = new o(this);
        return this.h;
    }

    protected final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.i != null && this.i.getParent() != null) {
            this.i.a(this.b);
        }
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    protected final void onDestroy() {
        super.onDestroy();
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
        this.h.h();
        this.h = null;
        this.i = null;
        e = "";
    }

    protected final void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Parcelable parcelableExtra = intent.getParcelableExtra("android.nfc.extra.TAG");
        if (parcelableExtra != null) {
            Log.d("NFCTAG", intent.getAction());
            com.unionpay.mobile.android.pboctransaction.b.b.b bVar = new com.unionpay.mobile.android.pboctransaction.b.b.b(IsoDep.get((Tag) parcelableExtra));
            bVar.a();
            com.unionpay.mobile.android.pboctransaction.b.a aVar = new com.unionpay.mobile.android.pboctransaction.b.a((com.unionpay.mobile.android.c.a) a(UPPayEngine.class.toString()), bVar);
            if (this.i != null && this.i.getParent() != null) {
                this.i.a(aVar);
            }
        }
    }

    protected final void onResume() {
        super.onResume();
        if (this.i != null && this.i.getParent() != null) {
            this.i.a(this.b);
        }
    }
}
