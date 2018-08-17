package com.unionpay.mobile.android.pro.views;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.g.c;
import com.unionpay.mobile.android.g.e;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.nocard.views.ah;
import com.unionpay.mobile.android.nocard.views.b;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.p;
import com.unionpay.mobile.android.widgets.ay;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class y extends b implements Callback, a.b {
    private a A;
    private String B;
    private boolean C;
    private Handler D;
    private OnClickListener E;
    private OnClickListener F;
    private OnClickListener G;
    private OnClickListener H;
    private OnClickListener I;
    private String r;
    private int s;
    private int t;
    private int u;
    private int v;
    private com.unionpay.mobile.android.upwidget.a w;
    private com.unionpay.mobile.android.upwidget.a x;
    private TextView y;
    private a z;

    public y(Context context, e eVar) {
        super(context, eVar);
        this.r = "00";
        this.s = 0;
        this.t = 0;
        this.u = 20;
        this.v = 5;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.C = false;
        this.D = null;
        this.E = new ag(this);
        this.F = new b(this);
        this.G = new c(this);
        this.H = new d(this);
        this.I = new f(this);
        this.f = 6;
        this.q = "sdpay";
        this.D = new Handler(this);
        this.C = this.a.K;
        setBackgroundColor(-1052684);
        e();
    }

    static /* synthetic */ String E(y yVar) {
        a.a b;
        String str = "";
        if (yVar.A != null) {
            b = yVar.A.b();
            if (b.a()) {
                str = str + b.b;
            }
        }
        if (yVar.z == null) {
            return str;
        }
        b = yVar.z.b();
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

    static /* synthetic */ void a(y yVar, c cVar, String str, HashMap hashMap) {
        yVar.t = 3;
        com.unionpay.mobile.android.pro.a.a.b v = yVar.v();
        if (v == null) {
            yVar.b(5);
        } else {
            new Thread(new e(yVar, v, cVar, str, hashMap)).start();
        }
    }

    static /* synthetic */ void a(y yVar, String str, String str2) {
        yVar.t = 7;
        yVar.b.a(com.unionpay.mobile.android.f.c.bD.U);
        yVar.e.c(str, str2);
    }

    private void d(String str, String str2) {
        this.t = 8;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.v--;
    }

    private void f(int i) {
        this.t = 4;
        this.s = i;
        int i2 = this.u;
        this.e.a("query", this.a.aj, 3);
        this.u--;
    }

    static /* synthetic */ void f(y yVar, String str) {
        yVar.j = false;
        yVar.t = 3;
        yVar.e.c(ah.a(yVar.a.C), ah.c("2", "1", yVar.w != null ? yVar.w.a() : null, str));
    }

    static /* synthetic */ HashMap j(y yVar) {
        HashMap hashMap = new HashMap();
        if (yVar.A != null) {
            hashMap = yVar.A.c();
        }
        if (yVar.z == null) {
            return hashMap;
        }
        Map c = yVar.z.c();
        if (c == null || hashMap == null) {
            return (c == null || hashMap != null) ? hashMap : c;
        } else {
            hashMap.putAll(c);
            return hashMap;
        }
    }

    public final void a(a.a aVar) {
        if (aVar.a()) {
            k.a("uppay", "sms elements:" + aVar.b);
            this.j = false;
            this.b.a(com.unionpay.mobile.android.f.c.bD.U);
            this.e.c("sms", aVar.b);
            this.t = 1;
            return;
        }
        a(aVar.b);
    }

    public final void a(JSONObject jSONObject) {
        this.C = false;
        String a;
        int i;
        JSONArray d;
        switch (this.t) {
            case 1:
                j();
                this.t = 0;
                this.A.a(com.unionpay.mobile.android.d.b.p);
                return;
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
                this.u = 20;
                f(this.t);
                return;
            case 4:
                this.r = j.a(jSONObject, "status");
                if (this.u <= 0 || !this.r.equalsIgnoreCase("01")) {
                    this.t = 0;
                    if (this.r.equalsIgnoreCase("00")) {
                        i = this.s;
                        j();
                        this.t = 0;
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
                        if (this.A != null) {
                            this.A.f();
                        }
                        b.a(this.d, this.q + "_succeed");
                        if (this.a.f) {
                            this.a.I.f = "success";
                            k();
                            return;
                        }
                        d(8);
                        return;
                    } else if (this.r.equalsIgnoreCase("03")) {
                        a = j.a(jSONObject, "fail_msg");
                        b.a(this.d, this.q + "_fail", p.j, (Object[]) new String[]{this.r, a});
                        if (this.t == 3) {
                            a(a);
                            return;
                        }
                        OnClickListener gVar = new g(this);
                        OnClickListener iVar = new i(this);
                        if (this.a.F) {
                            this.b.a(gVar, iVar);
                            this.b.a(com.unionpay.mobile.android.f.c.bD.Y, a, com.unionpay.mobile.android.f.c.bD.W, com.unionpay.mobile.android.f.c.bD.X);
                            return;
                        }
                        this.b.a(gVar, null);
                        this.b.a(com.unionpay.mobile.android.f.c.bD.Y, a, com.unionpay.mobile.android.f.c.bD.W);
                        return;
                    } else if (this.u > 0) {
                        return;
                    } else {
                        if (this.s == 3) {
                            a(this.a.ak, true);
                            return;
                        } else {
                            a(this.a.ak);
                            return;
                        }
                    }
                }
                f(this.s);
                return;
            case 6:
                j();
                i = f.a(this.a, jSONObject, false);
                if (i != 0) {
                    b(i);
                } else {
                    this.a.K = true;
                    if (this.a.z != null && this.a.z.length() > 0) {
                        d(6);
                    } else if (this.a.D != null && this.a.D.length() > 0) {
                        d(5);
                    }
                }
                this.t = 0;
                return;
            case 7:
                j();
                d = j.d(jSONObject, "options");
                if (this.z != null) {
                    this.z.a(d);
                    return;
                }
                return;
            case 8:
                a = j.a(jSONObject, "status");
                if (a == null || !"01".equals(a)) {
                    d = j.d(jSONObject, "options");
                    String a2 = j.a(jSONObject, "empty_info");
                    if (this.z != null) {
                        this.z.a(d, a2);
                        return;
                    }
                    return;
                }
                a = j.a(jSONObject, "uuid");
                if (this.v >= 0) {
                    d(this.B, a);
                    return;
                }
                a = com.unionpay.mobile.android.f.c.bD.D;
                if (this.z != null) {
                    this.z.a(null, a);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public final void a(boolean z) {
        if (this.y != null) {
            this.y.setEnabled(!z);
        }
    }

    protected final boolean a(String str, JSONObject jSONObject) {
        this.C = false;
        return false;
    }

    protected final void b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        View ayVar = new ay(getContext(), this.a.A, this);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    protected final void c() {
        LayoutParams layoutParams;
        boolean z = false;
        View linearLayout = new LinearLayout(this.d);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setOrientation(1);
        this.m.addView(linearLayout, new RelativeLayout.LayoutParams(-1, -2));
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            com.unionpay.mobile.android.g.f fVar = (com.unionpay.mobile.android.g.f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.a.aU = fVar.a("promotion_instalment_msgbox");
        }
        if (jSONArray.length() > 0) {
            this.z = new a(this.d, jSONArray, this, this.q);
            this.z.a(this.b, this.a.aU);
            this.z.a(this.F);
            this.z.b(this.G);
            this.z.c(this.H);
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
            linearLayout.addView(this.z, layoutParams);
        }
        if (this.z != null) {
            this.z.c("instalment");
        }
        this.A = new a(this.d, this.a.z, this.e.c(), this, this.a.aq, false, this.q);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
        linearLayout.addView(this.A, layoutParams);
        View linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(1);
        linearLayout2.setId(linearLayout2.hashCode());
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, linearLayout.getId());
        layoutParams2.leftMargin = com.unionpay.mobile.android.d.a.f;
        layoutParams2.topMargin = layoutParams2.leftMargin;
        this.m.addView(linearLayout2, layoutParams2);
        if (!(!i() && this.a.al == null && this.a.am == null)) {
            if (this.a.al != null) {
                this.x = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.al, this.I, this.q + "_agree_user_protocol");
                linearLayout2.addView(this.x);
            }
            if (this.a.am != null) {
                this.w = new com.unionpay.mobile.android.upwidget.a(this.d, this.a.am, null, this.q + "_remember_bankNO");
                layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
                layoutParams2.topMargin = com.unionpay.mobile.android.d.a.f;
                linearLayout2.addView(this.w, layoutParams2);
            }
        }
        this.y = new TextView(this.d);
        this.y.setText(j.a(this.a.C, "label"));
        this.y.setTextSize(com.unionpay.mobile.android.d.b.i);
        this.y.setTextColor(b.p());
        this.y.setGravity(17);
        TextView textView = this.y;
        if (this.A == null || this.A.e()) {
            z = true;
        }
        textView.setEnabled(z);
        int i = com.unionpay.mobile.android.d.a.n;
        this.y.setBackgroundDrawable(this.c.a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
        this.y.setOnClickListener(this.E);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, i);
        layoutParams3.addRule(3, linearLayout2.getId());
        layoutParams3.topMargin = com.unionpay.mobile.android.d.a.f;
        int a = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        layoutParams3.rightMargin = a;
        layoutParams3.leftMargin = a;
        this.m.addView(this.y, layoutParams3);
    }

    public final void c(String str) {
        this.j = false;
        this.b.a(com.unionpay.mobile.android.f.c.bD.U);
        String str2 = "\"card\":\"" + ((c) this.a.q.get(this.a.N)).a() + "\"";
        k.a("uppay", "cmd:" + str + ", ele:" + str2);
        this.e.c(str, str2);
        this.t = 6;
    }

    public final void c(String str, String str2) {
    }

    public boolean handleMessage(Message message) {
        if (message.obj != null) {
            Bundle bundle = (Bundle) message.obj;
            String string = bundle.getString("action_resp_code");
            String string2 = bundle.getString("action_resp_message");
            if (!"0000".equalsIgnoreCase(string)) {
                a(this.a.ap, false);
            } else if (string2 != null) {
                a(0, string2);
            } else {
                b(1);
            }
        } else {
            b(1);
        }
        return true;
    }

    public final void l() {
        if (!this.A.d()) {
            if (this.a.K && this.C) {
                this.a.K = false;
                n();
                return;
            }
            this.a.K = false;
            this.a.br = false;
            a(2);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.A.d();
    }

    public final void u() {
    }

    public com.unionpay.mobile.android.pro.a.a.b v() {
        return null;
    }
}
