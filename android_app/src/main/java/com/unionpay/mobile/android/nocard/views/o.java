package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.g.e;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.upwidget.c;
import com.unionpay.mobile.android.upwidget.g;
import com.unionpay.mobile.android.upwidget.w;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.p;
import com.unionpay.mobile.android.widgets.aj;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.mobile.android.widgets.z;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class o extends b implements com.unionpay.mobile.android.upviews.a.b {
    private int A;
    private int B;
    private com.unionpay.mobile.android.upwidget.a C;
    private com.unionpay.mobile.android.upviews.a D;
    private b E;
    private String F;
    private OnClickListener G;
    private OnClickListener H;
    private boolean I;
    LinearLayout r;
    private int s;
    private com.unionpay.mobile.android.upviews.a t;
    private OnClickListener u;
    private OnClickListener v;
    private OnClickListener w;
    private TextView x;
    private int y;
    private int z;

    public interface a {
        void a(int i);
    }

    private class b extends LinearLayout {
        final /* synthetic */ o a;
        private PopupWindow b;
        private c c;
        private g d;
        private String e;
        private TextView f;
        private int g = 1;
        private final OnClickListener h = new e(this);
        private final OnItemClickListener i = new f(this);
        private List<Map<String, Object>> j;
        private a k;
        private String l;

        public b(o oVar, Context context, a aVar, List<Map<String, Object>> list, JSONArray jSONArray, String str) {
            this.a = oVar;
            super(context);
            setOrientation(1);
            this.k = aVar;
            this.j = list;
            this.e = jSONArray;
            this.l = str;
            this.c = new c(oVar.d, this.j, this.e, this.l, "", this.g, 0);
            this.d = new g(oVar.d, this.c);
            this.d.a(this.i);
            this.d.a(this.h);
            if (list != null && list.size() > 0) {
                Drawable a = com.unionpay.mobile.android.j.c.a(this.a.d).a(2014);
                View relativeLayout = new RelativeLayout(this.a.d);
                relativeLayout.setBackgroundDrawable(a);
                relativeLayout.setOnClickListener(new h(this));
                addView(relativeLayout, new LayoutParams(-1, com.unionpay.mobile.android.d.a.n));
                View imageView = new ImageView(this.a.d);
                imageView.setId(imageView.hashCode());
                imageView.setBackgroundDrawable(com.unionpay.mobile.android.j.c.a(this.a.d).a(1002));
                int a2 = com.unionpay.mobile.android.utils.g.a(this.a.d, 15.0f);
                ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a2, a2);
                layoutParams.addRule(11, -1);
                layoutParams.addRule(15, -1);
                layoutParams.rightMargin = com.unionpay.mobile.android.utils.g.a(this.a.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
                relativeLayout.addView(imageView, layoutParams);
                this.f = new TextView(this.a.d);
                this.f.setTextSize(com.unionpay.mobile.android.d.b.k);
                this.f.setTextColor(-10066330);
                ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
                this.f.setEllipsize(TruncateAt.MIDDLE);
                this.f.setSingleLine(true);
                layoutParams2.leftMargin = com.unionpay.mobile.android.utils.g.a(this.a.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
                layoutParams2.rightMargin = layoutParams2.leftMargin;
                layoutParams2.addRule(15, -1);
                layoutParams2.addRule(9, -1);
                layoutParams2.addRule(0, imageView.getId());
                relativeLayout.addView(this.f, layoutParams2);
                a(0);
            }
        }

        static /* synthetic */ void a(b bVar, View view) {
            if (bVar.b == null) {
                bVar.b = new PopupWindow(bVar.d, -1, -1, true);
                bVar.b.setBackgroundDrawable(new ColorDrawable(-1342177280));
                bVar.b.update();
            }
            bVar.b.showAtLocation(view, 80, 0, 0);
        }

        public final void a(int i) {
            int c = this.c.c() + i;
            if (this.f != null) {
                this.f.setText(this.c.b(c));
            }
        }
    }

    public o(Context context, e eVar) {
        super(context, eVar);
        this.s = 0;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.r = null;
        this.y = 0;
        this.z = 0;
        this.A = 20;
        this.B = 5;
        this.C = null;
        this.D = null;
        this.G = new az(this);
        this.H = new bf(this);
        this.I = false;
        this.f = 13;
        if (this.a.K) {
            this.q = "loginpay_phoneNO_change";
        } else {
            this.q = "loginpay";
        }
        this.u = new bg(this);
        this.v = new bh(this);
        this.w = new bj(this);
        if (!(q() || w() || this.a.aZ)) {
            this.I = true;
        }
        setBackgroundColor(-1052684);
        e();
        if (this.a.aF != null) {
            c(null);
        }
    }

    private void a(LinearLayout linearLayout) {
        JSONArray jSONArray = this.a.ac;
        if (jSONArray != null) {
            for (int i = 0; i < jSONArray.length(); i++) {
                View a = a((JSONObject) j.b(jSONArray, i), this.q);
                if (a != null) {
                    linearLayout.addView(a);
                }
            }
        }
    }

    static /* synthetic */ void a(o oVar, int i) {
        if (oVar.a.ab == null || i != oVar.a.ab.size()) {
            b.a(oVar.d, "loginpay_choose_bankcard", p.f, new Object[]{Integer.valueOf(i)});
            oVar.I = false;
            oVar.z = oVar.y;
            oVar.y = i;
            String a = ((com.unionpay.mobile.android.g.c) oVar.a.ab.get(i)).a();
            oVar.j = false;
            oVar.s = 1;
            oVar.b.a(com.unionpay.mobile.android.f.c.bD.U);
            oVar.e.j(ah.a("1", a, "1", "2"));
            return;
        }
        oVar.a.aZ = true;
        oVar.I = true;
        oVar.d(13);
    }

    static /* synthetic */ void a(o oVar, String str, String str2) {
        oVar.s = 8;
        oVar.b.a(com.unionpay.mobile.android.f.c.bD.U);
        oVar.e.c(str, str2);
    }

    static /* synthetic */ void b(o oVar, String str) {
        oVar.j = false;
        oVar.s = 3;
        oVar.b.a(com.unionpay.mobile.android.f.c.bD.U);
        oVar.e.a("1", "2", "yes", str);
    }

    private void d(String str, String str2) {
        this.s = 9;
        if (TextUtils.isEmpty(str2)) {
            this.e.c(str, "");
        } else {
            this.e.a(str, "\"uuid\":\"" + str2 + "\"", 10);
        }
        this.B--;
    }

    private void d(JSONObject jSONObject) {
        z zVar = null;
        boolean z = true;
        int a = f.a(this.a, jSONObject, false);
        if (a != 0) {
            b(a);
            if (1 == this.s) {
                f(this.z);
                return;
            }
            return;
        }
        e a2 = f.a(jSONObject);
        if (5 != this.s) {
            this.p = a2;
            f(this.y);
            this.D.a(v(), this.a.aq, true, null, this.a.ad, this.q);
            this.D.a(this.G);
            this.D.b(this.H);
            this.D.a(this.b, this.a.aU);
            this.D.d(this.a.bt);
            if (this.D != null) {
                zVar = this.D.c("instalment");
            }
            this.t.a(this.a.z, this.a.aq, true, zVar, this.a.ad, this.q);
            TextView textView = this.x;
            if (!(this.t == null || this.t.e())) {
                z = false;
            }
            textView.setEnabled(z);
        } else if (this.a.z != null && this.a.z.length() > 0) {
            a(6, a2);
        } else if (this.a.D != null && this.a.D.length() > 0) {
            d(5);
        }
    }

    static /* synthetic */ void e(o oVar) {
        if (oVar.t != null) {
            com.unionpay.mobile.android.upviews.a.a b = oVar.t.b();
            if (b.a()) {
                oVar.j = false;
                oVar.s = 5;
                oVar.b.a(com.unionpay.mobile.android.f.c.bD.U);
                oVar.e.c("bindcardrules", b.b);
                return;
            }
            oVar.a(b.b);
        }
    }

    private void f(int i) {
        this.y = i;
        this.E.a(this.y);
    }

    private JSONArray v() {
        JSONArray jSONArray = new JSONArray();
        if (this.p != null) {
            com.unionpay.mobile.android.g.f fVar = (com.unionpay.mobile.android.g.f) this.p;
            jSONArray.put(fVar.a("promotion"));
            jSONArray.put(fVar.a("instalment"));
            this.a.aU = fVar.a("promotion_instalment_msgbox");
        }
        return jSONArray;
    }

    private final boolean w() {
        return (this.a.aZ || this.a.ab == null || this.a.ab.size() <= 0) ? false : true;
    }

    private void x() {
        this.s = 4;
        int i = this.A;
        this.e.a("query", this.a.aj, 3);
        this.A--;
    }

    public final void a(com.unionpay.mobile.android.upviews.a.a aVar) {
        this.t.d();
        if (aVar.a()) {
            this.j = false;
            this.b.a(com.unionpay.mobile.android.f.c.bD.U);
            this.e.c("sms", aVar.b);
            this.s = 2;
            return;
        }
        a(aVar.b);
    }

    public final void a(JSONObject jSONObject) {
        String a;
        String a2;
        int a3;
        e a4;
        JSONArray d;
        switch (this.s) {
            case 1:
            case 5:
                j();
                if (!b(jSONObject)) {
                    if (this.s == 5) {
                        this.a.L = true;
                    }
                    d(jSONObject);
                    return;
                }
                return;
            case 2:
                j();
                this.t.a(com.unionpay.mobile.android.d.b.p);
                return;
            case 3:
                this.a.aj = i.a(jSONObject.toString());
                a = j.a(jSONObject, "qn");
                if (!TextUtils.isEmpty(a)) {
                    this.a.n = this.e.i(com.unionpay.mobile.android.utils.c.b(a));
                }
                if (this.a.aj == null) {
                    b(2);
                    return;
                }
                this.A = 20;
                x();
                return;
            case 4:
                a = j.a(jSONObject, "status");
                if (this.A <= 0 || !a.equalsIgnoreCase("01")) {
                    j();
                    if (a.equalsIgnoreCase("00")) {
                        this.s = 0;
                        this.a.H = j.d(jSONObject, "result");
                        this.a.P = j.a(jSONObject, "openupgrade_flag");
                        this.a.Q = j.a(jSONObject, "temporary_pay_flag");
                        this.a.R = j.a(jSONObject, "temporary_pay_info");
                        this.a.V = j.a(jSONObject, "front_url");
                        this.a.W = j.a(jSONObject, "front_request");
                        this.a.A = j.a(jSONObject, "title");
                        this.a.B = j.a(jSONObject, "succ_info");
                        com.unionpay.mobile.android.nocard.utils.b.a(jSONObject, this.a);
                        com.unionpay.mobile.android.nocard.utils.b.b(jSONObject, this.a);
                        b.a(this.d, this.q + "_succeed");
                        if (this.a.f) {
                            PreferenceUtils.c(this.d, this.a.aQ);
                            this.a.I.f = "success";
                            k();
                            return;
                        }
                        d(8);
                        return;
                    } else if (a.equalsIgnoreCase("03")) {
                        a2 = j.a(jSONObject, "fail_msg");
                        b.a(this.d, this.q + "_fail", p.j, (Object[]) new String[]{a, a2});
                        a(a2);
                        return;
                    } else if (this.A <= 0) {
                        b(19);
                        return;
                    } else {
                        return;
                    }
                }
                x();
                return;
            case 6:
                j();
                a3 = f.a(this.a, jSONObject, true);
                if (a3 != 0) {
                    b(a3);
                } else {
                    this.a.K = true;
                    a4 = f.a(jSONObject);
                    if (this.a.z != null && this.a.z.length() > 0) {
                        a(6, a4);
                    } else if (this.a.D != null && this.a.D.length() > 0) {
                        d(5);
                    }
                }
                this.s = 0;
                return;
            case 7:
                j();
                a3 = f.a(this.a, jSONObject, false);
                if (a3 != 0) {
                    b(a3);
                    return;
                }
                a4 = f.a(jSONObject);
                if (this.a.z != null && this.a.z.length() > 0) {
                    a(6, a4);
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
                if (this.D != null) {
                    this.D.a(d);
                    return;
                }
                return;
            case 9:
                a = j.a(jSONObject, "status");
                if (a == null || !"01".equals(a)) {
                    d = j.d(jSONObject, "options");
                    a2 = j.a(jSONObject, "empty_info");
                    if (this.D != null) {
                        this.D.a(d, a2);
                        return;
                    }
                    return;
                }
                a = j.a(jSONObject, "uuid");
                if (this.B >= 0) {
                    d(this.F, a);
                    return;
                }
                a = com.unionpay.mobile.android.f.c.bD.D;
                if (this.D != null) {
                    this.D.a(null, a);
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
                this.D.a(jSONObject);
                this.s = 0;
                return;
            default:
                return;
        }
    }

    public final void a(boolean z) {
        this.x.setEnabled(!z);
    }

    protected final boolean a(String str, JSONObject jSONObject) {
        if (this.s != 1) {
            return false;
        }
        f(this.z);
        j();
        a(str);
        return true;
    }

    protected final void b() {
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        String str = com.unionpay.mobile.android.f.c.bD.o;
        View ayVar = new ay(this.d, str, this);
        if (this.a.aC && !((this.a.q != null && this.a.q.size() != 0) || this.a.aZ || TextUtils.isEmpty(this.a.u))) {
            ayVar = new ay(this.d, str, this.c.a(1030), com.unionpay.mobile.android.utils.g.a(this.d, TitleBar.BACKBTN_LEFT_MARGIN), this);
        }
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    public final void b(int i) {
        if (this.s == 16) {
            if (this.b != null) {
                this.b.c();
            }
            z c = this.D.c("instalment");
            if (c != null) {
                ((com.unionpay.mobile.android.widgets.p) c).a(false);
                ((com.unionpay.mobile.android.widgets.p) c).b(false);
            }
        }
        super.b(i);
    }

    protected final void b(String str, JSONObject jSONObject) {
        if ("init".equals(str)) {
            a(2);
        } else if ("".equals(str)) {
            if (this.s == 5) {
                this.a.L = true;
            }
            if (jSONObject != null) {
                d(jSONObject);
            }
        } else {
            this.b.a(com.unionpay.mobile.android.f.c.bD.U);
            this.j = false;
            this.s = 7;
            this.e.c(str, "");
        }
    }

    protected final void c() {
        View linearLayout;
        ViewGroup.LayoutParams layoutParams;
        View textView;
        this.m.removeAllViews();
        this.o.a((com.unionpay.mobile.android.upwidget.UPScrollView.a) this);
        LinearLayout linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(1);
        ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = com.unionpay.mobile.android.d.a.f;
        layoutParams2.addRule(10, -1);
        this.m.addView(linearLayout2, layoutParams2);
        a(linearLayout2);
        JSONArray v = v();
        if (v != null && v.length() > 0 && w()) {
            this.D = new com.unionpay.mobile.android.upviews.a(this.d, v, this, this.q);
            this.D.a(this.G);
            this.D.b(this.H);
            this.D.a(this.b, this.a.aU);
            this.D.d(this.a.bt);
            layoutParams2 = new LayoutParams(-1, -2);
            layoutParams2.bottomMargin = com.unionpay.mobile.android.d.a.f;
            linearLayout2.addView(this.D, layoutParams2);
        }
        z zVar;
        if (q()) {
            if (w()) {
                linearLayout = new LinearLayout(this.d);
                linearLayout.setBackgroundColor(-3419943);
                layoutParams = new LayoutParams(-1, 1);
                layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
                linearLayout2.addView(linearLayout, layoutParams);
                ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-1, -2);
                this.E = new b(this, this.d, new bl(this), com.unionpay.mobile.android.nocard.views.a.a.a(this.d, this.a.ab, false), com.unionpay.mobile.android.f.c.bD.bh, this.a.aY);
                linearLayout2.addView(this.E, layoutParams3);
                zVar = null;
                if (this.D != null) {
                    zVar = this.D.c("instalment");
                }
                this.t = new com.unionpay.mobile.android.upviews.a(this.d, this.a.z, this.e.c(), this, this.a.aq, true, true, zVar, this.a.ad, this.q);
                linearLayout2.addView(this.t, layoutParams3);
            } else if (!TextUtils.isEmpty(this.a.ae)) {
                layoutParams2 = new LayoutParams(-1, -2);
                layoutParams2.topMargin = com.unionpay.mobile.android.d.a.f;
                textView = new TextView(this.d);
                textView.setTextSize(com.unionpay.mobile.android.d.b.k);
                textView.setText(this.a.ae);
                linearLayout2.addView(textView, layoutParams2);
            }
        } else if (w()) {
            linearLayout = new LinearLayout(this.d);
            linearLayout.setBackgroundColor(-3419943);
            linearLayout2.addView(linearLayout, new LayoutParams(-1, 1));
            ViewGroup.LayoutParams layoutParams4 = new LayoutParams(-1, -2);
            this.E = new b(this, this.d, new c(this), com.unionpay.mobile.android.nocard.views.a.a.a(this.d, this.a.ab, false), com.unionpay.mobile.android.f.c.bD.bh, this.a.aY);
            linearLayout2.addView(this.E, layoutParams4);
            zVar = null;
            if (this.D != null) {
                zVar = this.D.c("instalment");
            }
            this.t = new com.unionpay.mobile.android.upviews.a(this.d, this.a.z, this.e.c(), this, this.a.aq, true, true, zVar, this.a.ad, this.q);
            linearLayout2.addView(this.t, new LayoutParams(-1, -2));
        } else if (TextUtils.isEmpty(this.a.aY)) {
            layoutParams2 = new LayoutParams(-1, -2);
            layoutParams2.topMargin = com.unionpay.mobile.android.d.a.f;
            layoutParams2.leftMargin = com.unionpay.mobile.android.utils.g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
            textView = new TextView(this.d);
            textView.setTextSize(com.unionpay.mobile.android.d.b.k);
            textView.setText(this.a.ae);
            linearLayout2.addView(textView, layoutParams2);
        } else {
            linearLayout = new RelativeLayout(this.d);
            textView = new TextView(this.d);
            textView.setTextSize(com.unionpay.mobile.android.d.b.k);
            textView.setTextColor(-13421773);
            textView.setText(com.unionpay.mobile.android.f.c.bD.bA);
            ViewGroup.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams5.addRule(9, -1);
            layoutParams5.addRule(15, -1);
            layoutParams5.leftMargin = com.unionpay.mobile.android.utils.g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
            linearLayout.addView(textView, layoutParams5);
            textView = new TextView(this.d);
            textView.setText(Html.fromHtml(com.unionpay.mobile.android.f.c.bD.j));
            textView.setTextSize(com.unionpay.mobile.android.d.b.k);
            textView.setTextColor(h.a(-10705958, -5846275, -5846275, -6710887));
            textView.setOnClickListener(new bk(this));
            layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams5.addRule(11, -1);
            layoutParams5.rightMargin = com.unionpay.mobile.android.utils.g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
            layoutParams5.addRule(15, -1);
            linearLayout.addView(textView, layoutParams5);
            layoutParams = new LayoutParams(-1, -2);
            layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
            linearLayout2.addView(linearLayout, layoutParams);
            this.t = new com.unionpay.mobile.android.upviews.a(this.d, this.a.t, this, this.q);
            layoutParams2 = new LayoutParams(-1, -2);
            layoutParams2.topMargin = com.unionpay.mobile.android.d.a.f;
            linearLayout2.addView(this.t, layoutParams2);
        }
        linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        linearLayout.setId(linearLayout.hashCode());
        layoutParams = new LayoutParams(-1, -2);
        layoutParams.topMargin = com.unionpay.mobile.android.d.a.d;
        linearLayout2.addView(linearLayout, layoutParams);
        if (this.a.Z != null && w()) {
            this.C = new com.unionpay.mobile.android.upwidget.a(this.d, com.unionpay.mobile.android.nocard.views.a.a.a(this.a.Z, com.unionpay.mobile.android.f.c.bD.s), new ba(this), this.q + "_agree_user_protocol");
            linearLayout.addView(this.C);
        }
        textView = w.a(this.d, this.a.aa, this.c.a(1017));
        if (textView != null) {
            textView.a(new bb(this, textView.a()));
            layoutParams5 = new LayoutParams(-2, -2);
            layoutParams5.topMargin = com.unionpay.mobile.android.d.a.f;
            linearLayout.addView(textView, layoutParams5);
        }
        this.x = new TextView(this.d);
        if (w()) {
            this.x.setText(com.unionpay.mobile.android.f.c.bD.p);
            this.x.setOnClickListener(this.u);
            TextView textView2 = this.x;
            boolean z = this.t == null || this.t.e();
            textView2.setEnabled(z);
        } else if (q()) {
            this.x.setText(com.unionpay.mobile.android.f.c.bD.q);
            this.x.setOnClickListener(new d(this));
            this.x.setEnabled(true);
        } else if (!TextUtils.isEmpty(this.a.aY) || this.a.aZ) {
            this.x.setText(com.unionpay.mobile.android.f.c.bD.r);
            this.x.setOnClickListener(this.v);
            this.x.setEnabled(false);
        } else {
            if (this.a.q == null || this.a.q.size() == 0) {
                this.x.setText(com.unionpay.mobile.android.f.c.bD.bu);
            } else {
                this.x.setText(com.unionpay.mobile.android.f.c.bD.bv);
            }
            this.x.setOnClickListener(this.w);
            this.x.setEnabled(true);
        }
        this.x.setTextSize(com.unionpay.mobile.android.d.b.i);
        this.x.setTextColor(b.p());
        this.x.setGravity(17);
        int i = com.unionpay.mobile.android.d.a.n;
        this.x.setBackgroundDrawable(this.c.a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
        layoutParams = new LayoutParams(-1, i);
        layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
        i = com.unionpay.mobile.android.utils.g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        layoutParams.rightMargin = i;
        layoutParams.leftMargin = i;
        linearLayout2.addView(this.x, layoutParams);
    }

    public final void c(String str) {
        this.j = false;
        this.b.a(com.unionpay.mobile.android.f.c.bD.U);
        String str2 = this.a.aZ ? "\"card\":\"" + this.a.aq + "\"" : "\"card\":\"" + ((com.unionpay.mobile.android.g.c) this.a.ab.get(this.y)).a() + "\"";
        k.a("uppay", "cmd:" + str + ", ele:" + str2);
        this.e.c(str, str2);
        this.s = 6;
    }

    public final void c(String str, String str2) {
        a(str, str2);
    }

    public final void l() {
        if (!TextUtils.isEmpty(this.a.u) && this.a.aC && (this.a.q == null || this.a.q.size() == 0)) {
            this.b.a(new bc(this), new be(this));
            this.b.a(com.unionpay.mobile.android.f.c.bD.Y, com.unionpay.mobile.android.f.c.bD.av, com.unionpay.mobile.android.f.c.bD.W, com.unionpay.mobile.android.f.c.bD.X);
            return;
        }
        if (this.a.aZ) {
            this.a.aZ = false;
        }
        if (this.t != null && this.t.d()) {
            return;
        }
        if (this.a.u == null || this.a.u.length() <= 0) {
            n();
        } else {
            a(2);
        }
    }

    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        boolean z = this.I;
    }

    public final void u() {
        this.b.a(com.unionpay.mobile.android.f.c.bD.U);
        z c = this.D.c("promotion");
        this.e.c("instalment", "\"promotion\":" + (c != null ? "\"" + ((aj) c).g() + "\"" : "\"\""));
        this.s = 16;
    }
}
