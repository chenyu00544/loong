package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.upwidget.c;
import com.unionpay.mobile.android.upwidget.g;
import com.unionpay.mobile.android.utils.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class a extends z {
    private static List<String> u;
    private static List<String> v;
    private Spinner a = null;
    private int b = 1;
    private String c;
    private c o;
    private TextView p;
    private RelativeLayout q;
    private PopupWindow r;
    private g s;
    private List<Map<String, Object>> t;
    private final OnClickListener w = new v(this);
    private final OnItemClickListener x = new ac(this);

    public a(Context context, JSONObject jSONObject, JSONArray jSONArray, String str) {
        int i;
        super(context, jSONObject, str);
        List arrayList = new ArrayList(1);
        for (i = 0; i < jSONArray.length(); i++) {
            arrayList.add((JSONArray) j.b(jSONArray, i));
        }
        if (arrayList.size() > 0) {
            u = new ArrayList(arrayList.size());
            v = new ArrayList(arrayList.size());
            for (i = 0; i < arrayList.size(); i++) {
                v.add(j.a((JSONArray) arrayList.get(i), 0));
                u.add(j.a((JSONArray) arrayList.get(i), 1));
            }
        }
        RelativeLayout relativeLayout = this.m;
        Drawable a = com.unionpay.mobile.android.j.c.a(this.d).a(2014);
        this.q = new RelativeLayout(this.d);
        this.q.setBackgroundDrawable(a);
        this.q.setOnClickListener(new ag(this));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.d.a.n);
        layoutParams.addRule(15, -1);
        relativeLayout.addView(this.q, layoutParams);
        View imageView = new ImageView(this.d);
        imageView.setId(imageView.hashCode());
        imageView.setBackgroundDrawable(com.unionpay.mobile.android.j.c.a(this.d).a(1002));
        i = com.unionpay.mobile.android.utils.g.a(this.d, 15.0f);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(i, i);
        layoutParams2.addRule(11, -1);
        layoutParams2.addRule(15, -1);
        layoutParams2.rightMargin = com.unionpay.mobile.android.utils.g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.q.addView(imageView, layoutParams2);
        View textView = new TextView(this.d);
        textView.setId(textView.hashCode());
        textView.setTextSize(b.k);
        textView.setEllipsize(TruncateAt.MIDDLE);
        textView.setTextColor(-13421773);
        textView.setSingleLine(true);
        textView.setEms(4);
        textView.setText(com.unionpay.mobile.android.f.c.bD.bd);
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
        } else if (u != null && u.size() > 0) {
            this.p.setText((CharSequence) u.get(0));
        }
    }

    static /* synthetic */ void a(a aVar, int i) {
        aVar.b = i;
        int c = i - aVar.o.c();
        aVar.o.a(aVar.b);
        if (aVar.p != null && u != null) {
            aVar.p.setText((CharSequence) u.get(c));
        }
    }

    static /* synthetic */ void a(a aVar, View view) {
        if (aVar.r == null) {
            aVar.c = com.unionpay.mobile.android.f.c.bD.be;
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
            aVar.t = list;
            aVar.o = new c(aVar.d, aVar.t, aVar.c, "", "", aVar.b, 0);
            aVar.s = new g(aVar.d, aVar.o);
            aVar.s.a(aVar.x);
            aVar.s.a(aVar.w);
            aVar.r = new PopupWindow(aVar.s, -1, -1, true);
            aVar.r.setBackgroundDrawable(new ColorDrawable(-1342177280));
            aVar.r.update();
        }
        aVar.r.showAtLocation(view, 80, 0, 0);
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
        int c = this.b - (this.o == null ? 1 : this.o.c());
        return this.i ? i() : (c < 0 || c > u.size()) ? "" : (String) v.get(c);
    }

    public final boolean b() {
        return true;
    }

    public final boolean c() {
        return true;
    }

    protected final String d() {
        return "_select_areacode";
    }
}
