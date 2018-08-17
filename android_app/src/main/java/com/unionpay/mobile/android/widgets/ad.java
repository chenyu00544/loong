package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.umeng.analytics.pro.x;
import com.unionpay.mobile.android.d.a;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public final class ad extends z {
    private int a;
    private String b;
    private TextView c;
    private TextView o;

    public ad(Context context, int i, JSONObject jSONObject, String str) {
        this(context, i, jSONObject, str, (byte) 0);
    }

    private ad(Context context, int i, JSONObject jSONObject, String str, byte b) {
        super(context, jSONObject, str);
        this.a = 0;
        this.a = i;
        if (jSONObject != null) {
            this.b = j.a(jSONObject, x.P);
        }
        RelativeLayout relativeLayout = this.m;
        View linearLayout = new LinearLayout(this.d);
        relativeLayout.addView(linearLayout, new LayoutParams(-1, -2));
        linearLayout.setOrientation(0);
        int i2 = this.a;
        i2 = this.a;
        i2 = a.f;
        this.c = new TextView(this.d);
        this.c.setTextSize(b.k);
        this.c.setText(p());
        this.c.setGravity(3);
        this.c.setTextColor(-13421773);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -2, 0.3f);
        layoutParams.gravity = 3;
        linearLayout.addView(this.c, layoutParams);
        this.o = new TextView(this.d);
        this.o.setGravity(16);
        this.o.setTextSize(b.k);
        this.o.setText(com.unionpay.mobile.android.b.a.a(i(), this.b));
        this.o.setPadding(0, 0, a.g, 0);
        layoutParams = new LinearLayout.LayoutParams(0, -2, 0.7f);
        layoutParams.gravity = 21;
        linearLayout.addView(this.o, layoutParams);
    }

    public ad(Context context, JSONObject jSONObject, String str) {
        super(context, jSONObject, str);
        this.a = 0;
        int i = a.f;
        String p = p();
        if (p != null && p.length() > 0) {
            this.c = new TextView(this.d);
            this.c.setTextSize(b.k);
            this.c.setText(p());
            this.c.setTextColor(-7829368);
            addView(this.c);
        }
        p = i();
        if (p != null && p.length() > 0) {
            this.o = new TextView(this.d);
            this.o.setTextSize(b.k);
            this.o.setTextColor(-7829368);
            this.o.setText(i());
            addView(this.o);
        }
    }

    public final String a() {
        return null;
    }

    public final void a(float f) {
        this.o.setTextSize(f);
    }

    public final boolean b() {
        return true;
    }

    public final boolean c() {
        return true;
    }

    public final void g() {
        this.o.setTextColor(-6710887);
    }
}
