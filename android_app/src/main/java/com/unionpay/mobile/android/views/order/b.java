package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.upwidget.c;
import com.unionpay.mobile.android.upwidget.g;
import com.unionpay.mobile.android.utils.k;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class b extends AbstractMethod {
    private TextView A;
    private boolean B = false;
    private int C = l.b.intValue();
    private JSONObject g;
    private JSONArray h;
    private boolean i;
    private com.unionpay.mobile.android.upviews.a j;
    private List<Map<String, Object>> k;
    private Drawable l;
    private PopupWindow m;
    private g n;
    private c o;
    private String p;
    private final OnClickListener q = new c(this);
    private final OnClickListener r = new d(this);
    private final OnItemClickListener s = new e(this);
    private a t;
    private int u = -1;
    private int v = 1;
    private b w;
    private Drawable x;
    private Drawable y;
    private Drawable z;

    public interface b {
        int a();

        int a(int i);

        int b(int i);
    }

    private class a {
        View a;
        TextView b;
        final /* synthetic */ b c;

        private a(b bVar) {
            this.c = bVar;
        }
    }

    public b(Context context, List<Map<String, Object>> list, String str) {
        super(context);
        this.k = list;
        this.p = str;
        this.o = new c(this.b, this.k, com.unionpay.mobile.android.f.c.bD.bh, this.p, com.unionpay.mobile.android.f.c.bD.bi, this.v, 0);
        this.o.a(this.q);
        this.n = new g(this.b, this.o);
        this.n.a(this.s);
        this.n.a(this.r);
    }

    static /* synthetic */ void a(b bVar, View view) {
        if (bVar.m == null) {
            bVar.m = new PopupWindow(bVar.n, -1, -1, true);
            bVar.m.setBackgroundDrawable(new ColorDrawable(-1342177280));
            bVar.m.update();
        }
        bVar.m.showAtLocation(view, 80, 0, 0);
    }

    private final void c(int i) {
        int c = i - this.o.c();
        if (i != 0) {
            if (this.k == null || i != this.k.size() + this.o.c()) {
                if (this.o.b() && this.o.c(i)) {
                    k.a("direct", " delete " + i);
                    i();
                    if (this.w != null) {
                        this.u = c;
                        this.w.a(c);
                    }
                } else {
                    this.v = i;
                    this.o.a(this.v);
                    k.a("direct", " pay with " + i);
                    if (this.t != null) {
                        this.t.b.setText(this.o.b(this.v));
                    }
                    if (this.w != null) {
                        this.w.b(c);
                    }
                }
                this.m.dismiss();
                return;
            }
            k.a("direct", " new ");
            if (this.w != null) {
                this.w.a();
            }
            this.m.dismiss();
        }
    }

    private boolean h() {
        return this.i || this.k == null || this.k.size() == 0;
    }

    private void i() {
        if (this.o != null) {
            this.o.a();
            String str = this.o.b() ? com.unionpay.mobile.android.f.c.bD.bj : com.unionpay.mobile.android.f.c.bD.bh;
            String str2 = this.o.b() ? com.unionpay.mobile.android.f.c.bD.bk : com.unionpay.mobile.android.f.c.bD.bi;
            this.o.a(str);
            this.o.b(str2);
            this.o.notifyDataSetChanged();
        }
    }

    public final b a(Drawable drawable, Drawable drawable2, Drawable drawable3) {
        this.x = drawable;
        this.y = drawable2;
        this.z = drawable3;
        return this;
    }

    public final b a(b bVar) {
        this.w = bVar;
        return this;
    }

    public final b a(JSONArray jSONArray) {
        this.h = jSONArray;
        return this;
    }

    public final b a(JSONObject jSONObject) {
        this.g = jSONObject;
        if (this.A != null) {
            this.A.setText(Html.fromHtml(AbstractMethod.a(this.g, "label")));
        }
        return this;
    }

    public final void a(int i) {
        int size = this.k != null ? this.k.size() : 0;
        if (size > 0 && this.u >= 0 && this.u < size) {
            this.k.remove(this.u);
            this.u = -1;
            this.o.notifyDataSetChanged();
        }
        c(this.o.c() + i);
    }

    public final void a(RelativeLayout relativeLayout) {
        View textView = new TextView(this.b);
        textView.setTextSize(com.unionpay.mobile.android.d.b.k);
        textView.setTextColor(-13421773);
        textView.setText(this.c);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        layoutParams.leftMargin = com.unionpay.mobile.android.utils.g.a(this.b, TitleBar.SHAREBTN_RIGHT_MARGIN);
        relativeLayout.addView(textView, layoutParams);
        if (TextUtils.isEmpty(this.c)) {
            relativeLayout.setVisibility(8);
        }
        if (h()) {
            String a = AbstractMethod.a(this.g, "label");
            this.A = new TextView(this.b);
            this.A.setOnClickListener(new f(this));
            if (!AbstractMethod.a(a)) {
                this.A.setText(Html.fromHtml(a));
            }
            AbstractMethod.a(this.A);
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(11, -1);
            layoutParams2.rightMargin = com.unionpay.mobile.android.utils.g.a(this.b, TitleBar.SHAREBTN_RIGHT_MARGIN);
            layoutParams2.addRule(15, -1);
            relativeLayout.addView(this.A, layoutParams2);
        }
    }

    public final int b() {
        return this.C;
    }

    public final b b(Drawable drawable) {
        this.l = drawable;
        return this;
    }

    public final b b(boolean z) {
        this.B = z;
        return this;
    }

    public final void b(int i) {
        this.C = i;
    }

    public final void b(RelativeLayout relativeLayout) {
        if (h() || this.B) {
            if (this.B) {
                g();
            }
            this.j = new com.unionpay.mobile.android.upviews.a(this.b, this.h, this, "bankpay");
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
            relativeLayout.addView(this.j, layoutParams);
            return;
        }
        View linearLayout = new LinearLayout(this.b);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundColor(-3419943);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, 1);
        layoutParams2.topMargin = com.unionpay.mobile.android.d.a.f;
        relativeLayout.addView(linearLayout, layoutParams2);
        View relativeLayout2 = new RelativeLayout(this.b);
        relativeLayout2.setId(relativeLayout2.hashCode());
        relativeLayout2.setBackgroundDrawable(this.l);
        relativeLayout2.setOnClickListener(new g(this));
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.d.b.n);
        layoutParams3.addRule(3, linearLayout.getId());
        relativeLayout.addView(relativeLayout2, layoutParams3);
        linearLayout = new ImageView(this.b);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundDrawable(com.unionpay.mobile.android.j.c.a(this.b).a(1002));
        int a = com.unionpay.mobile.android.utils.g.a(this.b, 15.0f);
        LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(a, a);
        layoutParams4.addRule(11, -1);
        layoutParams4.addRule(15, -1);
        layoutParams4.rightMargin = com.unionpay.mobile.android.utils.g.a(this.b, TitleBar.SHAREBTN_RIGHT_MARGIN);
        relativeLayout2.addView(linearLayout, layoutParams4);
        View textView = new TextView(this.b);
        textView.setText(this.o.b(this.v));
        textView.setTextSize(com.unionpay.mobile.android.d.b.k);
        textView.setTextColor(-10066330);
        textView.setSingleLine(true);
        textView.setEllipsize(TruncateAt.MIDDLE);
        layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.addRule(9, -1);
        layoutParams4.addRule(15, -1);
        layoutParams4.addRule(0, linearLayout.getId());
        layoutParams4.leftMargin = com.unionpay.mobile.android.utils.g.a(this.b, TitleBar.SHAREBTN_RIGHT_MARGIN);
        relativeLayout2.addView(textView, layoutParams4);
        linearLayout = new LinearLayout(this.b);
        linearLayout.setBackgroundColor(-3419943);
        layoutParams4 = new RelativeLayout.LayoutParams(-1, 1);
        layoutParams4.bottomMargin = com.unionpay.mobile.android.d.a.f;
        layoutParams4.addRule(3, relativeLayout2.getId());
        relativeLayout.addView(linearLayout, layoutParams4);
        this.t = new a();
        this.t.a = relativeLayout2;
        this.t.b = textView;
    }

    public final void b(String str) {
        if (this.t != null) {
            this.t.b.setText(str);
        }
    }

    public final com.unionpay.mobile.android.upviews.a.a c() {
        return this.j != null ? this.j.b() : null;
    }

    public final void c(RelativeLayout relativeLayout) {
        relativeLayout.setVisibility(8);
    }

    public final int d() {
        return this.v - this.o.c();
    }

    public final b d(String str) {
        this.c = str;
        return this;
    }

    public final b e(String str) {
        this.d = str;
        return this;
    }

    public final String e() {
        return this.d;
    }

    public final void f(String str) {
        this.o.b(str);
    }

    public final boolean f() {
        return this.j == null || this.j.e();
    }

    public final void u() {
    }
}
