package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.upviews.a;
import com.unionpay.mobile.android.utils.PreferenceUtils;
import com.unionpay.mobile.android.utils.g;
import org.json.JSONArray;
import org.json.JSONObject;

public final class i extends AbstractMethod {
    private JSONObject g;
    private JSONObject h;
    private a i;
    private TextView j;
    private TextView k;
    private RelativeLayout l;

    public i(Context context) {
        super(context);
    }

    private static JSONArray e(String str) {
        JSONArray jSONArray = new JSONArray();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("label", "");
            jSONObject.put("name", "user_name");
            if (!AbstractMethod.a(str)) {
                jSONObject.put("value", str);
            }
            jSONObject.put("regexp", "[.@_A-Za-z0-9]{1,64}");
            jSONObject.put("type", "user_name");
            jSONObject.put("tip", "");
            jSONObject.put("placeholder", c.bD.bw);
            jSONArray.put(jSONObject);
            jSONObject = new JSONObject();
            jSONObject.put("label", "");
            jSONObject.put("name", "password");
            jSONObject.put("type", "password");
            jSONObject.put("placeholder", c.bD.bx);
            jSONArray.put(jSONObject);
        } catch (Exception e) {
        }
        return jSONArray;
    }

    public final i a(JSONObject jSONObject) {
        this.g = jSONObject;
        if (this.j != null) {
            Object a = AbstractMethod.a(this.g, "label");
            if (!TextUtils.isEmpty(a)) {
                this.j.setText(Html.fromHtml(a));
                if (this.l != null) {
                    this.l.setVisibility(0);
                }
            }
        }
        return this;
    }

    public final void a(RelativeLayout relativeLayout) {
        View textView = new TextView(this.b);
        textView.setText(this.c);
        textView.setTextColor(-13421773);
        textView.setTextSize(b.k);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        layoutParams.leftMargin = g.a(this.b, TitleBar.SHAREBTN_RIGHT_MARGIN);
        relativeLayout.addView(textView, layoutParams);
    }

    public final int b() {
        return l.c.intValue();
    }

    public final i b(String str) {
        this.c = str;
        return this;
    }

    public final i b(JSONObject jSONObject) {
        this.h = jSONObject;
        if (this.k != null) {
            Object a = AbstractMethod.a(this.h, "label");
            if (!TextUtils.isEmpty(a)) {
                this.k.setText(Html.fromHtml(a));
                if (this.l != null) {
                    this.l.setVisibility(0);
                }
            }
        }
        return this;
    }

    public final void b(RelativeLayout relativeLayout) {
        this.i = new a(this.b, e(PreferenceUtils.c(this.b)), this, "");
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
        relativeLayout.addView(this.i, layoutParams);
    }

    public final a.a c() {
        return this.i != null ? this.i.b() : null;
    }

    public final void c(RelativeLayout relativeLayout) {
        CharSequence a = AbstractMethod.a(this.g, "label");
        this.j = new TextView(this.b);
        AbstractMethod.a(this.j);
        if (!TextUtils.isEmpty(a)) {
            this.j.setText(Html.fromHtml(a));
        }
        this.j.setOnClickListener(new j(this));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        layoutParams.leftMargin = g.a(this.b, TitleBar.SHAREBTN_RIGHT_MARGIN);
        relativeLayout.addView(this.j, layoutParams);
        CharSequence a2 = AbstractMethod.a(this.h, "label");
        this.k = new TextView(this.b);
        AbstractMethod.a(this.k);
        if (!TextUtils.isEmpty(a2)) {
            this.k.setText(Html.fromHtml(a2));
        }
        this.k.setOnClickListener(new k(this));
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(11, -1);
        layoutParams2.addRule(15, -1);
        layoutParams2.rightMargin = g.a(this.b, TitleBar.SHAREBTN_RIGHT_MARGIN);
        relativeLayout.addView(this.k, layoutParams2);
        if (TextUtils.isEmpty(a2) && TextUtils.isEmpty(a)) {
            relativeLayout.setVisibility(8);
        }
        this.l = relativeLayout;
    }

    public final i d(String str) {
        this.d = str;
        return this;
    }

    public final String e() {
        return this.d;
    }

    public final boolean f() {
        return this.i == null || this.i.e();
    }

    public final String h() {
        return this.i != null ? this.i.b("user_name") : "";
    }

    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        TextUtils.isEmpty(h());
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.i != null) {
            this.i.f();
        }
    }

    public final void u() {
    }
}
