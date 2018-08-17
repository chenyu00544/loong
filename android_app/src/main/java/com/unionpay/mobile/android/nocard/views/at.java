package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.text.TextUtils;
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
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.upviews.a.b;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.p;
import com.unionpay.mobile.android.widgets.aj;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.mobile.android.widgets.z;
import org.json.JSONArray;
import org.json.JSONObject;

public final class at extends b implements b {
    private a A;
    private a B;
    private boolean C;
    private boolean D;
    private String E;
    private OnClickListener F;
    private OnClickListener G;
    private OnClickListener H;
    private OnClickListener I;
    private OnClickListener J;
    LinearLayout r;
    private String s;
    private int t;
    private int u;
    private int v;
    private int w;
    private com.unionpay.mobile.android.upwidget.a x;
    private com.unionpay.mobile.android.upwidget.a y;
    private TextView z;

    public at(Context context) {
        this(context, null);
    }

    public at(Context context, e eVar) {
        boolean z = false;
        super(context, eVar);
        this.s = "00";
        this.t = 0;
        this.u = 0;
        this.v = 20;
        this.w = 5;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = false;
        this.D = false;
        this.r = new LinearLayout(this.d);
        this.F = new u(this);
        this.G = new v(this);
        this.H = new w(this);
        this.I = new x(this);
        this.J = new y(this);
        this.f = 6;
        if (this.a.K) {
            this.q = "bankpay_phoneNO_change";
        } else {
            this.q = "bankpay";
        }
        if (!this.a.K) {
            z = true;
        }
        this.D = z;
        setBackgroundColor(-1052684);
        e();
    }

    static /* synthetic */ void a(at atVar, String str, String str2) {
        atVar.u = 8;
        atVar.b.a(c.bD.U);
        atVar.e.c(str, str2);
    }

    static /* synthetic */ void a(at atVar, boolean z, String str) {
        atVar.j = false;
        if (z) {
            atVar.e(str);
            return;
        }
        atVar.u = 2;
        atVar.e.c(atVar.a.E, str);
    }

    private void d(String str) {
        a(str, new z(this), new aa(this));
    }

    private void d(String str, String str2) {
        this.u = 9;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.w--;
    }

    private static boolean d(JSONObject jSONObject) {
        return "0".equalsIgnoreCase(j.a(jSONObject, "reopen_flag"));
    }

    private void e(String str) {
        this.j = false;
        this.u = 3;
        this.e.c(ah.a(this.a.C), ah.c("1", "1", this.x != null ? this.x.a() : null, str));
    }

    private void f(int i) {
        this.u = 4;
        this.t = i;
        int i2 = this.v;
        this.e.a("query", this.a.aj, 3);
        this.v--;
    }

    static /* synthetic */ void g(at atVar) {
        atVar.a.K = true;
        atVar.b.a(c.bD.U);
        atVar.j = false;
        atVar.u = 7;
        atVar.e.c("reopenrules", "");
    }

    private String v() {
        a.a b;
        String str = "";
        if (this.B != null) {
            b = this.B.b();
            if (b.a()) {
                str = str + b.b;
            }
        }
        if (this.A == null) {
            return str;
        }
        b = this.A.b();
        if (!b.a()) {
            return str;
        }
        Object obj = b.b;
        if (TextUtils.isEmpty(obj)) {
            return str;
        }
        if (!TextUtils.isEmpty(str)) {
            str = str + ",";
        }
        return str + obj;
    }

    public final void a(a.a aVar) {
        if (aVar.a()) {
            k.a("uppay", "sms elements:" + aVar.b);
            this.j = false;
            this.b.a(c.bD.U);
            this.e.c("sms", aVar.b);
            this.u = 1;
            return;
        }
        a(aVar.b);
    }

    public final void a(JSONObject jSONObject) {
        this.D = false;
        String a;
        int a2;
        e a3;
        JSONArray d;
        switch (this.u) {
            case 1:
                j();
                this.u = 0;
                this.B.a(com.unionpay.mobile.android.d.b.p);
                return;
            case 2:
            case 3:
                this.a.aj = i.a(jSONObject.toString());
                a = j.a(jSONObject, "qn");
                if (!TextUtils.isEmpty(a)) {
                    this.a.n = this.e.i(com.unionpay.mobile.android.utils.c.b(a));
                }
                if (this.a.aj == null || this.a.aj.length() <= 0) {
                    b(2);
                    return;
                }
                this.v = 20;
                f(this.u);
                return;
            case 4:
                this.s = j.a(jSONObject, "status");
                if (d(jSONObject)) {
                    d(j.a(jSONObject, "fail_msg"));
                    return;
                } else if (this.v <= 0 || !this.s.equalsIgnoreCase("01")) {
                    this.u = 0;
                    if (this.s.equalsIgnoreCase("00")) {
                        switch (this.t) {
                            case 2:
                                this.C = true;
                                e(v());
                                return;
                            default:
                                j();
                                this.u = 0;
                                this.a.H = j.d(jSONObject, "result");
                                this.a.P = j.a(jSONObject, "openupgrade_flag");
                                this.a.Q = j.a(jSONObject, "temporary_pay_flag");
                                this.a.R = j.a(jSONObject, "temporary_pay_info");
                                this.a.V = j.a(jSONObject, "front_url");
                                this.a.W = j.a(jSONObject, "front_request");
                                this.a.A = j.a(jSONObject, "title");
                                this.a.B = j.a(jSONObject, "succ_info");
                                com.unionpay.mobile.android.nocard.utils.b.b(jSONObject, this.a);
                                com.unionpay.mobile.android.nocard.utils.b.a(jSONObject, this.a);
                                if (this.B != null) {
                                    this.B.f();
                                }
                                b.a(this.d, this.q + "_succeed");
                                if (this.a.f) {
                                    PreferenceUtils.c(this.d, this.a.aQ);
                                    this.a.I.f = "success";
                                    k();
                                    return;
                                }
                                d(8);
                                return;
                        }
                    } else if (this.s.equalsIgnoreCase("03")) {
                        a = j.a(jSONObject, "fail_msg");
                        b.a(this.d, this.q + "_fail", p.j, (Object[]) new String[]{this.s, a});
                        if (this.t == 3) {
                            a(a);
                            return;
                        }
                        OnClickListener abVar = new ab(this);
                        OnClickListener acVar = new ac(this);
                        if (this.a.F) {
                            this.b.a(abVar, acVar);
                            this.b.a(c.bD.ab, a, c.bD.ac, c.bD.ad);
                            return;
                        }
                        this.b.a(abVar, null);
                        this.b.a(c.bD.ab, a, c.bD.ac);
                        return;
                    } else if (this.v <= 0) {
                        a = c(19);
                        if (!(this.a.ak == null || TextUtils.isEmpty(this.a.ak))) {
                            a = this.a.ak;
                        }
                        if (this.t == 3) {
                            a(a, true);
                            return;
                        } else {
                            a(a);
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    f(this.t);
                    return;
                }
            case 6:
                j();
                a2 = f.a(this.a, jSONObject, true);
                if (a2 != 0) {
                    b(a2);
                } else {
                    this.a.K = true;
                    a3 = f.a(jSONObject);
                    if (this.a.z != null && this.a.z.length() > 0) {
                        a(6, a3);
                    } else if (this.a.D != null && this.a.D.length() > 0) {
                        d(5);
                    }
                }
                this.u = 0;
                return;
            case 7:
                j();
                a2 = f.a(this.a, jSONObject, false);
                if (a2 != 0) {
                    b(a2);
                    return;
                }
                a3 = f.a(jSONObject);
                if (this.a.z != null && this.a.z.length() > 0) {
                    a(6, a3);
                    return;
                } else if (this.a.D != null && this.a.D.length() > 0) {
                    d(5);
                    return;
                } else {
                    return;
                }
            case 8:
                j();
                d = j.d(jSONObject, "options");
                if (this.A != null) {
                    this.A.a(d);
                    return;
                }
                return;
            case 9:
                a = j.a(jSONObject, "status");
                if (a == null || !"01".equals(a)) {
                    d = j.d(jSONObject, "options");
                    String a4 = j.a(jSONObject, "empty_info");
                    if (this.A != null) {
                        this.A.a(d, a4);
                        return;
                    }
                    return;
                }
                a = j.a(jSONObject, "uuid");
                if (this.w >= 0) {
                    d(this.E, a);
                    return;
                }
                a = c.bD.D;
                if (this.A != null) {
                    this.A.a(null, a);
                    return;
                }
                return;
            case 16:
                if (this.b.a()) {
                    this.b.c();
                }
                JSONObject jSONObject2 = new JSONObject();
                if (TextUtils.isEmpty(j.a(jSONObject, "instalment_empty_info"))) {
                    jSONObject = j.c(jSONObject, "instalment");
                }
                this.A.a(jSONObject);
                this.u = 0;
                return;
            default:
                return;
        }
    }

    public final void a(boolean z) {
        if (this.z != null) {
            this.z.setEnabled(!z);
        }
    }

    protected final boolean a(String str, JSONObject jSONObject) {
        this.D = false;
        if (this.u != 1 || !d(jSONObject)) {
            return false;
        }
        d(str);
        return true;
    }

    protected final void b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        View ayVar = new ay(getContext(), this.a.A, this);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    public final void b(int i) {
        if (this.u == 16) {
            if (this.b != null) {
                this.b.c();
            }
            z c = this.A.c("instalment");
            if (c != null) {
                ((com.unionpay.mobile.android.widgets.p) c).a(false);
                ((com.unionpay.mobile.android.widgets.p) c).b(false);
            }
        }
        super.b(i);
    }

    protected final void c() {
        LayoutParams layoutParams;
        this.m.removeAllViews();
        this.o.a((UPScrollView.a) this);
        this.r.setId(this.r.hashCode());
        this.r.setOrientation(1);
        this.r.setBackgroundColor(0);
        this.m.addView(this.r, new RelativeLayout.LayoutParams(-1, -2));
        LinearLayout linearLayout = this.r;
        linearLayout.removeAllViews();
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            com.unionpay.mobile.android.g.f fVar = (com.unionpay.mobile.android.g.f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.a.aU = fVar.a("promotion_instalment_msgbox");
        }
        if (jSONArray.length() > 0) {
            this.A = new a(this.d, jSONArray, this, this.q);
            this.A.a(this.b, this.a.aU);
            this.A.d(this.a.bt);
            this.A.a(this.G);
            this.A.b(this.H);
            this.A.c(this.I);
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
            linearLayout.addView(this.A, layoutParams);
        }
        z zVar = null;
        if (this.A != null) {
            zVar = this.A.c("instalment");
        }
        this.B = new a(this.d, this.a.z, this.e.c(), this, this.a.aq, true, false, zVar, this.a.ad, this.q);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
        linearLayout.addView(this.B, layoutParams);
        View linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(1);
        linearLayout2.setId(linearLayout2.hashCode());
        layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(3, this.r.getId());
        layoutParams.leftMargin = com.unionpay.mobile.android.d.a.f;
        layoutParams.topMargin = layoutParams.leftMargin;
        this.m.addView(linearLayout2, layoutParams);
        if (!(!i() && this.a.al == null && this.a.am == null)) {
            if (this.a.al != null) {
                this.y = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.al, this.J, this.q + "_agree_user_protocol");
                linearLayout2.addView(this.y);
            }
            if (this.a.am != null) {
                this.x = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.am, null, this.q + "_remember_bankNO");
                layoutParams = new LinearLayout.LayoutParams(-2, -2);
                layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
                linearLayout2.addView(this.x, layoutParams);
            }
        }
        this.z = new TextView(this.d);
        this.z.setText(j.a(this.a.C, "label"));
        this.z.setTextSize(com.unionpay.mobile.android.d.b.i);
        this.z.setTextColor(b.p());
        this.z.setGravity(17);
        TextView textView = this.z;
        boolean z = this.B == null || this.B.e();
        textView.setEnabled(z);
        int i = com.unionpay.mobile.android.d.a.n;
        this.z.setBackgroundDrawable(this.c.a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
        this.z.setOnClickListener(this.F);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, i);
        layoutParams2.addRule(3, linearLayout2.getId());
        layoutParams2.topMargin = com.unionpay.mobile.android.d.a.f;
        i = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        layoutParams2.rightMargin = i;
        layoutParams2.leftMargin = i;
        this.m.addView(this.z, layoutParams2);
    }

    public final void c(String str) {
        this.j = false;
        this.b.a(c.bD.U);
        String str2 = i() ? "\"card\":\"" + this.a.aq + "\"" : "\"card\":\"" + ((com.unionpay.mobile.android.g.c) this.a.q.get(this.a.N)).a() + "\"";
        k.a("uppay", "cmd:" + str + ", ele:" + str2);
        this.e.c(str, str2);
        this.u = 6;
    }

    public final void c(String str, String str2) {
        a(str, str2);
    }

    public final void l() {
        if (!this.B.d()) {
            if (this.a.L) {
                a(13);
                this.a.L = false;
            } else if (this.a.K && this.D) {
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

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.B.d();
    }

    public final void u() {
        this.b.a(c.bD.U);
        z c = this.A.c("promotion");
        this.e.c("instalment", "\"promotion\":" + (c != null ? "\"" + ((aj) c).g() + "\"" : "\"\""));
        this.u = 16;
    }
}
