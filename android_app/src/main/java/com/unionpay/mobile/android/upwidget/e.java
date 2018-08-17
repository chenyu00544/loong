package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
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
import com.umeng.analytics.pro.x;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.j.c;
import com.unionpay.mobile.android.utils.g;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class e extends BaseAdapter {
    private Drawable a;
    private Context b;
    private String c = null;
    private String d = null;
    private int e = 1;
    private List<Map<String, Object>> f;
    private ArrayList<OnClickListener> g = new ArrayList();
    private OnClickListener h = new f(this);

    public e(Context context, List<Map<String, Object>> list, String str) {
        this.b = context;
        this.f = list;
        this.c = str;
        this.e = 0;
        this.a = c.a(this.b).a(1015);
    }

    private boolean b() {
        return (this.c == null || TextUtils.isEmpty(this.c)) ? false : true;
    }

    private boolean b(int i) {
        int a = i - a();
        if (this.f == null || a == this.f.size()) {
            return true;
        }
        Object obj = ((Map) this.f.get(a)).get("available");
        boolean z = obj == null || Boolean.FALSE != ((Boolean) obj);
        return z;
    }

    public final int a() {
        return b() ? 1 : 0;
    }

    public final void a(int i) {
        this.e = i;
    }

    public final int getCount() {
        int i = 1;
        if (this.f == null) {
            return 0;
        }
        int a = a() + this.f.size();
        int i2 = (this.d == null || TextUtils.isEmpty(this.d)) ? 0 : 1;
        if (i2 == 0) {
            i = 0;
        }
        return a + i;
    }

    public final Object getItem(int i) {
        return (i == 0 || this.f == null || i >= this.f.size()) ? null : this.f.get(i - a());
    }

    public final long getItemId(int i) {
        return (long) i;
    }

    public final View getView(int i, View view, ViewGroup viewGroup) {
        getItem(i);
        Map map = (Map) this.f.get(i - a());
        String str = (String) map.get(x.P);
        List list = (List) map.get("keys");
        List list2 = (List) map.get("values");
        View linearLayout = new LinearLayout(this.b);
        linearLayout.setOrientation(1);
        View relativeLayout = new RelativeLayout(this.b);
        int i2 = b.g;
        relativeLayout.setPadding(i2, i2, i2, i2);
        linearLayout.addView(relativeLayout);
        View linearLayout2 = new LinearLayout(this.b);
        linearLayout2.setBackgroundColor(-3419943);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 1);
        View imageView = new ImageView(this.b);
        imageView.setVisibility(4);
        imageView.setId(imageView.hashCode());
        View textView = new TextView(this.b);
        textView.setSingleLine(true);
        textView.setEllipsize(TruncateAt.END);
        textView.setText(str);
        textView.setTextSize(b.i);
        textView.setTextColor(-10066330);
        int a = g.a(this.b, TitleBar.BACKBTN_LEFT_MARGIN);
        int i3 = this.e == i ? 1008 : 1007;
        int a2 = g.a(this.b, TitleBar.BACKBTN_LEFT_MARGIN);
        Drawable a3 = c.a(this.b).a(i3, a, a);
        if (b(i)) {
            imageView.setVisibility(0);
        }
        imageView.setBackgroundDrawable(a3);
        a3 = this.a;
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(9, -1);
        relativeLayout.addView(imageView, layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(1, imageView.hashCode());
        layoutParams2.leftMargin = b.g;
        relativeLayout.addView(textView, layoutParams2);
        textView = new LinearLayout(this.b);
        textView.setOrientation(1);
        textView.setGravity(5);
        textView.setId(textView.hashCode());
        View linearLayout3 = new LinearLayout(this.b);
        linearLayout3.setOrientation(1);
        linearLayout3.setGravity(5);
        linearLayout3.setId(linearLayout3.hashCode());
        for (int i4 = 0; i4 < list.size(); i4++) {
            View textView2 = new TextView(this.b);
            textView2.setSingleLine(true);
            textView2.setEllipsize(TruncateAt.END);
            textView2.setText((CharSequence) list.get(i4));
            textView2.setTextSize(b.k);
            textView2.setTextColor(-6710887);
            LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams3.gravity = 5;
            textView.addView(textView2, layoutParams3);
            textView2 = new TextView(this.b);
            textView2.setSingleLine(true);
            textView2.setEllipsize(TruncateAt.END);
            textView2.setText((CharSequence) list2.get(i4));
            textView2.setTextSize(b.k);
            textView2.setTextColor(-6710887);
            linearLayout3.addView(textView2, layoutParams3);
        }
        layoutParams2 = new RelativeLayout.LayoutParams(g.a(this.b, 120.0f), -2);
        layoutParams2.addRule(11, -1);
        relativeLayout.addView(linearLayout3, layoutParams2);
        layoutParams2 = new RelativeLayout.LayoutParams(g.a(this.b, 100.0f), -2);
        layoutParams2.addRule(0, linearLayout3.getId());
        relativeLayout.addView(textView, layoutParams2);
        layoutParams.rightMargin = i2;
        layoutParams.leftMargin = i2;
        linearLayout.addView(linearLayout2, layoutParams);
        return linearLayout;
    }

    public final boolean isEnabled(int i) {
        return (!(b() && i == 0) && b(i)) ? super.isEnabled(i) : false;
    }
}
