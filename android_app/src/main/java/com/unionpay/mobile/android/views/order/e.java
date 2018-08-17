package com.unionpay.mobile.android.views.order;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final class e implements OnItemClickListener {
    final /* synthetic */ b a;

    e(b bVar) {
        this.a = bVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.c(i);
    }
}
