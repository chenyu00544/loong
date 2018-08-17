package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.unionpay.mobile.android.utils.p;

final class l implements OnItemClickListener {
    final /* synthetic */ aj a;

    l(aj ajVar) {
        this.a = ajVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        int intValue = ((Integer) view.getTag()).intValue();
        this.a.a(this.a.d, this.a.s() + this.a.d(), p.h, new Object[]{aj.a(this.a, intValue, "type"), Integer.valueOf(i), this.a.a(intValue, i, "label")});
        this.a.a(intValue, i);
        if (this.a.p != null) {
            this.a.p.dismiss();
        }
    }
}
