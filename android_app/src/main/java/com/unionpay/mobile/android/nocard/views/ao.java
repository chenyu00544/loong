package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.d_ResultType;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.g.d;
import com.unionpay.mobile.android.g.e;
import com.unionpay.mobile.android.nocard.utils.f;
import com.unionpay.mobile.android.upviews.a.b;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.p;
import com.unionpay.mobile.android.views.order.l;
import com.unionpay.mobile.android.views.order.o;
import com.unionpay.mobile.android.widgets.ay;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class ao extends b implements b {
    private Handler A;
    List<Map<String, Object>> r;
    private int s;
    private int t;
    private Button u;
    private com.unionpay.mobile.android.upviews.a v;
    private o w;
    private LinearLayout x;
    private boolean y;
    private Handler z;

    public class a implements com.unionpay.mobile.android.views.order.o.a {
        final /* synthetic */ ao a;

        public a(ao aoVar) {
            this.a = aoVar;
        }

        public final int a() {
            this.a.a.J = true;
            this.a.d(2);
            return 0;
        }

        public final int a(int i) {
            this.a.t = i;
            this.a.s = 3;
            this.a.j = false;
            this.a.b.a(c.bD.U);
            this.a.e.m(((com.unionpay.mobile.android.g.c) this.a.a.q.get(i)).a());
            return 0;
        }

        public final void a(int i, boolean z, int i2, com.unionpay.mobile.android.upviews.a.a aVar) {
            if (this.a.w != null) {
                this.a.a.aQ = this.a.w.a();
                k.c("functionEx", this.a.a.aQ);
            }
            if (i == l.e.intValue()) {
                ao.c(this.a, i2);
            } else if (z) {
                String a;
                r0 = this.a;
                b.a(this.a.d, "bankpay_choose_bankcard_next");
                this.a.a.N = i2;
                this.a.j = false;
                this.a.s = 2;
                this.a.b.a(c.bD.U);
                if (((com.unionpay.mobile.android.g.c) this.a.a.q.get(i2)).c() == 0) {
                    a = ((com.unionpay.mobile.android.g.c) this.a.a.q.get(i2)).a();
                    this.a.a.M = "1";
                    a = ah.a(this.a.a.M, a, "1", "1");
                } else {
                    this.a.a.M = "0";
                    a = ah.b(this.a.a.M, "\"pan\":\"" + ((com.unionpay.mobile.android.g.c) this.a.a.q.get(i2)).b() + "\"", "2", "1\",\"carrier_tp\":\"2");
                }
                this.a.e.c(ah.a(this.a.a.j), a);
            } else if (!aVar.a()) {
                this.a.a(aVar.b);
            } else if (i == l.c.intValue()) {
                r0 = this.a;
                b.a(this.a.d, "login");
                this.a.j = false;
                this.a.s = 4;
                this.a.b.a(c.bD.U);
                this.a.e.n(aVar.b);
            } else {
                r0 = this.a;
                b.a(this.a.d, "bankpay_input_cardNO_next");
                this.a.j = false;
                this.a.s = 2;
                this.a.b.a(c.bD.U);
                this.a.a.M = "0";
                this.a.e.c(ah.a(this.a.a.j), ah.b(this.a.a.M, aVar.b, "1", "1"));
            }
        }

        public final void a(String str, String str2) {
            this.a.a(str, str2);
        }

        public final int b(int i) {
            ao aoVar = this.a;
            b.a(this.a.d, "bankpay_choose_bankcard", p.f, new Object[]{Integer.valueOf(i)});
            return 0;
        }

        public final void c(int i) {
            this.a.r();
            ao aoVar;
            if (i == l.b.intValue()) {
                aoVar = this.a;
                b.a(this.a.d, "open_bankpay");
                this.a.a.aP = l.b.intValue();
                this.a.d(2);
            } else if (i == l.c.intValue()) {
                aoVar = this.a;
                b.a(this.a.d, "open_loginpay");
                ao.d(this.a);
            } else if (i == l.d.intValue()) {
                aoVar = this.a;
                b.a(this.a.d, "open_nfcpay");
                this.a.d(17);
            } else if (i == l.e.intValue()) {
                ao.f(this.a);
            } else if (i == l.f.intValue()) {
                this.a.d(this.a.a.bp, this.a.a.bq);
            }
        }
    }

    public ao(Context context, e eVar) {
        super(context, eVar);
        this.s = 0;
        this.t = -1;
        this.u = null;
        this.v = null;
        this.r = new ArrayList(1);
        this.y = false;
        this.z = null;
        this.A = new Handler(new q(this));
        this.f = 2;
        if (this.a.aE && this.a.az) {
            this.a.aP = l.e.intValue();
        }
        this.q = "order";
        setBackgroundColor(-1052684);
        int i = this.a.ah;
        e();
        if (!TextUtils.isEmpty(this.a.u) || this.a.aC) {
            this.z = new Handler(new r(this));
        }
        if (!com.unionpay.mobile.android.g.b.bl && this.a.aP == l.e.intValue() && this.a.az && !this.a.aC && !this.a.aD) {
            this.A.sendMessageDelayed(this.A.obtainMessage(d_ResultType.SHORT_URL), 8000);
            k.c("uppay", "mHceHandler.sendMessageDelayed(msg, 8000)");
        }
    }

    private static Map<String, Object> b(d dVar) {
        Map<String, Object> hashMap = new HashMap();
        String str = dVar.a().substring(0, 4) + " **** " + dVar.a().substring(dVar.a().length() - 4);
        hashMap.put("text1", dVar.b() + dVar.c());
        hashMap.put("text2", str);
        return hashMap;
    }

    static /* synthetic */ void c(ao aoVar, int i) {
        aoVar.s = 6;
        aoVar.j = false;
        aoVar.b.a(c.bD.U);
        String a = ((d) com.unionpay.mobile.android.g.b.bb.get(i)).a();
        String e = ((d) com.unionpay.mobile.android.g.b.bb.get(i)).e();
        String d = ((d) com.unionpay.mobile.android.g.b.bb.get(i)).d();
        aoVar.a.bc = i;
        d = "\"pan\":\"" + a + "\",\"carrier_tp\":\"9\",\"issuer\":\"" + e + "\",\"type\":\"" + d + "\"";
        k.c("uppay", d);
        aoVar.e.c("cardrules", d);
    }

    static /* synthetic */ void d(ao aoVar) {
        if (aoVar.a.u == null || aoVar.a.u.length() <= 0) {
            aoVar.a.aP = l.c.intValue();
            aoVar.d(2);
            return;
        }
        aoVar.s = 4;
        aoVar.j = false;
        aoVar.b.a(c.bD.U);
        aoVar.e.n(String.format("\"user_id\":\"%s\"", new Object[]{aoVar.a.u}));
    }

    private void d(JSONObject jSONObject) {
        int a = f.a(this.a, jSONObject, false);
        if (a != 0) {
            b(a);
        } else {
            e a2 = f.a(jSONObject);
            if (this.a.z != null && this.a.z.length() > 0) {
                a(6, a2);
            } else if (this.a.D != null && this.a.D.length() > 0) {
                d(5);
            }
        }
        this.s = 0;
    }

    private void e(JSONObject jSONObject) {
        int b = f.b(this.a, jSONObject);
        if (b != 0) {
            b(b);
            return;
        }
        if (!TextUtils.isEmpty(j.a(jSONObject, "userId"))) {
            b.a(this.d, "_login", p.e, new Object[]{r0});
        }
        if (this.w != null) {
            Object b2 = this.w.b();
            if (!TextUtils.isEmpty(b2)) {
                PreferenceUtils.d(this.d, b2);
            }
        }
        a(13, f.a(jSONObject));
        this.s = 0;
    }

    static /* synthetic */ void f(ao aoVar) {
        if (!com.unionpay.mobile.android.g.b.bl || (com.unionpay.mobile.android.g.b.bb != null && com.unionpay.mobile.android.g.b.bb.size() > 0)) {
            aoVar.a.aP = l.e.intValue();
            aoVar.d(2);
            return;
        }
        boolean z = false;
        if (aoVar.a.aE) {
            z = true;
        }
        String str = c.bD.bq;
        String str2 = aoVar.a.bh;
        int i = com.unionpay.mobile.android.d.a.t;
        i = com.unionpay.mobile.android.d.a.k;
        i = com.unionpay.mobile.android.d.a.s;
        aoVar.a(str, str2, z, true);
    }

    private void y() {
        if (this.a.aP == l.e.intValue() && this.a.az && !this.a.aC && !this.a.aD) {
            this.A.removeMessages(d_ResultType.SHORT_URL);
            k.c("uppay", "mHceHandler remove Message");
        }
    }

    protected void a(Handler handler) {
    }

    protected final void a(com.unionpay.mobile.android.g.a aVar) {
        this.a.br = true;
        this.a.bs = aVar;
        this.s = 2;
        this.b.a(c.bD.U);
        this.a.M = "0";
        this.e.c(ah.a(this.a.j), ah.b(this.a.M, "\"pan\":\"" + aVar.b() + "\"", "2", "1\",\"carrier_tp\":\"10"));
    }

    public final void a(com.unionpay.mobile.android.upviews.a.a aVar) {
    }

    public final void a(JSONObject jSONObject) {
        this.b.c();
        com.unionpay.mobile.android.g.b bVar;
        int a;
        e a2;
        switch (this.s) {
            case 1:
                bVar = this.a;
                bVar.X = j.d(jSONObject, "login_rules");
                bVar.Y = j.c(jSONObject, "register_url");
                boolean z = (bVar.X == null || bVar.X.length() <= 0) ? true : false;
                if (z) {
                    b(2);
                } else {
                    d(12);
                }
                this.s = 0;
                return;
            case 2:
                if (!b(jSONObject)) {
                    d(jSONObject);
                    return;
                }
                return;
            case 3:
                bVar = this.a;
                int i = this.t;
                if (bVar.q != null && i < bVar.q.size()) {
                    bVar.q.remove(i);
                }
                if (this.a.q == null || this.a.q.size() <= 0) {
                    this.a.N = -1;
                    c();
                } else {
                    if (this.t == this.a.N) {
                        this.a.N = 0;
                    } else if (this.t < this.a.N) {
                        bVar = this.a;
                        bVar.N--;
                    }
                    this.w.c(this.a.N);
                }
                this.s = 0;
                return;
            case 4:
                if (!b(jSONObject)) {
                    e(jSONObject);
                    return;
                }
                return;
            case 5:
                j();
                a = f.a(this.a, jSONObject, false);
                if (a != 0) {
                    b(a);
                    return;
                }
                a2 = f.a(jSONObject);
                if (this.a.z != null && this.a.z.length() > 0) {
                    a(6, a2);
                    return;
                } else if (this.a.D != null && this.a.D.length() > 0) {
                    d(5);
                    return;
                } else {
                    return;
                }
            case 6:
                this.b.c();
                a = f.a(this.a, jSONObject, false);
                if (a != 0) {
                    b(a);
                    return;
                }
                a2 = f.a(jSONObject);
                y();
                a(18, a2);
                return;
            default:
                return;
        }
    }

    public final void a(boolean z) {
        if (this.u != null && this.u != null) {
            this.u.setEnabled(!z);
        }
    }

    protected final void b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        String str = this.a.o;
        View ayVar = new ay(this.d, str, this);
        if (this.a.J) {
            ayVar.a(c.bD.l);
        } else {
            if ((this.a.aE && this.a.aP == l.e.intValue()) || this.a.aP == l.a.intValue() || (this.a.aP == l.c.intValue() && this.a.aC && (this.a.q == null || this.a.q.size() <= 0))) {
                ayVar = new ay(this.d, str, this.c.a(1030), g.a(this.d, TitleBar.BACKBTN_LEFT_MARGIN), this);
            }
            if (this.a.aP == l.e.intValue()) {
                ayVar.a(c.bD.bq);
            }
        }
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    protected final void b(String str, JSONObject jSONObject) {
        if ("init".equals(str)) {
            if (this.a.J) {
                n();
            }
        } else if (!"".equals(str)) {
            this.b.a(c.bD.U);
            this.j = false;
            this.s = 5;
            this.e.c(str, "");
        } else if (this.s == 2) {
            d(jSONObject);
        } else if (this.s == 4) {
            e(jSONObject);
        }
    }

    protected final void c() {
        int intValue;
        this.m.removeAllViews();
        this.o.a((com.unionpay.mobile.android.upwidget.UPScrollView.a) this);
        Drawable a = this.c.a(2014);
        Drawable a2 = this.c.a(1002);
        if (this.a.aP == l.b.intValue() || this.a.J) {
            List a3 = com.unionpay.mobile.android.nocard.views.a.a.a(this.d, this.a.q, true);
            if (this.a.J && (this.a.aP == l.b.intValue() || l.a.intValue() == this.a.aP)) {
                a3 = null;
            }
            this.w = o.a(this.d, this.a.t, a3, a, a2, j.a(this.a.w, "label"));
        } else if (this.a.aP == l.c.intValue()) {
            this.w = o.a(this.d, a, a2);
        } else if (this.a.aP == l.e.intValue()) {
            HashMap hashMap = new HashMap();
            if (!com.unionpay.mobile.android.g.b.bl) {
                this.r.add(hashMap);
            } else if (com.unionpay.mobile.android.g.b.bb != null && com.unionpay.mobile.android.g.b.bb.size() > 0) {
                int size = com.unionpay.mobile.android.g.b.bb.size();
                for (r1 = 0; r1 < size; r1++) {
                    this.r.add(b((d) com.unionpay.mobile.android.g.b.bb.get(r1)));
                }
            }
            this.w = o.b(this.d, this.a.t, this.r, a, a2, "");
        } else {
            this.w = o.a(this.d, a);
            o oVar = this.w;
            oVar.a(a2);
            intValue = l.a.intValue();
            boolean a4 = f.a(this.a.t);
            if (!a4) {
                String a5 = j.a(this.a.v, "label");
                if (this.a.v != null && a5.length() > 0) {
                    intValue |= l.c.intValue();
                }
                k.a("uppay", intValue);
                a5 = j.a(this.a.w, "label");
                if (this.a.w != null && a5.length() > 0 && this.a.q != null && this.a.q.size() > 0) {
                    oVar.a(com.unionpay.mobile.android.nocard.views.a.a.a(this.d, this.a.q, true));
                }
                if (this.a.ax && this.a.ay && !this.a.aC) {
                    intValue |= l.d.intValue();
                }
                if (w() && this.a.az && !this.a.aC) {
                    intValue |= l.e.intValue();
                }
                if (w() && com.unionpay.mobile.android.g.b.aA && com.unionpay.mobile.android.g.b.aB && this.a.aP == l.a.intValue() && !"1".equalsIgnoreCase(this.a.an)) {
                    k.c("spay", "in");
                    intValue |= l.f.intValue();
                }
            }
            if (this.a.aD) {
                intValue = l.a.intValue();
            }
            if (!this.a.aC || TextUtils.isEmpty(this.a.u) || (this.a.q != null && this.a.q.size() > 0)) {
                r1 = com.unionpay.mobile.android.nocard.utils.c.a(this.d, this.a) & intValue;
                if (r1 == 0 && !this.a.aD) {
                    r1 = l.b.intValue();
                }
                oVar.b(r1);
                if (!this.a.aD) {
                    intValue |= l.b.intValue();
                }
            } else {
                intValue = l.a.intValue() | l.c.intValue();
                oVar.b(l.a.intValue());
            }
            oVar.a(this.a.t);
            k.a("uppay", intValue);
            oVar.a(j.a(this.a.w, "label"));
            oVar.a(intValue);
            oVar.a(a4);
            oVar.c();
        }
        this.w.a(new a(this));
        this.w.b(this.a.au);
        this.w.c(this.a.Y);
        if (!TextUtils.isEmpty(this.a.s)) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("href", this.a.s);
                jSONObject.put("title", c.bD.k);
                jSONObject.put("label", c.bD.j);
                this.w.a(jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.w.b(this.c.a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
        this.m.addView(this.w, new RelativeLayout.LayoutParams(-1, -1));
        if (this.a.aP == l.e.intValue() && !com.unionpay.mobile.android.g.b.bl) {
            a(this.A);
            this.x = new LinearLayout(this.d);
            this.x.setOrientation(0);
            intValue = com.unionpay.mobile.android.d.a.w;
            LayoutParams layoutParams = new LinearLayout.LayoutParams(intValue, intValue);
            layoutParams.gravity = 17;
            this.x.addView(new ProgressBar(this.d), layoutParams);
            View textView = new TextView(this.d);
            textView.setText(c.bD.bs);
            textView.setTextSize(com.unionpay.mobile.android.d.b.l);
            textView.setTextColor(-10066330);
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            layoutParams.leftMargin = com.unionpay.mobile.android.d.a.b;
            this.x.addView(textView, layoutParams);
            this.w.setVisibility(8);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.topMargin = com.unionpay.mobile.android.d.a.n;
            layoutParams2.addRule(14, -1);
            this.m.addView(this.x, layoutParams2);
        }
    }

    public final void c(String str) {
    }

    public final void c(String str, String str2) {
    }

    protected void d(String str, String str2) {
    }

    protected final void e(String str, String str2) {
        if (str2 != null && str2.length() > 0) {
            this.a.I.f = str2;
        }
        a(str, true);
    }

    public final void l() {
        if (!this.a.aE && this.a.J) {
            super.l();
            y();
            this.a.J = false;
        } else if (this.a.aE || ((this.a.aP == l.a.intValue() || (this.a.aP == l.c.intValue() && this.a.aC && (this.a.q == null || this.a.q.size() <= 0))) && this.a.aP != l.e.intValue())) {
            y();
            v();
        } else {
            super.l();
            y();
            this.a.aP = l.a.intValue();
        }
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (com.unionpay.mobile.android.g.b.bm && this.a.aP == l.a.intValue() && !"1".equalsIgnoreCase(this.a.an)) {
            k.c("spay", "out");
            d(this.a.bp, this.a.bq);
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.v != null) {
            this.v.clearFocus();
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (!this.y && this.z != null) {
            this.y = true;
            this.z.sendEmptyMessage(0);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
    }

    public final void u() {
    }

    protected final void v() {
        this.b.a(new s(this), new t(this));
        this.b.a(c.bD.Y, c.bD.av, c.bD.W, c.bD.X);
    }

    protected boolean w() {
        return false;
    }

    protected void x() {
    }
}
