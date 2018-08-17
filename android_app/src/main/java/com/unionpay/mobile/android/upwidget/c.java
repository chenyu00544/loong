package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.utils.g;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class c extends BaseAdapter {
    private Drawable a;
    private Drawable b;
    private Drawable c;
    private Context d;
    private String e = null;
    private String f = null;
    private String g = null;
    private boolean h = false;
    private int i = 1;
    private int j = 0;
    private List<Map<String, Object>> k;
    private ArrayList<OnClickListener> l = new ArrayList();
    private OnClickListener m = new d(this);

    public c(Context context, List<Map<String, Object>> list, String str, String str2, String str3, int i, int i2) {
        this.d = context;
        this.k = list;
        this.e = str;
        this.f = str2;
        this.g = str3;
        this.i = i;
        this.j = i2;
        this.a = com.unionpay.mobile.android.j.c.a(this.d).a(1015);
        this.b = com.unionpay.mobile.android.j.c.a(this.d).a(1016);
        this.c = com.unionpay.mobile.android.j.c.a(this.d).a(1002);
    }

    private boolean d() {
        return (this.e == null || TextUtils.isEmpty(this.e)) ? false : true;
    }

    private boolean d(int i) {
        int c = i - c();
        if (c == this.k.size()) {
            return true;
        }
        Object obj = ((Map) this.k.get(c)).get("available");
        boolean z = obj == null || Boolean.FALSE != ((Boolean) obj);
        return z;
    }

    private boolean e() {
        return (this.f == null || TextUtils.isEmpty(this.f)) ? false : true;
    }

    public final void a() {
        this.h = !this.h;
    }

    public final void a(int i) {
        this.i = i;
    }

    public final void a(OnClickListener onClickListener) {
        this.l.add(onClickListener);
    }

    public final void a(String str) {
        this.e = str;
    }

    public final Spanned b(int i) {
        int c = i - c();
        if (c == this.k.size()) {
            return null;
        }
        Map map = (Map) this.k.get(c);
        String str = (String) map.get("text1");
        return Html.fromHtml(str + " " + ((String) map.get("text2")));
    }

    public final void b(String str) {
        this.g = str;
    }

    public final boolean b() {
        return this.h;
    }

    public final int c() {
        return d() ? 1 : 0;
    }

    public final boolean c(int i) {
        int c = i - c();
        if (c == this.k.size()) {
            return true;
        }
        Object obj = ((Map) this.k.get(c)).get("editable");
        boolean z = obj == null || Boolean.FALSE != ((Boolean) obj);
        return z;
    }

    public final int getCount() {
        int i = 0;
        if (this.k == null) {
            return 0;
        }
        int size = this.k.size() + c();
        if (e()) {
            i = 1;
        }
        return i + size;
    }

    public final Object getItem(int i) {
        return (i == 0 || this.k == null || i >= this.k.size()) ? null : this.k.get(i - c());
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        View linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        View relativeLayout = new RelativeLayout(this.d);
        int i2 = b.g;
        relativeLayout.setPadding(i2, i2, i2, i2);
        linearLayout.addView(relativeLayout);
        View linearLayout2 = new LinearLayout(this.d);
        linearLayout2.setBackgroundColor(-3419943);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 1);
        Object obj = i - c() == this.k.size() ? 1 : null;
        View textView;
        LayoutParams layoutParams2;
        if (d() && i == 0) {
            textView = new TextView(this.d);
            textView.setText(this.e);
            textView.setTextSize(b.k);
            textView.setTextColor(-13421773);
            layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(9, -1);
            layoutParams2.addRule(15, -1);
            layoutParams2.leftMargin = b.f;
            relativeLayout.addView(textView, layoutParams2);
            obj = (this.g == null || TextUtils.isEmpty(this.g)) ? null : 1;
            if (obj != null) {
                textView = new TextView(this.d);
                textView.setText(this.g);
                textView.setTextSize(b.k);
                textView.setTextColor(-13421773);
                textView.setOnClickListener(this.m);
                layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(11, -1);
                layoutParams2.addRule(15, -1);
                layoutParams2.rightMargin = b.f;
                relativeLayout.addView(textView, layoutParams2);
            }
            linearLayout.addView(linearLayout2, layoutParams);
        } else if (!e() || obj == null) {
            View imageView = new ImageView(this.d);
            imageView.setVisibility(4);
            imageView.setId(imageView.hashCode());
            View textView2 = new TextView(this.d);
            textView2.setSingleLine(true);
            textView2.setEllipsize(TruncateAt.END);
            textView2.setText(b(i));
            textView2.setTextSize(b.k);
            textView2.setTextColor(-10066330);
            int a = g.a(this.d, TitleBar.BACKBTN_LEFT_MARGIN);
            Drawable drawable;
            if (this.j == 0) {
                drawable = this.h ? this.b : this.a;
                if (!this.h && this.i == i && drawable != null) {
                    imageView.setVisibility(0);
                    imageView.setBackgroundDrawable(drawable);
                } else if (c(i) && this.h && drawable != null) {
                    imageView.setVisibility(0);
                    imageView.setBackgroundDrawable(drawable);
                }
                layoutParams2 = new RelativeLayout.LayoutParams(a, a);
                layoutParams2.addRule(15, -1);
                layoutParams2.addRule(9, -1);
                relativeLayout.addView(imageView, layoutParams2);
                layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(15, -1);
                layoutParams2.addRule(1, imageView.hashCode());
                layoutParams2.leftMargin = b.g;
                relativeLayout.addView(textView2, layoutParams2);
            } else if (this.j == 1) {
                r1 = this.i == i ? 1008 : 1007;
                int a2 = g.a(this.d, TitleBar.BACKBTN_LEFT_MARGIN);
                drawable = com.unionpay.mobile.android.j.c.a(this.d).a(r1, a, a);
                if (d(i)) {
                    imageView.setVisibility(0);
                }
                imageView.setBackgroundDrawable(drawable);
                if (this.h) {
                    drawable = this.b;
                } else {
                    drawable = this.a;
                }
                layoutParams2 = new RelativeLayout.LayoutParams(a2, a2);
                layoutParams2.addRule(15, -1);
                layoutParams2.addRule(11, -1);
                relativeLayout.addView(imageView, layoutParams2);
                layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams2.addRule(15, -1);
                layoutParams2.addRule(9, -1);
                layoutParams2.addRule(0, imageView.hashCode());
                layoutParams2.rightMargin = b.g;
                relativeLayout.addView(textView2, layoutParams2);
                layoutParams.leftMargin = i2;
            }
            if (!e() || obj == null) {
                linearLayout.addView(linearLayout2, layoutParams);
            }
        } else {
            textView = new TextView(this.d);
            textView.setText(this.f);
            textView.setTextSize(b.k);
            textView.setTextColor(-10066330);
            layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(15, -1);
            layoutParams2.addRule(9, -1);
            relativeLayout.addView(textView, layoutParams2);
            textView = new ImageView(this.d);
            textView.setBackgroundDrawable(this.c);
            r1 = g.a(this.d, TitleBar.BACKBTN_LEFT_MARGIN);
            LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(r1, r1);
            layoutParams3.addRule(15, -1);
            layoutParams3.addRule(11, -1);
            relativeLayout.addView(textView, layoutParams3);
        }
        return linearLayout;
    }

    public final boolean isEnabled(int i) {
        return (!(d() && i == 0) && d(i)) ? super.isEnabled(i) : false;
    }
}
