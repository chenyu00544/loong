package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final class ac implements OnItemClickListener {
    final /* synthetic */ a a;

    ac(a aVar) {
        this.a = aVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a(this.a.d, this.a.s() + this.a.d());
        a.a(this.a, i);
        if (this.a.r != null) {
            this.a.r.dismiss();
        }
    }
}
