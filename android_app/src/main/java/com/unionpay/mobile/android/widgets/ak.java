package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final class ak implements OnItemClickListener {
    final /* synthetic */ g a;

    ak(g gVar) {
        this.a = gVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a(this.a.d, this.a.s() + this.a.d());
        this.a.a(i);
        if (this.a.r != null) {
            this.a.r.dismiss();
        }
    }
}
