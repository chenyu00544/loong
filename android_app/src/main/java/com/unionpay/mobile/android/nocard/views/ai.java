package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.unionpay.mobile.android.d.a;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.widgets.ay;
import org.json.JSONObject;

public final class ai extends b {
    private TextView r;
    private OnClickListener s;

    public ai(Context context) {
        super(context);
        this.r = null;
        this.s = new k(this);
        this.f = 11;
        this.k = a();
        b();
        super.d();
        c();
    }

    private void u() {
        this.a.I.f = "success";
        k();
    }

    public final void a(JSONObject jSONObject) {
        j();
        this.a.T = j.d(jSONObject, "open_rules");
        if (this.a.T == null || this.a.T.length() <= 0) {
            b(2);
        } else {
            d(10);
        }
    }

    protected final void b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        View ayVar = new ay(getContext(), this.a.A, this);
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    protected final void c() {
        int i = a.d;
        View linearLayout = new LinearLayout(this.d);
        linearLayout.setBackgroundColor(-1114114);
        linearLayout.setOrientation(1);
        linearLayout.setPadding(0, i, 0, i);
        linearLayout.setId(linearLayout.hashCode());
        View textView = new TextView(this.d);
        textView.setText(this.a.B);
        textView.setTextSize(24.0f);
        textView.setTextColor(-15365480);
        textView.setGravity(1);
        textView.getPaint().setFakeBoldText(true);
        linearLayout.addView(textView);
        textView = new LinearLayout(this.d);
        textView.setOrientation(0);
        textView.setBackgroundColor(-6958338);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 2);
        layoutParams.addRule(14, -1);
        int i2 = a.d;
        layoutParams.bottomMargin = i2;
        layoutParams.topMargin = i2;
        linearLayout.addView(textView);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(10, -1);
        this.m.addView(linearLayout, layoutParams2);
        textView = new LinearLayout(this.d);
        textView.setPadding(i, i, i, i);
        textView.setOrientation(1);
        textView.setId(textView.hashCode());
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(3, linearLayout.getId());
        this.m.addView(textView, layoutParams3);
        View textView2 = new TextView(this.d);
        textView2.setTextSize(18.0f);
        textView2.setText(this.a.S);
        textView2.setTextColor(-10066330);
        textView2.setGravity(3);
        textView.addView(textView2, new RelativeLayout.LayoutParams(-1, -2));
        this.r = new TextView(this.d);
        this.r.setText(c.bD.E);
        this.r.setTextSize(22.0f);
        this.r.setTextColor(h.a(-1, -730710, -730710, -6745));
        this.r.setGravity(17);
        this.r.setOnClickListener(this.s);
        i = a.n;
        this.r.setBackgroundDrawable(this.c.a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, i);
        layoutParams4.addRule(3, textView.getId());
        layoutParams4.addRule(12, -1);
        layoutParams4.bottomMargin = a.b;
        layoutParams4.topMargin = a.b;
        i = a.d;
        layoutParams4.rightMargin = i;
        layoutParams4.leftMargin = i;
        this.m.addView(this.r, layoutParams4);
    }

    public final void l() {
        u();
    }
}
