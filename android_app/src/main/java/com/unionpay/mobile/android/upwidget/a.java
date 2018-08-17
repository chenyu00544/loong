package com.unionpay.mobile.android.upwidget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
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

public final class a extends LinearLayout {
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private Button j;
    private boolean k;
    private Context l;
    private float m;
    private OnClickListener n;
    private String o;
    private TextView p;
    private a q;
    private String r;

    public interface a {
    }

    public a(Context context, JSONObject jSONObject, OnClickListener onClickListener, String str) {
        this(context, jSONObject, onClickListener, str, (byte) 0);
    }

    private a(Context context, JSONObject jSONObject, OnClickListener onClickListener, String str, byte b) {
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
        this.j = null;
        this.k = false;
        this.l = null;
        this.m = 0.0f;
        this.n = new b(this);
        this.l = context;
        this.m = 16.0f;
        this.r = str;
        this.a = j.a(jSONObject, "name");
        this.b = j.a(jSONObject, "value");
        this.c = j.a(jSONObject, "label");
        this.d = j.a(jSONObject, "href_label");
        this.e = j.a(jSONObject, "href_url");
        this.f = j.a(jSONObject, "href_title");
        this.g = j.a(jSONObject, "checked");
        this.h = j.a(jSONObject, "required");
        this.i = j.a(jSONObject, "error_info");
        this.o = j.a(jSONObject, "ckb_style");
        this.j = new Button(this.l);
        if (a(this.g) && this.g.equalsIgnoreCase("0")) {
            this.k = true;
        } else {
            this.k = false;
        }
        this.j.setOnClickListener(this.n);
        g();
        f();
        int a = g.a(this.l, TitleBar.BACKBTN_LEFT_MARGIN);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        layoutParams.gravity = 16;
        addView(this.j, layoutParams);
        if (this.q != null) {
            a aVar = this.q;
            boolean z = this.k;
        }
        if (a(this.c)) {
            this.p = new TextView(this.l);
            this.p.setText(this.c);
            this.p.setTextSize(this.m);
            this.p.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            this.p.setOnClickListener(this.n);
            LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
            layoutParams2.gravity = 16;
            layoutParams2.leftMargin = com.unionpay.mobile.android.d.a.d;
            addView(this.p, layoutParams2);
        }
        if (a(this.d) && a(this.e)) {
            View textView = new TextView(this.l);
            textView.setText(Html.fromHtml(this.d));
            textView.setTextColor(h.a(-10705958, -5846275, -5846275, -6710887));
            String.format("<u>%s</u>", new Object[]{this.d});
            textView.setTextSize(this.m);
            textView.setOnClickListener(onClickListener);
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 16;
            addView(textView, layoutParams);
        }
    }

    static /* synthetic */ void a(a aVar) {
        int i = 0;
        aVar.k = !aVar.k;
        String str = aVar.k ? "y" : "n";
        Context context = aVar.l;
        String str2 = aVar.r;
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
        if (aVar.q != null) {
            a aVar2 = aVar.q;
            boolean z = aVar.k;
        }
        aVar.g();
    }

    private static boolean a(String str) {
        return str != null && str.length() > 0;
    }

    private boolean f() {
        return "small".equalsIgnoreCase(this.o);
    }

    private void g() {
        if (this.j != null) {
            int i = this.k ? 1008 : 1007;
            int a = f() ? g.a(this.l, 15.0f) : com.unionpay.mobile.android.d.a.w;
            this.j.setBackgroundDrawable(c.a(this.l).a(i, a, a));
        }
    }

    public final String a() {
        String str = "\"%s\":\"%s\"";
        String str2 = this.k ? this.b : "";
        return String.format(str, new Object[]{this.a, str2});
    }

    public final String b() {
        return this.i;
    }

    public final String c() {
        return this.e;
    }

    public final String d() {
        return this.f;
    }

    public final boolean e() {
        return (a(this.h) && this.h.equalsIgnoreCase("0")) ? this.k : true;
    }
}
