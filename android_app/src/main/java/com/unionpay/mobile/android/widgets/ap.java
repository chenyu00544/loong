package com.unionpay.mobile.android.widgets;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import android.text.InputFilter.LengthFilter;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.unionpay.mobile.android.d.b;
import com.unionpay.mobile.android.f.c;
import com.unionpay.mobile.android.utils.h;
import org.json.JSONObject;

public final class ap extends aa implements Callback {
    private a c = null;
    private TextView o = null;
    private Handler p = null;
    private int q = 0;

    public interface a {
        void a(z zVar);
    }

    public ap(Context context, int i, JSONObject jSONObject, String str) {
        super(context, i, jSONObject, str);
        j();
        this.c = null;
    }

    public ap(Context context, int i, JSONObject jSONObject, String str, byte b) {
        super(context, i, jSONObject, str);
        j();
    }

    private void a(boolean z, String str) {
        this.o.setText(str);
        this.o.setEnabled(z);
    }

    private void j() {
        int i = this.a;
        i = com.unionpay.mobile.android.d.a.b;
        this.b.a(new LengthFilter(6));
        this.b.a(2);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, b.n);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(15, -1);
        this.b.setLayoutParams(layoutParams);
        LinearLayout linearLayout = new LinearLayout(this.d);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(-3419943);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(1, -1);
        this.o = new TextView(getContext());
        this.o.setGravity(17);
        this.o.setText(c.bD.w);
        this.o.setTextColor(h.a(-10705958, -5846275, -5846275, -6710887));
        this.o.setTextSize(b.k);
        this.o.setOnClickListener(new n(this));
        this.b.a(this.o, new LinearLayout.LayoutParams(-2, -1));
    }

    public final void a(int i) {
        this.p = new Handler(this);
        Thread qVar = new q(this, i);
        a(false, String.format(c.bD.x, new Object[]{Integer.valueOf(i)}));
        qVar.start();
    }

    public final void a(a aVar) {
        this.c = aVar;
    }

    public final boolean b() {
        return this.i || 6 == a().length();
    }

    protected final String d() {
        return "_input_msg";
    }

    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case 0:
                this.q = message.arg1;
                if (c.bD == null) {
                    return true;
                }
                a(false, String.format(c.bD.x, new Object[]{Integer.valueOf(message.arg1)}));
                return true;
            case 1:
                if (c.bD != null) {
                    a(true, c.bD.y);
                }
                this.p = null;
                return true;
            default:
                return false;
        }
    }
}
