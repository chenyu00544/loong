package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.unionpay.mobile.android.d.a;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.f.c;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public final class g extends z {
    private static List<String> u;
    private static List<String> v;
    private Spinner a = null;
    private int b = 1;
    private String c = c.bD.bf;
    private com.unionpay.mobile.android.upwidget.c o;
    private TextView p;
    private RelativeLayout q;
    private PopupWindow r;
    private com.unionpay.mobile.android.upwidget.g s;
    private List<Map<String, Object>> t;
    private final OnClickListener w = new ai(this);
    private final OnItemClickListener x = new ak(this);

    static {
        List arrayList = new ArrayList(8);
        arrayList.add(c.bD.M);
        arrayList.add(c.bD.N);
        arrayList.add(c.bD.O);
        arrayList.add(c.bD.P);
        arrayList.add(c.bD.Q);
        arrayList.add(c.bD.R);
        arrayList.add(c.bD.S);
        arrayList.add(c.bD.T);
        u = arrayList;
        arrayList = new ArrayList(8);
        arrayList.add("01");
        arrayList.add("02");
        arrayList.add("03");
        arrayList.add("04");
        arrayList.add("05");
        arrayList.add("06");
        arrayList.add("07");
        arrayList.add("99");
        v = arrayList;
    }

    public g(Context context, JSONObject jSONObject, String str) {
        super(context, jSONObject, str);
        List list = null;
        if (u != null && u.size() > 0) {
            List arrayList = new ArrayList(u.size());
            for (int i = 0; i < u.size(); i++) {
                Map hashMap = new HashMap();
                hashMap.put("text1", u.get(i));
                hashMap.put("text2", "");
                hashMap.put("editable", Boolean.FALSE);
                arrayList.add(hashMap);
            }
            list = arrayList;
        }
        this.t = list;
        this.o = new com.unionpay.mobile.android.upwidget.c(context, this.t, this.c, "", "", this.b, 0);
        this.s = new com.unionpay.mobile.android.upwidget.g(this.d, this.o);
        this.s.a(this.x);
        this.s.a(this.w);
        RelativeLayout relativeLayout = this.m;
        Drawable a = com.unionpay.mobile.android.j.c.a(this.d).a(2014);
        this.q = new RelativeLayout(this.d);
        this.q.setBackgroundDrawable(a);
        this.q.setOnClickListener(new al(this));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, a.n);
        layoutParams.addRule(15, -1);
        relativeLayout.addView(this.q, layoutParams);
        View imageView = new ImageView(this.d);
        imageView.setId(imageView.hashCode());
        imageView.setBackgroundDrawable(com.unionpay.mobile.android.j.c.a(this.d).a(1002));
        int a2 = com.unionpay.mobile.android.utils.g.a(this.d, 15.0f);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a2, a2);
        layoutParams2.addRule(11, -1);
        layoutParams2.addRule(15, -1);
        layoutParams2.rightMargin = com.unionpay.mobile.android.utils.g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.q.addView(imageView, layoutParams2);
        View textView = new TextView(this.d);
        textView.setId(textView.hashCode());
        textView.setTextSize(b.k);
        textView.setEllipsize(TruncateAt.MIDDLE);
        textView.setSingleLine(true);
        textView.setEms(4);
        textView.setText(c.bD.bc);
        textView.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(9, -1);
        layoutParams2.leftMargin = com.unionpay.mobile.android.utils.g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.q.addView(textView, layoutParams2);
        this.p = new TextView(this.d);
        this.p.setTextSize(b.k);
        this.p.setEllipsize(TruncateAt.MIDDLE);
        this.p.setSingleLine(true);
        this.p.setTextColor(-10066330);
        layoutParams2 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(1, textView.getId());
        layoutParams2.addRule(0, imageView.getId());
        this.q.addView(this.p, layoutParams2);
        if (this.i) {
            this.p.setText(b(i()));
            imageView.setVisibility(8);
            this.q.setClickable(false);
            return;
        }
        a(1);
    }

    private void a(int i) {
        this.b = i;
        int c = i - this.o.c();
        this.o.a(this.b);
        if (this.p != null && u != null) {
            this.p.setText((CharSequence) u.get(c));
        }
    }

    static /* synthetic */ void a(g gVar, View view) {
        if (gVar.r == null) {
            gVar.r = new PopupWindow(gVar.s, -1, -1, true);
            gVar.r.setBackgroundDrawable(new ColorDrawable(-1342177280));
            gVar.r.update();
        }
        gVar.r.showAtLocation(view, 80, 0, 0);
    }

    private static String b(String str) {
        String str2 = "";
        int i = 0;
        while (i < v.size()) {
            String str3 = ((String) v.get(i)).equals(str) ? (String) u.get(i) : str2;
            i++;
            str2 = str3;
        }
        return str2;
    }

    public final String a() {
        String str = "";
        int c = this.b - this.o.c();
        return this.i ? i() : (c < 0 || c > u.size()) ? str : (String) v.get(c);
    }

    public final boolean b() {
        return true;
    }

    public final boolean c() {
        return true;
    }

    protected final String d() {
        return "_select_certtype";
    }
}
