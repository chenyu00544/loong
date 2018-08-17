package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.utils.j;
import org.json.JSONObject;

public final class ah extends aa {
    private a c;
    private String o;
    private String p;

    public interface a {
        void e(String str);
    }

    public ah(Context context, int i, JSONObject jSONObject, String str) {
        this(context, i, jSONObject, str, (byte) 0);
    }

    private ah(Context context, int i, JSONObject jSONObject, String str, byte b) {
        super(context, i, jSONObject, str);
        this.c = null;
        this.o = null;
        this.p = null;
        this.o = j.a(jSONObject, "button_label");
        this.p = j.a(jSONObject, "button_action");
        this.b.a(new LengthFilter(11));
        this.b.a(2);
        if (this.o != null && this.o.length() > 0) {
            int i2 = this.a;
            i2 = com.unionpay.mobile.android.d.a.b;
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, b.n);
            layoutParams.addRule(9, -1);
            layoutParams.addRule(15, -1);
            this.b.setLayoutParams(layoutParams);
            View textView = new TextView(getContext());
            textView.setGravity(17);
            textView.setText(this.o);
            textView.setTextColor(-7829368);
            textView.setTextSize(b.k);
            textView.setOnClickListener(new h(this));
            this.b.a(textView, new LinearLayout.LayoutParams(-2, -1));
        }
    }

    public final String a() {
        return this.b.b();
    }

    public final void a(a aVar) {
        this.c = aVar;
    }

    public final boolean b() {
        if (this.i) {
            return true;
        }
        if (this.j == null || TextUtils.isEmpty(this.j)) {
            if (11 == a().length() && a().startsWith("1")) {
                return true;
            }
        } else if (a().matches(this.j)) {
            return true;
        }
        return false;
    }

    protected final String d() {
        return "_input_phoneNO";
    }
}
