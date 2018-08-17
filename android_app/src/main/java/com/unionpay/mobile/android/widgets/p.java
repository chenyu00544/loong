package com.unionpay.mobile.android.widgets;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.open.yyb.TitleBar;
import com.umeng.analytics.pro.x;
import com.unionpay.mobile.android.d.a;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.upwidget.e;
import com.unionpay.mobile.android.upwidget.q;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.j;
import com.unionpay.mobile.android.utils.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class p extends z {
    private final OnClickListener a = new ar(this);
    private final OnItemClickListener b = new aw(this);
    private JSONArray c = j.d(this.n, "new_instalments");
    private List<Map<String, Object>> o;
    private AlertDialog p;
    private PopupWindow q;
    private e r;
    private int s = 1;
    private TextView t;
    private q u;
    private TextView v;
    private String w;
    private RelativeLayout x;
    private boolean y = false;
    private boolean z = true;

    public p(Context context, JSONObject jSONObject, String str) {
        super(context, jSONObject, str);
        this.w = j.a(jSONObject, "label");
        if (a(this.w)) {
            this.w = c.bD.bg;
        }
        this.o = b(this.c);
        this.r = new e(this.d, this.o, "");
        a(this.m);
    }

    private String a(int i, String str) {
        String str2 = "";
        Object b = j.b(this.c, i);
        return b != null ? j.a((JSONObject) b, str) : str2;
    }

    private JSONObject a(String str, String str2, String str3) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", str);
            jSONObject.put("label", str2);
            jSONObject.put("checked", str3);
            jSONObject.put("ckb_style", "small");
            jSONObject.put("required", "0");
            if ("instalment".equals(str)) {
                JSONObject c = j.c(this.n, "url");
                if (c != null) {
                    jSONObject.put("href_label", j.a(c, "label"));
                    jSONObject.put("href_url", j.a(c, "href"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    private void a(int i) {
        this.s = i;
        int a = i - this.r.a();
        this.r.a(this.s);
        if (this.t != null) {
            this.t.setText(a(a, "label"));
        }
    }

    private void a(RelativeLayout relativeLayout) {
        Drawable a = com.unionpay.mobile.android.j.c.a(this.d).a(2014);
        View linearLayout = new LinearLayout(this.d);
        linearLayout.setId(linearLayout.hashCode());
        linearLayout.setBackgroundColor(-3419943);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, 1);
        String a2 = j.a(this.n, "type");
        if ("instalment".equals(a2)) {
            layoutParams.leftMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        }
        relativeLayout.addView(linearLayout, layoutParams);
        this.x = new RelativeLayout(this.d);
        this.x.setId(this.x.hashCode());
        this.x.setBackgroundDrawable(a);
        this.x.setOnClickListener(new ax(this));
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, a.n);
        layoutParams2.addRule(15, -1);
        layoutParams2.addRule(3, linearLayout.getId());
        relativeLayout.addView(this.x, layoutParams2);
        View imageView = new ImageView(this.d);
        imageView.setId(imageView.hashCode());
        imageView.setBackgroundDrawable(com.unionpay.mobile.android.j.c.a(this.d).a(1002));
        int a3 = g.a(this.d, 15.0f);
        layoutParams = new RelativeLayout.LayoutParams(a3, a3);
        layoutParams.addRule(11, -1);
        layoutParams.addRule(15, -1);
        layoutParams.rightMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        this.x.addView(imageView, layoutParams);
        this.t = new TextView(this.d);
        this.t.setTextSize(b.k);
        this.t.setEllipsize(TruncateAt.MIDDLE);
        this.t.setSingleLine(true);
        this.t.setTextColor(-10066330);
        LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(15, -1);
        layoutParams3.addRule(9, -1);
        layoutParams3.addRule(0, imageView.getId());
        layoutParams3.leftMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        layoutParams3.rightMargin = layoutParams3.leftMargin;
        this.x.addView(this.t, layoutParams3);
        if (!"instalment".equals(a2)) {
            imageView = new LinearLayout(this.d);
            imageView.setBackgroundColor(-3419943);
            layoutParams3 = new RelativeLayout.LayoutParams(-1, 1);
            layoutParams3.bottomMargin = a.f;
            layoutParams3.addRule(3, this.x.getId());
            relativeLayout.addView(imageView, layoutParams3);
        }
        b(g());
        a(this.r.a());
    }

    static /* synthetic */ Dialog b(p pVar) {
        if (pVar.p == null || pVar.z) {
            pVar.z = false;
            pVar.p = new Builder(pVar.d).setInverseBackgroundForced(false).create();
            AlertDialog alertDialog = pVar.p;
            View linearLayout = new LinearLayout(pVar.d);
            linearLayout.setOrientation(1);
            linearLayout.setBackgroundColor(-1);
            int a = g.a(pVar.d, 1.0f);
            View relativeLayout = new RelativeLayout(pVar.d);
            int i = b.g;
            relativeLayout.setPadding(i, i, i, i);
            linearLayout.addView(relativeLayout);
            View linearLayout2 = new LinearLayout(pVar.d);
            linearLayout2.setBackgroundColor(-3419943);
            linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, 1));
            linearLayout2 = new TextView(pVar.d);
            linearLayout2.setText(pVar.w);
            linearLayout2.setTextSize(b.i);
            linearLayout2.setTextColor(-13421773);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(13, -1);
            relativeLayout.addView(linearLayout2, layoutParams);
            relativeLayout.setBackgroundColor(-986892);
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, -2);
            relativeLayout = new ListView(pVar.d);
            relativeLayout.setDivider(null);
            relativeLayout.setAdapter(pVar.r);
            relativeLayout.setDividerHeight(a);
            relativeLayout.setOnItemClickListener(new az(pVar));
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, -2);
            linearLayout.addView(relativeLayout);
            alertDialog.setView(linearLayout, -1, -1, -1, -1);
            pVar.p.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        return pVar.p;
    }

    private List<Map<String, Object>> b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        List<Map<String, Object>> arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            Map hashMap = new HashMap();
            hashMap.put("label", a(i, "label"));
            hashMap.put(x.P, a(i, "rel_value_style"));
            String[] split = a(i, "rel_value").split("\\|");
            List arrayList2 = new ArrayList(split.length);
            List arrayList3 = new ArrayList(split.length);
            for (String split2 : split) {
                String[] split3 = split2.split(":");
                arrayList2.add(split3[0]);
                arrayList3.add(split3[1]);
            }
            hashMap.put("keys", arrayList2);
            hashMap.put("values", arrayList3);
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    public final String a() {
        String a = a(this.s - this.r.a(), "value");
        if (!(this.u == null || this.u.b())) {
            a = null;
        }
        k.c("uppay", n() + " : " + a);
        return a;
    }

    public final void a(q.a aVar) {
        this.u.a(aVar);
    }

    public final void a(JSONArray jSONArray) {
        if (jSONArray != null && jSONArray.length() > 0) {
            this.z = true;
            this.c = jSONArray;
            this.o = b(jSONArray);
            this.r = new e(this.d, this.o, "");
            a(this.m);
        }
    }

    public final void a(boolean z) {
        this.y = z;
    }

    protected final boolean a(LinearLayout linearLayout, String str) {
        if (!a(str)) {
            View linearLayout2 = new LinearLayout(this.d);
            linearLayout2.setBackgroundColor(-1);
            linearLayout2.setOrientation(1);
            linearLayout.addView(linearLayout2, new LinearLayout.LayoutParams(-1, a.n));
            String a = j.a(this.n, "type");
            if ("instalment".equals(a)) {
                View linearLayout3 = new LinearLayout(this.d);
                linearLayout3.setId(linearLayout3.hashCode());
                linearLayout3.setBackgroundColor(-3419943);
                LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, 1);
                layoutParams.leftMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
                linearLayout2.addView(linearLayout3, layoutParams);
            }
            this.u = new q(this.d, a(a, str, j.a(this.n, "checked")), s() + "_agree_installment");
            this.u.a();
            this.u.a(b.k);
            LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, a.n);
            layoutParams2.gravity = 16;
            int a2 = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
            layoutParams2.rightMargin = a2;
            layoutParams2.leftMargin = a2;
            linearLayout2.addView(this.u, layoutParams2);
        }
        return true;
    }

    public final void b(boolean z) {
        this.u.a(z);
        if (!z) {
            this.y = z;
        }
        int i = this.y ? z ? 0 : 8 : 8;
        this.m.setVisibility(i);
        if (this.v == null) {
            return;
        }
        if (TextUtils.isEmpty(this.v.getText().toString())) {
            this.v.setVisibility(8);
        } else {
            this.v.setVisibility(i);
        }
    }

    public final boolean b() {
        return true;
    }

    protected final boolean b_() {
        this.v = new TextView(this.d);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.leftMargin = g.a(this.d, TitleBar.SHAREBTN_RIGHT_MARGIN);
        int a = g.a(this.d, 5.0f);
        layoutParams.bottomMargin = a;
        layoutParams.topMargin = a;
        this.v.setTextSize(b.k);
        addView(this.v, layoutParams);
        this.v.setVisibility(8);
        return true;
    }

    public final boolean c() {
        return true;
    }

    protected final String d() {
        return "_select_installment";
    }

    public final boolean f() {
        CharSequence a = a(this.s - this.r.a(), "available");
        return TextUtils.isEmpty(a) || !"1".equals(a);
    }

    public final boolean g() {
        return this.u != null ? this.u.b() : true;
    }
}
