package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.upviews.b.a;
import com.unionpay.mobile.android.upviews.b.b;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.widgets.ay;
import org.json.JSONObject;

public final class bi extends b implements a, b {
    private static String w = "download://";
    private com.unionpay.mobile.android.upviews.b r;
    private ViewGroup s;
    private int t;
    private boolean u;
    private boolean v;

    public bi(Context context) {
        this(context, false, false);
    }

    public bi(Context context, boolean z, boolean z2) {
        super(context);
        this.r = null;
        this.s = null;
        this.t = 0;
        this.u = false;
        this.v = false;
        this.f = 14;
        this.t = ((com.unionpay.mobile.android.d.a.t - com.unionpay.mobile.android.d.a.k) - com.unionpay.mobile.android.d.a.b(this.d)) - (com.unionpay.mobile.android.d.a.s * 3);
        this.u = z;
        this.v = z2;
        this.k = a();
        b();
        d();
    }

    public final void a(JSONObject jSONObject) {
    }

    protected final void b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        View ayVar = new ay(this.d, this.a.af, this);
        if (this.u) {
            ayVar = new ay(this.d, this.a.af, this.c.a(1030), g.a(this.d, TitleBar.BACKBTN_LEFT_MARGIN), this);
        }
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    public final void c(String str) {
        if (str != null && str.length() > 0 && str.startsWith(w)) {
            String substring = str.substring(w.length());
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(substring));
            this.d.startActivity(intent);
        }
    }

    protected final void d() {
        super.d();
        this.r = new com.unionpay.mobile.android.upviews.b(this.d, this);
        this.r.setOnTouchListener(new aj(this));
        if (this.v) {
            this.r.a(w);
        }
        LayoutParams layoutParams = this.t == 0 ? new RelativeLayout.LayoutParams(-1, -1) : new RelativeLayout.LayoutParams(-1, this.t);
        layoutParams.addRule(3, this.k.getId());
        layoutParams.addRule(12, -1);
        this.m.addView(this.r, layoutParams);
        this.s = new RelativeLayout(this.d);
        layoutParams = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.d.a.t - com.unionpay.mobile.android.d.a.k);
        layoutParams.addRule(3, this.k.getId());
        layoutParams.addRule(12, -1);
        layoutParams.addRule(10, -1);
        layoutParams.bottomMargin = 0;
        layoutParams.topMargin = 0;
        this.m.addView(this.s, layoutParams);
        View progressBar = new ProgressBar(this.d);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        this.s.addView(progressBar, layoutParams2);
        this.r.b(this.a.ag);
        if (this.u) {
            a(this.a.bi, false);
        }
    }

    public final void l() {
        ((InputMethodManager) this.d.getSystemService("input_method")).hideSoftInputFromWindow(getWindowToken(), 0);
        if (this.u) {
            this.b.a(new al(this), new am(this));
            this.b.a(c.bD.Y, c.bD.av, c.bD.W, c.bD.X);
            return;
        }
        super.l();
    }

    public final void u() {
        this.r.setVisibility(8);
        this.s.setVisibility(0);
    }

    public final void v() {
        this.r.setVisibility(0);
        this.s.setVisibility(8);
    }
}
