package com.unionpay.mobile.android.nocard.views;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.umeng.socialize.media.WeiXinShareContent;
import com.unionpay.mobile.android.g.e;
import com.unionpay.mobile.android.j.c;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine;
import com.unionpay.mobile.android.nocard.utils.UPPayEngine.a;
import com.unionpay.mobile.android.nocard.utils.d;
import com.unionpay.mobile.android.upwidget.UPScrollView;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.p;
import com.unionpay.mobile.android.views.order.l;
import com.unionpay.mobile.android.views.order.m;
import com.unionpay.mobile.android.widgets.UPWidget;
import com.unionpay.mobile.android.widgets.aa;
import com.unionpay.mobile.android.widgets.ad;
import com.unionpay.mobile.android.widgets.ae;
import com.unionpay.mobile.android.widgets.af;
import com.unionpay.mobile.android.widgets.ah;
import com.unionpay.mobile.android.widgets.an;
import com.unionpay.mobile.android.widgets.ao;
import com.unionpay.mobile.android.widgets.ap;
import com.unionpay.mobile.android.widgets.as;
import com.unionpay.mobile.android.widgets.at;
import com.unionpay.mobile.android.widgets.au;
import com.unionpay.mobile.android.widgets.av;
import com.unionpay.mobile.android.widgets.ay;
import com.unionpay.mobile.android.widgets.f;
import com.unionpay.mobile.android.widgets.u;
import com.unionpay.mobile.android.widgets.y;
import com.unionpay.mobile.android.widgets.z;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class b extends RelativeLayout implements a, a, UPScrollView.a, aa.a, ay.a {
    protected com.unionpay.mobile.android.g.b a;
    protected an b;
    protected c c;
    protected Context d;
    protected UPPayEngine e;
    protected int f;
    protected String g;
    protected String h;
    protected String i;
    protected boolean j;
    protected RelativeLayout k;
    protected ViewGroup l;
    protected RelativeLayout m;
    protected as n;
    protected UPScrollView o;
    protected e p;
    protected String q;
    private LinearLayout r;
    private LinearLayout s;
    private LinearLayout t;
    private int u;
    private int v;
    private Activity w;

    public b(Context context) {
        this(context, null);
    }

    public b(Context context, e eVar) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = true;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.q = "uppay";
        this.w = null;
        this.f = 0;
        this.d = context;
        if (this.d instanceof Activity) {
            this.w = (Activity) this.d;
        }
        this.p = eVar;
        this.e = (UPPayEngine) ((com.unionpay.mobile.android.i.a) context).a(UPPayEngine.class.toString());
        this.a = (com.unionpay.mobile.android.g.b) ((com.unionpay.mobile.android.i.a) context).a(null);
        this.b = (an) ((com.unionpay.mobile.android.i.a) context).a(an.class.toString());
        this.c = c.a(context);
        setId(8888);
        setLayoutParams(new LayoutParams(-1, -1));
        setBackgroundColor(-1);
        k.b("uppayEx", "UPViewBase:" + toString());
    }

    private void a(int i, boolean z, boolean z2) {
        b a;
        com.unionpay.mobile.android.i.a aVar = (com.unionpay.mobile.android.i.a) this.d;
        switch (i) {
            case 2:
                a = aVar.a(i, null);
                break;
            case 5:
                a = new g(this.d);
                break;
            case 6:
                int i2 = 0;
                if (this.a.q != null && this.a.q.size() > 0) {
                    i2 = ((com.unionpay.mobile.android.g.c) this.a.q.get(this.a.N)).c();
                }
                if ((!i() && r1 != 0) || this.a.br) {
                    a = aVar.a(6, null);
                    break;
                } else {
                    a = new at(this.d);
                    break;
                }
                break;
            case 8:
                a = new bd(this.d);
                break;
            case 10:
                a = new ak(this.d);
                break;
            case 11:
                a = new ai(this.d);
                break;
            case 12:
                a = new af(this.d);
                break;
            case 13:
                a = new o(this.d, null);
                break;
            case 14:
                a = new bi(this.d, z, z2);
                break;
            case 17:
                a = aVar.a(i, null);
                break;
            case 18:
                a = aVar.a(i, null);
                break;
            default:
                a = null;
                break;
        }
        if (a != null) {
            aVar.a(a);
        }
    }

    public static void a(Context context, String str) {
        a(context, str, null, null);
    }

    public static void a(Context context, String str, String[] strArr, Object[] objArr) {
        if (com.unionpay.mobile.android.d.a.L) {
            k.a("uppay-TD", "event:" + str + ", keys:" + Arrays.toString(strArr) + ", values:" + Arrays.toString(objArr));
            if (strArr == null || objArr == null) {
                com.unionpay.c.a.c(context, str);
            } else if (strArr.length != objArr.length || strArr.length > 10) {
                throw new IllegalArgumentException();
            } else {
                Map hashMap = new HashMap();
                for (int i = 0; i < strArr.length; i++) {
                    hashMap.put(strArr[i], objArr[i]);
                }
                com.unionpay.c.a.a(context, str, str, hashMap);
            }
        }
    }

    protected static boolean b(String str) {
        return str != null && str.length() > 0;
    }

    protected static ColorStateList p() {
        return h.a(com.unionpay.mobile.android.d.b.b, com.unionpay.mobile.android.d.b.c, com.unionpay.mobile.android.d.b.c, com.unionpay.mobile.android.d.b.d);
    }

    private RelativeLayout u() {
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        if (this.k != null) {
            layoutParams.addRule(3, this.k.getId());
            layoutParams.addRule(12, -1);
        }
        View frameLayout = new FrameLayout(this.d);
        addView(frameLayout, layoutParams);
        layoutParams = new FrameLayout.LayoutParams(-1, -1);
        this.o = new UPScrollView(this.d);
        this.o.setPadding(0, 0, 0, 0);
        frameLayout.addView(this.o, layoutParams);
        ViewGroup.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
        int a = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.t = new LinearLayout(this.d);
        this.t.setId(this.t.hashCode());
        this.t.setOrientation(1);
        if (!this.a.aM || com.unionpay.mobile.android.g.b.bm) {
            this.t.setBackgroundColor(-267336);
        } else {
            this.t.setBackgroundColor(-34177);
        }
        this.t.setPadding(a, a, a, a);
        String str = "";
        if (b(this.a.ar)) {
            str = str + this.a.ar;
        }
        if (b(str)) {
            View textView = new TextView(this.d);
            if (!this.a.aM || com.unionpay.mobile.android.g.b.bm) {
                textView.setTextColor(-10066330);
            } else {
                textView.setTextColor(-1);
            }
            textView.setText(str);
            textView.setTextSize(com.unionpay.mobile.android.d.b.k);
            this.t.addView(textView);
        } else {
            this.t.setVisibility(8);
        }
        this.t.setVisibility(8);
        frameLayout.addView(this.t, layoutParams2);
        View relativeLayout = new RelativeLayout(this.d);
        relativeLayout.setBackgroundColor(-1052684);
        this.o.addView(relativeLayout, new LayoutParams(-1, -1));
        return relativeLayout;
    }

    protected final RelativeLayout a() {
        View relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setId(relativeLayout.hashCode());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(10, -1);
        addView(relativeLayout, layoutParams);
        return relativeLayout;
    }

    protected final z a(JSONObject jSONObject, String str) {
        z zVar = null;
        String a = j.a(jSONObject, "type");
        int i = com.unionpay.mobile.android.d.a.I - (com.unionpay.mobile.android.d.a.f * 4);
        if ("pan".equalsIgnoreCase(a)) {
            zVar = new af(this.d, i, jSONObject, str);
        } else if ("mobile".equalsIgnoreCase(a)) {
            zVar = new ah(this.d, i, jSONObject, str);
        } else if ("sms".equalsIgnoreCase(a)) {
            zVar = new ap(this.d, i, jSONObject, str);
        } else if ("cvn2".equalsIgnoreCase(a)) {
            zVar = new com.unionpay.mobile.android.widgets.e(this.d, i, jSONObject, str);
        } else if ("expire".equalsIgnoreCase(a)) {
            zVar = new av(this.d, i, jSONObject, str);
        } else if ("pwd".equalsIgnoreCase(a)) {
            zVar = new UPWidget(this.d, this.e.c(), i, jSONObject, str);
        } else if (WeiXinShareContent.TYPE_TEXT.equalsIgnoreCase(a)) {
            zVar = new at(this.d, i, jSONObject, str);
        } else if ("string".equalsIgnoreCase(a)) {
            zVar = new ad(this.d, jSONObject, str);
        } else if ("cert_id".equalsIgnoreCase(a)) {
            zVar = new f(this.d, i, jSONObject, str);
        } else if ("cert_type".equalsIgnoreCase(a)) {
            zVar = new com.unionpay.mobile.android.widgets.g(this.d, jSONObject, str);
        } else if ("name".equalsIgnoreCase(a)) {
            zVar = new ae(this.d, i, jSONObject, str);
        } else if ("hidden".equalsIgnoreCase(a)) {
            zVar = new y(this.d, jSONObject, str);
        } else if ("user_name".equalsIgnoreCase(a)) {
            zVar = new au(this.d, i, jSONObject, str);
        } else if ("password".equalsIgnoreCase(a)) {
            zVar = new ao(this.d, i, jSONObject, str);
        }
        if (zVar != null && (zVar instanceof aa)) {
            ((aa) zVar).a((aa.a) this);
        }
        return zVar;
    }

    public final void a(int i) {
        ((com.unionpay.mobile.android.i.a) this.d).a(i);
    }

    protected final void a(int i, e eVar) {
        com.unionpay.mobile.android.i.a aVar = (com.unionpay.mobile.android.i.a) this.d;
        b bVar = null;
        switch (i) {
            case 2:
                bVar = aVar.a(i, eVar);
                break;
            case 5:
                bVar = new g(this.d);
                break;
            case 6:
                int i2 = 0;
                if (this.a.q != null && this.a.q.size() > 0) {
                    i2 = ((com.unionpay.mobile.android.g.c) this.a.q.get(this.a.N)).c();
                }
                if ((!i() && r1 != 0 && this.a.aP != l.c.intValue()) || this.a.br) {
                    bVar = aVar.a(6, eVar);
                    break;
                } else {
                    bVar = new at(this.d, eVar);
                    break;
                }
            case 8:
                bVar = new bd(this.d);
                break;
            case 10:
                bVar = new ak(this.d);
                break;
            case 11:
                bVar = new ai(this.d);
                break;
            case 12:
                bVar = new af(this.d);
                break;
            case 13:
                bVar = new o(this.d, eVar);
                break;
            case 14:
                bVar = new bi(this.d);
                break;
            case 17:
                bVar = aVar.a(i, eVar);
                break;
            case 18:
                bVar = aVar.a(i, eVar);
                break;
        }
        if (bVar != null) {
            aVar.a(bVar);
        }
    }

    public final void a(int i, String str) {
        int i2 = 0;
        this.j = true;
        k.a("uppay", " " + toString());
        if (i == 0) {
            k.a("uppay", "parserResponseMesage() +++");
            JSONObject jSONObject = null;
            if (str == null || str.length() == 0) {
                k.a("uppay", " ERROR_MSG_FORMAT");
                i2 = 2;
            } else {
                try {
                    JSONObject jSONObject2 = new JSONObject(str);
                    this.g = j.a(jSONObject2, "resp");
                    this.h = j.a(jSONObject2, "msg");
                    this.i = j.a(jSONObject2, com.taobao.agoo.a.a.b.JSON_CMD);
                    jSONObject = j.c(jSONObject2, "params");
                    if (!this.g.equalsIgnoreCase("00")) {
                        if ("pay".equals(this.i) || (this.a.E != null && this.a.E.length() > 0 && this.a.E.equals(this.i))) {
                            a(this.d, this.q + "_fail", p.j, new String[]{this.g, this.h});
                        }
                        if ("rules".equals(this.i)) {
                            a(this.d, this.q + "_cardNO_fail", p.j, new String[]{this.g, this.h});
                        }
                        if ("getuserinfo".equals(this.i)) {
                            a(this.d, "login_fail", p.j, new String[]{this.g, this.h});
                        }
                        if (this.g.equalsIgnoreCase("21")) {
                            i2 = 17;
                            k.a("uppay", " ERROR_ORDER_TIMEOUT");
                        } else {
                            i2 = 3;
                            k.a("uppay", " ERROR_TRANSACTION");
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    k.a("uppay", " ERROR_MSG_FORMAT");
                    i2 = 2;
                }
            }
            if (i2 != 0) {
                String str2 = this.g;
                if (!a(this.h, jSONObject)) {
                    b(i2);
                }
            } else {
                a(jSONObject);
            }
            k.a("uppay", "parserResponseMesage() ---");
            return;
        }
        b(i);
    }

    public final void a(u uVar, String str) {
    }

    protected final void a(String str) {
        a(str, false);
    }

    protected final void a(String str, OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.b.a(onClickListener, onClickListener2);
        if (this.w != null && !this.w.isFinishing() && com.unionpay.mobile.android.f.c.bD != null) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.Y, str, com.unionpay.mobile.android.f.c.bD.W, com.unionpay.mobile.android.f.c.bD.X, false);
        }
    }

    protected final void a(String str, String str2) {
        a(str, str2, false, false);
    }

    protected final void a(String str, String str2, boolean z, boolean z2) {
        ((InputMethodManager) this.d.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        this.a.ag = str2;
        this.a.af = str;
        a(14, z, z2);
    }

    protected void a(String str, boolean z) {
        OnClickListener apVar = new ap(this, z);
        k.a("uppay", " showErrDialog(msg, boolean)  ");
        this.b.a(apVar, null);
        if (this.w != null && !this.w.isFinishing() && com.unionpay.mobile.android.f.c.bD != null) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.Y, str, com.unionpay.mobile.android.f.c.bD.W);
        }
    }

    protected boolean a(String str, JSONObject jSONObject) {
        return false;
    }

    protected void b() {
    }

    public void b(int i) {
        if (i == 8 || i == 17 || i == 19) {
            this.a.I.f = "fail";
            k.a("uppay", "showErrDialog 1");
            a(c(i), true);
            return;
        }
        k.a("uppay", "showErrDialog 2");
        a(c(i), false);
    }

    protected final void b(String str, String str2) {
        a(str, str2, true, true);
    }

    protected void b(String str, JSONObject jSONObject) {
    }

    protected final boolean b(JSONObject jSONObject) {
        if (!com.unionpay.mobile.android.nocard.utils.f.c(this.a, jSONObject)) {
            return false;
        }
        c(jSONObject);
        return true;
    }

    protected final String c(int i) {
        switch (i) {
            case 2:
                return com.unionpay.mobile.android.f.c.bD.aB;
            case 3:
            case 17:
                return this.h;
            case 4:
                return com.unionpay.mobile.android.f.c.bD.az;
            case 5:
                return com.unionpay.mobile.android.f.c.bD.aH;
            case 6:
                return com.unionpay.mobile.android.f.c.bD.aI;
            case 7:
                return com.unionpay.mobile.android.f.c.bD.aG;
            case 8:
                return com.unionpay.mobile.android.f.c.bD.aJ;
            case 9:
                return com.unionpay.mobile.android.f.c.bD.aK;
            case 16:
                return com.unionpay.mobile.android.f.c.bD.aM;
            case 18:
                return com.unionpay.mobile.android.f.c.bD.aP;
            case 19:
                return com.unionpay.mobile.android.f.c.bD.aN;
            case 20:
                return com.unionpay.mobile.android.f.c.bD.aO;
            case 21:
                return com.unionpay.mobile.android.f.c.bD.aL;
            default:
                return com.unionpay.mobile.android.f.c.bD.aA;
        }
    }

    protected void c() {
    }

    protected final void c(JSONObject jSONObject) {
        this.b.a(new aq(this, jSONObject), new ar(this, jSONObject));
        if (this.w != null && !this.w.isFinishing() && this.a != null) {
            this.b.a(this.a.aG, this.a.aH, this.a.aI, this.a.aK);
        }
    }

    protected void d() {
        this.m = u();
    }

    protected final void d(int i) {
        a(i, false, false);
    }

    protected final void e() {
        this.k = a();
        b();
        ViewGroup u = u();
        View linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundColor(-1114114);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.addRule(10, -1);
        u.addView(linearLayout, layoutParams);
        this.l = linearLayout;
        this.l.setBackgroundColor(0);
        f();
        int id = this.l.getId();
        layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(12, -1);
        layoutParams.addRule(3, id);
        linearLayout = new RelativeLayout(this.d);
        u.addView(linearLayout, layoutParams);
        this.m = linearLayout;
        c();
    }

    public final void e(int i) {
        if (i >= this.v) {
            if (this.t.getVisibility() != 0 && this.t != null && this.r.getVisibility() == 0) {
                this.t.setVisibility(0);
            }
        } else if (i <= this.v + this.u && this.t.getVisibility() == 0 && this.t != null) {
            this.t.setVisibility(8);
        }
    }

    protected void f() {
        View textView;
        this.s = new LinearLayout(this.d);
        this.s.setOrientation(1);
        if (!this.a.aM || com.unionpay.mobile.android.g.b.bm) {
            this.s.setBackgroundColor(-267336);
        } else {
            this.s.setBackgroundColor(-34177);
        }
        int a = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        if (b(this.a.ar)) {
            this.s.setPadding(a, a, a, 0);
        } else {
            this.s.setPadding(a, a, a, a);
        }
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = 0;
        this.l.addView(this.s, layoutParams);
        String str = "";
        if (b(this.a.at)) {
            str = str + this.a.at;
        }
        if (b(str)) {
            View textView2 = new TextView(this.d);
            if (!this.a.aM || com.unionpay.mobile.android.g.b.bm) {
                textView2.setTextColor(-10066330);
            } else {
                textView2.setTextColor(-1);
            }
            textView2.setText(str);
            textView2.setTextSize(com.unionpay.mobile.android.d.b.k);
            this.s.addView(textView2);
        } else {
            this.s.setVisibility(8);
        }
        this.r = new LinearLayout(this.d);
        this.r.setOrientation(1);
        if (!this.a.aM || com.unionpay.mobile.android.g.b.bm) {
            this.r.setBackgroundColor(-267336);
        } else {
            this.r.setBackgroundColor(-34177);
        }
        this.r.setPadding(a, a, a, a);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = 0;
        this.l.addView(this.r, layoutParams);
        str = "";
        if (b(this.a.ar)) {
            str = str + this.a.ar;
        }
        if (b(str)) {
            textView = new TextView(this.d);
            if (!this.a.aM || com.unionpay.mobile.android.g.b.bm) {
                textView.setTextColor(-10066330);
            } else {
                textView.setTextColor(-1);
            }
            textView.setText(str);
            textView.setTextSize(com.unionpay.mobile.android.d.b.k);
            this.r.addView(textView);
        } else {
            this.r.setVisibility(8);
        }
        this.r.getViewTreeObserver().addOnPreDrawListener(new an(this));
        textView = new m(this.d);
        textView.a(this.c.a(1003), this.c.a(1001));
        textView.a(!(this instanceof ao), this.a.h, this.a.i);
        this.l.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        Drawable a2 = this.c.a(1026);
        View linearLayout = new LinearLayout(this.d);
        if (a2 != null) {
            linearLayout.setBackgroundDrawable(a2);
        }
        this.l.addView(linearLayout, new LinearLayout.LayoutParams(-1, g.a(this.d, 2.0f)));
    }

    protected final void g() {
        RelativeLayout relativeLayout = this.m;
    }

    public final int h() {
        return this.f;
    }

    protected final boolean i() {
        return this.a.J || this.a.q == null || this.a.q.size() == 0;
    }

    protected final void j() {
        if (this.b != null && this.b.a()) {
            this.b.c();
        }
    }

    public final void k() {
        d.a(this.d, this.a);
    }

    public void l() {
        if (this.j) {
            n();
        }
    }

    public final void m() {
        l();
    }

    public final void n() {
        ((com.unionpay.mobile.android.i.a) this.d).b();
    }

    protected final boolean o() {
        boolean z = false;
        if (this.b != null && this.b.a()) {
            z = true;
        }
        k.a("uppay", " dialog showing:" + z);
        return z;
    }

    protected void onAttachedToWindow() {
        k.b("uppayEx", toString() + " onAttachedToWindow()");
        super.onAttachedToWindow();
        this.e.a((a) this);
    }

    protected final boolean q() {
        return !this.a.c;
    }

    public final void r() {
        if (this.d != null) {
            ((InputMethodManager) this.d.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        }
    }

    public final void s() {
        if (com.unionpay.mobile.android.d.a.L && !TextUtils.isEmpty(this.q) && this.w != null) {
            k.a("uppay-TD", "page start: " + this.q + "_View");
            com.unionpay.c.a.a(this.w, this.q + "_View");
        }
    }

    public final void t() {
        if (com.unionpay.mobile.android.d.a.L && !TextUtils.isEmpty(this.q) && this.w != null) {
            k.a("uppay-TD", "page end: " + this.q + "_View");
            com.unionpay.c.a.b(this.w, this.q + "_View");
        }
    }
}
