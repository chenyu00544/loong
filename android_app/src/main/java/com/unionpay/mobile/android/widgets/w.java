package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.j.c;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

abstract class w extends LinearLayout {
    private String a = null;
    private String b = null;
    private String c = null;
    protected Context d = null;
    protected int e = ViewCompat.MEASURED_STATE_MASK;
    protected int f = -7829368;
    protected String g = null;
    protected String h = null;
    protected boolean i = false;
    protected String j = null;
    protected LinearLayout k = null;
    protected TextView l = null;
    protected RelativeLayout m = null;
    protected JSONObject n;
    private String o = null;
    private TextView p = null;
    private ImageView q = null;
    private boolean r = false;
    private String s = "uppay";

    public interface a {
        String a();

        boolean b();

        boolean c();
    }

    public w(Context context, JSONObject jSONObject, String str) {
        super(context);
        this.n = jSONObject;
        this.d = context;
        this.h = j.a(jSONObject, "label");
        this.o = j.a(jSONObject, "placeholder");
        this.c = j.a(jSONObject, "tip");
        this.a = j.a(jSONObject, "name");
        this.g = j.a(jSONObject, "value");
        this.b = j.a(jSONObject, "type");
        this.j = j.a(jSONObject, "regexp");
        String a = j.a(jSONObject, "readonly");
        if (a != null && a.equalsIgnoreCase("true")) {
            this.i = true;
        }
        this.r = j.a(jSONObject, "margin").length() > 0;
        this.s = str;
        Context context2 = this.d;
        setLayoutParams(new LayoutParams(-1, -2));
        setBackgroundColor(0);
        setOrientation(1);
        setPadding(2, 2, 2, 2);
        if (this.b.equalsIgnoreCase("string")) {
            a();
            return;
        }
        if (!a((LinearLayout) this, this.h)) {
            this.p = new TextView(this.d);
            this.p.setTextSize(TitleBar.BACKBTN_LEFT_MARGIN);
            this.p.setText("");
            this.p.setTextColor(this.e);
            ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
            layoutParams.leftMargin = com.unionpay.mobile.android.d.a.f;
            addView(this.p, layoutParams);
            if (this.h == null || this.h.length() == 0) {
                this.p.setVisibility(8);
            } else {
                this.p.setText(this.h);
                this.p.setVisibility(8);
            }
        }
        a();
        if (!b_()) {
            this.k = new LinearLayout(this.d);
            this.k.setBackgroundColor(-267336);
            addView(this.k, new LayoutParams(-1, -2));
            this.l = new TextView(this.d);
            this.l.setTextSize(15.0f);
            this.l.setTextColor(this.f);
            layoutParams = new LayoutParams(-1, -2);
            int a2 = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
            layoutParams.rightMargin = a2;
            layoutParams.leftMargin = a2;
            a2 = g.a(this.d, 5.0f);
            layoutParams.bottomMargin = a2;
            layoutParams.topMargin = a2;
            this.k.addView(this.l, layoutParams);
            if (this.c == null || this.c.length() <= 0) {
                this.k.setVisibility(8);
                this.q.setVisibility(8);
                return;
            }
            this.q.setVisibility(0);
            this.l.setText(this.c);
        }
    }

    private void a() {
        View frameLayout = new FrameLayout(this.d);
        addView(frameLayout, new LayoutParams(-1, -2));
        this.m = new RelativeLayout(this.d);
        frameLayout.addView(this.m, new FrameLayout.LayoutParams(-1, -2));
        this.q = new ImageView(this.d);
        this.q.setBackgroundDrawable(c.a(this.d).a(1038));
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN), g.a(this.d, 5.0f));
        layoutParams.gravity = 80;
        layoutParams.leftMargin = g.a(this.d, TitleBar.BACKBTN_LEFT_MARGIN);
        this.q.setVisibility(8);
        frameLayout.addView(this.q, layoutParams);
    }

    public void a(Context context, String str) {
        a(context, str, null, null);
    }

    public void a(Context context, String str, String[] strArr, Object[] objArr) {
        if (com.unionpay.mobile.android.d.a.L) {
            k.a("uppay-TD", "event:" + str + ", keys:" + Arrays.toString(strArr) + ", values:" + Arrays.toString(objArr));
            if (strArr == null || objArr == null) {
                com.unionpay.c.a.c(context, str);
            } else if (strArr.length != objArr.length || strArr.length > 10) {
                throw new IllegalArgumentException();
            } else {
                Map hashMap = new HashMap();
                for (int i = 0; i < strArr.length; i++) {
                    hashMap.put(strArr[i], objArr[i]);
                }
                com.unionpay.c.a.a(context, str, str, hashMap);
            }
        }
    }

    protected final void a(CharSequence charSequence, BufferType bufferType) {
        if (this.p != null && charSequence != null && charSequence.length() > 0) {
            this.p.setText(charSequence, bufferType);
        }
    }

    protected boolean a(LinearLayout linearLayout, String str) {
        return false;
    }

    public boolean a(String str) {
        return str == null || str.length() == 0;
    }

    protected boolean b_() {
        return false;
    }

    protected final void c(String str) {
        if (this.l != null && str != null && str.length() > 0) {
            this.l.setText(str);
        }
    }

    protected String d() {
        return "_input_method";
    }

    public boolean f() {
        return true;
    }

    public String i() {
        return this.g;
    }

    public final String n() {
        return this.a;
    }

    public final String o() {
        return this.b;
    }

    public final String p() {
        return this.h;
    }

    public final String q() {
        return this.c;
    }

    public final String r() {
        return this.o;
    }

    protected final String s() {
        return this.s;
    }

    protected final void t() {
        if (this.p != null) {
            this.p.setVisibility(0);
        }
    }

    protected final void u() {
        if (this.l != null) {
            this.l.setVisibility(0);
            this.q.setVisibility(0);
        }
    }

    protected final void v() {
        if (this.p != null) {
            this.p.setTextSize(16.0f);
        }
    }
}
