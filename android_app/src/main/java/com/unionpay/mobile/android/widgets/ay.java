package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.j.c;
import com.unionpay.mobile.android.utils.g;

public final class ay extends RelativeLayout {
    private Context a;
    private TextView b;
    private ImageView c;
    private TextView d;
    private Drawable e;
    private ImageView f;
    private LinearLayout g;
    private int h;
    private int i;
    private int j;
    private a k;

    public interface a {
        void m();
    }

    public ay(Context context, String str, Drawable drawable, int i, a aVar) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.a = context;
        this.k = aVar;
        this.j = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.e = drawable;
        this.h = i;
        b(str);
    }

    public ay(Context context, String str, a aVar) {
        this(context, str, aVar, (byte) 0);
    }

    private ay(Context context, String str, a aVar, byte b) {
        super(context);
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.a = context;
        this.k = aVar;
        this.j = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
        b(str);
    }

    private void b(String str) {
        this.i = com.unionpay.mobile.android.d.a.k;
        setLayoutParams(new LayoutParams(-1, this.i));
        setBackgroundColor(com.unionpay.mobile.android.d.a.M);
        c a = c.a(this.a);
        this.g = new LinearLayout(this.a);
        this.g.setOnClickListener(new t(this));
        this.g.setPadding(this.j, this.j, this.j, this.j);
        this.g.setGravity(16);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        addView(this.g, layoutParams);
        int a2 = g.a(this.a, TitleBar.BACKBTN_LEFT_MARGIN);
        int a3 = g.a(this.a, 11.0f);
        if (this.h != 0) {
            a3 = this.h;
        }
        View imageView = new ImageView(this.a);
        if (this.e != null) {
            imageView.setBackgroundDrawable(this.e);
        } else {
            imageView.setBackgroundDrawable(a.a(1029));
        }
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(a3, a2);
        layoutParams2.addRule(15, -1);
        this.g.addView(imageView, layoutParams2);
        layoutParams = new LayoutParams(com.unionpay.mobile.android.d.a.l, this.i);
        layoutParams.addRule(13, -1);
        this.d = new TextView(this.a);
        this.d.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN);
        this.d.setTextColor(-1);
        this.d.setText(str);
        this.d.setGravity(17);
        this.d.setSingleLine(true);
        this.d.setEllipsize(TruncateAt.END);
        addView(this.d, layoutParams);
        if (!TextUtils.isEmpty(null)) {
            a3 = com.unionpay.mobile.android.d.a.e;
            ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-2, this.i);
            layoutParams3.addRule(11, -1);
            layoutParams3.addRule(15, -1);
            layoutParams3.rightMargin = a3;
            this.b = new TextView(this.a);
            this.b.setTextSize(16.0f);
            this.b.setTextColor(-1);
            this.b.setText(null);
            this.b.setGravity(16);
            this.b.setId(this.b.hashCode());
            addView(this.b, layoutParams3);
            layoutParams2 = new LayoutParams(com.unionpay.mobile.android.d.a.H, com.unionpay.mobile.android.d.a.m);
            layoutParams2.addRule(0, this.b.getId());
            layoutParams2.addRule(15, -1);
            layoutParams2.rightMargin = a3;
            addView(new o(this.a, com.unionpay.mobile.android.d.a.N, 1), layoutParams2);
        }
        layoutParams = new LayoutParams(com.unionpay.mobile.android.d.a.E, com.unionpay.mobile.android.d.a.D);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(15, -1);
        layoutParams.rightMargin = this.j;
        Drawable a4 = a.a(1031);
        this.f = new ImageView(this.a);
        this.f.setBackgroundDrawable(a4);
        addView(this.f, layoutParams);
        layoutParams = new LayoutParams(com.unionpay.mobile.android.d.a.D, com.unionpay.mobile.android.d.a.D);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(15, -1);
        layoutParams.rightMargin = this.j;
        this.c = new ImageView(this.a);
        addView(this.c, layoutParams);
    }

    public final void a() {
        if (this.g != null) {
            this.g.setVisibility(8);
        }
    }

    public final void a(int i) {
        if (this.c != null) {
            if (i == 0) {
                this.f.setVisibility(8);
                this.c.setVisibility(8);
            } else {
                this.c.setVisibility(8);
                this.f.setVisibility(0);
            }
            this.c.setVisibility(i);
        }
    }

    public final void a(String str) {
        if (this.d != null) {
            this.d.setText(str);
        }
    }

    public final void b() {
        setBackgroundColor(-16686660);
    }
}
