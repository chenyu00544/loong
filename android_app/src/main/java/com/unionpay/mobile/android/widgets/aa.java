package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.unionpay.mobile.android.j.c;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public abstract class aa extends z {
    protected int a;
    protected u b;
    private a c;

    public interface a {
        void a(u uVar, String str);
    }

    public aa(Context context, int i, JSONObject jSONObject, String str) {
        this(context, i, jSONObject, str, (byte) 0);
    }

    public aa(Context context, int i, JSONObject jSONObject, String str, byte b) {
        super(context, jSONObject, str);
        this.b = null;
        this.c = null;
        this.a = i;
        c.a(this.d);
        Context context2 = getContext();
        int i2 = this.a;
        this.b = new u(context2);
        if (this.i) {
            this.b.a();
            this.b.d();
        }
        this.b.c(i());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, com.unionpay.mobile.android.d.a.n);
        layoutParams.addRule(15, -1);
        this.m.addView(this.b, layoutParams);
        this.b.b(j.a(jSONObject, "placeholder"));
        this.b.setFocusable(true);
        this.b.a(new b(this));
        this.b.a(new c(this));
        this.b.a(c.a(this.d).a(m_AppUI.MSG_APP_DATA_OK, -1, com.unionpay.mobile.android.d.a.v));
        if (this instanceof af) {
            if (this.i) {
                this.b.c(this.h + " " + this.g);
            } else {
                this.b.a(com.unionpay.mobile.android.f.c.bD.aQ);
            }
        } else if (this instanceof ap) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.aR);
        } else if (this instanceof ah) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.aT);
        } else if (this instanceof UPWidget) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.aS);
        } else if (this instanceof au) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.aU);
        } else if (this instanceof ao) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.aV);
        } else if (this instanceof e) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.aW);
        } else if (this instanceof ae) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.aX);
        } else if (this instanceof bd) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.aY);
        } else if (this instanceof at) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.aZ);
        } else if (this instanceof av) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.ba);
        } else if (this instanceof f) {
            this.b.a(com.unionpay.mobile.android.f.c.bD.bb);
        }
        if (this instanceof k) {
            this.b.setBackgroundDrawable(c.a(this.d).a(1011));
        } else {
            this.b.setBackgroundDrawable(c.a(this.d).a(1013));
        }
    }

    public String a() {
        return this.b.b();
    }

    public void a(Editable editable) {
    }

    public final void a(a aVar) {
        this.c = aVar;
    }

    protected final boolean a(View view) {
        boolean z = true;
        if (view == null) {
            throw new NullPointerException();
        }
        Rect rect = new Rect();
        view.getGlobalVisibleRect(rect);
        Log.e("uppay", "v getGlobalVisibleRect():" + rect.toString());
        Rect rect2 = new Rect();
        ((Activity) this.d).getWindow().getDecorView().findViewById(16908290).getGlobalVisibleRect(rect2);
        int[] iArr = new int[2];
        view.getLocationInWindow(iArr);
        Log.e("uppay", " locationW = [" + iArr[0] + "," + iArr[1] + "]");
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        Log.e("uppay", " locationS = [" + iArr2[0] + "," + iArr2[1] + "]");
        if ((iArr[1] + view.getHeight()) + 10 <= rect2.bottom) {
            z = false;
        }
        View findViewById = ((Activity) this.d).getWindow().getDecorView().findViewById(16908290);
        Rect rect3 = new Rect();
        findViewById.getLocalVisibleRect(rect3);
        Log.e("uppay", " getLocalVisibleRect = " + rect3.toString());
        rect3 = new Rect();
        findViewById.getGlobalVisibleRect(rect3);
        Log.e("uppay", " getGlobalVisibleRect = " + rect3.toString());
        return z;
    }

    public final boolean a(u uVar) {
        return uVar != null && this.b == uVar;
    }

    public boolean c() {
        return (a() == null || a().length() == 0) ? false : true;
    }

    public final void g() {
        if (this.b != null && !this.i) {
            this.b.e();
        }
    }
}
