package com.unionpay.mobile.android.nocard.views;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.i;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.views.order.l;
import com.unionpay.mobile.android.widgets.as;
import com.unionpay.mobile.android.widgets.ay;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class bd extends b {
    private TextView r = null;
    private OnClickListener s = new ad(this);
    private a t;

    public class a extends LinearLayout {
        final /* synthetic */ bd a;
        private Context b;

        public a(bd bdVar, Context context) {
            this.a = bdVar;
            super(context);
            this.b = context;
            setOrientation(1);
        }

        private void a(JSONArray jSONArray) {
            if (jSONArray != null && jSONArray.length() > 0) {
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                View linearLayout = new LinearLayout(this.a.d);
                linearLayout.setOrientation(1);
                linearLayout.setBackgroundColor(-1);
                addView(linearLayout, layoutParams);
                LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, b.n);
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        CharSequence a = j.a(jSONObject, "label");
                        String a2 = j.a(jSONObject, "url");
                        View linearLayout2 = new LinearLayout(this.a.d);
                        linearLayout2.setBackgroundColor(-3419943);
                        LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, 1);
                        if (i != 0) {
                            layoutParams3.leftMargin = g.a(this.a.d, 12.0f);
                        }
                        linearLayout.addView(linearLayout2, layoutParams3);
                        Context context = this.b;
                        View relativeLayout = new RelativeLayout(this.b);
                        relativeLayout.setBackgroundDrawable(this.a.c.a(2014));
                        relativeLayout.setOnClickListener(new ag(this, i, a, a2));
                        View textView = new TextView(context);
                        textView.setText(a);
                        textView.setTextSize(b.k);
                        textView.setTextColor(-13421773);
                        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
                        layoutParams4.addRule(15, -1);
                        layoutParams4.addRule(9, -1);
                        layoutParams4.leftMargin = g.a(context, 12.0f);
                        relativeLayout.addView(textView, layoutParams4);
                        int a3 = g.a(context, TitleBar.BACKBTN_LEFT_MARGIN);
                        Drawable a4 = this.a.c.a(1002);
                        View imageView = new ImageView(context);
                        imageView.setBackgroundDrawable(a4);
                        layoutParams4 = new RelativeLayout.LayoutParams(a3, a3);
                        layoutParams4.addRule(15, -1);
                        layoutParams4.addRule(11, -1);
                        layoutParams4.rightMargin = g.a(context, 12.0f);
                        relativeLayout.addView(imageView, layoutParams4);
                        linearLayout.addView(relativeLayout, layoutParams2);
                        if (i == jSONArray.length() - 1) {
                            textView = new LinearLayout(this.a.d);
                            textView.setBackgroundColor(-3419943);
                            linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, 1));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public final void a(JSONObject jSONObject) {
            removeAllViews();
            CharSequence a = j.a(jSONObject, "label");
            bd bdVar = this.a;
            if (b.b((String) a)) {
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.leftMargin = g.a(this.a.d, 12.0f);
                layoutParams.bottomMargin = com.unionpay.mobile.android.d.a.f;
                View textView = new TextView(this.b);
                textView.setText(a);
                textView.setTextSize(b.k);
                textView.setTextColor(-25009);
                addView(textView, layoutParams);
            }
            a(j.d(jSONObject, "options"));
        }
    }

    public bd(Context context) {
        super(context);
        e();
        this.q = "result";
        this.a.aq = null;
        if (this.a.aV) {
            this.e.c("bingopromotion", "");
        }
    }

    private void u() {
        String str = this.a.aQ;
        k.c("functionEx", str);
        if (this.a.aQ == l.b.intValue() || this.a.aQ == l.c.intValue()) {
            PreferenceUtils.c(this.d, str);
        }
        this.a.I.f = "success";
        k();
    }

    public final void a(JSONObject jSONObject) {
        JSONObject c = j.c(jSONObject, "luck_draw");
        if (c != null) {
            this.t.setVisibility(0);
            this.t.a(c);
        }
    }

    protected final void b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        View ayVar = new ay(getContext(), this.a.A, this);
        ayVar.a();
        layoutParams.addRule(13, -1);
        this.k.addView(ayVar, layoutParams);
    }

    public final void b(int i) {
    }

    protected final void c() {
        g();
        this.m.invalidate();
        if (this.o != null) {
            this.o.setBackgroundColor(-1052684);
        }
        View relativeLayout = new RelativeLayout(this.d);
        relativeLayout.setBackgroundColor(-1052684);
        relativeLayout.setId(relativeLayout.hashCode());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(10, -1);
        layoutParams.topMargin = com.unionpay.mobile.android.d.a.d;
        this.m.addView(relativeLayout, layoutParams);
        View relativeLayout2 = new RelativeLayout(this.d);
        relativeLayout2.setId(relativeLayout2.hashCode());
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.topMargin = com.unionpay.mobile.android.d.a.d;
        relativeLayout.addView(relativeLayout2, layoutParams2);
        int i = com.unionpay.mobile.android.d.a.n;
        Drawable a = this.c.a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE);
        this.r = new TextView(this.d);
        this.r.setId(this.r.hashCode());
        this.r.setText(c.bD.E);
        this.r.setTextSize(b.i);
        this.r.setTextColor(b.p());
        this.r.setGravity(17);
        this.r.setOnClickListener(this.s);
        this.r.setBackgroundDrawable(a);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, i);
        i = g.a(this.d, 12.0f);
        layoutParams3.rightMargin = i;
        layoutParams3.leftMargin = i;
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(15, -1);
        relativeLayout2.addView(this.r, layoutParams3);
        this.t = new a(this, this.d);
        this.t.setVisibility(8);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(3, relativeLayout2.getId());
        layoutParams2.topMargin = g.a(this.d, TitleBar.BACKBTN_LEFT_MARGIN);
        relativeLayout.addView(this.t, layoutParams2);
    }

    protected final void f() {
        CharSequence charSequence = null;
        View linearLayout;
        int a;
        View textView;
        if (this.a.H != null) {
            LayoutParams layoutParams;
            linearLayout = new LinearLayout(this.d);
            linearLayout.setOrientation(1);
            a = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
            if (!TextUtils.isEmpty(this.a.B)) {
                textView = new TextView(this.d);
                textView.setText(this.a.B);
                textView.setTextSize(24.0f);
                textView.setTextColor(-15365480);
                textView.setGravity(1);
                textView.setPadding(0, com.unionpay.mobile.android.d.a.d, 0, 0);
                textView.getPaint().setFakeBoldText(true);
                linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
                textView = new LinearLayout(this.d);
                textView.setOrientation(0);
                textView.setBackgroundColor(-6958338);
                LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 1);
                int i = com.unionpay.mobile.android.d.a.d;
                layoutParams2.bottomMargin = i;
                layoutParams2.topMargin = i;
                linearLayout.addView(textView, layoutParams2);
                this.l.addView(linearLayout);
            }
            JSONArray jSONArray = this.a.H;
            if (jSONArray != null) {
                int length = jSONArray.length() >= 2 ? 2 : jSONArray.length();
                View a2 = ah.a(this.d, jSONArray, 0, length);
                a2.setBackgroundColor(0);
                LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
                layoutParams3.rightMargin = a;
                layoutParams3.leftMargin = a;
                linearLayout.addView(a2, layoutParams3);
                textView = ah.a(this.d, jSONArray, length, jSONArray.length());
                textView.setBackgroundColor(0);
                layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.rightMargin = a;
                layoutParams.leftMargin = a;
                this.l.addView(textView, layoutParams);
            }
            textView = new as(this.d);
            textView.setId(textView.hashCode());
            this.l.setOnClickListener(new ae(this));
            layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.bottomMargin = 0;
            this.l.addView(textView, layoutParams);
            return;
        }
        length = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        a2 = new LinearLayout(this.d);
        a2.setPadding(length, length, length, length);
        a2.setOrientation(1);
        a2.setBackgroundColor(-1);
        this.l.addView(a2);
        View linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(0);
        a2.addView(linearLayout2);
        int a3 = g.a(this.d, 25.0f);
        View imageView = new ImageView(this.d);
        imageView.setBackgroundDrawable(this.c.a(1035));
        linearLayout2.addView(imageView, new LinearLayout.LayoutParams(a3, a3));
        imageView = new LinearLayout(this.d);
        imageView.setOrientation(1);
        layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.leftMargin = length;
        linearLayout2.addView(imageView, layoutParams2);
        String str = this.a.aR;
        if (!i.b(str)) {
            String substring;
            String str2;
            int indexOf = str.indexOf(60);
            String substring2 = -1 != indexOf ? str.substring(0, indexOf) : null;
            int indexOf2 = str.indexOf(62) + 1;
            String substring3 = -1 != indexOf2 ? str.substring(indexOf2) : null;
            a = indexOf + 1;
            int i2 = indexOf2 - 1;
            if (!(-1 == indexOf || a >= i2 || indexOf2 == -1)) {
                substring = str.substring(a, i2);
                if (!i.b(substring)) {
                    String[] split = substring.split("#");
                    if (split != null && split.length == 2) {
                        substring = split[0];
                        str2 = split[1];
                        charSequence = i.a(substring2, substring, str2, substring3);
                    }
                }
            }
            substring = null;
            charSequence = i.a(substring2, substring, str2, substring3);
        }
        if (charSequence != null) {
            textView = new TextView(this.d);
            textView.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN);
            textView.setText(charSequence);
            imageView.addView(textView);
        }
        if (b.b(this.a.aS)) {
            textView = new TextView(this.d);
            textView.setTextSize(b.l);
            textView.setText(this.a.aS);
            textView.setTextColor(-10066330);
            imageView.addView(textView);
        }
        if (b.b(this.a.aT)) {
            textView = new TextView(this.d);
            textView.setTextSize(b.l);
            textView.setTextColor(-10066330);
            textView.setText(this.a.aT);
            imageView.addView(textView);
        }
        length = g.a(this.d, 5.0f);
        linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(-1052684);
        linearLayout.setPadding(length, length, length, length);
        LayoutParams layoutParams4 = new LinearLayout.LayoutParams(-1, -2);
        layoutParams4.topMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        a2.addView(linearLayout, layoutParams4);
        linearLayout.setVisibility(8);
        if ("0".equals(this.a.aW) && b.b(this.a.aX)) {
            textView = new TextView(this.d);
            textView.setTextSize(b.l);
            textView.setText(this.a.aX);
            linearLayout.addView(textView);
            linearLayout.setVisibility(0);
        }
        Drawable a4 = this.c.a(1026);
        linearLayout = new LinearLayout(this.d);
        if (a4 != null) {
            linearLayout.setBackgroundDrawable(a4);
        }
        this.l.addView(linearLayout, new LinearLayout.LayoutParams(-1, g.a(this.d, 2.0f)));
    }

    public final void l() {
        u();
    }
}
