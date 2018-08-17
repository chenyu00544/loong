package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.upviews.a.b;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.upwidget.w;
import com.unionpay.mobile.android.widgets.ay;
import org.json.JSONObject;

public final class af extends b implements b {
    private TextView r;
    private OnClickListener s;
    private a t;
    private int u;

    public af(Context context) {
        super(context);
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = 0;
        this.f = 12;
        this.s = new i(this);
        setBackgroundColor(-1052684);
        e();
    }

    static /* synthetic */ void a(af afVar) {
        afVar.u = 1;
        a.a b = afVar.t.b();
        if (b.a()) {
            afVar.j = false;
            afVar.b.a(c.bD.U);
            afVar.e.n(b.b);
            return;
        }
        afVar.a(b.b);
    }

    public final void a(a.a aVar) {
    }

    public final void a(JSONObject jSONObject) {
        switch (this.u) {
            case 1:
                this.b.c();
                f.c(this.a, jSONObject);
                int b = f.b(this.a, jSONObject);
                if (b != 0) {
                    b(b);
                    return;
                }
                if (this.t != null) {
                    this.t.f();
                }
                d(13);
                return;
            default:
                return;
        }
    }

    public final void a(boolean z) {
        if (this.r != null) {
            this.r.setEnabled(!z);
        }
    }

    protected final void b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        View ayVar = new ay(this.d, c.bD.m, this);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    protected final void c() {
        LayoutParams layoutParams;
        boolean z = true;
        this.o.a((UPScrollView.a) this);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(10, -1);
        layoutParams2.topMargin = com.unionpay.mobile.android.d.a.f;
        this.t = new a(this.d, this.a.X, this, "");
        this.t.setOrientation(1);
        this.t.setId(this.t.hashCode());
        this.m.addView(this.t, layoutParams2);
        View a = w.a(this.d, this.a.Y, this.c.a(1017));
        if (a != null) {
            a.setId(a.hashCode());
            a.a(new j(this, a.a()));
            layoutParams = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams.addRule(3, this.t.getId());
            int i = com.unionpay.mobile.android.d.a.d;
            layoutParams.bottomMargin = i;
            layoutParams.topMargin = i;
            layoutParams.leftMargin = com.unionpay.mobile.android.d.a.d;
            this.m.addView(a, layoutParams);
        }
        this.r = new TextView(this.d);
        this.r.setText(c.bD.n);
        this.r.setTextSize(com.unionpay.mobile.android.d.b.i);
        this.r.setTextColor(b.p());
        this.r.setGravity(17);
        TextView textView = this.r;
        if (!(this.t == null || this.t.e())) {
            z = false;
        }
        textView.setEnabled(z);
        int i2 = com.unionpay.mobile.android.d.a.n;
        this.r.setBackgroundDrawable(this.c.a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
        this.r.setOnClickListener(this.s);
        layoutParams = new RelativeLayout.LayoutParams(-1, i2);
        layoutParams.addRule(3, a != null ? a.getId() : this.t.getId());
        layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
        this.m.addView(this.r, layoutParams);
    }

    public final void c(String str) {
    }

    public final void c(String str, String str2) {
    }

    public final void u() {
    }
}
