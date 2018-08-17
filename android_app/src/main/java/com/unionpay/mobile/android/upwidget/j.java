package com.unionpay.mobile.android.upwidget;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.tencent.open.yyb.TitleBar;
import com.tencent.tauth.AuthActivity;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.j.c;
import com.unionpay.mobile.android.utils.g;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.widgets.ad;
import com.unionpay.mobile.android.widgets.k;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class j extends LinearLayout {
    private OnClickListener A = new m(this);
    private OnClickListener B = new n(this);
    private OnClickListener C = new o(this);
    private OnClickListener D = new p(this);
    private Context a;
    private JSONArray b;
    private int c;
    private int d;
    private boolean e = true;
    private a[] f;
    private ArrayList<Object> g;
    private LinearLayout h;
    private HorizontalScrollView i;
    private k j = null;
    private ad k = null;
    private TextView l = null;
    private TextView m = null;
    private int n = 0;
    private int o = 0;
    private int p = -1;
    private int q;
    private int r;
    private String s;
    private ArrayList<OnItemClickListener> t = new ArrayList();
    private ArrayList<OnClickListener> u = new ArrayList();
    private ArrayList<OnClickListener> v = new ArrayList();
    private ArrayList<OnClickListener> w = new ArrayList();
    private ArrayList<OnClickListener> x = new ArrayList();
    private OnItemClickListener y = new k(this);
    private OnClickListener z = new l(this);

    private class a {
        TextView a;
        LinearLayout b;
        View c;
        String d;
        final /* synthetic */ j e;

        private a(j jVar) {
            this.e = jVar;
        }
    }

    public j(Context context, JSONArray jSONArray, int i, String str) {
        super(context);
        this.a = context;
        this.b = jSONArray;
        this.o = i;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.a).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.q = displayMetrics.widthPixels;
        displayMetrics = new DisplayMetrics();
        ((Activity) this.a).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        this.r = displayMetrics.heightPixels;
        this.s = str;
        if (this.b != null) {
            View frameLayout = new FrameLayout(this.a);
            View relativeLayout = new RelativeLayout(this.a);
            frameLayout.addView(relativeLayout, new LayoutParams(-1, -1));
            ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, (this.r / 3) * 2);
            layoutParams.addRule(12, -1);
            View linearLayout = new LinearLayout(this.a);
            linearLayout.setOrientation(1);
            linearLayout.setBackgroundColor(-1);
            linearLayout.setId(linearLayout.hashCode());
            relativeLayout.addView(linearLayout, layoutParams);
            layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            View linearLayout2 = new LinearLayout(this.a);
            layoutParams.addRule(10, -1);
            layoutParams.addRule(2, linearLayout.getId());
            relativeLayout.addView(linearLayout2, layoutParams);
            linearLayout2.setOnClickListener(this.z);
            this.h = new LinearLayout(this.a);
            this.h.setBackgroundColor(-1);
            this.h.setOrientation(0);
            linearLayout.addView(this.h, new LinearLayout.LayoutParams(-1, com.unionpay.mobile.android.d.a.n));
            layoutParams = new LinearLayout.LayoutParams(-1, g.a(this.a, 1.0f));
            relativeLayout = new LinearLayout(this.a);
            relativeLayout.setBackgroundColor(-3355444);
            linearLayout.addView(relativeLayout, layoutParams);
            this.i = new HorizontalScrollView(this.a);
            this.i.setBackgroundColor(-1052684);
            linearLayout.addView(this.i, new LinearLayout.LayoutParams(-2, -1));
            int a = g.a(this.a, 40.0f);
            View imageView = new ImageView(this.a);
            imageView.setBackgroundDrawable(c.a(this.a).a(1034));
            imageView.setOnClickListener(this.z);
            ViewGroup.LayoutParams layoutParams2 = new LayoutParams(a, a);
            layoutParams2.gravity = 85;
            layoutParams2.rightMargin = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
            layoutParams2.bottomMargin = ((this.r / 3) * 2) - (a / 2);
            frameLayout.addView(imageView, layoutParams2);
            addView(frameLayout);
            a();
        }
    }

    private View a(LinearLayout linearLayout, JSONObject jSONObject) {
        ListAdapter cVar = new c(this.a, b(com.unionpay.mobile.android.utils.j.d(jSONObject, "options")), "", "", "", this.p, 1);
        this.g.add(cVar);
        View listView = new ListView(this.a);
        listView.setDivider(null);
        listView.setAdapter(cVar);
        listView.setOnItemClickListener(this.y);
        listView.setCacheColorHint(-1);
        linearLayout.addView(listView, new LinearLayout.LayoutParams(this.q, -1));
        return listView;
    }

    private static String a(JSONArray jSONArray, int i, String str) {
        String str2 = "";
        Object b = com.unionpay.mobile.android.utils.j.b(jSONArray, i);
        return b != null ? com.unionpay.mobile.android.utils.j.a((JSONObject) b, str) : str2;
    }

    private void a() {
        int length = this.b.length();
        this.f = new a[length];
        for (int i = 0; i < length; i++) {
            this.f[i] = new a();
            if (this.f[i].a == null) {
                this.f[i].a = new TextView(this.a);
            }
            if (this.f[i].b == null) {
                this.f[i].b = new LinearLayout(this.a);
            }
            if (this.f[i].c == null) {
                this.f[i].c = new ListView(this.a);
            }
            if (this.f[i].d == null) {
                this.f[i].d = "";
            }
        }
        this.g = new ArrayList(this.b.length());
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setOrientation(0);
        this.i.addView(linearLayout, new LinearLayout.LayoutParams(-2, -1));
        for (length = 0; length < this.b.length(); length++) {
            View b;
            JSONObject jSONObject = (JSONObject) com.unionpay.mobile.android.utils.j.b(this.b, length);
            String a = com.unionpay.mobile.android.utils.j.a(jSONObject, AuthActivity.ACTION_KEY);
            Object a2 = com.unionpay.mobile.android.utils.j.a(jSONObject, "label");
            View relativeLayout = new RelativeLayout(this.a);
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -1);
            layoutParams.leftMargin = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
            this.h.addView(relativeLayout, layoutParams);
            int a3 = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
            View textView = new TextView(this.a);
            textView.setText(a2);
            textView.setTextSize(b.k);
            textView.setEllipsize(TruncateAt.MIDDLE);
            textView.setSingleLine(true);
            textView.setTextColor(-10066330);
            textView.setPadding(a3, 0, a3, 0);
            ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams2.addRule(15, -1);
            relativeLayout.addView(textView, layoutParams2);
            layoutParams = new RelativeLayout.LayoutParams((((int) textView.getPaint().measureText(a2)) + a3) + a3, g.a(this.a, 2.0f));
            layoutParams.addRule(12, -1);
            View linearLayout2 = new LinearLayout(this.a);
            linearLayout2.setBackgroundColor(-16730965);
            if (this.o != length) {
                linearLayout2.setVisibility(8);
            }
            relativeLayout.addView(linearLayout2, layoutParams);
            relativeLayout.setTag(Integer.valueOf(length));
            relativeLayout.setOnClickListener(this.D);
            this.f[length].a = textView;
            this.f[length].b = linearLayout2;
            this.f[length].d = a;
            if (this.o == length) {
                this.p = 0;
            } else {
                this.p = -1;
            }
            a = com.unionpay.mobile.android.utils.j.a(jSONObject, "type");
            if ("coupon".equals(a)) {
                this.d = length;
                b = b(linearLayout, jSONObject);
            } else if ("point".equals(a)) {
                this.c = length;
                b = c(linearLayout, jSONObject);
            } else {
                b = "upoint".equals(a) ? c(linearLayout, jSONObject) : a(linearLayout, jSONObject);
            }
            this.f[length].c = b;
            this.f[length].c.setVisibility(8);
        }
        a(this.o);
    }

    private void a(int i) {
        this.f[this.o].b.setVisibility(8);
        this.f[this.o].a.setTextColor(ViewCompat.MEASURED_STATE_MASK);
        this.f[this.o].c.setVisibility(8);
        this.f[i].b.setVisibility(0);
        this.f[i].a.setTextColor(-16730965);
        this.f[i].c.setVisibility(0);
        this.o = i;
    }

    private void a(LinearLayout linearLayout, boolean z, String str, JSONObject jSONObject, c cVar) {
        linearLayout.removeAllViews();
        View listView = new ListView(this.a);
        listView.setDivider(null);
        listView.setAdapter(cVar);
        listView.setOnItemClickListener(this.y);
        this.g.add(cVar);
        linearLayout.addView(listView, new LinearLayout.LayoutParams(this.q, -2));
        if (cVar != null) {
            ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).gravity = 48;
        }
        if (z) {
            int i = com.unionpay.mobile.android.d.a.p;
            ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i);
            layoutParams.bottomMargin = g.a(this.a, 12.0f);
            layoutParams.gravity = 17;
            linearLayout.addView(new ProgressBar(this.a), layoutParams);
        }
        listView = new TextView(this.a);
        if (!TextUtils.isEmpty(str)) {
            listView.setText(str);
            listView.setTextSize(b.k);
            listView.setTextColor(-13421773);
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            linearLayout.addView(listView, layoutParams);
        }
        if (jSONObject != null) {
            View textView = new TextView(this.a);
            textView.setText(com.unionpay.mobile.android.utils.j.a(jSONObject, "label"));
            textView.setTextSize(b.i);
            textView.setTextColor(h.a(b.b, b.c, b.c, b.d));
            textView.setGravity(17);
            textView.setEnabled(true);
            int i2 = com.unionpay.mobile.android.d.a.n;
            textView.setBackgroundDrawable(c.a(this.a).a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
            float measureText = listView.getPaint().measureText(str);
            textView.setOnClickListener(this.A);
            ViewGroup.LayoutParams layoutParams2 = new LinearLayout.LayoutParams((int) measureText, i2);
            i = com.unionpay.mobile.android.d.a.f;
            layoutParams2.bottomMargin = i;
            layoutParams2.topMargin = i;
            i = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
            layoutParams2.rightMargin = i;
            layoutParams2.leftMargin = i;
            linearLayout.addView(textView, layoutParams2);
        }
    }

    private View b(LinearLayout linearLayout, JSONObject jSONObject) {
        int i;
        JSONObject jSONObject2;
        View relativeLayout = new RelativeLayout(this.a);
        View listView = new ListView(this.a);
        listView.setDivider(null);
        listView.setAdapter(null);
        this.g.add(listView);
        JSONArray d = com.unionpay.mobile.android.utils.j.d(jSONObject, "rules");
        JSONObject jSONObject3 = null;
        JSONObject jSONObject4 = null;
        if (d != null && d.length() > 0) {
            i = 0;
            while (i < d.length()) {
                Object b = com.unionpay.mobile.android.utils.j.b(d, i);
                if (b != null) {
                    jSONObject2 = (JSONObject) b;
                    String a = com.unionpay.mobile.android.utils.j.a(jSONObject2, "type");
                    if ("coupon_code".equals(a)) {
                        JSONObject jSONObject5 = jSONObject4;
                        jSONObject4 = jSONObject2;
                        jSONObject2 = jSONObject5;
                    } else if ("string".equals(a)) {
                        jSONObject4 = jSONObject3;
                    }
                    i++;
                    jSONObject3 = jSONObject4;
                    jSONObject4 = jSONObject2;
                }
                jSONObject2 = jSONObject4;
                jSONObject4 = jSONObject3;
                i++;
                jSONObject3 = jSONObject4;
                jSONObject4 = jSONObject2;
            }
        }
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams.addRule(10, -1);
        relativeLayout.addView(listView, layoutParams);
        int i2 = com.unionpay.mobile.android.d.a.I - (com.unionpay.mobile.android.d.a.f * 4);
        this.j = new k(this.a, i2, jSONObject3, this.s);
        this.j.setId(this.j.hashCode());
        ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams2.addRule(10, -1);
        i = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
        layoutParams2.topMargin = i;
        layoutParams2.rightMargin = i;
        layoutParams2.leftMargin = i;
        relativeLayout.addView(this.j, layoutParams2);
        this.k = new ad(this.a, i2, jSONObject4, this.s);
        layoutParams = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams.addRule(3, this.j.getId());
        int a2 = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
        layoutParams.topMargin = a2;
        layoutParams.rightMargin = a2;
        layoutParams.leftMargin = a2;
        relativeLayout.addView(this.k, layoutParams);
        this.l = new TextView(this.a);
        this.l.setTextSize(b.k);
        this.l.setTextColor(-10066330);
        this.l.setVisibility(8);
        layoutParams = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams.addRule(3, this.j.getId());
        a2 = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
        layoutParams.topMargin = a2;
        layoutParams.rightMargin = a2;
        layoutParams.leftMargin = a2;
        relativeLayout.addView(this.l, layoutParams);
        jSONObject2 = com.unionpay.mobile.android.utils.j.c(jSONObject, "use_button");
        View linearLayout2 = new LinearLayout(this.a);
        linearLayout2.setOrientation(1);
        linearLayout2.setBackgroundColor(-1);
        ViewGroup.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, g.a(this.a, 1.0f));
        View linearLayout3 = new LinearLayout(this.a);
        linearLayout3.setBackgroundColor(-3355444);
        linearLayout2.addView(linearLayout3, layoutParams3);
        this.m = new TextView(this.a);
        this.m.setText(com.unionpay.mobile.android.utils.j.a(jSONObject2, "label"));
        this.m.setTextSize(b.i);
        this.m.setTextColor(h.a(b.b, b.c, b.c, b.d));
        this.m.setGravity(17);
        this.m.setEnabled(false);
        i2 = com.unionpay.mobile.android.d.a.n;
        this.m.setBackgroundDrawable(c.a(this.a).a((int) m_AppUI.MSG_APP_VERSION_FORCE_NAV_MODULE));
        this.m.setTag(Integer.valueOf(this.d));
        this.m.setOnClickListener(this.C);
        layoutParams2 = new LinearLayout.LayoutParams(-1, i2);
        i2 = com.unionpay.mobile.android.d.a.f;
        layoutParams2.bottomMargin = i2;
        layoutParams2.topMargin = i2;
        i2 = g.a(this.a, TitleBar.SHAREBTN_RIGHT_MARGIN);
        layoutParams2.rightMargin = i2;
        layoutParams2.leftMargin = i2;
        linearLayout2.addView(this.m, layoutParams2);
        layoutParams = new RelativeLayout.LayoutParams(this.q, -2);
        layoutParams.addRule(12, -1);
        relativeLayout.addView(linearLayout2, layoutParams);
        linearLayout.addView(relativeLayout, new LinearLayout.LayoutParams(this.q, -2));
        return relativeLayout;
    }

    private static List<Map<String, Object>> b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        List<Map<String, Object>> arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            Map hashMap = new HashMap();
            hashMap.put("text1", a(jSONArray, i, "label"));
            hashMap.put("text2", "");
            hashMap.put("editable", Boolean.FALSE);
            CharSequence a = a(jSONArray, i, "available");
            boolean booleanValue = Boolean.TRUE.booleanValue();
            if (!TextUtils.isEmpty(a) && "1".equals(a)) {
                booleanValue = Boolean.FALSE.booleanValue();
            }
            hashMap.put("available", Boolean.valueOf(booleanValue));
            arrayList.add(hashMap);
        }
        return arrayList;
    }

    private View c(LinearLayout linearLayout, JSONObject jSONObject) {
        View linearLayout2 = new LinearLayout(this.a);
        linearLayout2.setOrientation(1);
        String a = com.unionpay.mobile.android.utils.j.a(jSONObject, "tip");
        CharSequence a2 = com.unionpay.mobile.android.utils.j.a(jSONObject, "empty_info");
        JSONObject c = com.unionpay.mobile.android.utils.j.c(jSONObject, "button");
        ViewGroup.LayoutParams layoutParams;
        if (c != null) {
            a(linearLayout2, false, a, c, null);
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            linearLayout2.setGravity(17);
            linearLayout.addView(linearLayout2, layoutParams);
            return linearLayout2;
        } else if (!"upoint".equals(com.unionpay.mobile.android.utils.j.a(jSONObject, "type"))) {
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            linearLayout2.setGravity(17);
            linearLayout.addView(linearLayout2, layoutParams);
            return linearLayout2;
        } else if (a2 == null || TextUtils.isEmpty(a2)) {
            return a(linearLayout, jSONObject);
        } else {
            a(linearLayout2, false, a2, null, null);
            layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.gravity = 17;
            linearLayout2.setGravity(17);
            linearLayout.addView(linearLayout2, layoutParams);
            return linearLayout2;
        }
    }

    public final void a(OnClickListener onClickListener) {
        this.u.add(onClickListener);
    }

    public final void a(OnItemClickListener onItemClickListener) {
        this.t.add(onItemClickListener);
    }

    public final void a(JSONArray jSONArray) {
        Object b = com.unionpay.mobile.android.utils.j.b(jSONArray, 0);
        if (b != null) {
            this.l.setText(com.unionpay.mobile.android.utils.j.a((JSONObject) b, "label"));
            this.l.setVisibility(0);
            this.k.setVisibility(8);
        }
        this.m.setEnabled(true);
    }

    public final void a(JSONArray jSONArray, String str) {
        c cVar;
        if (jSONArray == null || jSONArray.length() <= 0) {
            cVar = null;
        } else {
            c cVar2 = new c(this.a, b(jSONArray), "", "", "", -1, 1);
            this.g.add(this.c, cVar2);
            cVar = cVar2;
        }
        a((LinearLayout) this.f[this.c].c, false, str, null, cVar);
    }

    public final void b(OnClickListener onClickListener) {
        this.v.add(onClickListener);
    }

    public final void c(OnClickListener onClickListener) {
        this.w.add(onClickListener);
    }

    public final void d(OnClickListener onClickListener) {
        this.x.add(onClickListener);
    }

    public final void e(OnClickListener onClickListener) {
        if (this.j != null) {
            this.j.a(onClickListener);
            this.j.b(this.B);
        }
    }
}
