package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.utils.h;
import com.unionpay.mobile.android.utils.j;
import java.util.ArrayList;
import org.json.JSONObject;

public final class k extends aa {
    private final String c = "[A-Za-z0-9]{8,32}";
    private ArrayList<OnClickListener> o = new ArrayList();
    private ArrayList<OnClickListener> p = new ArrayList();
    private TextView q = null;
    private boolean r = true;
    private String s = null;
    private String t = null;
    private OnClickListener u = new am(this);

    public k(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        this.s = j.a(jSONObject, "button_label");
        this.t = j.a(jSONObject, "button_action");
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, b.n);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        this.b.setLayoutParams(layoutParams);
        this.q = new TextView(getContext());
        this.q.setGravity(17);
        this.q.setText(this.s);
        this.q.setTextColor(h.a(-10705958, -5846275, -5846275, -6710887));
        this.q.setTextSize(b.k);
        this.q.setOnClickListener(this.u);
        a(false);
        this.b.a(this.q, new LinearLayout.LayoutParams(-2, -1));
    }

    public final void a(OnClickListener onClickListener) {
        this.o.add(onClickListener);
    }

    public final void a(boolean z) {
        if (z) {
            this.q.setText(c.bD.B);
            this.r = false;
            return;
        }
        this.q.setText(this.s);
        this.r = true;
    }

    public final void b(OnClickListener onClickListener) {
        this.p.add(onClickListener);
    }

    public final boolean b() {
        return this.i || 6 == a().length();
    }

    protected final String d() {
        return "_input_coupon";
    }
}
