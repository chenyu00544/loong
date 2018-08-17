package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.view.View.OnClickListener;
import com.unionpay.mobile.android.utils.p;

final class j implements OnClickListener {
    final /* synthetic */ aj a;

    j(aj ajVar) {
        this.a = ajVar;
    }

    public final void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        this.a.a(this.a.d, this.a.s() + this.a.d(), p.h, new Object[]{aj.a(this.a, intValue, "type"), Integer.valueOf(0), this.a.a(intValue, 0, "label")});
        this.a.a(intValue, 0);
        if (this.a.p != null) {
            this.a.p.dismiss();
        }
    }
}
