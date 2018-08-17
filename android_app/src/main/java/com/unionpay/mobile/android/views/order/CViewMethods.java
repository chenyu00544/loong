package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.utils.g;
import java.util.HashMap;

public class CViewMethods extends LinearLayout {
    private static final Integer a = Integer.valueOf(-1);
    private static final Integer b = Integer.valueOf(-2);
    private static final int c = b.a;
    private Context d;
    private int e;
    private TextView f;
    private HashMap<Integer, String> g;
    private HashMap<Integer, Drawable> h;
    private String i;
    private a j;
    private Drawable k;

    public interface a {
        void c(int i);
    }

    public CViewMethods(Context context) {
        this(context, null);
    }

    public CViewMethods(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CViewMethods(Context context, AttributeSet attributeSet, int i) {
        super(context);
        this.d = context;
        this.e = l.a.intValue();
        setOrientation(1);
    }

    private void a(LinearLayout linearLayout, int i) {
        float f = b.k;
        View relativeLayout = new RelativeLayout(this.d);
        relativeLayout.setClickable(true);
        if (this.k != null) {
            relativeLayout.setBackgroundDrawable(this.k.getConstantState().newDrawable());
        }
        relativeLayout.setOnClickListener(new h(this, i));
        linearLayout.addView(relativeLayout, new LayoutParams(a.intValue(), b.n));
        View imageView = new ImageView(this.d);
        imageView.setId(imageView.hashCode());
        if (this.h != null) {
            Drawable drawable = (Drawable) this.h.get(Integer.valueOf(i));
            if (drawable != null) {
                imageView.setBackgroundDrawable(drawable);
            }
        }
        int a = g.a(this.d, 15.0f);
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a);
        layoutParams.addRule(15, -1);
        layoutParams.addRule(11, -1);
        layoutParams.rightMargin = c;
        relativeLayout.addView(imageView, layoutParams);
        View textView = new TextView(this.d);
        textView.setClickable(false);
        textView.setTextSize(f);
        textView.setTextColor(-13421773);
        if (this.g != null) {
            String str = (String) this.g.get(Integer.valueOf(i));
            if (str != null) {
                textView.setText(Html.fromHtml(str));
            }
        }
        ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a.intValue(), b.intValue());
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(9, -1);
        layoutParams2.addRule(0, imageView.getId());
        layoutParams2.leftMargin = c;
        relativeLayout.addView(textView, layoutParams2);
    }

    public final CViewMethods a(int i) {
        if (i > 0) {
            this.e = i;
        }
        return this;
    }

    public final CViewMethods a(Drawable drawable) {
        this.k = drawable;
        return this;
    }

    public final CViewMethods a(a aVar) {
        this.j = aVar;
        return this;
    }

    public final CViewMethods a(String str) {
        this.i = str;
        return this;
    }

    public final CViewMethods a(HashMap<Integer, String> hashMap) {
        this.g = hashMap;
        return this;
    }

    public final void a() {
        removeAllViews();
        if (this.e == l.a.intValue()) {
            setVisibility(8);
            return;
        }
        this.f = new TextView(this.d);
        this.f.setTextColor(-13421773);
        this.f.setTextSize(b.k);
        if (this.i != null) {
            CharSequence charSequence = this.i;
            if (this.f != null) {
                this.f.setText(charSequence);
            }
            TextView textView = this.f;
        }
        this.f.setGravity(19);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(a.intValue(), b.intValue());
        layoutParams.gravity = 19;
        layoutParams.topMargin = c;
        layoutParams.leftMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        addView(this.f, layoutParams);
        View linearLayout = new LinearLayout(this.d);
        linearLayout.setBackgroundColor(-3419943);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-1, 1);
        layoutParams2.topMargin = c;
        addView(linearLayout, layoutParams2);
        layoutParams = new LayoutParams(-1, -2);
        View linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setOrientation(1);
        linearLayout2.setBackgroundColor(-1);
        addView(linearLayout2, layoutParams);
        if (l.b.intValue() == (l.b.intValue() & this.e)) {
            a(linearLayout2, l.b.intValue());
        }
        if (l.c.intValue() == (l.c.intValue() & this.e)) {
            a(linearLayout2, l.c.intValue());
        }
        if (l.e.intValue() == (l.e.intValue() & this.e)) {
            linearLayout = new LinearLayout(this.d);
            linearLayout.setBackgroundColor(-3419943);
            ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-1, 1);
            layoutParams3.leftMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
            linearLayout2.addView(linearLayout, layoutParams3);
            a(linearLayout2, l.e.intValue());
        }
        if (l.d.intValue() == (l.d.intValue() & this.e)) {
            linearLayout = new LinearLayout(this.d);
            linearLayout.setBackgroundColor(-3419943);
            layoutParams3 = new LayoutParams(-1, 1);
            layoutParams3.leftMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
            linearLayout2.addView(linearLayout, layoutParams3);
            a(linearLayout2, l.d.intValue());
        }
        if (l.f.intValue() == (l.f.intValue() & this.e)) {
            linearLayout = new LinearLayout(this.d);
            linearLayout.setBackgroundColor(-3419943);
            layoutParams3 = new LayoutParams(-1, 1);
            layoutParams3.leftMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
            linearLayout2.addView(linearLayout, layoutParams3);
            a(linearLayout2, l.f.intValue());
        }
        linearLayout = new LinearLayout(this.d);
        linearLayout.setBackgroundColor(-3419943);
        addView(linearLayout, new LayoutParams(-1, 1));
    }

    public final CViewMethods b(HashMap<Integer, Drawable> hashMap) {
        this.h = hashMap;
        return this;
    }
}
