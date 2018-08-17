package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.k;
import java.util.Arrays;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class AbstractMethod extends LinearLayout implements com.unionpay.mobile.android.upviews.a.b {
    protected static final int a = com.unionpay.mobile.android.d.b.a;
    protected Context b;
    protected String c;
    protected String d;
    protected b e;
    protected a f;
    private Button g;
    private RelativeLayout h;

    public interface a {
        void a(int i, boolean z, int i2, com.unionpay.mobile.android.upviews.a.a aVar);
    }

    public interface b {
        void a(String str, String str2);
    }

    public AbstractMethod(Context context) {
        this(context, null);
    }

    public AbstractMethod(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AbstractMethod(Context context, AttributeSet attributeSet, int i) {
        super(context);
        this.b = context;
        setOrientation(1);
    }

    protected static String a(JSONObject jSONObject, String str) {
        String str2 = null;
        if (jSONObject != null) {
            try {
                str2 = jSONObject.getString(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return str2;
    }

    public static void a(Context context, String str) {
        if (com.unionpay.mobile.android.d.a.L) {
            k.a("uppay-TD", "event:" + str + ", keys:" + Arrays.toString(null) + ", values:" + Arrays.toString(null));
            com.unionpay.c.a.c(context, str);
        }
    }

    protected static void a(TextView textView) {
        if (textView != null) {
            textView.setTextSize(com.unionpay.mobile.android.d.b.k);
            textView.setTextColor(h.a(-10705958, -5846275, -5846275, -6710887));
        }
    }

    protected static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public final void a() {
        this.h = new RelativeLayout(this.b);
        addView(this.h, new LayoutParams(-1, -2));
        a(this.h);
        View relativeLayout = new RelativeLayout(this.b);
        addView(relativeLayout, new LayoutParams(-1, -2));
        b(relativeLayout);
        relativeLayout = new RelativeLayout(this.b);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.topMargin = com.unionpay.mobile.android.d.a.f;
        addView(relativeLayout, layoutParams);
        this.g = new Button(this.b);
        this.g.setText(e());
        this.g.setTextColor(h.a(com.unionpay.mobile.android.d.b.b, com.unionpay.mobile.android.d.b.c, com.unionpay.mobile.android.d.b.c, com.unionpay.mobile.android.d.b.d));
        this.g.setTextSize(com.unionpay.mobile.android.d.b.i);
        this.g.setOnClickListener(new a(this));
        ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.d.b.n);
        layoutParams2.addRule(15, -1);
        layoutParams2.topMargin = com.unionpay.mobile.android.d.a.f;
        int a = g.a(this.b, TitleBar.SHAREBTN_RIGHT_MARGIN);
        layoutParams2.rightMargin = a;
        layoutParams2.leftMargin = a;
        relativeLayout.addView(this.g, layoutParams2);
        RelativeLayout relativeLayout2 = new RelativeLayout(this.b);
        addView(relativeLayout2, layoutParams);
        c(relativeLayout2);
        this.g.setEnabled(f());
    }

    public final void a(Drawable drawable) {
        if (this.g != null && drawable != null) {
            this.g.setBackgroundDrawable(drawable);
        }
    }

    public abstract void a(RelativeLayout relativeLayout);

    public final void a(com.unionpay.mobile.android.upviews.a.a aVar) {
    }

    public final void a(a aVar) {
        this.f = aVar;
    }

    public final void a(b bVar) {
        this.e = bVar;
    }

    public final void a(boolean z) {
        this.g.setEnabled(!z);
    }

    public abstract int b();

    public abstract void b(RelativeLayout relativeLayout);

    public abstract com.unionpay.mobile.android.upviews.a.a c();

    public abstract void c(RelativeLayout relativeLayout);

    public final void c(String str) {
    }

    public final void c(String str, String str2) {
    }

    public int d() {
        return 0;
    }

    public abstract String e();

    public abstract boolean f();

    protected final void g() {
        this.h.setVisibility(8);
    }
}
