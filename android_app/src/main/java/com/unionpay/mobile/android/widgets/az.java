package com.unionpay.mobile.android.widgets;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final class az implements OnItemClickListener {
    final /* synthetic */ p a;

    az(p pVar) {
        this.a = pVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.a(i);
        this.a.p.dismiss();
    }
}
