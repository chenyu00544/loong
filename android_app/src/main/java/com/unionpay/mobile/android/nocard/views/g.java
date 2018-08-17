package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.g.e;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.upviews.a.b;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.upwidget.a;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.p;
import com.unionpay.mobile.android.widgets.ay;
import org.json.JSONObject;

public final class g extends b implements b {
    private int r;
    private int s;
    private a t;
    private TextView u;
    private com.unionpay.mobile.android.upviews.a v;
    private boolean w;
    private boolean x;
    private OnClickListener y;
    private OnClickListener z;

    public g(Context context) {
        boolean z = true;
        super(context);
        this.r = 20;
        this.s = 100;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = false;
        this.x = true;
        this.y = new as(this);
        this.z = new au(this);
        this.f = 5;
        if (this.a.K) {
            this.q = "entrust_phoneNO_change";
        } else {
            this.q = "entrust";
        }
        if (this.a.K) {
            z = false;
        }
        this.x = z;
        setBackgroundColor(-1052684);
        e();
    }

    private void d(JSONObject jSONObject) {
        j();
        this.a.z = j.d(jSONObject, "rules");
        this.a.A = j.a(jSONObject, "title");
        this.a.C = j.c(jSONObject, "followrules_button");
        this.a.al = j.c(jSONObject, "service_checkbox");
        this.a.am = j.c(jSONObject, "bind_card_checkbox");
        this.a.aq = j.a(jSONObject, "pan");
        if (this.a.z == null || this.a.z.length() <= 0) {
            b(2);
            return;
        }
        if (this.v != null) {
            this.v.f();
        }
        e a = f.a(jSONObject);
        this.a.K = false;
        a(6, a);
    }

    private void v() {
        this.e.k(this.v.a("pan"));
        this.s = 104;
    }

    private void w() {
        this.s = 103;
        int i = this.r;
        this.e.a("query", this.a.aj, 3);
        this.r--;
    }

    public final void a(com.unionpay.mobile.android.upviews.a.a aVar) {
        if (aVar.a()) {
            this.j = false;
            this.s = 101;
            this.b.a(c.bD.U);
            k.a("uppay", "sms elements:" + aVar.b);
            this.e.c("sms", aVar.b);
            return;
        }
        a(aVar.b);
    }

    public final void a(JSONObject jSONObject) {
        this.x = false;
        switch (this.s) {
            case 101:
                this.v.a(com.unionpay.mobile.android.d.b.p);
                this.b.c();
                this.s = 100;
                return;
            case 102:
                this.a.aj = i.a(jSONObject.toString());
                if (this.a.aj == null || this.a.aj.length() <= 0) {
                    b(2);
                    return;
                }
                this.r = 20;
                w();
                return;
            case 103:
                String a = j.a(jSONObject, "status");
                String a2 = j.a(jSONObject, "fail_msg");
                if (this.r <= 0 || !a.equalsIgnoreCase("01")) {
                    this.s = 100;
                    if (a.equalsIgnoreCase("00")) {
                        this.w = true;
                        b.a(this.d, this.q + "_open_succeed");
                        v();
                        return;
                    }
                    j();
                    b.a(this.d, this.q + "_open_fail", p.j, (Object[]) new String[]{a, a2});
                    if (a.equalsIgnoreCase("03")) {
                        OnClickListener avVar = new av(this);
                        OnClickListener awVar = new aw(this);
                        if (this.a.F) {
                            this.b.a(avVar, awVar);
                            this.b.a(c.bD.ab, a2, c.bD.ac, c.bD.ad);
                            return;
                        }
                        this.b.a(avVar, null);
                        this.b.a(c.bD.ab, a2, c.bD.ac);
                        return;
                    } else if (this.r <= 0) {
                        a(this.a.ak);
                        return;
                    } else {
                        return;
                    }
                }
                w();
                return;
            case 104:
                if (!b(jSONObject)) {
                    d(jSONObject);
                    return;
                }
                return;
            case 105:
                j();
                int a3 = f.a(this.a, jSONObject, false);
                if (a3 != 0) {
                    b(a3);
                    return;
                }
                e a4 = f.a(jSONObject);
                if (this.a.z != null && this.a.z.length() > 0) {
                    a(6, a4);
                    return;
                } else if (this.a.D != null && this.a.D.length() > 0) {
                    d(5);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }

    public final void a(boolean z) {
        if (this.u != null) {
            this.u.setEnabled(!z);
        }
    }

    protected final boolean a(String str, JSONObject jSONObject) {
        this.x = false;
        return false;
    }

    protected final void b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        View ayVar = new ay(getContext(), this.a.A, this);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    protected final void b(String str, JSONObject jSONObject) {
        if ("init".equals(str)) {
            a(2);
        } else if ("".equals(str)) {
            d(jSONObject);
        } else {
            this.b.a(c.bD.U);
            this.j = false;
            this.s = 105;
            this.e.c(str, "");
        }
    }

    protected final void c() {
        boolean z = true;
        this.o.a((UPScrollView.a) this);
        View linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        linearLayout.setId(linearLayout.hashCode());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        int i = com.unionpay.mobile.android.d.a.d;
        this.m.addView(linearLayout, layoutParams);
        this.v = new com.unionpay.mobile.android.upviews.a(this.d, this.a.D, this.e.c(), this, this.a.aq, true, this.q);
        layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
        linearLayout.addView(this.v, layoutParams);
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = i;
        layoutParams.leftMargin = com.unionpay.mobile.android.d.a.f;
        layoutParams.addRule(3, linearLayout.getId());
        View linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(0);
        linearLayout2.setId(linearLayout2.hashCode());
        this.t = new a(this.d, this.a.al, this.z, this.q + "_agree_user_protocol");
        linearLayout2.addView(this.t, new LinearLayout.LayoutParams(-2, -2));
        this.m.addView(linearLayout2, layoutParams);
        this.u = new TextView(this.d);
        this.u.setText(j.a(this.a.C, "label"));
        this.u.setTextSize(com.unionpay.mobile.android.d.b.i);
        this.u.setTextColor(b.p());
        this.u.setGravity(17);
        int i2 = com.unionpay.mobile.android.d.a.n;
        this.u.setBackgroundDrawable(this.c.a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
        this.u.setOnClickListener(this.y);
        TextView textView = this.u;
        if (!(this.v == null || this.v.e())) {
            z = false;
        }
        textView.setEnabled(z);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, i2);
        layoutParams2.topMargin = com.unionpay.mobile.android.d.a.f;
        i2 = com.unionpay.mobile.android.utils.g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
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
            if (this.a.L) {
                a(13);
                this.a.L = false;
            } else if (this.a.K && this.x) {
                this.a.K = false;
                f.a(this.a, this.a.G);
                n();
            } else {
                this.a.K = false;
                this.a.G = null;
                a(2);
            }
        }
    }

    public final void u() {
    }
}
