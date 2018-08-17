package com.unionpay.mobile.android.upwidget;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.Iterator;

final class h implements OnItemClickListener {
    final /* synthetic */ g a;

    h(g gVar) {
        this.a = gVar;
    }

    public final void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        Iterator it = this.a.c.iterator();
        while (it.hasNext()) {
            ((OnItemClickListener) it.next()).onItemClick(adapterView, view, i, j);
        }
    }
}
