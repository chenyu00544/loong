package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.upviews.a.b;
import com.unionpay.mobile.android.upwidget.a;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.ay;
import org.json.JSONObject;

public final class ak extends b implements b {
    private int r;
    private int s;
    private a t;
    private TextView u;
    private com.unionpay.mobile.android.upviews.a v;
    private OnClickListener w;
    private OnClickListener x;

    public ak(Context context) {
        super(context);
        this.r = 100;
        this.s = 20;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = new m(this);
        this.x = new n(this);
        this.f = 10;
        this.q = "openupgrade";
        setBackgroundColor(-1052684);
        this.k = a();
        b();
        super.d();
        c();
    }

    private void v() {
        this.r = 103;
        k.c("uppay", this.s);
        int i = this.s;
        this.e.a("query", this.a.aj, 3);
        this.s--;
    }

    public final void a(com.unionpay.mobile.android.upviews.a.a aVar) {
        if (aVar.a()) {
            this.j = false;
            this.r = 101;
            this.b.a(c.bD.U);
            k.a("uppay", "sms elements:" + aVar.b);
            this.e.c("sms", aVar.b);
            return;
        }
        a(aVar.b);
    }

    public final void a(JSONObject jSONObject) {
        switch (this.r) {
            case 101:
                this.v.a(com.unionpay.mobile.android.d.b.p);
                this.b.c();
                this.r = 100;
                return;
            case 102:
                this.a.aj = i.a(jSONObject.toString());
                if (this.a.aj == null || this.a.aj.length() <= 0) {
                    b(2);
                    return;
                }
                this.s = 20;
                v();
                return;
            case 103:
                String a = j.a(jSONObject, "status");
                String a2 = j.a(jSONObject, "fail_msg");
                this.a.S = j.a(jSONObject, "open_info");
                this.a.A = j.a(jSONObject, "title");
                this.a.B = j.a(jSONObject, "succ_info");
                if (this.s <= 0 || !a.equalsIgnoreCase("01")) {
                    this.r = 100;
                    j();
                    if (a.equalsIgnoreCase("00")) {
                        a = this.a.S;
                        d(11);
                        return;
                    } else if (a.equalsIgnoreCase("03")) {
                        this.b.a(new p(this), null);
                        this.b.a(c.bD.ab, a2, c.bD.ac);
                        return;
                    } else if (this.s <= 0) {
                        b(20);
                        return;
                    } else {
                        return;
                    }
                }
                v();
                return;
            default:
                return;
        }
    }

    public final void a(boolean z) {
        if (this.u != null) {
            this.u.setEnabled(!z);
        }
    }

    protected final void b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        View ayVar = new ay(getContext(), this.a.A, this);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    protected final void c() {
        boolean z = true;
        View linearLayout = new LinearLayout(this.d);
        linearLayout.setBackgroundColor(-1);
        linearLayout.setOrientation(1);
        linearLayout.setId(linearLayout.hashCode());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        int i = com.unionpay.mobile.android.d.a.f;
        layoutParams.rightMargin = i;
        layoutParams.leftMargin = i;
        this.m.addView(linearLayout, layoutParams);
        this.v = new com.unionpay.mobile.android.upviews.a(this.d, this.a.T, this.e.c(), this, this.a.aq, true, this.q);
        new LinearLayout.LayoutParams(-1, -1).topMargin = com.unionpay.mobile.android.d.a.f;
        linearLayout.addView(this.v);
        View linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(1);
        linearLayout2.setId(linearLayout2.hashCode());
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = com.unionpay.mobile.android.d.a.d;
        layoutParams2.leftMargin = com.unionpay.mobile.android.d.a.d;
        layoutParams2.addRule(3, linearLayout.getId());
        this.m.addView(linearLayout2, layoutParams2);
        this.t = new a(this.d, this.a.al, this.x, this.q + "_agree_user_protocol");
        linearLayout2.addView(this.t);
        this.u = new TextView(this.d);
        this.u.setText(j.a(this.a.C, "label"));
        this.u.setTextSize(com.unionpay.mobile.android.d.b.i);
        this.u.setTextColor(b.p());
        this.u.setGravity(17);
        int i2 = com.unionpay.mobile.android.d.a.n;
        this.u.setBackgroundDrawable(this.c.a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
        this.u.setOnClickListener(this.w);
        TextView textView = this.u;
        if (!(this.v == null || this.v.e())) {
            z = false;
        }
        textView.setEnabled(z);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, i2);
        layoutParams2.topMargin = com.unionpay.mobile.android.d.a.d;
        i2 = com.unionpay.mobile.android.d.a.d;
        layoutParams2.rightMargin = i2;
        layoutParams2.leftMargin = i2;
        layoutParams2.addRule(3, linearLayout2.getId());
        this.m.addView(this.u, layoutParams2);
    }

    public final void c(String str) {
    }

    public final void c(String str, String str2) {
    }

    public final void l() {
        if (!this.v.d()) {
            n();
        }
    }

    public final void u() {
    }
}
