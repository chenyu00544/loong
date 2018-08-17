package com.unionpay.mobile.android.i;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.nocard.views.l;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.an;
import java.util.ArrayList;

public abstract class a extends Activity implements b, c {
    public static String[][] c;
    public static IntentFilter[] d;
    private static int h = 0;
    protected ArrayList<b> a = null;
    protected NfcAdapter b;
    private l e = null;
    private a f = null;
    private an g = null;
    private PendingIntent i;

    private class a {
        public com.unionpay.mobile.android.g.b a;
        public UPPayEngine b;
        final /* synthetic */ a c;

        public a(a aVar, UPPayEngine uPPayEngine) {
            this.c = aVar;
            this.a = null;
            this.b = null;
            this.a = new com.unionpay.mobile.android.g.b();
            this.b = uPPayEngine;
            this.b.a(this.a);
        }
    }

    static {
        try {
            r0 = new String[3][];
            r0[0] = new String[]{IsoDep.class.getName()};
            r0[1] = new String[]{NfcV.class.getName()};
            r0[2] = new String[]{NfcF.class.getName()};
            c = r0;
            d = new IntentFilter[]{new IntentFilter("android.nfc.action.TECH_DISCOVERED", "*/*")};
        } catch (Exception e) {
        }
    }

    public Object a(String str) {
        return str == null ? this.f.a : str.equalsIgnoreCase(UPPayEngine.class.toString()) ? this.f.b : str.equalsIgnoreCase(an.class.toString()) ? this.g : null;
    }

    public final void a(int i) {
        if (this.a != null) {
            int size = this.a.size() - 1;
            this.a.get(size);
            for (int i2 = size; i2 >= 0; i2--) {
                b bVar = (b) this.a.get(i2);
                if (bVar.h() == i) {
                    bVar.s();
                    setContentView(bVar);
                    return;
                }
                if (i2 == size) {
                    bVar.t();
                }
                this.a.remove(i2);
            }
        }
    }

    public final void a(b bVar) {
        if (this.a != null) {
            int size = this.a.size();
            if (size > 0) {
                ((b) this.a.get(size - 1)).t();
            }
            bVar.s();
            this.a.add(bVar);
            setContentView(bVar);
        }
    }

    public boolean a() {
        return false;
    }

    public final void b() {
        if (this.a != null) {
            int size = this.a.size();
            if (size > 0) {
                this.a.get(size - 1);
                ((b) this.a.get(size - 1)).t();
                this.a.remove(size - 1);
                if (this.a.size() != 0) {
                    ((b) this.a.get(this.a.size() - 1)).s();
                    setContentView((View) this.a.get(this.a.size() - 1));
                }
            }
        }
    }

    public final String c() {
        return this.f.a.a;
    }

    public Resources getResources() {
        Resources resources = super.getResources();
        Configuration configuration = new Configuration();
        configuration.setToDefaults();
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        return resources;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.e != null) {
            this.e.y();
        }
        super.onActivityResult(i, i2, intent);
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        k.a("uppay", "PayActivityEx.onCreate() +++");
        c.a();
        com.unionpay.mobile.android.d.a.a(this);
        this.a = new ArrayList(1);
        this.f = new a(this, d());
        this.g = new an(this);
        com.unionpay.c.a.a = false;
        requestWindowFeature(1);
        super.onCreate(bundle);
        this.e = (l) a(1, null);
        setContentView(this.e);
        getWindow().addFlags(8192);
        h++;
        k.a("uppay", "PayActivityEx.onCreate() ---");
        if (a()) {
            this.b = NfcAdapter.getDefaultAdapter(this);
            this.i = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(536870912), 0);
            onNewIntent(getIntent());
        }
    }

    protected void onDestroy() {
        if (this.a != null) {
            this.a.clear();
        }
        if (this.e != null) {
            this.e.B();
        }
        this.e = null;
        com.unionpay.mobile.android.g.b.bl = false;
        com.unionpay.mobile.android.g.b.bb = null;
        com.unionpay.mobile.android.g.b.bm = false;
        int i = h - 1;
        h = i;
        if (i == 0) {
            com.unionpay.mobile.android.j.c.a((Context) this).a();
        }
        this.g.c();
        this.g = null;
        this.f.b = null;
        this.f.a = null;
        this.f = null;
        ((ViewGroup) getWindow().getDecorView().findViewById(16908290)).removeAllViews();
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        if (this.a != null && this.a.size() > 0) {
            ((b) this.a.get(this.a.size() - 1)).l();
        }
        return true;
    }

    protected void onPause() {
        super.onPause();
        if (a() && this.b != null) {
            this.b.disableForegroundDispatch(this);
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.g.a()) {
            this.g.b();
        }
        if (a() && this.b != null) {
            this.b.enableForegroundDispatch(this, this.i, d, c);
        }
    }
}
