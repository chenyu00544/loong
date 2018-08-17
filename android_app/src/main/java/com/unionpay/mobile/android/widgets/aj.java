package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.text.style.ForegroundColorSpan;
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
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.upwidget.j;
import com.unionpay.mobile.android.upwidget.q;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.k;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class aj extends z {
    private String A = "";
    private boolean B = true;
    private String C = "";
    private a D = null;
    private final OnClickListener a = new i(this);
    private final OnClickListener b = new j(this);
    private final OnItemClickListener c = new l(this);
    private JSONArray o;
    private PopupWindow p;
    private j q;
    private int r;
    private int s = 0;
    private JSONArray t = null;
    private JSONArray u = null;
    private TextView v;
    private q w;
    private TextView x;
    private String y;
    private RelativeLayout z;

    public interface a {
        void g();
    }

    public aj(Context context, JSONObject jSONObject, String str, a aVar) {
        boolean z = true;
        super(context, jSONObject, str);
        this.D = aVar;
        this.r = 0;
        this.o = com.unionpay.mobile.android.utils.j.d(this.n, "items");
        this.y = com.unionpay.mobile.android.utils.j.a(jSONObject, "label");
        if (a(this.y)) {
            this.y = c.bD.bg;
        }
        if (!TextUtils.isEmpty(com.unionpay.mobile.android.utils.j.a(jSONObject, "default_item_idx"))) {
            this.s = Integer.parseInt(com.unionpay.mobile.android.utils.j.a(jSONObject, "default_item_idx"));
        }
        this.q = new j(context, this.o, this.s, str);
        this.q.a(this.c);
        this.q.a(this.a);
        this.q.d(this.b);
        RelativeLayout relativeLayout = this.m;
        Drawable a = com.unionpay.mobile.android.j.c.a(this.d).a(2014);
        View linearLayout = new LinearLayout(this.d);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundColor(-3419943);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 1);
        com.unionpay.mobile.android.utils.j.a(this.n, "type");
        relativeLayout.addView(linearLayout, layoutParams);
        this.z = new RelativeLayout(this.d);
        this.z.setId(this.z.hashCode());
        this.z.setBackgroundDrawable(a);
        this.z.setOnClickListener(new m(this));
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.d.a.n);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(3, linearLayout.getId());
        relativeLayout.addView(this.z, layoutParams2);
        View imageView = new ImageView(this.d);
        imageView.setId(imageView.hashCode());
        imageView.setBackgroundDrawable(com.unionpay.mobile.android.j.c.a(this.d).a(1002));
        int a2 = g.a(this.d, 15.0f);
        layoutParams = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(15, -1);
        layoutParams.rightMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.z.addView(imageView, layoutParams);
        this.v = new TextView(this.d);
        this.v.setTextSize(b.k);
        this.v.setEllipsize(TruncateAt.MIDDLE);
        this.v.setSingleLine(true);
        this.v.setTextColor(-10066330);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(15, -1);
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(0, imageView.getId());
        layoutParams3.leftMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        layoutParams3.rightMargin = layoutParams3.leftMargin;
        this.z.addView(this.v, layoutParams3);
        if (!"instalment".equals("promotion")) {
            imageView = new LinearLayout(this.d);
            imageView.setBackgroundColor(-3419943);
            layoutParams3 = new RelativeLayout.LayoutParams(-1, 1);
            layoutParams3.bottomMargin = com.unionpay.mobile.android.d.a.f;
            layoutParams3.addRule(3, this.z.getId());
            relativeLayout.addView(imageView, layoutParams3);
        }
        if (this.w != null) {
            z = this.w.b();
        }
        a(z);
        a(this.s, 0);
    }

    private String a(int i, int i2, String str) {
        String str2 = "";
        Object b = com.unionpay.mobile.android.utils.j.b(this.o, i);
        if (b != null) {
            JSONObject jSONObject = (JSONObject) b;
            String a = com.unionpay.mobile.android.utils.j.a(jSONObject, "type");
            JSONArray d = "coupon".equals(a) ? this.t : "point".equals(a) ? this.u : com.unionpay.mobile.android.utils.j.d(jSONObject, "options");
            b = com.unionpay.mobile.android.utils.j.b(d, i2);
            if (b != null) {
                return com.unionpay.mobile.android.utils.j.a((JSONObject) b, str);
            }
        }
        return str2;
    }

    static /* synthetic */ String a(aj ajVar, int i, String str) {
        String str2 = "";
        Object b = com.unionpay.mobile.android.utils.j.b(ajVar.o, i);
        return b != null ? com.unionpay.mobile.android.utils.j.a((JSONObject) b, str) : str2;
    }

    private static JSONObject a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str);
            jSONObject.put("label", str2);
            jSONObject.put("checked", str3);
            jSONObject.put("ckb_style", "small");
            jSONObject.put("required", "0");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void a(int i, int i2) {
        int i3 = 0;
        this.s = i;
        this.r = i2;
        if (this.v != null) {
            this.v.setText(a(i, i2, "label"));
        }
        String str = this.C;
        String a = a(i, i2, "rel_label");
        String a2 = a(i, i2, "rel_value");
        str = a(i, i2, "rel_value_style");
        this.C = a2;
        if (!this.B) {
            this.D.g();
        }
        this.B = false;
        this.A = a(i, i2, "value");
        if (a(a) && a(a2)) {
            this.x.setVisibility(8);
            return;
        }
        if (com.unionpay.mobile.android.b.a.a(str)) {
            str = Integer.toString(Color.parseColor(str), 16);
        }
        str = "#ff" + str;
        TextView textView = this.x;
        int parseColor = Color.parseColor(str);
        int length = a.length();
        int length2 = (TextUtils.isEmpty(a2) ? 0 : a2.length()) + length;
        CharSequence spannableStringBuilder = new SpannableStringBuilder(a + a2);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(-13421773);
        ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(parseColor);
        spannableStringBuilder.setSpan(foregroundColorSpan, 0, length, 33);
        spannableStringBuilder.setSpan(foregroundColorSpan2, length, length2, 18);
        textView.setText(spannableStringBuilder);
        if (!(this.w != null ? this.w.b() : true)) {
            i3 = 8;
        }
        this.x.setVisibility(i3);
    }

    static /* synthetic */ void a(aj ajVar, View view) {
        if (ajVar.p == null) {
            ajVar.p = new PopupWindow(ajVar.q, -1, -1, true);
            ajVar.p.setBackgroundDrawable(new ColorDrawable(-1342177280));
            ajVar.p.update();
        }
        ajVar.p.showAtLocation(view, 80, 0, 0);
    }

    public final String a() {
        String a = a(this.s, this.r, "value");
        if (a != null) {
            a = a.replace("\"", "\\\"");
        }
        if (!(this.w == null || this.w.b())) {
            a = null;
        }
        k.c("uppay", n() + " : " + a);
        return a;
    }

    public final void a(OnClickListener onClickListener) {
        this.q.b(this.a);
        this.q.b(onClickListener);
    }

    public final void a(com.unionpay.mobile.android.upwidget.q.a aVar) {
        if (this.w != null) {
            this.w.a(aVar);
        }
    }

    public final void a(JSONArray jSONArray) {
        this.t = jSONArray;
        this.q.a(jSONArray);
    }

    public final void a(JSONArray jSONArray, String str) {
        this.u = jSONArray;
        this.q.a(jSONArray, str);
    }

    public final void a(boolean z) {
        if (z) {
            this.A = a(this.s, this.r, "value");
        } else {
            this.A = "";
        }
        if (this.w != null) {
            this.w.a(z);
        }
        int i = z ? 0 : 8;
        this.m.setVisibility(i);
        if (this.x == null) {
            return;
        }
        if (TextUtils.isEmpty(this.x.getText().toString())) {
            this.x.setVisibility(8);
        } else {
            this.x.setVisibility(i);
        }
    }

    protected final boolean a(LinearLayout linearLayout, String str) {
        if (!a(str)) {
            View linearLayout2 = new LinearLayout(this.d);
            linearLayout2.setBackgroundColor(-1);
            linearLayout2.setOrientation(1);
            linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.d.a.n));
            this.w = new q(this.d, a(com.unionpay.mobile.android.utils.j.a(this.n, "type"), str, com.unionpay.mobile.android.utils.j.a(this.n, "checked")), s() + "_agree_reduce_activity");
            this.w.a();
            this.w.a(b.k);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.d.a.n);
            layoutParams.gravity = 16;
            int a = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
            layoutParams.rightMargin = a;
            layoutParams.leftMargin = a;
            linearLayout2.addView(this.w, layoutParams);
        }
        return true;
    }

    public final void b(OnClickListener onClickListener) {
        this.q.e(onClickListener);
    }

    public final boolean b() {
        return true;
    }

    protected final boolean b_() {
        this.x = new TextView(this.d);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        int a = g.a(this.d, 5.0f);
        layoutParams.bottomMargin = a;
        layoutParams.topMargin = a;
        this.x.setTextSize(b.k);
        addView(this.x, layoutParams);
        this.x.setVisibility(8);
        return true;
    }

    public final void c(OnClickListener onClickListener) {
        this.q.c(onClickListener);
    }

    public final boolean c() {
        return true;
    }

    protected final String d() {
        return "_select_reduce_activity";
    }

    public final boolean f() {
        CharSequence a = a(this.s, this.r, "available");
        return TextUtils.isEmpty(a) || !"1".equals(a);
    }

    public final String g() {
        return this.A;
    }
}
