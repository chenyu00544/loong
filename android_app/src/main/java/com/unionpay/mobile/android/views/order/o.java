package com.unionpay.mobile.android.views.order;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.unionpay.mobile.android.j.c;
import com.unionpay.mobile.android.views.order.AbstractMethod.b;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public final class o extends LinearLayout {
    private Context a;
    private int b = l.a.intValue();
    private int c = l.a.intValue();
    private JSONObject d;
    private JSONObject e;
    private JSONObject f;
    private Drawable g;
    private JSONArray h;
    private List<Map<String, Object>> i;
    private String j;
    private AbstractMethod k;
    private CViewMethods l;
    private Drawable m;
    private boolean n;

    public interface a extends com.unionpay.mobile.android.views.order.AbstractMethod.a, b, com.unionpay.mobile.android.views.order.CViewMethods.a, b.b {
    }

    private o(Context context) {
        super(context);
        this.a = context;
        setOrientation(1);
    }

    public static o a(Context context, Drawable drawable) {
        o oVar = new o(context);
        oVar.g = drawable;
        return oVar;
    }

    public static o a(Context context, Drawable drawable, Drawable drawable2) {
        o oVar = new o(context);
        oVar.m = drawable2;
        oVar.b = l.c.intValue();
        oVar.c = l.c.intValue();
        oVar.g = drawable;
        oVar.c();
        return oVar;
    }

    public static o a(Context context, JSONArray jSONArray, List<Map<String, Object>> list, Drawable drawable, Drawable drawable2, String str) {
        o oVar = new o(context);
        oVar.m = drawable2;
        oVar.b = l.b.intValue();
        oVar.c = l.b.intValue();
        oVar.g = drawable;
        oVar.h = jSONArray;
        oVar.i = list;
        oVar.j = str;
        oVar.c();
        return oVar;
    }

    public static o b(Context context, JSONArray jSONArray, List<Map<String, Object>> list, Drawable drawable, Drawable drawable2, String str) {
        o oVar = new o(context);
        oVar.m = drawable2;
        oVar.b = l.e.intValue();
        oVar.c = l.e.intValue();
        oVar.g = drawable;
        oVar.h = jSONArray;
        oVar.i = list;
        oVar.j = str;
        oVar.c();
        return oVar;
    }

    public final int a() {
        return this.b;
    }

    public final o a(int i) {
        this.c = i;
        return this;
    }

    public final o a(Drawable drawable) {
        this.m = drawable;
        return this;
    }

    public final o a(a aVar) {
        if (this.k != null) {
            this.k.a((b) aVar);
            this.k.a((com.unionpay.mobile.android.views.order.AbstractMethod.a) aVar);
            if (this.k instanceof b) {
                ((b) this.k).a((b.b) aVar);
            }
        }
        if (this.l != null) {
            this.l.a((com.unionpay.mobile.android.views.order.CViewMethods.a) aVar);
        }
        return this;
    }

    public final o a(String str) {
        this.j = str;
        return this;
    }

    public final o a(List<Map<String, Object>> list) {
        this.i = list;
        return this;
    }

    public final o a(JSONArray jSONArray) {
        this.h = jSONArray;
        return this;
    }

    public final o a(JSONObject jSONObject) {
        this.d = jSONObject;
        if (this.k != null && (this.k instanceof b)) {
            ((b) this.k).a(this.d);
        }
        return this;
    }

    public final o a(boolean z) {
        this.n = z;
        return this;
    }

    public final o b(int i) {
        this.b = i;
        return this;
    }

    public final o b(Drawable drawable) {
        if (this.k != null) {
            this.k.a(drawable);
        }
        return this;
    }

    public final o b(JSONObject jSONObject) {
        this.e = jSONObject;
        if (this.k != null && (this.k instanceof i)) {
            ((i) this.k).a(this.e);
        }
        return this;
    }

    public final String b() {
        return (this.k == null || !(this.k instanceof i)) ? null : ((i) this.k).h();
    }

    public final void b(String str) {
        ((b) this.k).b(str);
    }

    public final o c(JSONObject jSONObject) {
        this.f = jSONObject;
        if (this.k != null && (this.k instanceof i)) {
            ((i) this.k).b(this.f);
        }
        return this;
    }

    public final void c() {
        this.k = null;
        AbstractMethod bVar;
        c a;
        if (this.b == l.b.intValue()) {
            this.c &= l.b.intValue() ^ -1;
            bVar = new b(this.a, this.i, this.j);
            bVar.d(com.unionpay.mobile.android.f.c.bD.by);
            bVar.e(com.unionpay.mobile.android.f.c.bD.bz);
            bVar.a(this.d);
            bVar.a(this.h);
            bVar.b(this.n);
            bVar.b(c.a(this.a).a(2014));
            a = c.a(this.a);
            bVar.a(a.a(1015), a.a(1016), a.a(1002));
            this.k = bVar;
        } else if (this.b == l.c.intValue()) {
            this.c &= l.c.intValue() ^ -1;
            bVar = new i(this.a);
            bVar.b(com.unionpay.mobile.android.f.c.bD.bA);
            bVar.d(com.unionpay.mobile.android.f.c.bD.bB);
            bVar.a(this.e);
            bVar.b(this.f);
            this.k = bVar;
        } else if (this.b == l.e.intValue()) {
            this.c &= l.e.intValue() ^ -1;
            bVar = new b(this.a, this.i, "");
            bVar.d("");
            bVar.e(com.unionpay.mobile.android.f.c.bD.bz);
            bVar.a(null);
            bVar.a(this.h);
            bVar.b(l.e.intValue());
            bVar.f("");
            bVar.b(c.a(this.a).a(2014));
            a = c.a(this.a);
            bVar.a(a.a(1015), a.a(1016), a.a(1002));
            this.k = bVar;
        }
        if (this.k != null) {
            this.k.a();
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.topMargin = com.unionpay.mobile.android.d.b.a;
            addView(this.k, layoutParams);
        }
        if (this.b != l.e.intValue()) {
            this.l = new CViewMethods(this.a);
            this.l.a(this.g);
            this.l.a(this.c);
            HashMap hashMap = new HashMap();
            hashMap.put(l.b, com.unionpay.mobile.android.f.c.bD.by);
            if (com.unionpay.mobile.android.f.c.bD.bp == null || TextUtils.isEmpty(com.unionpay.mobile.android.f.c.bD.bp)) {
                hashMap.put(l.d, com.unionpay.mobile.android.f.c.bD.bo);
            } else {
                hashMap.put(l.d, com.unionpay.mobile.android.f.c.bD.bp);
            }
            hashMap.put(l.c, com.unionpay.mobile.android.f.c.bD.bA);
            if (com.unionpay.mobile.android.f.c.bD.br == null || TextUtils.isEmpty(com.unionpay.mobile.android.f.c.bD.br)) {
                hashMap.put(l.e, com.unionpay.mobile.android.f.c.bD.bq);
            } else {
                hashMap.put(l.e, com.unionpay.mobile.android.f.c.bD.br);
            }
            hashMap.put(l.f, com.unionpay.mobile.android.f.c.bD.bt);
            this.l.a(hashMap);
            hashMap = new HashMap();
            hashMap.put(l.b, this.m);
            hashMap.put(l.d, this.m);
            hashMap.put(l.c, this.m);
            hashMap.put(l.e, this.m);
            hashMap.put(l.f, this.m);
            this.l.b(hashMap);
            this.l.a(com.unionpay.mobile.android.f.c.bD.bC).a();
            addView(this.l, new LinearLayout.LayoutParams(-1, -2));
        }
    }

    public final void c(int i) {
        if (this.k != null && (this.k instanceof b)) {
            ((b) this.k).a(i);
        }
    }
}
