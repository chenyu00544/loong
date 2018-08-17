package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.d.a;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.j.c;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import java.lang.ref.WeakReference;

public final class an {
    private static final float k = b.k;
    private static final float l = b.k;
    private static final float m = b.j;
    private Context a;
    private TextView b;
    private WeakReference<OnClickListener> c;
    private TextView d;
    private WeakReference<OnClickListener> e;
    private int f;
    private c g;
    private Dialog h;
    private Drawable i;
    private WeakReference<OnDismissListener> j;

    public an(Context context) {
        this(context, (byte) 0);
    }

    private an(Context context, byte b) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = 0;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.a = context;
        this.j = new WeakReference(null);
        this.g = c.a(context);
        this.f = a.I - (a.b * 4);
        this.i = this.g.a(1028, this.f / 2, -1);
    }

    private RelativeLayout a(Context context) {
        c();
        this.h = new aq(this, context);
        if (!(this.j == null || this.j.get() == null)) {
            this.h.setOnDismissListener((OnDismissListener) this.j.get());
        }
        this.h.setCanceledOnTouchOutside(false);
        this.h.setOwnerActivity((Activity) context);
        this.h.requestWindowFeature(1);
        this.h.getWindow().setBackgroundDrawable(this.g.a(4004));
        View relativeLayout = new RelativeLayout(this.a);
        this.h.getWindow().setBackgroundDrawable(this.g.a(4004));
        this.h.setContentView(relativeLayout, new LayoutParams(this.f, -2));
        View relativeLayout2 = new RelativeLayout(this.a);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(13, -1);
        relativeLayout.addView(relativeLayout2, layoutParams);
        return relativeLayout;
    }

    private boolean d() {
        return ((Activity) this.a).isFinishing();
    }

    public final void a(OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.c = new WeakReference(onClickListener);
        this.e = new WeakReference(onClickListener2);
    }

    public final void a(String str) {
        int a = g.a(this.a, 12.0f);
        g.a(this.a, TitleBar.BACKBTN_LEFT_MARGIN);
        RelativeLayout a2 = a(this.a);
        a2.setBackgroundColor(a.M);
        if (this.h != null) {
            WindowManager.LayoutParams attributes = this.h.getWindow().getAttributes();
            attributes.alpha = 0.7f;
            this.h.getWindow().setAttributes(attributes);
        }
        View linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        int i = this.f - (a.j << 1);
        View imageView = new ImageView(this.a);
        imageView.setImageDrawable(this.i);
        linearLayout.addView(imageView, new LinearLayout.LayoutParams(i, -2));
        View textView = new TextView(this.a);
        textView.setTextSize(l);
        textView.setTextColor(-1);
        textView.setText(str);
        textView.setGravity(16);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.topMargin = a;
        linearLayout.addView(textView, layoutParams);
        LinearLayout linearLayout2 = new LinearLayout(this.a);
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(17);
        i = a.p;
        layoutParams = new LinearLayout.LayoutParams(i, i);
        layoutParams.topMargin = a;
        linearLayout.addView(new ProgressBar(this.a), layoutParams);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, -2);
        layoutParams2.addRule(10, -1);
        layoutParams2.addRule(14, -1);
        i = g.a(this.a, TitleBar.BACKBTN_LEFT_MARGIN);
        a2.setPadding(i, i, i, i);
        a2.addView(linearLayout, layoutParams2);
        if (this.h != null && !this.h.isShowing() && !d()) {
            this.h.show();
        }
    }

    public final void a(String str, String str2, String str3) {
        View textView;
        RelativeLayout a = a(this.a);
        int i = a.b;
        View linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        a.addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
        if (!(str == null || str.length() == 0)) {
            textView = new TextView(this.a);
            textView.getPaint().setFakeBoldText(true);
            textView.setTextSize(k);
            textView.setTextColor(-13421773);
            textView.setText(str);
            textView.setGravity(17);
            textView.setPadding(i, i << 1, i, i);
            linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        }
        textView = new TextView(this.a);
        textView.setTextSize(l);
        textView.setTextColor(-13421773);
        textView.setText(str2);
        textView.setPadding(i, i, i, 0);
        textView.setGravity(17);
        linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        View frameLayout = new FrameLayout(this.a);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = a.b << 1;
        linearLayout.addView(frameLayout, layoutParams);
        textView = new LinearLayout(this.a);
        textView.setOrientation(1);
        frameLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(0);
        linearLayout.setBackgroundColor(-7829368);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
        textView.addView(linearLayout);
        linearLayout = new LinearLayout(this.a);
        textView.addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        this.b = new TextView(this.a);
        this.b.setPadding(5, 5, 5, 5);
        this.b.getPaint().setFakeBoldText(true);
        this.b.setText(str3);
        this.b.setTextSize(m);
        this.b.setTextColor(h.a(-15364869, -5846275));
        this.b.setGravity(17);
        int i2 = a.o;
        if (!(this.c == null || this.c.get() == null)) {
            this.b.setOnClickListener((OnClickListener) this.c.get());
        }
        linearLayout.addView(this.b, new LinearLayout.LayoutParams(-1, i2));
        frameLayout.addView(new o(this.a), new FrameLayout.LayoutParams(-1, a.H));
        if (this.h != null && !this.h.isShowing() && !d()) {
            this.h.show();
        }
    }

    public final void a(String str, String str2, String str3, String str4) {
        a(str, str2, str3, str4, true);
    }

    public final void a(String str, String str2, String str3, String str4, boolean z) {
        View textView;
        RelativeLayout a = a(this.a);
        int i = a.b;
        View linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(1);
        a.addView(linearLayout, new LinearLayout.LayoutParams(-1, -2));
        if (!(str == null || str.length() == 0)) {
            textView = new TextView(this.a);
            textView.getPaint().setFakeBoldText(true);
            textView.setTextSize(k);
            textView.setTextColor(-13421773);
            textView.setText(str);
            textView.setGravity(17);
            textView.setPadding(i, i << 1, i, i);
            linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        }
        textView = new TextView(this.a);
        textView.setTextSize(l);
        textView.setTextColor(-13421773);
        textView.setText(str2);
        textView.setPadding(i, i, i, 0);
        textView.setGravity(17);
        linearLayout.addView(textView, new LinearLayout.LayoutParams(-1, -2));
        i = g.a(this.a, 1.0f);
        textView = new LinearLayout(this.a);
        textView.setOrientation(0);
        textView.setBackgroundColor(-7829368);
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, i);
        layoutParams.topMargin = a.b << 1;
        linearLayout.addView(textView, layoutParams);
        View linearLayout2 = new LinearLayout(this.a);
        linearLayout2.setBackgroundColor(-1);
        linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, -2));
        linearLayout2.setOrientation(0);
        linearLayout2.setGravity(17);
        int i2 = (this.f - a.H) >> 1;
        this.b = new TextView(this.a);
        if (!z) {
            this.b.getPaint().setFakeBoldText(true);
        }
        this.b.setText(str3);
        this.b.setTextSize(m);
        this.b.setTextColor(h.a(-15364869, -5846275));
        this.b.setGravity(17);
        int i3 = a.o;
        if (!(this.c == null || this.c.get() == null)) {
            this.b.setOnClickListener((OnClickListener) this.c.get());
        }
        ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(i2, i3);
        layoutParams2.leftMargin = 5;
        layoutParams2.bottomMargin = 5;
        linearLayout2.addView(this.b, layoutParams2);
        textView = new LinearLayout(this.a);
        textView.setOrientation(1);
        textView.setBackgroundColor(-7829368);
        linearLayout2.addView(textView, new LinearLayout.LayoutParams(i, -1));
        this.d = new TextView(this.a);
        if (z) {
            this.d.getPaint().setFakeBoldText(true);
        }
        this.d.setText(str4);
        this.d.setTextSize(m);
        this.d.setTextColor(h.a(-15364869, -5846275));
        this.d.setGravity(17);
        if (!(this.e == null || this.e.get() == null)) {
            this.d.setOnClickListener((OnClickListener) this.e.get());
        }
        layoutParams2 = new LinearLayout.LayoutParams(i2, i3);
        layoutParams2.leftMargin = 5;
        layoutParams2.bottomMargin = 5;
        linearLayout2.addView(this.d, layoutParams2);
        if (this.h != null && !this.h.isShowing() && !d()) {
            this.h.show();
        }
    }

    public final boolean a() {
        return this.h != null && this.h.isShowing();
    }

    public final void b() {
        if (this.h != null) {
            this.h.hide();
            this.h.show();
        }
    }

    public final void c() {
        if (this.h != null && this.h.isShowing()) {
            this.h.dismiss();
            this.h = null;
        }
    }
}
