package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.j.c;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.utils.p;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class q extends RelativeLayout {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private RelativeLayout k;
    private Button l;
    private boolean m;
    private Context n;
    private float o;
    private OnClickListener p;
    private OnClickListener q;
    private String r;
    private TextView s;
    private String t;
    private a u;

    public interface a {
        void a(String str, String str2);

        void a(String str, boolean z);
    }

    public q(Context context, JSONObject jSONObject, String str) {
        this(context, jSONObject, str, (byte) 0);
    }

    private q(Context context, JSONObject jSONObject, String str, byte b) {
        LayoutParams layoutParams;
        super(context);
        this.a = "";
        this.b = "";
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
        this.l = null;
        this.m = false;
        this.n = null;
        this.o = 0.0f;
        this.p = new r(this);
        this.q = new s(this);
        this.n = context;
        this.o = 16.0f;
        this.t = str;
        this.a = j.a(jSONObject, "name");
        this.b = j.a(jSONObject, "type");
        this.c = j.a(jSONObject, "value");
        this.d = j.a(jSONObject, "label");
        this.e = j.a(jSONObject, "href_label");
        this.f = j.a(jSONObject, "href_url");
        this.g = j.a(jSONObject, "href_title");
        this.h = j.a(jSONObject, "checked");
        this.i = j.a(jSONObject, "required");
        this.j = j.a(jSONObject, "error_info");
        this.r = j.a(jSONObject, "ckb_style");
        this.k = new RelativeLayout(this.n);
        addView(this.k, new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.d.a.n));
        if (a(this.d)) {
            this.s = new TextView(this.n);
            this.s.setId(this.s.hashCode());
            this.s.setText(this.d);
            this.s.setTextSize(this.o);
            this.s.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(9, -1);
            layoutParams.addRule(15, -1);
            this.k.addView(this.s, layoutParams);
        }
        this.l = new Button(this.n);
        this.l.setId(this.l.hashCode());
        if (a(this.h) && this.h.equalsIgnoreCase("0")) {
            this.m = true;
        } else {
            this.m = false;
        }
        this.l.setOnClickListener(this.p);
        c();
        layoutParams = new RelativeLayout.LayoutParams(g.a(this.n, 60.0f), g.a(this.n, 34.0f));
        layoutParams.addRule(11, -1);
        layoutParams.addRule(15, -1);
        this.k.addView(this.l, layoutParams);
        if (this.u != null) {
            this.u.a(this.b, this.m);
        }
        if (a(this.e) && a(this.f)) {
            View textView = new TextView(this.n);
            textView.setText(Html.fromHtml(this.e));
            textView.setTextSize(b.l);
            textView.setOnClickListener(this.q);
            textView.setTextColor(h.a(-10705958, -5846275, -5846275, -6710887));
            LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(1, this.s.getId());
            layoutParams2.addRule(15, -1);
            layoutParams2.leftMargin = g.a(this.n, TitleBar.SHAREBTN_RIGHT_MARGIN);
            this.k.addView(textView, layoutParams2);
        }
    }

    static /* synthetic */ void a(q qVar) {
        int i = 0;
        qVar.m = !qVar.m;
        String str = qVar.m ? "y" : "n";
        Context context = qVar.n;
        String str2 = qVar.t;
        String[] strArr = p.g;
        String[] strArr2 = new String[]{str};
        if (com.unionpay.mobile.android.d.a.L) {
            k.a("uppay-TD", "event:" + str2 + ", keys:" + Arrays.toString(strArr) + ", values:" + Arrays.toString(strArr2));
            if (strArr == null) {
                com.unionpay.c.a.c(context, str2);
            } else if (strArr.length != strArr2.length || strArr.length > 10) {
                throw new IllegalArgumentException();
            } else {
                Map hashMap = new HashMap();
                while (i < strArr.length) {
                    hashMap.put(strArr[i], strArr2[i]);
                    i++;
                }
                com.unionpay.c.a.a(context, str2, str2, hashMap);
            }
        }
        if (qVar.u != null) {
            qVar.u.a(qVar.b, qVar.m);
        }
        qVar.c();
    }

    private static boolean a(String str) {
        return str != null && str.length() > 0;
    }

    static /* synthetic */ void b(q qVar) {
        if (qVar.u != null) {
            qVar.u.a(qVar.e, qVar.f);
        }
    }

    private void c() {
        if (this.l != null) {
            this.l.setBackgroundDrawable(c.a(this.n).a(this.m ? 1010 : 1009, g.a(this.n, 60.0f), g.a(this.n, 34.0f)));
        }
    }

    public final void a() {
        if (this.s != null) {
            this.s.setTextColor(-13421773);
        }
    }

    public final void a(float f) {
        if (this.s != null) {
            this.s.setTextSize(f);
        }
    }

    public final void a(a aVar) {
        this.u = aVar;
    }

    public final void a(boolean z) {
        this.m = z;
        c();
    }

    public final boolean b() {
        return (a(this.i) && this.i.equalsIgnoreCase("0")) ? this.m : true;
    }
}
