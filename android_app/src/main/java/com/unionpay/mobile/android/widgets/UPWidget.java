package com.unionpay.mobile.android.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.text.InputFilter.LengthFilter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import com.unionpay.mobile.android.d.a;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.mobile.android.widgets.u.b;
import org.json.JSONObject;

public final class UPWidget extends aa implements b {
    private static final int o = (a.t / 3);
    protected int c = 0;
    private long p;
    private boolean q = true;
    private String r = null;
    private boolean s = false;
    private OnGlobalLayoutListener t = new r(this);
    private x u = null;
    private OnClickListener v = new s(this);

    public UPWidget(Context context, long j, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.p = j;
        this.b.a((b) this);
        this.b.a(new LengthFilter(6));
        this.b.f();
        this.b.d();
        e();
    }

    private native void appendOnce(long j, String str);

    static /* synthetic */ void b(UPWidget uPWidget) {
        k.a("kb", "pwdInputFinished() +++");
        k.a("kb", "size = " + uPWidget.c);
        uPWidget.k();
        k.a("kb", "pwdInputFinished() ---");
    }

    private native void clearAll(long j);

    private native void deleteOnce(long j);

    private native String getMsg(long j);

    private native String getMsgExtra(long j, String str);

    private void w() {
        if (x() != null) {
            x().getViewTreeObserver().removeGlobalOnLayoutListener(this.t);
        }
        if (this.u != null && this.u.b()) {
            this.u.a();
        }
    }

    private View x() {
        return ((Activity) this.d).findViewById(8888);
    }

    public final String a() {
        return this.q ? getMsgExtra(this.p, this.r) : getMsg(this.p);
    }

    public final void a(long j) {
        this.p = j;
    }

    public final void a(boolean z) {
        this.s = z;
        if (z) {
            ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(this.b.getWindowToken(), 0);
            int i = 1;
            int height = x().getRootView().getHeight() - x().getHeight();
            Rect rect = new Rect();
            getWindowVisibleDisplayFrame(rect);
            if (height != rect.top) {
                i = 0;
            }
            if (i != 0) {
                l();
                return;
            } else if (!j()) {
                k.a("uppay", "key board is closing..");
                k.a("uppay", "registerKeyboardDissmisslisner() +++");
                if (x() != null) {
                    x().getViewTreeObserver().addOnGlobalLayoutListener(this.t);
                }
                k.a("uppay", "registerKeyboardDissmisslisner() ---");
                return;
            } else {
                return;
            }
        }
        w();
    }

    public final void a_() {
        if (this.s && !j()) {
            l();
        }
    }

    public final void b(String str) {
        this.r = str;
    }

    public final void b(boolean z) {
        this.q = z;
    }

    public final boolean b() {
        return this.c == 6;
    }

    public final boolean c() {
        k.a("uppay", "emptyCheck() +++ ");
        k.a("uppay", "mPINCounts =  " + this.c);
        k.a("uppay", "emptyCheck() --- ");
        return this.c != 0;
    }

    protected final String d() {
        return "_bank_pwd";
    }

    public final void e() {
        clearAll(this.p);
        this.c = 0;
    }

    public final boolean j() {
        return this.u != null && this.u.b();
    }

    public final void k() {
        k.a("uppay", "closeCustomKeyboard() +++");
        if (j()) {
            w();
        }
        k.a("uppay", "closeCustomKeyboard() ---");
    }

    public final void l() {
        if (this.s && !j()) {
            this.u = new x(getContext(), this.v, this);
            this.u.a((View) this);
            int i = this.c;
            String str = "";
            for (int i2 = 0; i2 < i; i2++) {
                str = str + "*";
            }
            this.b.c(str);
            this.b.b(str.length());
        }
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        k();
    }
}
