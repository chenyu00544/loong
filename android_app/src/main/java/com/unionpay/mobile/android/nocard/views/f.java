package com.unionpay.mobile.android.nocard.views;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final class f implements OnItemClickListener {
    final /* synthetic */ b a;

    f(b bVar) {
        this.a = bVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        this.a.b.dismiss();
        if (this.a.j != null && i - this.a.c.c() < this.a.j.size()) {
            this.a.g = i;
            this.a.c.a(this.a.g);
        }
        if (this.a.k != null) {
            this.a.k.a(i - this.a.c.c());
        }
    }
}
